package views;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CreateLoginController;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class CreateLoginForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfLoginName;
	private JTextField tfUsername;
	private JButton btnExitForm;
	private JButton btnCreateLogin;
	private JPasswordField passwordField;
	private JRadioButton rdbtnChiNhanhGrp;
	private JRadioButton rdbtnCongTyGrp;
	private JRadioButton rdbtnUserGrp;
	private ButtonGroup bg;
	
	/**
	 * Create the frame.
	 */
	public CreateLoginForm() {
		setTitle("Tạo tài khoản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tạo Tài Khoản");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 434, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblLoginName = new JLabel("Tên tài khoản:");
		lblLoginName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLoginName.setBounds(69, 63, 83, 14);
		contentPane.add(lblLoginName);
		
		tfLoginName = new JTextField();
		tfLoginName.setBounds(69, 81, 275, 20);
		contentPane.add(tfLoginName);
		tfLoginName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Mật khẩu:");
		lblPassword.setBounds(69, 112, 71, 14);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Mã nhân viên:");
		lblUsername.setBounds(69, 161, 83, 14);
		contentPane.add(lblUsername);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(69, 179, 275, 20);
		contentPane.add(tfUsername);
		
		JLabel lblGroup = new JLabel("Nhóm:");
		lblGroup.setBounds(69, 210, 46, 14);
		contentPane.add(lblGroup);
		
		
		rdbtnCongTyGrp = new JRadioButton("CONGTY");
		rdbtnCongTyGrp.setBounds(69, 232, 71, 23);
		contentPane.add(rdbtnCongTyGrp);
		
		rdbtnChiNhanhGrp = new JRadioButton("CHINHANH");
		rdbtnChiNhanhGrp.setBounds(151, 232, 79, 23);
		contentPane.add(rdbtnChiNhanhGrp);
		
		rdbtnUserGrp = new JRadioButton("USER");
		rdbtnUserGrp.setBounds(251, 232, 57, 23);
		contentPane.add(rdbtnUserGrp);
		
		bg = new ButtonGroup();
		bg.add(rdbtnCongTyGrp);
		bg.add(rdbtnChiNhanhGrp);
		bg.add(rdbtnUserGrp);
		
		btnCreateLogin = new JButton("Tạo");
		btnCreateLogin.setBounds(69, 285, 89, 29);
		contentPane.add(btnCreateLogin);
		
		btnExitForm = new JButton("Thoát");
		btnExitForm.setBounds(255, 285, 89, 29);
		contentPane.add(btnExitForm);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(69, 130, 275, 20);
		contentPane.add(passwordField);
		
		setLocationRelativeTo(null);
		
		CreateLoginController ac = new CreateLoginController(this);
		ac.initController();
	}
	
	public ButtonGroup getBg() {
		return bg;
	}

	public JTextField getTfLoginName() {
		return tfLoginName;
	}

	public void setTfLoginName(JTextField tfLoginName) {
		this.tfLoginName = tfLoginName;
	}

	public JTextField getTfUsername() {
		return tfUsername;
	}

	public void setTfUsername(JTextField tfUsername) {
		this.tfUsername = tfUsername;
	}

	public JButton getBtnExitForm() {
		return btnExitForm;
	}

	public JButton getBtnCreateLogin() {
		return btnCreateLogin;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JRadioButton getRdbtnChiNhanhGrp() {
		return rdbtnChiNhanhGrp;
	}

	public JRadioButton getRdbtnCongTyGrp() {
		return rdbtnCongTyGrp;
	}

	public JRadioButton getRdbtnUserGrp() {
		return rdbtnUserGrp;
	}
}
