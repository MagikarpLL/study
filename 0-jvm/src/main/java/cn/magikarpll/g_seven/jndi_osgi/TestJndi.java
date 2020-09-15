package cn.magikarpll.g_seven.jndi_osgi;

import java.sql.Connection;
import java.sql.SQLException;

public class TestJndi {

    public static void main(String[] args) throws SQLException {
        // 加载Class到AppClassLoader（系统类加载器），然后注册驱动类
        //Class.forName("com.mysql.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://localhost:3306/d_gateway";
        // 通过java库获取数据库连接
        Connection conn = java.sql.DriverManager.getConnection(url, "root", "leizhen1996");

        System.out.println("Success");

    }


}
