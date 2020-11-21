package chat.Frame.operation.alterColumn;

import chat.Frame.chat.linkmen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 修改昵称的对话框
 */
public class changeNickname extends JDialog{

    private Container container;
    public JTextField jTextField;
    private JButton button;

    public changeNickname(linkmen linkmen){
        super(linkmen,"修改昵称",true);
        container = getContentPane();
        jTextField = new JTextField(20);
        jTextField.setBounds(0,0,200,50);
        container.add(jTextField);
        button = new JButton("确认修改");
        button.setBounds(30,50,140,40);
        container.add(button);
        setBounds(780,400,220,140);
        setLayout(null);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeNickname.this.setVisible(false);
            }
        });
    }

}
