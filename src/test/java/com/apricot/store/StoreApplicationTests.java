package com.apricot.store;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
@MapperScan("com.apricot.store.Mapper")
class StoreApplicationTests {
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    /**
     * author: Apricot
     * description: 测试数据库连接
     * date: 2024/11/20 22:56
     * HikariProxyConnection@1208600433 wrapping com.mysql.cj.jdbc.ConnectionImpl@5d37aa0f
     * Hikari SpringBoot 默认整合的数据库连接池 由某个小日本开发 类似的技术有DBCP\C3P0\BoneCP等
     * @throws SQLException
     */
    @Test
    void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
