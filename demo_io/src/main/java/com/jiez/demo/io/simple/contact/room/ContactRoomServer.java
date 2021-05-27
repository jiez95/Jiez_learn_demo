package com.jiez.demo.io.simple.contact.room;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import org.springframework.util.StringUtils;

import java.net.SocketAddress;
import java.util.*;

/**
 * @author : jiez
 * @date : 2021/5/27 22:08
 */
public class ContactRoomServer {

    public static void main(String[] args) throws InterruptedException {
        // 服务端创建两个线程组, 一个是Boss线程组，一个是work线程组
        EventLoopGroup bossEventLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerEventLoopGroup = new NioEventLoopGroup(8);

        try {
            // 构建Netty模型
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossEventLoopGroup, workerEventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ContactRoomServerNettyHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(9000).sync();

            channelFuture.channel().closeFuture().sync();

        } finally {
            bossEventLoopGroup.shutdownGracefully();
            workerEventLoopGroup.shutdownGracefully();
        }

    }
}

class ContactRoomServerNettyHandler extends ChannelInboundHandlerAdapter {

    private static volatile Map<String, Channel> channelMap = new HashMap<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String remoteAddress = String.valueOf(channel.remoteAddress());
        channelMap.put(remoteAddress, channel);
        String messageFormat = "客户端[%s]: 上线了";
        for (String key : channelMap.keySet()) {
            if (Objects.equals(key, remoteAddress)) {
                continue;
            }
            Channel otherChannel = channelMap.get(key);
            ByteBuf buf = Unpooled.copiedBuffer(String.format(messageFormat, remoteAddress), CharsetUtil.UTF_8);
            otherChannel.writeAndFlush(buf);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        String remoteAddress = String.valueOf(channel.remoteAddress());
        channelMap.remove(remoteAddress);
        String messageFormat = "客户端[%s]下线了";
        for (String key : channelMap.keySet()) {
            if (Objects.equals(key, remoteAddress)) {
                continue;
            }
            Channel otherChannel = channelMap.get(key);
            ByteBuf buf = Unpooled.copiedBuffer(String.format(messageFormat, remoteAddress), CharsetUtil.UTF_8);
            otherChannel.writeAndFlush(buf);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);

        Channel channel = ctx.channel();
        String remoteAddress = String.valueOf(channel.remoteAddress());

        String messageFormat = "客户端[%s]: %s";
        for (String key : channelMap.keySet()) {
            if (Objects.equals(key, remoteAddress)) {
                continue;
            }
            Channel otherChannel = channelMap.get(key);
            ByteBuf buf = Unpooled.copiedBuffer(String.format(messageFormat, remoteAddress, message), CharsetUtil.UTF_8);
            otherChannel.writeAndFlush(buf);
        }
        super.channelRead(ctx, msg);
    }
}
