package chat.Frame.chat;

import chat.Frame.operation.alterColumn.changeNickname;
import chat.Frame.operation.alterColumn.changePassword;
import chat.Frame.operation.alterColumn.changeSignature;
import chat.Frame.operation.friendHandle.addFriend;
import chat.Frame.operation.friendHandle.delFriend;
import chat.Frame.tipFrame;
import chat.Project.services.sendServers;
import io.netty.channel.Channel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 * 联系人界面
 */
public class linkmen extends JFrame {
    //容器
    private JFrame frame;
    //标签
    private JLabel  label_2, label_3, label_4, label;
    //昵称
    public static JLabel label_1;
    //状态框
    private JComboBox box, box_1, box_2;
    //图片
    private ImageIcon icon_1, icon;
    //文本
    private JTextField  field_1;
    //个性签名
    public static JTextField field;
    //面板
    private JPanel panel_1, panel_3, panel;
    //滚动面板
    public JScrollPane panel_2;
    //列表
    public static JList list;
    //与服务端通信的通道
    private Channel channel;
    //用户的id
    private Integer id;
    //暂存oldPasswd
    public static JLabel label_5,label_6;
    //好友列表数组
    private String[] fd;
    //列表
    public static DefaultListModel<String> model;

    public linkmen(Integer id, Channel channel,String[] fd) {
        this.id = id;
        this.channel = channel;
        this.fd = fd;
    }

