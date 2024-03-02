package main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

import views.LoginForm;

public class Program {
	public static Connection conn;
	public static String connstr;

	public static ResultSet myReader;
	public static String servername = "ADMIN-PC";
	public static String username = "";
	public static String mlogin = "";
	public static String password = "";

	public static String database = "QLVT_DATHANG";
	public static String remotelogin = "htkn";
	public static String remotepassword = "123456";

	public static String mloginDN = "";
	public static String passwordDN = "";
	public static String mGroup = "";
	public static String mHoten = "";

	public static int mChinhanh = 0;
	public static HashMap<String, String> servers;
	public static LoginForm frmChinh;

	public static int Connect() {
		if (Program.conn != null) {
			try {
				Program.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Program.connstr = "jdbc:sqlserver://" + Program.servername + ";databaseName=" + Program.database
					+ ";encrypt=true;trustServerCertificate=true" + ";user=" + Program.mlogin + ";password="
					+ Program.password;
			try {
				Program.conn = DriverManager.getConnection(Program.connstr);
			}catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Lỗi kết nối cơ sở dữ liệu.\nBạn xem lại user name và password.\n " + e.getMessage(), "",
						JOptionPane.WARNING_MESSAGE);
			}
			
			System.out.println(Program.connstr);
			return 1;
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"", e.getMessage(), JOptionPane.WARNING_MESSAGE);
			return 0;
		}
	}

	public static ResultSet ExecSqlDataReader(String strLenh) {
		ResultSet myReader = null;
		try {
			PreparedStatement p = Program.conn.prepareStatement(strLenh);
			myReader = p.executeQuery();
			return myReader;
		} catch (SQLException ex) {
			try {
				Program.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, ex.getMessage());
			return null;
		}
	}
	
	public static HashMap<String, String> getServer() {
    	Program.mlogin = Program.remotelogin;
    	Program.password = Program.remotepassword; 
    	Program.servername = "ADMIN-PC";
    	Program.Connect();
        HashMap<String, String> server = new LinkedHashMap<String, String>();
        try {
            String sql = "SELECT * FROM [dbo].[V_DS_PHANMANH]";
            PreparedStatement statement = Program.conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                server.put(rs.getString("TENCN"), rs.getString("TENSERVER"));
            }
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return server;
    }

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmChinh = new LoginForm();
					frmChinh.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
