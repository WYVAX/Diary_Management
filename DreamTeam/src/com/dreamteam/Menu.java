package com.dreamteam;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class Menu extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP,
			JTabbedPane.WRAP_TAB_LAYOUT);
	JPanel pAccont = new JPanel(new FlowLayout(FlowLayout.LEFT));
	JLabel name;
	ImageIcon icon = new ImageIcon("ico/close.gif");
	String User;
	String Row;
	static String userName="";
	public Menu(String userName) {
		this.userName=userName;
		this.setTitle("�ҵ��ռ�");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		init();
	}

	public void init() {
		/******************* panelģ�� ***********************/
		name = new JLabel("�û��� "+userName);
		name.setIcon(icon);
		pAccont.add(name);
		/******************* tabģ�� ***********************/
		tabbedPane.addTab("                 �ҵ��ռ�                   ", icon,
				null, "�鿴�ҵ��ռ�");
		tabbedPane.addTab("                 д�ռ�                   ", icon,
				null, "д�ռ�");
		tabbedPane.addTab("                 ��Ϣ�޸�                   ", icon,
				null, "�޸ĸ�����Ϣ");

		// ΪJTabbedPane����¼�������
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// �����ѡ��������Ȼ�ǿ�
				if (tabbedPane.getSelectedComponent() == null) {
					// ��ȡ��ѡTabҳ
					int n = tabbedPane.getSelectedIndex();
					// Ϊָ����ǰҳ��������
					loadTab(n);
				}
			}
		});
		// ϵͳĬ��ѡ���һҳ�����ص�һҳ����
		loadTab(0);
		tabbedPane.setPreferredSize(new Dimension(1000, 600));

		this.add(pAccont);
		this.add(tabbedPane, BorderLayout.CENTER);
		this.setResizable(false); // �����Ƿ�ɸ��Ĵ��ڴ�С
		this.setLocation(190, 20);// ���ô��ھ�����ʾ
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}

	private void loadTab(int n) {
		String title = tabbedPane.getTitleAt(n);

		String image =  "user/1/1/1.png,"+ "user/1/1/2.jpg,"+
				"user/1/1/3.jpg,"+"user/1/1/4.jpg" ;
		String text = "user/1/1/novel.txt";

		
	   MyDiary md = new MyDiary();
       WriteDiary m1=new WriteDiary(userName);
       UserUI uu = new UserUI();

		JPanel p = new JPanel();
		p = md.MyDiary;
		
		JPanel b = new JPanel();
		b = m1.getpShow();
		
		JPanel t = new JPanel();
		t = uu.getUser();
		if (n == 0)
			tabbedPane.setComponentAt(n, p);
		else if (n == 1)
			tabbedPane.setComponentAt(n, b);
		else if(n == 2)
			tabbedPane.setComponentAt(n, t);
		// �ı��ǩҳ��ͼ��
		tabbedPane.setIconAt(n, new ImageIcon("ico/open.gif"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

//	public static void main(String[] args) {
//
////		 EventQueue.invokeLater(new Runnable() {
////		 @Override
////		 public void run() {
////		 JFrame.setDefaultLookAndFeelDecorated(true);
////		 JDialog.setDefaultLookAndFeelDecorated(true);
////		 try {
////		
////		 UIManager.setLookAndFeel(new
////		 org.jvnet.substance.skin.SubstanceAutumnLookAndFeel());
////		
////		 } catch (Exception e) {
////		
////		 e.printStackTrace();
////		 }
////		
////		 }
////		 });
//		 Menu browser = new Menu(userName);
//		 browser.setVisible(true);
////
////		OperaDB op = new OperaDB();
////		Connection conn = op.getConnection();
////		op.registe(conn, "yss", "yss");
////		op.login(conn, "yss", "yss");
////		
////		op.searchedAll(conn, "wxq", "hell");
//		
////		String name = "wxq", weather = "��", time = "2015-12-12", mood = "����",title="hi",contenturl="uytt",picturl="xzz";
////
////		Vector<DiaryModel> sr=op.searched(conn, name, weather, time, mood);
////		
////		op.save(conn,name, title, contenturl, picturl, time, weather, mood);
//
//	}

	
//	@Override
//	public void hyperlinkUpdate(HyperlinkEvent arg0) {
//
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//
//	}
}
