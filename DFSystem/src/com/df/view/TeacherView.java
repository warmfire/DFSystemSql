package com.df.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import com.df.controller.StudentsSend;
import com.df.controller.WriteExcel;
import com.df.controller.WriteExcelout;
import com.df.controller.WriteExceltout;
import com.df.db.Dao;
import com.df.db.IntoGrades;
import com.df.view.TeacherLogin;
import com.df.db.selecttcr;
import com.df.model.TGrades;;

//��ʦ�û�ҳ�棬������ע�����¼
public class TeacherView extends JFrame {

	private int x = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height;
	private Image icon = this.getToolkit().getImage("img/IconImage.png");
	private JPanel jp = new JPanel();
	private JTable jtb1;
	private JTableHeader jth;
	//private JLabel jlb = new JLabel("�������ֱ�");
	//private JLabel jlb2 = new JLabel("������ֱ�");
	private JScrollPane scpDemo = new JScrollPane();;
	private JButton jbtn1 = new JButton("�������"), 
			//jbtn2 = new JButton("ת������"),
			jbtn3 = new JButton("�ɼ���ѯ"), 
			jbtn4 = new JButton("�ύ����"),
			jbtn5 = new JButton("��������");
	//private JComboBox comboBox;
	private Object[][] info;

	private String[] names;

	public TeacherView() {

		setTitle("��ʦ����̨");
		this.setIconImage(icon);

		names = new String[4];
		names[0] = "0";
		names[1] = "1";
		names[2] = "2";
		names[3] = "3";
		//comboBox = new JComboBox(names);

		//comboBox.setEditable(false);

		//jp.add(comboBox);

		jp.add(jbtn1);
		//jp.add(jbtn2);
		jp.add(jbtn3);
		jp.add(jbtn4);
		jp.add(jbtn5);
		jp.add(scpDemo);
		//jp.add(jlb);
		//jp.add(jlb2);
		this.add(jp);

		jp.setLayout(null);
		//jbtn2.setBounds(590, 10, 100, 50);
		jbtn3.setBounds(590, 80, 100, 50);
		jbtn1.setBounds(590, 170, 100, 50);
		jbtn4.setBounds(590, 260, 100, 50);
		jbtn5.setBounds(590, 350, 100, 50);
		//comboBox.setBounds(92, 10, 150, 30);

		scpDemo.setBounds(20, 50, 550, 400);
		//jlb.setBounds(20, 1, 100, 50);

		jbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					selecttcr stg = new selecttcr();
					stg.deleteTgrades();
					JOptionPane.showMessageDialog(null, "��ճɹ�!");
					info = stg.selecttcr();
					// �����ͷ
					String[] title = { "����", "ѧ��", "���", "����"};
					jtb1 = new JTable(info, title);
					// ��ʾ��ͷ
					jth = jtb1.getTableHeader();
					// ��JTable���뵽���������������
					scpDemo.getViewport().add(jtb1);
				} catch (Exception sqle) {
					JOptionPane.showMessageDialog(null, "���ݲ�������", "����",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

//		jbtn2.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//				new TeacherView2().show();
//			}
//		});

		jbtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					selecttcr stg = new selecttcr();
					info = stg.selecttcr();
					// �����ͷ
					String[] title = { "����", "ѧ��", "���", "����"};
					jtb1 = new JTable(info, title);
					// ��ʾ��ͷ
					jth = jtb1.getTableHeader();
					// ��JTable���뵽���������������
					scpDemo.getViewport().add(jtb1);
				} catch (Exception sqle) {
					JOptionPane.showMessageDialog(null, "���ݲ�������", "����",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Dao d = new Dao();
					Connection conn = d.getConnection();
					selecttcr tcr = new selecttcr();
					int tnum = tcr.selecttnum();
					for(int i = 0; i<tnum; i++)
					{
						TGrades ig = new TGrades();
						ig.setsName(info[i][0].toString());
						ig.setGrades(Integer.parseInt(info[i][3].toString()));
						ig.setGrader("xyg");
						selecttcr itg = new selecttcr();
						itg.insertTgrades(ig);				
					}
				}catch(Exception sqle){
					sqle.printStackTrace();
					JOptionPane.showMessageDialog(null,"�ɼ��ύ����","����",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbtn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WriteExcel();
				new WriteExcelout();
				new WriteExceltout();
				JOptionPane.showMessageDialog(null,"�����ɼ��ɹ�");
			}
		});
		this.setBounds((x / 2) - 375, (y / 2) - 260, 750, 530);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//	// ����
//	public static void main(String[] args) {
//		new TeacherView();
//	}
}