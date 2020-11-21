package chat.Project.dao;

import chat.Project.bean.information;

import java.util.ArrayList;

public interface informationDao {

    //获取用户登录信息
    information getInformation(Integer id);

    //将修改后的签名存储到SQL
    void storeSignature(String signature, Integer id);

    //将修改后的昵称存储到SQL
    void storeNickname(String nickname, Integer id);

    //获取id
    information nicknameGetId(String nickname);

}
