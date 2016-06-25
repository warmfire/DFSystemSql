package com.df.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import com.df.controller.StudentsSend;
import com.df.controller.WindowControl;
import com.df.db.Dao;
import com.df.db.IntoGrades;
import com.df.db.StudentsOperation;
import com.df.model.InGrades;
import com.df.model.Students;
import com.df.controller.StudentsSend;
import com.df.db.selectNum;

public class InGradeView extends JFrame{
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height;
	private Image icon = this.getToolkit().getImage("img/IconImage.png");
	private JTable jtb1;
	private JPanel jp = new JPanel();
	private JButton jbtn1 = new JButton("�ύ����"),
			jbtn2 = new JButton("��ʼ���"),
			jbtn3 = new JButton("����");
	private Object[][] info;
	private String stuInf;
	private int count;
	private static StudentsSend stuSend;
	
	public InGradeView() {
		setTitle("���ڴ��");
		this.setIconImage(icon);
		this.add(jp);
		
		stuInf = new StudentsSend().getStu();
		
		this.scpDemo = new JScrollPane();
		jp.add(jbtn1);
		jp.add(jbtn2);
		jp.add(jbtn3);
		jp.add(scpDemo);
		jp.setLayout(null);
		scpDemo.setBounds(20,20,750,120);
		jbtn1.setBounds(350, 160, 90, 25);
		jbtn2.setBounds(250, 160, 90, 25);
		jbtn3.setBounds(460, 160, 90, 25);
		jbtn1.setEnabled(false);
		
		jbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					selectNum snum = new selectNum();
					count = snum.selectGrpid(StudentsSend.getStu());
					info = snum.selectNum(count);	
					// �����ͷ
					String[] title = {"����","ѧ��","���","��ɫ","������30","����20","̬��20","����20","����10","����100","����100"};
					jtb1 = new JTable(info,title);
					// ��ʾ��ͷ
					jth = jtb1.getTableHeader();
					// ��JTable���뵽���������������
					scpDemo.getViewport().add(jtb1);
					jbtn1.setEnabled(true);
					
				}catch(Exception sqle){
					JOptionPane.showMessageDialog(null,"���ݲ�������","����",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dao d = new Dao();
				Connection conn = d.getConnection();
				selectNum snum = new selectNum();
				count = snum.selectGrpid(StudentsSend.getStu());
				int allnum = snum.selectinum(count);
				int i,x,y;
				boolean tf=true;
				for(x=0;x<allnum;x++){
					for(y=0;y<11;y++){
						if(info[x][y].toString().equals("0")) tf=false;
					}
				}
				if(tf){
					jbtn1.setEnabled(true);
					try{
						for(i = 0;i<allnum; i++)
						{						
							InGrades ig = new InGrades();
							ig.setsName(info[i][0].toString());
							ig.setsId(info[i][1].toString());
							ig.setGroupid(Integer.parseInt(info[i][2].toString()));
							ig.setPart(info[i][3].toString());
							ig.setWorkloads(Integer.parseInt(info[i][4].toString()));
							ig.setOffer(Integer.parseInt(info[i][5].toString()));
							ig.setAttitude(Integer.parseInt(info[i][6].toString()));
							ig.setCooperate(Integer.parseInt(info[i][7].toString()));
							ig.setProgress(Integer.parseInt(info[i][8].toString()));
							ig.setSelfgrades(Integer.parseInt(info[i][9].toString()));
							ig.setGrader(StudentsSend.getStu());
							ig.setOthergrades(Integer.parseInt(info[i][10].toString()));
							IntoGrades itg = new IntoGrades();
							int res = itg.IntoGrades(ig);				
						}
					}catch(Exception sqle){
						sqle.printStackTrace();
						JOptionPane.showMessageDialog(null,"�ɼ��ύ����","����",JOptionPane.ERROR_MESSAGE);
					}
					jbtn2.setEnabled(false);
				}
				else{
					JOptionPane.showMessageDialog(null,"δ�������","����",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				StudentsOperation so = new StudentsOperation();
				Students stu = new Students();
				stu.setsUserName(StudentsSend.getStu());
				if(so.isHeadman(stu)){
					MemberView mv = new MemberView();
					mv.show();
				}else{
					CaptainIndex ci = new CaptainIndex();
					ci.show();
				}
				
			}
		});
		this.setBounds((x / 2) - 405, (y / 2) - 175, 810, 250);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowControl(stuInf));//ʵ�ֵ�������ڹرհ�ťʱ�ı�isLogin״̬
	}
	
	
	public static void main(String[] args) {
		new InGradeView();
	}
	
}
