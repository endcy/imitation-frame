package tk.endcy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: chenxinxin
 * @Date: 2022/4/26 16:22
 */
@Component
public class UserDAO implements IUserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(String name) {
        String sql = "INSERT INTO label(name, app_id) VALUES(?,?);";
        int updateResult = jdbcTemplate.update(sql, name, 51);
        System.out.println("saveResult:" + (updateResult > 0));
    }
}
