package chat.Project.dao;

import chat.Project.bean.friend;

import java.util.ArrayList;

public interface friendDao {

    //获取好友列表
    ArrayList<String> getFriends(Integer uid);

    //添加好友
    void addFriends(Integer userId,String localName,String friendNickname);

    //判断是否存在好友
    boolean isExist(String nickname,Integer friendId);

    //删除好友
    void delFriend(int userId,String friend);
}
