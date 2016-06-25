package com.df.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.df.controller.StudentsSend;
import com.df.db.StudentsOperation;
import com.df.model.Students;

/*点击JTextFiled改变大小
 * 在获取密码时使用该格式，String pw = new String(jpwf.getPassWord());
 * 在提示内容JLabel中显示密码长度等信息
 * */
//学生登录页面
public class StudentLogin extends JFrame{
	
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height;
	private Image icon = this.getToolkit().getImage("img/IconImage.png");
	private JPanel jp = new JPanel();
	private JLabel jl1 = new JLabel("用户名："),
		jl2 = new JLabel("密码：");
	private JTextField jtf1 = new JTextField();
	private JPasswordField jpsf = new JPasswordField();
	private JButton jbtn1 = new JButton("登录"),
		jbtn2 = new JButton("重置");
	private static StudentsSend stuSend;
	
	public StudentLogin()
	{
		setTitle("打分系统");
		this.setIconImage(icon);
		
		jp.add(jl1);
		jp.add(jl2);
		jp.add(jtf1);
		jp.add(jpsf);
		jp.add(jbtn1);
		jp.add(jbtn2);
		this.add(jp);
		
		
		jbtn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentsOperation so = new StudentsOperation();
				String sUserName = jtf1.getText(),
						sPassword = jpsf.getText();
				if ("".equals(sUserName) || sUserName == null){
					JOptionPane.showMessageDialog(null, "用户名不能为空");
					return;
				}
				if ("".equals(sPassword) || sPassword == null){
					JOptionPane.showMessageDialog(null, "密码不能为空");
					return;
				}
				Students stu = new Students();
				stu.setsUserName(sUserName);
				stu.setsPassword(sPassword);
				Students returnStu = so.login(stu);
				if(returnStu != null)
				{
					if(so.isOnline(stu))
					{
						if(so.isHeadman(stu)){
							stuSend = new StudentsSend();
							stuSend.setStu(stu.getsUserName());
//							System.out.println("0000000000000000" + stuSend.getStu());
							
							dispose();
							new MemberView().show();
							
							if(so.changeIsLogin(stu))
								System.out.println(stu.getsUserName() + "已登录");
							else
								System.out.println(stu.getsUserName() + "登录失败");
						}else{
							stuSend = new StudentsSend();
							stuSend.setStu(stu.getsUserName());
							System.out.println("0000000" + stuSend.getStu());
							
							dispose();
							new CaptainIndex().show();
							
							if(so.changeIsLogin(stu))
								System.out.println(stu.getsUserName() + "已登录");
							else
								System.out.println(stu.getsUserName() + "登录失败");
						}
						//改变该用户数据库中isLogin的值为登录状态	 check
						//离线后还需要更改其在数据库中的isLogin的值		check
					}else{
						JOptionPane.showMessageDialog(null, "该用户已在线");
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "账号或密码出错");
				}
				
				//首先判断用户名是否存在	check
				//之后判断其是否已经登录--- 经数据库查询isLogin判断是否在线	check
				//然后还需判断其是否为组长，如果是组长(isHeadman == 1)则new CaptainIndex().show()\否则(isHeadman == -1)new MemberView().show check
				//然后改变该用户数据库中isLogin的值为登录状态	也就是说离线后还需要更改其在数据库中的isLogin的值		check
			}
		});
		jbtn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtf1.setText("");
				jpsf.setText("");
			}
		});
		
		
		jp.setLayout(null);
		jl1.setBounds(25, 50, 55, 20);
		jl2.setBounds(40, 130, 55, 20);
		jtf1.setBounds(85, 50, 130, 25);
		jpsf.setBounds(85, 130, 130, 25);
		jbtn1.setBounds(60, 200, 60, 30);
		jbtn2.setBounds(180, 200, 60, 30);
		
		jtf1.setBorder(BorderFactory.createLoweredBevelBorder());
		jpsf.setBorder(BorderFactory.createLoweredBevelBorder());

		this.setBounds((x / 2) - 150, (y / 2) - 150, 300, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public StudentsSend getStu()
	{
		return stuSend;
	}
		
	
//		//测试
		public static void main(String[] args) {
			new StudentLogin();
		}
}
