package chat.Frame;

import chat.Frame.chat.linkmen;
import chat.Frame.operation.alterColumn.changeNickname;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tipFrame extends JDialog {

    private Container container;
    //显示错误信息
    public JLabel label;
    //确认按钮
    private JButton button;

    public tipFrame(){

    }

    public void init(String msg){
        container = getContentPane();
        label = new JLabel(msg);
        label.setBounds(70,0,200,70);
        label.setFont(new Font("微软雅黑",Font.PLAIN,20));
        container.add(label);
        button = new JButton("确认");
        button.setBounds(35,50,140,40);
        container.add(button);
        setBounds(780,170,220,140);
        setLayout(null);
        setVisible(true);
        container.setBackground(new Color(0xD8FFD5));
        //提示窗口前置
        setAlwaysOnTop(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipFrame.this.dispose();
            }
        });
    }
}
