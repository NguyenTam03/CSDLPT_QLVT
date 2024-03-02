package controller;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.Program;
import views.LoginForm;
import views.frmMain;

public class LoginController {
	private LoginForm loginForm;
	
	public LoginController(LoginForm loginForm) {
		this.loginForm = loginForm;
	}
	
	public void initController() {
		loginForm.getBtnLogin().addActionListener(e -> clickBtnLogin());
		loginForm.getBtnExit().addActionListener(e -> clickBtnExit());
	}
	
	private void clickBtnLogin() {
		JComboBox<String> cbChiNhanh = loginForm.getCbChiNhanh();
		JTextField login = loginForm.getTfUsername();
		JTextField password = loginForm.getTfPassword();
		login.setText(login.getText().trim());
		
		if (login.getText().equals("") || password.getText().equals("")) {
			JOptionPane.showMessageDialog(
					null, 
					"Điền đầy đủ thông tin tên đăng nhập và mật khẩu!",
					"Thông báo",
					JOptionPane.WARNING_MESSAGE
					);
		}else {
			Program.servername = Program.servers.get(cbChiNhanh.getSelectedItem());
			Program.mChinhanh = cbChiNhanh.getSelectedIndex();
			Program.mlogin = login.getText();
			Program.password = password.getText();
			if (Program.Connect() == 0) return;
			Program.mloginDN = Program.mlogin;
			Program.passwordDN = Program.password;
		    String strLenh = "EXEC SP_LayThongTinNhanVien '" + Program.mlogin + "'";
            Program.myReader = Program.ExecSqlDataReader(strLenh);
            if( Program.myReader == null) return;
            try {
            	Program.myReader.next();
            	Program.username =  Program.myReader.getString(1);	
            	Program.mHoten = Program.myReader.getString(2);
            	Program.mGroup = Program.myReader.getString(3);
            	
            	loginForm.dispose();
            	new frmMain().setVisible(true);
            }
            catch(Exception e1) {
    			e1.printStackTrace();
            }
		}
	}
	
	private void clickBtnExit() {
		System.exit(0);
		return;
	}
	
}
