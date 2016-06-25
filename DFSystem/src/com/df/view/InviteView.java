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
	private JButton jbtn1 = new JButton("��������"),
			jbtn2 = new JButton("����"),
			jbtn3 = new JButton("�ύ��Ա����");
	private JLabel jl2 = new JLabel();//������ʾ��ʾ
	private String stuInf;
	private String tem = "";
	private String sUserName;
	
	public InviteView()
	{
		setTitle("���ϵͳ");
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
				//��������ݣ��ɹ���������ʾ������ɹ�
			}});
		
		jbtn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
				new CaptainIndex().show();
			}
		});
		jbtn3.addActionListener(new ActionListener() {//�Զ��������	check
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StudentsOperation so = new StudentsOperation();
				int maxGroupId = so.maxGroupId();
				//���ȵð�sUserName���� 	check
				
				String[] retMes = SplitMessage.SplitMessage(sUserName);
				for(String i : retMes){
					System.out.println(i);
					so.setGroupId(maxGroupId, i);
					so.insertAll(i);	//ִ�д洢���̰�gruopId���õ�3����
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
		this.addWindowListener(new WindowControl(stuInf));//ʵ�ֵ�������ڹرհ�ťʱ�ı�isLogin״̬
	}
	
	
	
//	public static void main(String[] args) {
//		new InviteView();
//	}
	
	
}
