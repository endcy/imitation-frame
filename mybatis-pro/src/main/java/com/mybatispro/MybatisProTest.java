package com.mybatispro;

import com.mybatispro.entity.User;
import com.mybatispro.mapper.UserMapper;
import com.mybatispro.session.MySqlsession;

/**
 * @author cxx
 * @date 2021/12/13 1:14
 **/
public class MybatisProTest {
    public static void main(String[] args) {
        MySqlsession sqlsession = new MySqlsession();
        UserMapper mapper = sqlsession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);
    }
}
