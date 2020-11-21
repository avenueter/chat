package chat.Project.dao;

import chat.Project.bean.friend;
import chat.Project.bean.information;
import chat.utils.C3p0Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class friendDaoImpl implements friendDao {

    //获取好友列表
    @Override
    public ArrayList<String> getFriends(Integer uid) {

        //存放好友列表
        ArrayList<String> res = new ArrayList<>();

        //建立连接
        Connection connection = C3p0Util.getConnection();
        String sql = "select *from friend where uid = ?";

        try {
            //查询数据库
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,uid);

            //获取的是多个结果
            //需要修改
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                res.add(resultSet.getString("linker"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    //更新好友列表
    @Override
    public void addFriends(Integer userId, String localName, String friendNickname) {
        PreparedStatement preparedStatement = null;
        //建立连接
        Connection connection = C3p0Util.getConnection();
        String sql = "insert into friend(user,linker,uid) values(?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,localName);
            preparedStatement.setString(2,friendNickname);
            preparedStatement.setInt(3,userId);

            //将数据更新到数据库
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据好友昵称判断是否存在好友
     * @param nickname
     * @return
     */
    @Override
    public boolean isExist(String nickname,Integer friendId) {
        PreparedStatement statement = null;
        Connection connection = C3p0Util.getConnection();
        boolean flag = false;
        String sql = "select *from friend where linker = ? and uid = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,nickname);
            statement.setInt(2,friendId);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    //删除好友
    @Override
    public void delFriend(int userId,String friend) {
        PreparedStatement preparedStatement = null;
        Connection connection = C3p0Util.getConnection();
        String sql = "delete from friend where uid = ? and linker = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            preparedStatement.setString(2,friend);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
