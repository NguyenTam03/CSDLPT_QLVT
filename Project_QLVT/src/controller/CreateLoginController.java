package controller;

import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

import main.Program;
import views.CreateLoginForm;

public class CreateLoginController {
	private CreateLoginForm form;

	public CreateLoginController(CreateLoginForm form) {
		this.form = form;
	}
	
	public void initController() {
		form.getBtnExitForm().addActionListener(l -> exitForm());
		form.getBtnCreateLogin().addActionListener(l -> createLogin());
	}
	
	private void exitForm() {
		form.dispose();
	}
	
	@SuppressWarnings("deprecation")
	private void createLogin() {
		String loginName = form.getTfLoginName().getText().trim();
		String password = form.getPasswordField().getText();
		String username = form.getTfUsername().getText().trim();
		String group = null;
		Enumeration<AbstractButton> rdbtns = form.getBg().getElements();
		while (rdbtns.hasMoreElements()) {
			AbstractButton rdb = rdbtns.nextElement();
			if (rdb.isSelected()) {
				group = rdb.getText();
				break;
			}
		}
		if (loginName.equals("") || password.equals("") || username.equals("") || group == null) {
			JOptionPane.showMessageDialog(null, "Bạn phải điền đầy đủ thông tin.", "", JOptionPane.WARNING_MESSAGE);
		}else {
			String sql = "EXEC dbo.sp_TaoLogin '" + loginName + "', '" + password + "', '" + username + "', '" + group + "'";
			System.out.println(sql);
			Program.myReader = Program.ExecSqlDataReader(sql);
			JOptionPane.showMessageDialog(null, "Thông báo", "Tạo thành công!", JOptionPane.INFORMATION_MESSAGE);
			exitForm();
		}
	}
}
