package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.NhanVienDao;
import main.Program;
import model.NhanVienModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;

public class NhanVienOptionForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldSearch;
	private DefaultTableModel model;
	private NhanVienDao dao;
	private List<NhanVienModel> list;
	private JComboBox<String> comboBox;

	/**
	 * Create the frame.
	 */
	public NhanVienOptionForm() {
		super("Chọn Nhân Viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelHeader = new JPanel();
		contentPane.add(panelHeader, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Chi Nhánh");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		comboBox = new JComboBox<>();
		comboBox.setEditable(false);
		comboBox.setEnabled(false);
		for (String key : Program.servers.keySet()) {
			comboBox.addItem(key);
		}
		comboBox.addItemListener(l -> loadDataOtherServer(l));

		comboBox.setSelectedIndex(Program.mChinhanh);
		if (Program.mGroup.equals("CONGTY"))
			comboBox.setEnabled(true);

		GroupLayout gl_panelHeader = new GroupLayout(panelHeader);
		gl_panelHeader.setHorizontalGroup(gl_panelHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHeader.createSequentialGroup().addGap(106)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(107, Short.MAX_VALUE)));
		gl_panelHeader.setVerticalGroup(gl_panelHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelHeader.createSequentialGroup()
						.addGroup(gl_panelHeader.createParallelGroup(Alignment.BASELINE)
								.addGroup(gl_panelHeader.createSequentialGroup().addGap(2).addComponent(comboBox))
								.addGroup(gl_panelHeader.createSequentialGroup().addGap(2).addComponent(lblNewLabel,
										GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addContainerGap()));
		panelHeader.setLayout(gl_panelHeader);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		textFieldSearch = new JTextField();
		textFieldSearch.setForeground(Color.LIGHT_GRAY);
		textFieldSearch.setText("Search");
		textFieldSearch.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchNhanVien();
			}
		});

		textFieldSearch.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (textFieldSearch.getText().isEmpty()) {
					textFieldSearch.setText("Search");
					textFieldSearch.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (textFieldSearch.getText().equals("Search")) {
					textFieldSearch.setText("");
					textFieldSearch.setForeground(Color.BLACK);
				}
			}
		});

		panel_1.add(textFieldSearch);
		textFieldSearch.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
//      Chi duoc select 1 hang khong dc select nhieu hang
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPanel panelFooter = new JPanel();
		contentPane.add(panelFooter, BorderLayout.SOUTH);

		JButton btnChoose = new JButton("Chọn");
		btnChoose.setBackground(SystemColor.textHighlight);
		btnChoose.addActionListener(l -> chooseNhanVien());

		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(l -> exitForm());
		btnExit.setBackground(new Color(255, 0, 0));

		GroupLayout gl_panelFooter = new GroupLayout(panelFooter);
		gl_panelFooter
				.setHorizontalGroup(gl_panelFooter.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
						gl_panelFooter.createSequentialGroup().addGap(189)
								.addComponent(btnChoose, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
								.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addGap(170)));
		gl_panelFooter.setVerticalGroup(gl_panelFooter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFooter.createSequentialGroup().addGap(4)
						.addGroup(gl_panelFooter.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnChoose, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
								.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		panelFooter.setLayout(gl_panelFooter);
		setLocationRelativeTo(null);
		
		loadNhanVien();
	}

	private void loadNhanVienNotAccount() {
		dao = NhanVienDao.getInstance();
		list = new ArrayList<NhanVienModel>();
		String sql = "{call sp_DS_NhanVien_ChuaCoTK}";
		Program.myReader = Program.ExecSqlDataReader(sql);
		try {
			while (Program.myReader.next()) {
				NhanVienModel nv = new NhanVienModel(Program.myReader.getInt(1), Program.myReader.getString(2),
						Program.myReader.getString(3), Program.myReader.getString(4), Program.myReader.getString(5),
						Program.myReader.getDate(6), Program.myReader.getFloat(7), Program.myReader.getString(8),
						Program.myReader.getBoolean(9));
				list.add(nv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadNhanVien() {
		loadNhanVienNotAccount();
		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(dao.getColName());

		for (NhanVienModel NhanVien : list) {
			Object[] rowData = { NhanVien.getManv(), NhanVien.getHo(), NhanVien.getTen(), NhanVien.getSoCMND(),
					NhanVien.getDiaChi(), NhanVien.getNgaySinh(), NhanVien.getLuong(), NhanVien.getMacn(),
					NhanVien.getTrangThaiXoa() };
			model.addRow(rowData);
		}
		if (list.size() > 0)
			table.getSelectionModel().setSelectionInterval(0, 0);
	}

	private void loadDataOtherServer(ItemEvent l) {
		if (l.getStateChange() == ItemEvent.SELECTED) {
			if (Program.conn != null) {
				try {
					Program.conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			Program.servername = Program.servers.get(comboBox.getSelectedItem());
			Program.mlogin = Program.remotelogin;
			Program.password = Program.remotepassword;
			Program.mChinhanh = comboBox.getSelectedIndex();
			if (Program.Connect() == 0)
				return;

			model.setRowCount(0);
			loadNhanVien();

		}
	}

	private void chooseNhanVien() {
		String manv = table.getValueAt(table.getSelectedRow(), 0).toString();
		String firstName = table.getValueAt(table.getSelectedRow(), 1).toString().trim();
		String lastName = table.getValueAt(table.getSelectedRow(), 2).toString().trim();
		CreateLoginForm.getTextFieldMaNV().setText(manv);
		CreateLoginForm.getTextFieldName().setText(firstName + " " + lastName);
		this.dispose();
	}

	private void exitForm() {
		this.dispose();
	}

	private void searchNhanVien() {
		String input = textFieldSearch.getText().trim().toLowerCase();
		model.setRowCount(0);

		for (NhanVienModel NhanVien : list) {
			if (NhanVien.getTen().toLowerCase().contains(input)) {
				Object[] rowData = { NhanVien.getManv(), NhanVien.getHo(), NhanVien.getTen(), NhanVien.getSoCMND(),
						NhanVien.getDiaChi(), NhanVien.getNgaySinh(), NhanVien.getLuong(), NhanVien.getMacn(),
						NhanVien.getTrangThaiXoa() };
				model.addRow(rowData);
			}
		}

		if (table.getRowCount() > 0) {
			table.getSelectionModel().setSelectionInterval(0, 0);
		}
	}
}
