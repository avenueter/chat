package chat.Project.dao;

import chat.Project.bean.information;
import chat.utils.C3p0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class informationDaoImpl implements informationDao {

    @Override
    public information getInformation(Integer id) {

        information information = null;

        //建立连接
        Connection connection = C3p0Util.getConnection();
        String sql = "select *from information where uid = ?";

        //查询数据库
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                information = new information();
                information.setNickname(resultSet.getString("nickname"));
                information.setSignature(resultSet.getString("signature"));
            }
            //关闭资源
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return information;
    }

    //存储个性签名
    @Override
    public void storeSignature(String signature,Integer id) {

        //建立连接
        Connection connection = C3p0Util.getConnection();
        String sql = "update information set signature = ? where uid = ?";

        try {
            //查询数据库
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,signature);
            preparedStatement.setInt(2,id);

            //执行preparedStatement里的SQL语句
            preparedStatement.executeUpdate();

            //关闭资源
            if (preparedStatement != null){
                preparedStatement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //存储昵称
    @Override
    public void storeNickname(String nickname, Integer id) {

        //建立连接
        Connection connection = C3p0Util.getConnection();
        String sql = "update information set nickname = ? where uid = ?";

        try {
            //查询数据库
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,nickname);
            preparedStatement.setInt(2,id);

            //执行preparedStatement里的SQL语句
            preparedStatement.executeUpdate();

            //关闭资源
            if (preparedStatement != null){
                preparedStatement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //根据昵称获取id
    @Override
    public information nicknameGetId(String nickname) {

        information information = null;

        //建立连接
        Connection connection = C3p0Util.getConnection();
        String sql = "select * from information where nickname = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,nickname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                information = new information();
                information.setUid(resultSet.getInt("uid"));
            }

            //关闭资源
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return information;
    }



}
