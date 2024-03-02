package Test;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YourMainClass {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JPanel panelNhanVien;
    private JButton btnShowNhanVien;

    public YourMainClass() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // Create NhanVien panel
        panelNhanVien = new JPanel();
        tabbedPane.addTab("Nhan vien", null, panelNhanVien, null);
        panelNhanVien.setLayout(null);

        JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(85, 58, 85, 21);
        panelNhanVien.add(btnNewButton);

        // Create Admin panel
        JPanel panelAdmin = new JPanel();
        tabbedPane.addTab("Admin", null, panelAdmin, null);

        JTextField textField = new JTextField();
        panelAdmin.add(textField);
        textField.setColumns(10);

        // Create Boss panel
        JPanel panelBoss = new JPanel();
//        panelBoss.setBorder(new LineBorder(new Color(), 0));
        tabbedPane.addTab("Boss", null, panelBoss, null);

        // Create button to show/hide NhanVien panel
        btnShowNhanVien = new JButton("Show/Hide NhanVien");
        btnShowNhanVien.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleNhanVienPanel();
            }
        });
        frame.getContentPane().add(btnShowNhanVien, BorderLayout.SOUTH);
    }

    private void toggleNhanVienPanel() {
        if (panelNhanVien.isVisible()) {
            panelNhanVien.setVisible(false);
        } else {
            panelNhanVien.setVisible(true);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    YourMainClass window = new YourMainClass();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

