package chat.Frame.operation.alterColumn;

import chat.Frame.chat.linkmen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 生成修改签名的对话框
 */
public class changeSignature extends JDialog{
    private Container container;
    public JTextField jTextField;
    private JButton button;

    public changeSignature(linkmen linkmen){
        super(linkmen,"修改签名",true);
        container = getContentPane();
        jTextField = new JTextField(20);
        jTextField.setBounds(0,0,200,50);
        container.add(jTextField);
        button = new JButton("确认修改");
        button.setBounds(60,50,90,40);
        container.add(button);
        setBounds(780,400,220,140);
        setLayout(null);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSignature.this.setVisible(false);
            }
        });
    }
}
