package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		form.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
			}
		});
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
			String sql = "{cal dbo.sp_TaoLogin(?, ?, ?, ?)}";
			Program.ExecSqlDML(sql, loginName, password, username, group);
			JOptionPane.showMessageDialog(null, "Tạo thành công.", "Success", JOptionPane.INFORMATION_MESSAGE);
			exitForm();
		}
	}
}
