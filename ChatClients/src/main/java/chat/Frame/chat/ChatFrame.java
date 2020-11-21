package chat.Frame.chat;


import chat.Project.netty.ClientHandler;
import chat.Project.services.sendServers;
import io.netty.channel.Channel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//聊天窗口界面
public class ChatFrame extends JFrame{
    //序列化
    private static final long serialVersionUID = 1L;
    //设置背景图片
    private ImageIcon icon, backGround;
    //顶层标签
    public JLabel label;
    //聊天内容显示文本面板
    private JScrollPane displayPanel;
    public static JTextPane displayTextPanel;
    //输入聊天文本面板
    private JScrollPane inputPanel;
    private JTextPane inputTextPanel;
    //按钮
    private JButton sendButton, emjioButton;
    //好友id
    private Integer id;
    //通信通道
    private Channel channel;
    //暂存消息  防止消息
    public static StringBuilder sb = new StringBuilder();

    public ChatFrame(Integer id,Channel channel){
        this.channel = channel;
        this.id = id;
        init();
    }

    public void init(){

        setLayout(new BorderLayout());
        setSize(550, 500);
        this.setMinimumSize(new Dimension(550, 500));
        //设置窗口不能调节大小
        setResizable(false);
        //设置窗口的指定位置
        setLocationRelativeTo(null);
        //聊天窗体前置
        setAlwaysOnTop(true);
        setTitle("with friend chat");

        //聊天信息框
        displayTextPanel = new JTextPane();

        //聊天信息框不可编辑
        displayTextPanel.setEditable(false);
        displayPanel = new JScrollPane(displayTextPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //聊天信息框设置为透明
        displayPanel.getViewport().setOpaque(false);
        displayPanel.setOpaque(false);
        displayTextPanel.setOpaque(false);
        displayTextPanel.setFont(setFonts(1));

        //用户聊天信息输入框
        inputTextPanel = new JTextPane();

        //输入信息框变为透明
        inputPanel = new JScrollPane(inputTextPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        inputPanel.setPreferredSize(new Dimension(100, 100));
        inputPanel.getViewport().setOpaque(false);
        inputTextPanel.setFont(setFonts(1));
        inputPanel.setOpaque(false);
        inputTextPanel.setOpaque(false);

        //发送按钮
        sendButton = new JButton("发送");
        sendButton.setFont(setFonts(0));
        sendButton.setFocusable(false);
        sendButton.setOpaque(false);
        //添加鼠标事件监听
        JPanel PaneLeftSouth = new JPanel();
        PaneLeftSouth.setLayout(new BorderLayout());

        //表情按钮初始化
        emjioButton = new JButton("表情");
        emjioButton.setFont(setFonts(0));
        emjioButton.setFocusable(false);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = inputTextPanel.getText();
                inputTextPanel.setText("");
                String message = ClientHandler.Nickname+"   " +sf.format(new Date())+"\n"+msg;
                //暂存显示面板之中的消息
                sb.append(message+"\n");
                //显示面板读取消息
                displayTextPanel.setText(sb.toString());
                //发送消息
                sendMsg(message,id);
            }
        });

        //放置组件的时候从右向左
        Box box_1 = Box.createHorizontalBox();
        box_1.add(emjioButton);
        box_1.add(sendButton);
        PaneLeftSouth.add(box_1, BorderLayout.NORTH);
        PaneLeftSouth.add(inputPanel, BorderLayout.CENTER);
        PaneLeftSouth.setOpaque(false);

        //设置背景
        backGround = new ImageIcon("E:\\聊天软件\\untitled\\src\\imageSource\\1.jpg");
        label = new JLabel();
        label.setIcon(backGround);
        label.setLayout(new BorderLayout());
        label.setOpaque(false);
        label.add(displayPanel, BorderLayout.CENTER);
        label.add(PaneLeftSouth, BorderLayout.SOUTH);
        add(label, BorderLayout.CENTER);
        icon = new ImageIcon("E:\\聊天软件\\untitled\\src\\imageSource\\3.png");
        setIconImage(icon.getImage());
        setVisible(true);
    }

    //设置字体
    public Font setFonts(Integer n){
        if (n==1){
            return new Font("微软雅黑",Font.ITALIC,15);
        }else {
            return new Font("微软雅黑", Font.PLAIN, 18);
        }
    }

    //时间的格式
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
    //发送消息
    private void sendMsg(String message,Integer id) {

        new sendServers(channel).singleChat(message,id);
    }

}