    public void init() {
        //初始化面板1并设置信息
        panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setLocation(0, 0);
        panel_1.setBorder(BorderFactory.createTitledBorder("资料卡"));
        panel_1.setSize(new Dimension(295, 148));
        panel_1.setOpaque(false);


        //初始化面板3并设置信息
        panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBorder(BorderFactory.createTitledBorder("系统设置"));
        panel_3.setLocation(0, 617);
        panel_3.setSize(new Dimension(295, 55));
        panel_3.setOpaque(false);

        //设置头像标签
        label_2 = new JLabel(new ImageIcon("E:\\聊天软件\\untitled\\src\\imageSource\\4.png"));
        label_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label_2.setBounds(15, 15, 100, 100);
        panel_1.add(label_2);

        //初始暂存标签
        label_5 = new JLabel();
        label_6 = new JLabel();

        //设置昵称标签
        label_1 = new JLabel("");
        label_1.setBounds(130, 10, 100, 30);
        label_1.setFont(new Font("宋体", Font.PLAIN, 18));
        panel_1.add(label_1);

        list = new JList<String>(model);
        //设置每个列表的高
        list.setFixedCellHeight(20);
        list.setSelectionBackground(new Color(0xD8FF2F));
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //打开一个聊天窗口
                if (e.getValueIsAdjusting()) {
                    for (int i = 0; i < model.size(); i++) {
                        if (model.get(i).equals(list.getSelectedValue())){
                            //获取id有错误
                            int ids = new sendServers(channel).getId((String) list.getSelectedValue());
                            if (ids!=0) {
                                new sendServers(channel).friendIsActive(ids);
                                new ChatFrame(ids, channel).setVisible(true);
                            }else{
                                System.out.println("好友不存在");
                            }
                        }
                    }
                }
            }
        });

        //初始化面板二
        panel_2 = new JScrollPane(list);
        panel_2.setBorder(BorderFactory.createTitledBorder("联系人"));
        panel_2.setLocation(0, 147);
        panel_2.setSize(new Dimension(295, 470));
        panel_2.getViewport().setOpaque(false);
        list.setOpaque(false);
        panel_2.setOpaque(false);

        //设置在线状态bBox();
        box = new JComboBox();
        box.addItem("✅在线");
        box.addItem("\uD83D\uDCBF隐身");
        box.addItem("\uD83D\uDCBB忙碌");
        box.addItem("❎离线");
        box.setBounds(200, 10, 70, 30);
        panel_1.add(box);

        //设置个性签名的标签
        label_4 = new JLabel("个性签名:");
        label_4.setFont(new Font("宋体", Font.PLAIN, 16));
        label_4.setForeground(Color.BLUE);
        label_4.setBounds(120, 50, 100, 20);
        panel_1.add(label_4);

        //设置文本
        field = new JTextField("");
        field.setBounds(120, 80, 160, 30);
        panel_1.add(field);

        label_3 = new JLabel("\uD83D\uDD0D");
        label_3.setForeground(Color.RED);
        label_3.setBounds(10, 122, 20, 20);
        panel_1.add(label_3);

        //设置搜索栏
        field_1 = new JTextField();
        field_1.setBounds(30, 120, 250, 25);
        panel_1.add(field_1);

        //对面板三进行初始化
        box_1 = new JComboBox();
        box_1.addItem("\uD83D\uDD12\uD83D\uDD28\uD83D\uDD13");
        box_1.addItem("修改密码");
        box_1.addItem("修改昵称");
        box_1.addItem("修改签名");
        box_1.setBounds(8, 20, 100, 25);
        panel_3.add(box_1);
        box_1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if ("修改签名".equals(box_1.getSelectedItem())) {
                    //执行一次
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        changeSignature changeSignature = new changeSignature(linkmen.this);
                        changeSignature.setVisible(true);
                        field.setText(changeSignature.jTextField.getText());
                        String signature = field.getText();
                        //存储签名的方法
                        new sendServers(channel).modifySignature(signature, id);
                    }
                }
                if ("修改密码".equals(box_1.getSelectedItem())) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        changePassword changePassword = new changePassword(linkmen.this);
                        changePassword.setVisible(true);
                        label_5.setText(changePassword.oldPassword.getText());
                        String oldPasswd = label_5.getText();
                        label_6.setText(new String(changePassword.newPassword.getPassword()));
                        String newPasswd = label_6.getText();
                        //进行验证
                        new sendServers(channel).verifyPasswd(oldPasswd, id,newPasswd);

                    }
                }
                if ("修改昵称".equals(box_1.getSelectedItem())) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        changeNickname changeNickname = new changeNickname(linkmen.this);
                        changeNickname.setVisible(true);
                        label_1.setText(changeNickname.jTextField.getText());
                        String nickname = label_1.getText();
                        //存储昵称
                        new sendServers(channel).modifyNickname(nickname, id);
                    }
                }
            }
        });
        //添加好友、删除好友
        box_2 = new JComboBox();
        box_2.addItem("\uD83D\uDC65");
        box_2.addItem("添加好友");
        box_2.addItem("删除好友");
        box_2.setBounds(170, 20, 100, 25);
        box_2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if ("添加好友".equals(box_2.getSelectedItem())) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        addFriend addFriend = new addFriend(linkmen.this);
                        addFriend.setVisible(true);
                        //读取要搜索的ID
                        String friendIds = addFriend.jTextField.getText();
                        //判断是否是字符串
                        if (judgeDigit(friendIds)){
                            int friendId = Integer.parseInt(friendIds);
                            //搜索数据库
                            new sendServers(channel).addFriendOperate(friendId,id,label_1.getText());
                        }else {
                            new tipFrame().init("输入参数错误");
                        }
                    }
                }
                if ("删除好友".equals(box_2.getSelectedItem())) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        delFriend delFriend = new delFriend(linkmen.this);
                        delFriend.setVisible(true);
                        //对其数据库进行删除操作
                        String friendIds = delFriend.TextField.getText();
                        //判断是否是字符串
                        if(judgeDigit(friendIds)){
                            int friendId = Integer.parseInt(friendIds);
                            //操作数据库
                            new sendServers(channel).delFriendOperate(friendId,id,label_1.getText());
                        }else{
                            new tipFrame().init("输入参数错误");
                        }
                    }
                }
            }
        });
        panel_3.add(box_2);
        //设置frame信息
        frame = new JFrame();
        //设置窗体信息
        frame.setTitle("腾讯QQ");
        //给窗体设置图片
        icon_1 = new ImageIcon("E:\\聊天软件\\untitled\\src\\imageSource\\3.png");
        frame.setIconImage(icon_1.getImage());
        icon = new ImageIcon("E:\\聊天软件\\untitled\\src\\imageSource\\5.png");
        label = new JLabel(icon);
        //获取窗口的第二层，将label放入
        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        //获取frame的顶层容器,并设置为透明
        panel = (JPanel) frame.getContentPane();
        panel.setOpaque(false);
        frame.setLayout(null);
        frame.setLocation(750, 150);
        frame.setSize(287, 700);
        frame.setVisible(true);
        frame.setResizable(false);
        label.setBounds(0, 0, 287, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel_1);
        frame.add(panel_2);
        frame.add(panel_3);
    }

    public void mian() {
        //初始化面板2并设置信息
        model = new DefaultListModel<>();
        for (int i = 0; i < fd.length; i++) {
            model.addElement(fd[i]);
        }
        init();
        //更新昵称和签名
        new sendServers(channel).update(id);
        //获取用户的昵称,和好友列表

        //设置签名和昵称字体初始样式和大小
        label_1.setFont(new Font("宋体", Font.PLAIN, 18));
        field.setFont(new Font("宋体", Font.PLAIN, 18));
    }

    //判断是否是数字
    private static boolean judgeDigit(String string){
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
