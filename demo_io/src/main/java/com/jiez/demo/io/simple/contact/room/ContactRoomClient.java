package com.jiez.demo.io.simple.contact.room;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author : jiez
 * @date : 2021/5/29 11:00
 */
public class ContactRoomClient {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup workEventLoopGroup = new NioEventLoopGroup(1);

        Bootstrap bootstrap = new Bootstrap()
                .group(workEventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline()
//                                .addLast(new StringDecoder())
                                .addLast(new ObjectDecoder(10240, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())))
                                .addLast(new ContactRoomClientNettyHandler())

//                                .addLast(new StringEncoder())
                                .addLast(new ObjectEncoder())
                        ;
                    }
                })
                ;

        try {
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000);
            channelFuture.sync();

            Channel channel = channelFuture.channel();

            /**
             * 测试 StringEncoder/StringEncoder 发送字符串对象
             */
//            Scanner sc = new Scanner(System.in);
//            while (true) {
//                channel.writeAndFlush(sc.next());
//            }

            /**
             * 测试 使用ObjectDecoder/ObjectEncoder 发送对象
             */
            MessageDto messageDto = new MessageDto();
            messageDto.setId("123");
            messageDto.setName("321");
            channel.writeAndFlush(messageDto);

        } finally {
            workEventLoopGroup.shutdownGracefully();
        }
    }
}

class ContactRoomClientNettyHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
    }
}
