package chat.Frame.operation.friendHandle;

import chat.Frame.chat.linkmen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 删除好友
 */
public class delFriend extends JDialog {
    //容器
    private Container container;
    //好友id
    public static JTextField TextField;
    //提示标签
    private JLabel label;
    //确认按钮
    private JButton button;

    public delFriend(linkmen linkmen){
        super(linkmen,"删除好友",true);
        container = getContentPane();
        TextField = new JTextField(20);
        TextField.setBounds(0,30,200,50);
        container.add(TextField);
        label = new JLabel("请输入不是好友ID：");
        label.setFont(new Font("宋体",Font.PLAIN,18));
        label.setBounds(0,0,200,20);
        container.add(label);
        button = new JButton("确认删除");
        button.setBounds(30,80,140,40);
        container.add(button);
        setBounds(780,400,220,170);
        setLayout(null);
        container.setBackground(new Color(5, 255, 116, 52));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delFriend.this.setVisible(false);
            }
        });
    }

}
