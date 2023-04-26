package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Common {
        final static String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
        final static String ORACLE_ID = "scott";
        final static String ORACLE_PW = "tiger";
        final static String ORACLE_DRV = "oracle.jdbc.driver.OracleDriver";

        public static Connection getConnection() {
            Connection conn = null;
            try {
                Class.forName(ORACLE_DRV);
                conn = DriverManager.getConnection(ORACLE_URL, ORACLE_ID, ORACLE_PW);
            }catch(Exception e) {
                e.printStackTrace();
            }
            return conn;
        }
        public static void close(Connection conn) {
            try {
                if(conn != null && !conn.isClosed()) {
                    conn.close();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        public static void close(Statement stmt) {
            try {
                if(stmt != null && !stmt.isClosed()) {
                    stmt.close();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        public static void close(ResultSet rs) {
            try {
                if(rs != null && !rs.isClosed()) {
                    rs.close();
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

