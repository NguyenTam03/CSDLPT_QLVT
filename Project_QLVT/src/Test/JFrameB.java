package Test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class JFrameB extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String username;
	private JTextField textFieldB;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		textFieldB.setText(username);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameB frame = new JFrameB();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameB() {
		setTitle("Frame B");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldB = new JTextField();
		textFieldB.setBounds(45, 55, 108, 28);
		contentPane.add(textFieldB);
		textFieldB.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(143, 134, 85, 21);
		contentPane.add(btnNewButton);
//		btnNewButton.setVisible(false);
		loadData();
		
	}
	private void loadData() {
		textFieldB.setText(this.username);
	}
}
