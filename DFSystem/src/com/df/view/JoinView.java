package com.df.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.df.controller.StudentsSend;
import com.df.controller.WindowControl;
import com.df.model.Students;
import com.df.netserver.TCPClient;
import com.df.netserver.TCPServer;

public class JoinView extends JFrame{
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height;
	private Image icon = this.getToolkit().getImage("img/IconImage.png");
	private JPanel jp = new JPanel();
	private JLabel jl1 = new JLabel("请输入组长IP地址:");
	private JButton jbtn1 = new JButton("确认加入"),
			jbtn2 = new JButton("返回");
	private JTextField jtf1 = new JTextField();
	private String stuInf;
	
	public JoinView()
	{
		setTitle("打分系统");
		this.setIconImage(icon);
		
		stuInf = new StudentsSend().getStu();
		
		this.add(jp);
		jp.add(jl1);
		jp.add(jtf1);
		jp.add(jbtn1);
		jp.add(jbtn2);

		jp.setLayout(null);
		
		jbtn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String address = jtf1.getText();
				TCPClient client = new TCPClient(address);
			}
		});
		jbtn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MemberView().show();
				dispose();
			}
		});
		
		jl1.setBounds(20, 20, 150, 50);
		jtf1.setBounds(20, 70, 120, 25);
		jbtn1.setBounds(150, 50, 100, 50);
		jbtn2.setBounds(100, 180, 100, 50);
		
		jtf1.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		this.setBounds((x / 2) - 150, (y / 2) - 150, 300, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowControl(stuInf));//实现当点击窗口关闭按钮时改变isLogin状态
	}
	
//	public static void main(String[] args) {
//		new JoinView();
//	}
	
}
