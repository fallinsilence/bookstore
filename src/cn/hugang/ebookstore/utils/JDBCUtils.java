package cn.hugang.ebookstore.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class JDBCUtils {

    private static final  String URL = "jdbc:mysql://localhost:3306/user";
    private static final String username = "root";
    private static final String password = "970318";

	public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL , username , password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

	public static DataSource getDataSource(){
        return new ComboPooledDataSource();
    }

    @SuppressWarnings("all")
    public static void release(ResultSet rs , Connection con , PreparedStatement stat){
	    if(rs != null){
	        try{
	            rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }

        if(con != null){
	        try{
	            con.close();
            } catch (SQLException e){
	            e.printStackTrace();
            }
            con = null;
        }

        if(stat != null){
            try{
                stat.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            stat = null;
        }
    }
}


