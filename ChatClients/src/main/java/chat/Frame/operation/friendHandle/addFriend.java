package chat.Frame.operation.friendHandle;

import chat.Frame.chat.linkmen;
import chat.Frame.operation.alterColumn.changeNickname;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.Channel;

/**
 * 添加好友
 */
public class addFriend extends JDialog {

    //容器
    private Container container;
    //好友id
    public static JTextField jTextField;
    //提示标签
    private JLabel label;
    //确认按钮
    private JButton button;

    public addFriend(linkmen linkmen){
        super(linkmen,"添加好友",true);
        container = getContentPane();
        jTextField = new JTextField(20);
        jTextField.setBounds(0,30,200,50);
        container.add(jTextField);
        label = new JLabel("请输入不是好友ID：");
        label.setFont(new Font("宋体",Font.PLAIN,18));
        label.setBounds(0,0,200,20);
        container.add(label);
        button = new JButton("确认添加");
        button.setBounds(30,80,140,40);
        container.add(button);
        setBounds(780,400,220,170);
        setLayout(null);
        container.setBackground(new Color(5, 255, 116, 52));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFriend.this.setVisible(false);
            }
        });
    }

}
