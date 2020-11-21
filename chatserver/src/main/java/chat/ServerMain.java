package chat;

import chat.Project.netty.NettyServer;

//启动服务端
public class ServerMain {

    public static void main( String[] args ) {
        NettyServer.start(8888);
    }
}
