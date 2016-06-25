package com.df.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import com.df.controller.IsEmpty;
import com.df.controller.SplitMessage;
import com.df.controller.StudentsSend;
import com.df.controller.TemMessage;
import com.df.controller.WindowControl;
import com.df.db.StudentsOperation;
import com.df.model.Students;
import com.df.netserver.TCPServer;

public class InviteView extends JFrame{
	
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height;
	private Image icon = this.getToolkit().getImage("img/IconImage.png");
	private JPanel jp = new JPanel();
	private JButton jbtn1 = new JButton("启动服务"),
			jbtn2 = new JButton("返回"),
			jbtn3 = new JButton("提交组员名单");
	private JLabel jl2 = new JLabel();//用来显示提示
	private String stuInf;
	private String tem = "";
	private String sUserName;
	
	public InviteView()
	{
		setTitle("打分系统");
		this.setIconImage(icon);
		
		stuInf = new StudentsSend().getStu();
		
		this.add(jp);
		jp.add(jbtn1);
		jp.add(jl2);
		jp.add(jbtn2);
		jp.add(jbtn3);
		
		jp.setLayout(null);
		
		jbtn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TCPServer server = new TCPServer();
			
				
				TemMessage tm = new TemMessage();
				tm.setTemMessage(server.getMessage());
				sUserName = tm.getTemMessage();
				jl2.setText(tm.getTemMessage());
				//待添加内容：成功加入后会显示出加入成功
			}});
		
		jbtn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
				new CaptainIndex().show();
			}
		});
		jbtn3.addActionListener(new ActionListener() {//自动生成组别	check
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentsOperation so = new StudentsOperation();
				int maxGroupId = so.maxGroupId();
				//首先得把sUserName分离 	check
				
				String[] retMes = SplitMessage.SplitMessage(sUserName);
				for(String i : retMes){
					System.out.println(i);
					so.setGroupId(maxGroupId, i);
					so.insertAll(i);	//执行存储过程把gruopId设置到3个表
				}
				
			}
		});
		
		jl2.setBounds(70, 100, 150, 40);
		jl2.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		jbtn1.setBounds(105, 35, 90, 35);
		jbtn2.setBounds(30, 210, 80, 35);
		jbtn3.setBounds(135, 210, 120, 35);
		
		this.setBounds((x / 2) - 150, (y / 2) - 150, 300, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowControl(stuInf));//实现当点击窗口关闭按钮时改变isLogin状态
	}
	
	
	
//	public static void main(String[] args) {
//		new InviteView();
//	}
	
	
}
