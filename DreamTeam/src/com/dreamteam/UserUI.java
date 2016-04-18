package com.dreamteam;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.util.Timer;
import java.util.TimerTask;

public class UserUI {
	JPanel User = new JPanel();
	JLabel imageicon = new JLabel();
	JButton imagechange = new JButton("            �޸�ͷ��            ");
	static JButton passchange = new JButton("            �޸�����            ");
	static JTextField oldpass = new JTextField(30);
	static JTextField newpass = new JTextField(30);
	static int flag = 0;
	static boolean jug = true;
	static String Users = Menu.userName;
	static String nep = new String();
	static String olp = new String();

	public UserUI() {
		init();
		flag = 0;
		action();
	}

	public JPanel getUser() {
		return User;
	}

	public void setUser(JPanel User) {
		this.User = User;
	}

	public void init() {
		User.setLayout(new GridBagLayout());
		imageicon.setIcon(new ImageIcon("image/cute.png"));

		GridBagConstraints c1 = new GridBagConstraints();

		JPanel c = new JPanel();
		c.add(new JLabel("�����������ڵ�����"));
		c.add(oldpass);
		c1.gridx = 0;
		c1.gridy = 0;
		c1.insets = new Insets(150, 0, 0, 0);
		c1.fill = GridBagConstraints.NONE;
		c1.weightx = 0;
		c1.weighty = 0;
		User.add(c, c1);

		JPanel d = new JPanel();
		d.add(new JLabel("���������������"));
		d.add(newpass);
		
		c1.gridx = 0;
		c1.gridy = 1;
		c1.insets = new Insets(150, 0, 0, 0);
		c1.fill = GridBagConstraints.NONE;
		c1.weightx = 0;
		c1.weighty = 0;
		User.add(d, c1);

		c1.gridx = 2;
		c1.gridy = 0;
		c1.insets = new Insets(150, 0, 0, 0);
		c1.fill = GridBagConstraints.NONE;
		c1.weightx = 1;
		c1.weighty = 0;
		User.add(imageicon, c1);

		c1.gridx = 2;
		c1.gridy = 1;
		c1.fill = GridBagConstraints.NONE;
		c1.insets = new Insets(5, 0, 0, 0);
		c1.weightx = 0;
		c1.weighty = 0;
		User.add(imagechange, c1);

		c1.gridx = 0;
		c1.gridy = 2;
		c1.fill = GridBagConstraints.NONE;
		c1.insets = new Insets(15, 0, 0, 0);
		c1.weightx = 0;
		c1.weighty = 0;
		User.add(passchange, c1);

		JPanel p = new JPanel();
		c1.gridx = 0;
		c1.gridy = 3;
		c1.fill = GridBagConstraints.NONE;
		c1.insets = new Insets(5, 0, 0, 0);
		c1.weightx = 1;
		c1.weighty = 1;
		User.add(p, c1);

	}

	public static void pass(String oldp, String newp, String Users) {
		// �趨���ݿ����������ݿ����ӵ�ַ���˿ڡ����ƣ��û���������
		String driverName = "com.mysql.jdbc.Driver";
		ResultSet rs = null;
		PreparedStatement pstmt1 = null; // ���ڲ�ѯ���
		PreparedStatement pstmt2 = null;// ���ڲ������

		// ���ݿ����Ӷ���
		Connection conn = null;

		// ��������
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			// ��������
			Class.forName(driverName);
			try {
				fis = new FileInputStream("mysql.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				properties.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String database = properties.getProperty("database");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			String sql = null;// ��¼sql���

			if (database != null && user != null && password != null) {
				String url = "jdbc:mysql://localhost:3306/" + database + "?useUnicode=true&characterEncoding=utf-8";
				conn = DriverManager.getConnection(url, user, password);
			}

			sql = "select count(*) cou from user where password=? and name = ?";
			// �����������µ�PreparedStatement����
			pstmt1 = conn.prepareStatement(sql);

			// ���ݵ�һ������ֵ �������һ���ʺ�
			pstmt1.setString(1, oldp);
			pstmt1.setString(2, Users);

			// ִ�в�ѯ��䣬�����ݱ��浽ResultSet������
			rs = pstmt1.executeQuery();

			int count = 0;
			if (rs.next()) {
				count = rs.getInt("cou");
			}

			if (count == 1) {

				sql = "update user set password=? where name = ?";
				// �����������µ�PreparedStatement����
				pstmt2 = conn.prepareStatement(sql);

				// ���ݵ�һ������ֵ �������һ���ʺ�
				pstmt2.setString(1, newp);
				pstmt2.setString(2, Users);
				pstmt2.executeUpdate();
				flag = 1;
			} else
				flag = 2;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt1 != null) {
					pstmt1.close();
				}
				if (pstmt2 != null) {
					pstmt2.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private static void showDialog(int type, String msg) {
		JOptionPane.showMessageDialog(null, msg, "������ʾ", type);
	}
	
	public static void action() {
		// Ϊͳ�����ְ�ť����¼�
		passchange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (oldpass.getText().trim().equals("")) {
					showDialog(JOptionPane.ERROR_MESSAGE, "û�������룡");
					//JOptionPane.showMessageDialog(null, "ûд���룡");
					return;
				}
				if (newpass.getText().trim().equals("")) {
					showDialog(JOptionPane.ERROR_MESSAGE, "û�������룡");
					//JOptionPane.showMessageDialog(null, "ûд���룡", null, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if (olp.equals("") == false && nep.equals("") == false) {
					pass(olp, nep, Users);
				}
				if (flag == 1) {
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�", null, JOptionPane.INFORMATION_MESSAGE);
				
					return;
				} else if (flag == 2) {
					JOptionPane.showMessageDialog(null, "�޸�ʧ��", null, JOptionPane.INFORMATION_MESSAGE);

					return;
				}
			}
		});// end

		// ΪtextFiled����¼�
		oldpass.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				olp = oldpass.getText().trim();
			}
		});// end

		// ΪtextFiled����¼�
		newpass.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				nep = newpass.getText().trim();
			}
		});// end

	}

}