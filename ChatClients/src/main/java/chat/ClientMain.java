package chat;

import chat.Project.netty.NettyClient;

//客户端主线程
public class ClientMain {

    public static void main( String[] args ) {
        NettyClient.start("127.0.0.1",8888);
    }
}
