package chat.utils;

import org.apache.commons.mail.SimpleEmail;

public class EmailUtils {
    /**
     * 发送邮件
     * @param toEmail  邮箱地址
     * @param msg   邮件正文（发送用户密码）
     * @throws Exception
     */
    public static void toEmail(String toEmail, String msg) throws Exception{
        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.qq.com");//邮件服务器

        email.setAuthentication("XXX@qq.com", "sadf");//邮件登录用户名及授权码
        email.setSSLOnConnect(true);
        email.setFrom("", "");//发送方邮箱、发送方名称
        email.setSubject("用户忘记密码邮件");//主题名称
        email.setCharset("UTF-8");//设置字符集编码
        email.setMsg(msg);//发送内容
        email.addTo(toEmail);//接收方邮箱
        email.send();//发送方法
    }

}
