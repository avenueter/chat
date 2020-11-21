package chat.Frame.operation.alterColumn;

import chat.Frame.chat.linkmen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 修改密码的对话框
 */

public class changePassword extends JDialog {
    //账号
    private JTextField account;
    //旧密码
    public JTextField oldPassword;
    //新密码
    public JPasswordField newPassword;
    //图像
    private ImageIcon icon,icon_1;
    //标签
    private JLabel label_1,label_2,label_3,label_4;
    //按钮
    private JButton button;
    //容器
    private Container container;

    public changePassword(final linkmen linkmen){
        super(linkmen,"修改密码",true);

        //获取容器
        container = getContentPane();

        //设置密码账号标签
        label_2 = new JLabel("账号：");
        label_2.setBounds(100,40,80,30);
        label_2.setFont(new Font("宋体",Font.PLAIN,20));
        label_3 = new JLabel("旧密码：");
        label_3.setBounds(100,90,80,30);
        label_3.setFont(new Font("宋体",Font.PLAIN,20));
        label_4 = new JLabel("新密码：");
        label_4.setBounds(100,140,80,30);
        label_4.setFont(new Font("宋体",Font.PLAIN,20));

        //确认按钮的设置
        button = new JButton("确认修改");
        button.setBounds(220,270,120,30);
        button.setFont(new Font("宋体",Font.PLAIN,20));
        icon_1 = new ImageIcon("C:\\Users\\16502\\Desktop\\8.png");

        //设置账号密码文本框
        account = new JTextField(10);
        account.setBounds(190,40,200,30);
        oldPassword = new JTextField(10);
        oldPassword.setBounds(190,90,200,30);
        newPassword = new JPasswordField();
        newPassword.setBounds(190,140,200,30);

        icon = new ImageIcon("E:\\聊天软件\\untitled\\src\\imageSource\\7.png");
        label_1 = new JLabel(icon);
        label_1.setBounds(0,0,500,400);

        button.setIcon(icon_1);
        //添加按钮响应事件
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword.this.setVisible(false);
            }
        });
        container.setBackground(new Color(95, 255, 30,60));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        container.add(label_2);
        container.add(label_3);
        container.add(label_4);
        container.add(button);
        container.add(account);
        container.add(oldPassword);
        container.add(newPassword);
        setBounds(650,300,500,400);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

        /*public changePassword(){
        //设置密码账号标签
        label_2 = new JLabel("账号：");
        label_2.setBounds(100,40,80,30);
        label_2.setFont(new Font("宋体",Font.PLAIN,20));
        label_3 = new JLabel("旧密码：");
        label_3.setBounds(100,90,80,30);
        label_3.setFont(new Font("宋体",Font.PLAIN,20));
        label_4 = new JLabel("新密码：");
        label_4.setBounds(100,140,80,30);
        label_4.setFont(new Font("宋体",Font.PLAIN,20));

        //提示标签
        label = new JLabel();
        label.setBounds(120,185,40,20);

        //确认按钮的设置
        button = new JButton("确认修改");
        button.setBounds(220,250,120,30);
        button.setFont(new Font("宋体",Font.PLAIN,20));
        icon_1 = new ImageIcon("C:\\Users\\16502\\Desktop\\8.png");

        //设置账号密码文本框
        account = new JTextField(10);
        account.setBounds(190,40,200,30);
        oldPassword = new JTextField(10);
        oldPassword.setBounds(190,90,200,30);
        newPassword = new JPasswordField();
        newPassword.setBounds(190,140,200,30);

        button.setIcon(icon_1);
        //添加按钮响应事件
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //判断输入的密码是否与之前的一致
                frame.dispose();
            }
        });


        //设置frame信息
        frame = new JFrame();
        //设置窗体信息
        frame.setTitle("修改密码");
        icon = new ImageIcon("E:\\聊天软件\\untitled\\src\\imageSource\\7.png");
        label_1 = new JLabel(icon);
        label_1.setBounds(0,0,500,400);
        //获取窗口的第二层，将label放入
        frame.getLayeredPane().add(label_1, new Integer(Integer.MIN_VALUE));
        //获取frame的顶层容器,并设置为透明
        panel = (JPanel) frame.getContentPane();
        panel.setOpaque(false);
        frame.setLayout(null);
        frame.setLocation(650, 300);
        frame.setSize(new Dimension(500,400));
        frame.setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(label_2);
        frame.add(label_3);
        frame.add(label_4);
        frame.add(button);
        frame.add(account);
        frame.add(oldPassword);
        frame.add(newPassword);
    }*/
}
