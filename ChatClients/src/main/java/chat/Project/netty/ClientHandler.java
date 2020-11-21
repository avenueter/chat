package chat.Project.netty;

import chat.Frame.chat.ChatFrame;
import chat.Frame.chat.linkmen;
import chat.Frame.chat.login;
import chat.Project.constant.EnMsgType;
import chat.util.JsonUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.SynchronousQueue;


public class ClientHandler extends SimpleChannelInboundHandler<String> {

    //定义一个同步阻塞队列状态码
    public static SynchronousQueue<Object> queue = new SynchronousQueue<>();

    public static String Nickname;
    public String Signature;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

    }

    //客户端接收数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        //解析服务端发送的消息
        ObjectNode jsonNodes = JsonUtils.getObjectNode((String) msg);
        String msgtype = jsonNodes.get("msgtype").asText();
        if (EnMsgType.EN_MSG_ACK.toString().equals(msgtype)) {
            String srctype = jsonNodes.get("srctype").asText();
            if (EnMsgType.EN_MSG_LOGIN.toString().equals(srctype)) {
                //登录操作
                queue.offer(jsonNodes.get("code").asInt());
            }else if(EnMsgType.EN_MSG_GETINFORMATION.toString().equals(srctype)){
                //存取信息
                 Nickname = jsonNodes.get("Nickname").asText();
                 Signature = jsonNodes.get("Signature").asText();
                linkmen.label_1.setText(Nickname);
                linkmen.field.setText(Signature);
            }else if (EnMsgType.EN_MSG_CHAT.toString().equals(srctype)){
                //发送端返回消息
                queue.offer(jsonNodes.get("code").asInt());
            }else if (EnMsgType.EN_MSG_GET_ID.toString().equals(srctype)){
                int uid = jsonNodes.get("uid").asInt();
                queue.offer(uid);
            }else if (EnMsgType.EN_MSG_GET_FRIEND.toString().equals(srctype)){
                //获取登录用户的好友
                int count = jsonNodes.get("count").asInt();
                login.friend = new String[count];
                for ( int i = 0;i<count;i++){
                    login.friend[i] = jsonNodes.get("res"+i).asText();
                    System.out.println(jsonNodes.get("res"+i));
                }
            }else if (EnMsgType.EN_MSG_ADD_FRIEND.toString().equals(srctype)){
                //添加好友
                queue.offer(jsonNodes.get("code").asInt());
            }else if (EnMsgType.EN_MSG_DEL_FRIEND.toString().equals(srctype)){
                //删除好友
                queue.offer(jsonNodes.get("code").asInt());
            }else if (EnMsgType.EN_MSG_ACTIVE_STATE.toString().equals(srctype)){
                //好友在线状态
                queue.offer(jsonNodes.get("code").asInt());
            }
        }else if (EnMsgType.EN_MSG_VERIFY_PASSWORD.toString().equals(msgtype)){
            //修改密码
            int code = 0;
            code = jsonNodes.get("code").asInt();
            queue.offer(code);
        }else if (EnMsgType.EN_MSG_CHAT.toString().equals(msgtype)){
            //接收端接受消息  封装朋友昵称
            String message = " "+ jsonNodes.get("message").asText();
            //聊天显示框读取消息
            ChatFrame.sb.append(message+"\n");
            ChatFrame.displayTextPanel.setText(ChatFrame.sb.toString());
        }
    }

}
