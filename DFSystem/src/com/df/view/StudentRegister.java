package com.df.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.df.controller.IsRTrue;
import com.df.db.StudentsOperation;
import com.df.model.Students;

/*可实现班级添加至Vector功能
 * */
//学生注册页面
public class StudentRegister extends JFrame{
	
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height;
	private Image icon = this.getToolkit().getImage("img/IconImage.png");
	private JPanel jp = new JPanel();
	private JLabel jl1 = new JLabel("用户名："),
		jl2 = new JLabel("密码："),
		jl3 = new JLabel("确认密码："),
		jl4 = new JLabel("姓名："),
		jl5 = new JLabel("学号："),
		jl6 = new JLabel("班级："),
		jl7 = new JLabel("是否为组长："),
		jlTS1 = new JLabel(""),
		jlTS2 = new JLabel("密码格式不符"),
		jlTS3 = new JLabel("两次密码不同"),
		jlTS4 = new JLabel(""),
		jlTS5 = new JLabel("学号格式不符"),
		jlTS6 = new JLabel(""),
		jlTS7 = new JLabel("");
		
	private JTextField jtf1 = new JTextField(),
			jtf2 = new JTextField(),
			jtf3 = new JTextField();
	private JPasswordField jpsf1 = new JPasswordField(),
			jpsf2 = new JPasswordField();
	private JButton jbtn1 = new JButton("提交"),
		jbtn2 = new JButton("重置");
	private JComboBox jcb = null;
	private Vector<String> v=new Vector<String>();
	private JRadioButton jrb1 = new JRadioButton("是"),
		jrb2 = new JRadioButton("否");
	IsRTrue irt = new IsRTrue();
	public StudentRegister()
	{
		jbtn1.setEnabled(false);
		jlTS1.setVisible(true);
		jlTS2.setVisible(false);
		jlTS3.setVisible(false);
		jlTS4.setVisible(true);
		jlTS5.setVisible(false);
		jlTS6.setVisible(false);
		jlTS7.setVisible(true);
		setTitle("打分系统");
		this.setIconImage(icon);
		v.add("13计算机本一");
		v.add("13计算机专一");
		jcb = new JComboBox(v);
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
		
		jp.add(jl1);
		jp.add(jl2);
		jp.add(jl3);
		jp.add(jl4);
		jp.add(jl5);
		jp.add(jl6);
		jp.add(jl7);
		jp.add(jlTS1);
		jp.add(jlTS2);
		jp.add(jlTS3);
		jp.add(jlTS4);
		jp.add(jlTS5);
		jp.add(jlTS6);
		jp.add(jlTS7);
		jp.add(jtf1);
		jp.add(jtf2);
		jp.add(jtf3);
		jp.add(jpsf1);
		jp.add(jpsf2);
		jp.add(jcb);
		jp.add(jrb1);
		jp.add(jrb2);
		jp.add(jbtn1);
		jp.add(jbtn2);
		this.add(jp);
		jrb1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(jrb1.isSelected()||jrb2.isSelected()) jlTS7.setVisible(false);
				
				irt.isrtrue(jlTS1, jlTS2, jlTS3, jlTS4, jlTS5, jlTS6, jlTS7, jbtn1);	
			}
		});
		jrb2.addChangeListener(new ChangeListener() {//list<Object>
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(jrb1.isSelected()||jrb2.isSelected()) jlTS7.setVisible(false);
				
				irt.isrtrue(jlTS1, jlTS2, jlTS3, jlTS4, jlTS5, jlTS6, jlTS7, jbtn1);	
			}
		});
		jtf1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(jtf1.getText().length()!= 0) jlTS1.setVisible(false);
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				irt.isrtrue(jlTS1, jlTS2, jlTS3, jlTS4, jlTS5, jlTS6, jlTS7, jbtn1);
			}
		});
		jtf2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				if(jtf2.getText().length()!= 0) jlTS4.setVisible(false);
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				irt.isrtrue(jlTS1, jlTS2, jlTS3, jlTS4, jlTS5, jlTS6, jlTS7, jbtn1);
			}
		});
		jpsf1.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				int len = jpsf1.getText().length();
				if(len<3 || len >16) jlTS2.setVisible(true);
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				jlTS2.setVisible(false);
				irt.isrtrue(jlTS1, jlTS2, jlTS3, jlTS4, jlTS5, jlTS6, jlTS7, jbtn1);
			}
		});
		jpsf2.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				String str1 =  jpsf1.getText();
				String str2 =  jpsf2.getText();
				if(!str1.equals(str2)) jlTS3.setVisible(true);
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				jlTS3.setVisible(false);
				irt.isrtrue(jlTS1, jlTS2, jlTS3, jlTS4, jlTS5, jlTS6, jlTS7, jbtn1);
			}
		});
		jtf3.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				int str =  jtf3.getText().length();
				if(str!=11) jlTS5.setVisible(true);
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				jlTS5.setVisible(false);
				irt.isrtrue(jlTS1, jlTS2, jlTS3, jlTS4, jlTS5, jlTS6, jlTS7, jbtn1);
			}
		});
		
		jbtn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(jlTS1.isVisible()||jlTS2.isVisible()
						||jlTS3.isVisible()||jlTS4.isVisible()
						||jlTS5.isVisible()||jlTS6.isVisible()
						||jlTS7.isVisible()){
					jbtn1.setEnabled(false);
					return;
				}
				String sUserName = jtf1.getText(),
					sPassword = jpsf1.getText(),
					sName = jtf2.getText(),
					sId = jtf3.getText(),
					className  = jcb.getSelectedItem().toString();
				String isHeadman = "-1",
					groupId = "-1";
				if(jrb1.isSelected())
					isHeadman = "1";
				if(jrb2.isSelected())
					isHeadman = "-1";
				Students stu = new Students();
				stu.setsUserName(sUserName);
				stu.setsPassword(sPassword);
				stu.setsName(sName);
				stu.setsId(sId);
				stu.setClassName(className);
				stu.setIsHeadman(isHeadman);
				stu.setGroupId(groupId);
				StudentsOperation so = new StudentsOperation();
				int res = 1;
				if(res != 0){
					if(so.isExist(stu)){
						JOptionPane.showMessageDialog(null, "注册失败,该用户名已存在");
						return;
					}else{
						JOptionPane.showMessageDialog(null, "注册成功");
						dispose();
						new StudentLogin().show();
					}
				}else{
					JOptionPane.showMessageDialog(null, "注册失败");
					return;
				}

				res = so.register(stu);
