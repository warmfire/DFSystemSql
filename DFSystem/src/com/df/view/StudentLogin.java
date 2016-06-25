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

/*���JTextFiled�ı��С
 * �ڻ�ȡ����ʱʹ�øø�ʽ��String pw = new String(jpwf.getPassWord());
 * ����ʾ����JLabel����ʾ���볤�ȵ���Ϣ
 * */
//ѧ����¼ҳ��
public class StudentLogin extends JFrame{
	
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
	private static StudentsSend stuSend;
	
	public StudentLogin()
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
				StudentsOperation so = new StudentsOperation();
				String sUserName = jtf1.getText(),
						sPassword = jpsf.getText();
				if ("".equals(sUserName) || sUserName == null){
					JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
					return;
				}
				if ("".equals(sPassword) || sPassword == null){
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
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
								System.out.println(stu.getsUserName() + "�ѵ�¼");
							else
								System.out.println(stu.getsUserName() + "��¼ʧ��");
						}else{
							stuSend = new StudentsSend();
							stuSend.setStu(stu.getsUserName());
							System.out.println("0000000" + stuSend.getStu());
							
							dispose();
							new CaptainIndex().show();
							
							if(so.changeIsLogin(stu))
								System.out.println(stu.getsUserName() + "�ѵ�¼");
							else
								System.out.println(stu.getsUserName() + "��¼ʧ��");
						}
						//�ı���û����ݿ���isLogin��ֵΪ��¼״̬	 check
						//���ߺ���Ҫ�����������ݿ��е�isLogin��ֵ		check
					}else{
						JOptionPane.showMessageDialog(null, "���û�������");
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "�˺Ż��������");
				}
				
				//�����ж��û����Ƿ����	check
				//֮���ж����Ƿ��Ѿ���¼--- �����ݿ��ѯisLogin�ж��Ƿ�����	check
				//Ȼ�����ж����Ƿ�Ϊ�鳤��������鳤(isHeadman == 1)��new CaptainIndex().show()\����(isHeadman == -1)new MemberView().show check
				//Ȼ��ı���û����ݿ���isLogin��ֵΪ��¼״̬	Ҳ����˵���ߺ���Ҫ�����������ݿ��е�isLogin��ֵ		check
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
		
	
//		//����
		public static void main(String[] args) {
			new StudentLogin();
		}
}
