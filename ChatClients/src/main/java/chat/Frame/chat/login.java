package chat.Frame.chat;

import chat.Project.constant.EnMsgType;
import chat.Project.netty.ClientHandler;
import chat.Project.services.sendServers;
import chat.util.JsonUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.Channel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;


public class login extends JFrame{

    //容器
    private Container container;
    //账号文本
    private JTextField account;
    //密码文本
    private JPasswordField password;
    //登录按钮
    private JButton button;
    //标签
    private JLabel label_1,label_2,label_3,label_4,label_5,label_6;
    //复选框
    private JCheckBox checkBox_1,checkBox_2;
    //从图片源获取图片
    private ImageIcon icon;

    private Channel channel;
    //好友信息
    public static String[] friend;

    public login(Channel channel){
        this.channel = channel;
    }


    //存储图形用户界面
    public void init(){

        //获取一个容器
        container = getContentPane();
        //初始化账号文本
        account = new JTextField();
        //初始化密码文本
        password = new JPasswordField();
        //初始化登录按钮
        button = new JButton("安全登录",new ImageIcon("C:\\Users\\16502\\Desktop\\2 .png"));
        //上沿显示标签
        label_4 = new JLabel(new ImageIcon("E:\\聊天软件\\untitled\\src\\imageSource\\1.png"));
        //账号标签
        label_1 = new JLabel("QQ号码：");
        //密码标签
        label_2 = new JLabel("QQ密码：");
        //安全登录
        label_3 = new JLabel("<html> <a href = 'www.qq.com'>忘 记 密 码</a>");
        //注册账号
        label_5 = new JLabel("<html><a href = 'www.qq.com'>注 册 账 号</a>");
        //客服投诉
        label_6 = new JLabel("<html><a href = 'www.qq.com'>客 服 投 诉</a>");
        //隐身登录
        checkBox_1 = new JCheckBox("隐身登录");
        //记住密码
        checkBox_2 = new JCheckBox("记住密码");
        //标签变为可选状态
        icon = new ImageIcon("E:\\聊天软件\\untitled\\src\\imageSource\\3.png");
        label_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setLayout(null);
        //初始化窗体位置大小
        setBounds(650,300,500,400);
        label_4.setBounds(0,0,500,179);
        container.add(label_4);
        //设置标签1和2的字体大小与样式
        label_1.setFont(new Font("宋体",Font.PLAIN,20));
        label_2.setFont(new Font("宋体",Font.PLAIN,20));
        label_1.setBounds(10,199,100,20);
        label_2.setBounds(10,229,100,20);
        //添加标签
        container.add(label_1);
        container.add(label_2);
        //设置密码文本与账号文本大小
        account.setBounds(120,199,200,20);
        password.setBounds(120,229,200,20);
        //添加文本框
        container.add(account);
        container.add(password);
        //设置忘记密码、注册账号标签
        label_3.setFont(new Font("宋体",Font.PLAIN,20));
        label_3.setForeground(Color.BLUE);
        label_3.setBounds(340,225,150,25);
        label_5.setFont(new Font("宋体",Font.PLAIN,20));
        label_5.setForeground(Color.BLUE);
        label_5.setBounds(340,195,150,25);
        //添加标签3、5
        container.add(label_5);
        container.add(label_3);
        //设置边框信息
        button.setBorderPainted(false);//无边框
        button.setBounds(120,280,200,50);
        //添加按钮
        container.add(button);

        //设置、添加复选框的位置大小
        checkBox_1.setFont(new Font("宋体",Font.PLAIN,16));
        checkBox_2.setFont(new Font("宋体",Font.PLAIN,16));
        checkBox_1.setBounds(120,250,100,20);
        checkBox_2.setBounds(235,250,100,20);
        container.add(checkBox_1);
        container.add(checkBox_2);
        //添加设置客服申诉
        label_6.setFont(new Font("宋体",Font.PLAIN,20));
        label_6.setForeground(Color.BLUE);
        //650,300,500,400
        label_6.setBounds(370,315,120,50);
        container.add(label_6);
        //窗口可见
        setVisible(true);
        //设置窗体的标题
        setTitle("腾讯QQ");
        //给标题设置图片
        setIconImage(icon.getImage());
        //窗口不可变
        setResizable(false);
        //设置窗体的关闭方式
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = new String(account.getText());
                String passwd = new String(password.getPassword());
                judge(ID,passwd);
            }
        });

    }

    private void judge(java.lang.String ID, java.lang.String passwd){
        Integer id = Integer.valueOf(ID);

        //封装JSON消息
        ObjectNode objectNode = JsonUtils.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_LOGIN.toString());
        objectNode.put("id",id);
        objectNode.put("passwd",passwd);
        //将消息写回服务端

        channel.writeAndFlush(objectNode.toString());

        //等待服务端返回
        int code = 0;
        try {
            //这块队列的长度为0没有  服务消息接收有问题
            code = (int) ClientHandler.queue.take();
            System.out.println("after");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (code == 200){
            //验证成功
            login.this.dispose();
            new sendServers(channel).getFriend(id);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new linkmen(id,channel,friend).mian();

        }else {
            //验证失败
            System.out.println("验证失败");
        }
    }

}
