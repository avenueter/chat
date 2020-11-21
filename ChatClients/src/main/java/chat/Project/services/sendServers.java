package chat.Project.services;

import chat.Frame.tipFrame;
import chat.Project.constant.EnMsgType;
import chat.Project.netty.ClientHandler;
import chat.util.JsonUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.Channel;


public class sendServers {

    private Channel channel;

    public sendServers(Channel channel) {
        this.channel = channel;
    }

    /**
     * 更新当前用户的个人信息
     * @param id
     */
    public void update(Integer id) {
        ObjectNode objectNode = JsonUtils.getObjectNode();
        objectNode.put("msgtype", EnMsgType.EN_MSG_GETINFORMATION.toString());
        objectNode.put("id", id);

        //将消息写回至服务端
        channel.writeAndFlush(objectNode.toString());

    }

    /**
     * 修改昵称
     * @param nickname
     * @param id
     */
    public void modifyNickname(String nickname, Integer id) {
        ObjectNode objectNode = JsonUtils.getObjectNode();
        objectNode.put("msgtype", EnMsgType.EN_MSG_MODIFY_NICKNAME.toString());
        objectNode.put("id", id);
        objectNode.put("nickname", nickname);

        //将消息写回服务端
        channel.writeAndFlush(objectNode.toString());
    }

    /**
     * 修改个性签名并更新到数据库
     * @param signature
     * @param id
     */
    public void modifySignature(String signature, Integer id) {
        ObjectNode objectNode = JsonUtils.getObjectNode();
        objectNode.put("msgtype", EnMsgType.EN_MSG_MODIFY_SIGNATURE.toString());
        objectNode.put("id", id);
        objectNode.put("signature", signature);

        //消息写回到服务端
        channel.writeAndFlush(objectNode.toString());
    }

    /**
     * 修改密码 并且更新数据库数据
     * @param oldPasswd
     * @param id
     * @param newPasswd
     */
    public void verifyPasswd(String oldPasswd,Integer id,String newPasswd){
        ObjectNode objectNode = JsonUtils.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_VERIFY_PASSWORD.toString());
        objectNode.put("id",id);
        objectNode.put("oldPasswd",oldPasswd);
        objectNode.put("newPasswd",newPasswd);

        //消息写道服务端
        channel.writeAndFlush(objectNode.toString());

        int code = 0;
        try {
            code = (int) ClientHandler.queue.take();
            if (code == 300){
                //验证成功
                new tipFrame().init("修改成功");
            }else{
                //验证失败
                new tipFrame().init("修改失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一对一单聊模式
     * @param message
     * @param id
     */
    public void singleChat(String message,Integer id){

        //封装JSON发送消息到服务端
        ObjectNode objectNode = JsonUtils.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_CHAT.toString());
        objectNode.put("id",id);
        objectNode.put("message",message);

        //将消息写回到服务端
        channel.writeAndFlush(objectNode.toString());
        int code = 0;
        try {
            code = (int)ClientHandler.queue.take();
            if(code == 200){
                new tipFrame().init("好友不在线");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前好友的id
     * @param nickname
     * @return
     */
    public  int getId(String nickname){
        //封装JSON
        ObjectNode objectNode = JsonUtils.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_GET_ID.toString());
        objectNode.put("nickname",nickname);

        //消息写回服务端
        channel.writeAndFlush(objectNode.toString());

        int uid = 0;
        try {
            uid = (int) ClientHandler.queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return uid;
    }

    /**
     * 获取好友列表 登录时候显示
     * @param uid
     */
    public void getFriend(Integer uid){
        //封装JSON
        ObjectNode objectNode = JsonUtils.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_GET_FRIEND.toString());
        objectNode.put("uid",uid);

        channel.writeAndFlush(objectNode.toString());
    }

    /**
     * 添加好友进行相互添加
     * @param friendId
     * @param id
     * @param localName
     */
    public void addFriendOperate(Integer friendId,Integer id,String localName) {
        //封装JSON
        ObjectNode objectNode = JsonUtils.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_ADD_FRIEND.toString());
        objectNode.put("friendId",friendId);
        objectNode.put("id",id);
        objectNode.put("localName",localName);

        //将消息写到服务端
        channel.writeAndFlush(objectNode.toString());

        int code = 0;
        try {
            code = (int) ClientHandler.queue.take();
            if (code == 300){
                //添加成功
                new tipFrame().init("添加成功");
            }else {
                //添加失败
                new tipFrame().init("添加失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除好友
     * @param friendId
     * @param id
     * @param localName
     */
    public void delFriendOperate(int friendId, Integer id, String localName) {
        //封装JSON
        ObjectNode objectNode = JsonUtils.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_DEL_FRIEND.toString());
        objectNode.put("friendId",friendId);
        objectNode.put("id",id);
        objectNode.put("localName",localName);

        //将消息写回客户端
        channel.writeAndFlush(objectNode.toString());

        //返回状态码
        int code = 0;

        try {
            code = (int) ClientHandler.queue.take();
            if (code == 300){
                //删除成功
                new tipFrame().init("删除成功");
            }else {
                //删除失败
                new tipFrame().init("删除失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void friendIsActive(Integer friendId){
        //封装JSON
        ObjectNode objectNode = JsonUtils.getObjectNode();
        objectNode.put("msgtype",EnMsgType.EN_MSG_ACTIVE_STATE.toString());
        objectNode.put("friendId",friendId);

        //写回服务端进行判断
        channel.writeAndFlush(objectNode.toString());
        int code = 0;
        try {
            code = (int) ClientHandler.queue.take();
            if (code == 200){
                //好友不在线
                new tipFrame().init("好友不在线");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
