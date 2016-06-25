package com.df.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.df.db.StudentsOperation;
import com.df.db.TeachersOperation;
import com.df.model.Students;
import com.df.model.Teachers;

//��ʦ��¼ҳ��
public class TeacherLogin extends JFrame{
	
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height;
	private Image icon = this.getToolkit().getImage("img/IconImage.png");
	private JPanel jp = new JPanel();
	private JLabel jl1 = new JLabel("�û�����"),
		jl2 = new JLabel("���룺");
	private JTextField jtf1 = new JTextField();
	private JPasswordField jpsf = new JPasswordField();
	private JButton jbtn1 = new JButton("��¼"),
		jbtn2 = new JButton("����");
	
	public TeacherLogin()
	{
		setTitle("���ϵͳ");
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
				TeachersOperation to = new TeachersOperation();
				String tUserName = jtf1.getText(),
						tPassword = jpsf.getText();
				if ("".equals(tUserName) || tUserName == null){
					JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
					return;
				}
				if ("".equals(tPassword) || tPassword == null){
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
					return;
				}
				Teachers teacher = new Teachers();
				teacher.settUserName(tUserName);
				teacher.settPassword(tPassword);
				Teachers returnTea = to.login(teacher);
				if(returnTea != null)
				{
					dispose();
					new TeacherView().show();
				}else{
					JOptionPane.showMessageDialog(null, "��¼ʧ��");
				}
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
	
	
		//����
		public static void main(String[] args) {
			new TeacherLogin();
		}
}
