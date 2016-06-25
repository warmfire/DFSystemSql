package com.df.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//ѧ���û�ҳ�棬������ע�����¼
public class StudentUser extends JFrame{
	
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height;
	private Image icon = this.getToolkit().getImage("img/IconImage.png");
	private JPanel jp = new JPanel();
	private JButton jbtn1 = new JButton("ע��"),
		jbtn2 = new JButton("��¼");
	
	public StudentUser()
	{
		setTitle("���ϵͳ");
		this.setIconImage(icon);
		
		jp.add(jbtn1);
		jp.add(jbtn2);
		this.add(jp);
		
		jbtn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new StudentRegister().show();
				
			}
		});
		jbtn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new StudentLogin().show();
			}
		});
		
		jp.setLayout(null);
		jbtn1.setBounds(90, 60, 100, 50);
		jbtn2.setBounds(90, 150, 100, 50);

		this.setBounds((x / 2) - 150, (y / 2) - 150, 300, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//����
//	public static void main(String[] args) {
//		new StudentUser();
//	}
	
}
