package com.df.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.df.controller.WindowControl;

//��ʾΪ��ҳ��,�����н�ʦ��ڡ�ѧ�����
public class Index extends JFrame{
	
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height;
	private Image icon = this.getToolkit().getImage("img/IconImage.png");
	private JPanel jp = new JPanel();
	private JButton jbtn1 = new JButton("ѧ�����"),
		jbtn2 = new JButton("��ʦ���");
	
	public Index()
	{
		setTitle("���ϵͳ");
		this.setIconImage(icon);
		
		jp.add(jbtn1);
		jp.add(jbtn2);
		this.add(jp);
		
		jbtn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new StudentUser().show();
			}
		});
		jbtn2.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TeacherLogin().show();
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
	public static void main(String[] args) {
		new Index();
	}
}
