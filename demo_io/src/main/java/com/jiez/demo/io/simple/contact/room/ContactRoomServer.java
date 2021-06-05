package com.jiez.demo.io.simple.contact.room;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
                            ch.pipeline()
                                    // 添加ChannelInboundHandlerAdapter, 处理数据流入

                                    /**
                                     * 由于UDP/TCP有数据包粘包/拆包现象
                                     * 因此要定义解包器，保证收到的数据是正确的
                                     */

                                    /**
                                     * 回车换行分包
                                     *
                                     * @param 1. 设置最大长度
                                     */
//                                    .addLast(new LineBasedFrameDecoder(1000))

                                    /**
                                     * 特殊分隔符分包
                                     *
                                     * @param 1. 设置最大长度
                                     * @param 2. 设置分割符的ByteBuff
                                     */
//                                    .addLast(new DelimiterBasedFrameDecoder(1000, Unpooled.copiedBuffer("_".getBytes())))

                                    /**
                                     * 固定长度报文来分包
                                     *
                                     * @param 1. 设置最大长度
                                     * @param 2. 设置分割符的ByteBuff
                                     */
//                                    .addLast(new FixedLengthFrameDecoder(1000));

                                    /**
                                     * 自定义分包处理器
                                     */

                                    /**
                                     * 添加解码器
                                     * 字符串解码器
                                     * 对象解码器（JDK序列化）
                                     * protobuf解码器
                                     * protostuff解码器
                                     */
//                                    .addLast(new StringDecoder())
                                    .addLast(new ObjectDecoder(10240, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())))

                                    // 添加数据处理器
                                    .addLast(new ContactRoomServerNettyHandler())

                                    // 添加ChannelOutboundHandlerAdapter，处理数据流出

                                    /**
                                     * 添加编码器
                                     * 字符串编码器
                                     * 对象编码器（JDK序列化）
                                     * protobuf编码器
                                     * protostuff编码器
                                     */
//                                    .addLast(new StringEncoder())
                                    .addLast(new ObjectEncoder())
                            ;
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
            otherChannel.writeAndFlush(String.format(messageFormat, remoteAddress));
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
            otherChannel.writeAndFlush(String.format(messageFormat, remoteAddress));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        String remoteAddress = String.valueOf(channel.remoteAddress());

        String messageFormat = "客户端[%s]: %s";
        for (String key : channelMap.keySet()) {
            if (Objects.equals(key, remoteAddress)) {
                continue;
            }
            Channel otherChannel = channelMap.get(key);
            otherChannel.writeAndFlush(String.format(messageFormat, remoteAddress, msg));
        }
    }
}