//				System.out.println(isHeadman);
			}
		});
		jbtn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtf1.setText("");
				jpsf1.setText("");
				jpsf2.setText("");
				jtf2.setText("");
				jtf3.setText("");
				jrb2.setSelected(true);//设置默认为非组长
				jbtn1.setEnabled(false);
			}
		});
		
		
		jp.setLayout(null);
		jl1.setBounds(20, 20, 55, 20);
		jl2.setBounds(35, 55, 55, 20);
		jl3.setBounds(9, 90, 65, 20);
		jl4.setBounds(35, 125, 65, 20);
		jl5.setBounds(35, 160, 65, 20);
		jl6.setBounds(35, 195, 65, 20);
		jl7.setBounds(2, 230, 80, 20);
		
		jtf1.setBounds(80, 20, 120, 25);
		jtf2.setBounds(80, 125, 120, 25);
		jtf3.setBounds(80, 160, 120, 25);
		jpsf1.setBounds(80, 55, 120, 25);
		jpsf2.setBounds(80, 90, 120, 25);
		jcb.setBounds(80, 195, 120, 20);
		jrb1.setBounds(90, 230, 65, 20);
		jrb2.setBounds(150, 230, 40, 20);
		
		jlTS1.setBounds(205, 20, 95, 20);
		jlTS2.setBounds(205, 55, 95, 20);
		jlTS3.setBounds(205, 90, 95, 20);
		jlTS4.setBounds(205, 125, 95, 20);
		jlTS5.setBounds(205, 160, 95, 20);
		jlTS6.setBounds(205, 195, 95, 20);
		jlTS7.setBounds(205, 230, 95, 20);
		
		jbtn1.setBounds(60, 280, 60, 30);
		jbtn2.setBounds(180, 280, 60, 30);
		
		jtf1.setBorder(BorderFactory.createLoweredBevelBorder());
		jpsf1.setBorder(BorderFactory.createLoweredBevelBorder());
		jpsf2.setBorder(BorderFactory.createLoweredBevelBorder());
		jtf2.setBorder(BorderFactory.createLoweredBevelBorder());
		jtf3.setBorder(BorderFactory.createLoweredBevelBorder());

		this.setBounds((x / 2) - 150, (y / 2) - 150, 350, 380);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//测试
		public static void main(String[] args) {
			new StudentRegister();
		}
}


