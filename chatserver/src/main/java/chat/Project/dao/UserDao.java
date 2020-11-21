package chat.Project.dao;


public interface UserDao {

    boolean getInformation(int id,String passwd);

    //验证密码
    boolean verifyPassword(String oldPasswd,Integer id);

    //修改密码
    void modifyPasswd(String newPasswd,Integer id);

    //验证是否存在所添加好友的id
    boolean verifyExistFriend(Integer id);
}
