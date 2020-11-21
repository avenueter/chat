package chat.utils;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Random;

public class PortUtils {
    //已被占用端口集合
    private static HashSet<Integer> busyPorts = new HashSet <>();

    /**
     * 获取当前系统空闲端口
     * @return
     */
    public static synchronized int getFreePort() {
        Random random = new Random();
        while (true) {
            //随机生成端口，取值范围在5001~65535
            int port = random.nextInt(60535)+5001;
            if (busyPorts.contains(port)) {
                //端口已被占用，则在重新产生端口
                continue;
            }
            try {
                //用new DatagramSocket(port)能测试本地主机,
                // 如果port被绑定，则会被IOException捕获，未被绑定则可以使用
                new DatagramSocket(port);
                //未报异常就可以 直接使用,并将该端口加入已被使用集合
                busyPorts.add(port);
                return  port;
            } catch (SocketException e) {
            }
        }
    }

    public static synchronized void closePort(int port) {
        if (busyPorts.contains(port)) {
            busyPorts.remove(port);
        }
    }
}
