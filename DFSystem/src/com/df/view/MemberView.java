package com.df.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.df.controller.StudentsSend;
import com.df.controller.WindowControl;
import com.df.model.Students;

public class MemberView extends JFrame{
	
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height;
	private Image icon = this.getToolkit().getImage("img/IconImage.png");
	private JPanel jp = new JPanel();
	private JButton jbtn1 = new JButton("加入组"),
		jbtn2 = new JButton("组内打分");
	private String stuInf;
	
	public MemberView()
	{
		setTitle("打分系统");
		this.setIconImage(icon);
		
		stuInf = new StudentsSend().getStu();
//		System.out.println("2222222222" + stuInf);
		
		this.add(jp);
		jp.add(jbtn1);
		jp.add(jbtn2);
		
		jp.setLayout(null);
		
		jbtn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new JoinView().show();
				dispose();
			}
		});
		jbtn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
				new InGradeView().show();
			}
		});
		
		jbtn1.setBounds(90, 60, 100, 50);
		jbtn2.setBounds(90, 150, 100, 50);
		
		this.setBounds((x / 2) - 150, (y / 2) - 150, 300, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowControl(stuInf));//实现当点击窗口关闭按钮时改变isLogin状态
	}
	
//	public static void main(String[] args) {
//		new MemberView();
//	}
	
}
