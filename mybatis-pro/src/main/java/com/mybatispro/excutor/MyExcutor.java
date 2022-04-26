package com.mybatispro.excutor;

import com.mybatispro.entity.User;
import com.mybatispro.session.MyConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author cxx
 * @date 2021/12/13 1:11
 **/
public class MyExcutor implements Excutor {

    private MyConfiguration xmlConfiguration = new MyConfiguration();

    @Override
    public <T> T query(String sql, Object parameter) {
        Connection connection = getConnection();
        ResultSet set = null;
        PreparedStatement pre = null;
        try {
            pre = connection.prepareStatement(sql);
            //设置参数
            pre.setString(1, parameter.toString());
            set = pre.executeQuery();
            User u = new User();
            //遍历结果集 这里写死，其实需要使用handler做灵活处理
            while (set.next()) {
                u.setId(set.getInt(1));
                u.setAge(set.getInt(2));
                u.setName(set.getString(3));
            }
            return (T) u;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (set != null) {
                    set.close();
                }
                if (pre != null) {
                    pre.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    private Connection getConnection() {
        try {
            Connection connection = xmlConfiguration.build("config.xml");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}