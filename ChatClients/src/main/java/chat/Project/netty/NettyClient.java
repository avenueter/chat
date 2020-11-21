package chat.Project.netty;

import chat.Frame.chat.login;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {

    public static void start(String ip,int port){
        //创建事件循环组
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();

        try{
            //创建启动辅助类
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            //获取容器
                            ChannelPipeline pipeline = nioSocketChannel.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new ClientHandler());
                        }
                    });

            //同步启动客户端
            Channel channel = bootstrap.connect(ip, port).sync().channel();
            System.out.println("客户端启动...");
            //通过channel连接服务端
            login login = new login(channel);
            login.init();
            //关闭通信
            channel.closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            loopGroup.shutdownGracefully();
        }

    }
}
