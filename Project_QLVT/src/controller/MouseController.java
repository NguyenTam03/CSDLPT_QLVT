package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;


public class MouseController {
	public void hoverComponent(Component t) {
		t.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				t.setCursor(new Cursor(Cursor.HAND_CURSOR));
				t.setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				t.setBackground(new Color(240, 240, 240));
			}
		});
	}
	
	public void clickedComponentShowTab(JTabbedPane tab, String label, String tip, Component t, Component t1) {
		t.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tab.addTab(label, null, t1, tip);
				tab.setSelectedComponent(t1);
			}
		});
	}
}
