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

//教师用户页面，内容有注册与登录
public class TeacherView extends JFrame {

	private int x = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height;
	private Image icon = this.getToolkit().getImage("img/IconImage.png");
	private JPanel jp = new JPanel();
	private JTable jtb1;
	private JTableHeader jth;
	//private JLabel jlb = new JLabel("组内评分表：");
	//private JLabel jlb2 = new JLabel("组间评分表：");
	private JScrollPane scpDemo = new JScrollPane();;
	private JButton jbtn1 = new JButton("清空数据"), 
			//jbtn2 = new JButton("转到组间表"),
			jbtn3 = new JButton("成绩查询"), 
			jbtn4 = new JButton("提交分数"),
			jbtn5 = new JButton("导出数据");
	//private JComboBox comboBox;
	private Object[][] info;

	private String[] names;

	public TeacherView() {

		setTitle("教师控制台");
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
					JOptionPane.showMessageDialog(null, "清空成功!");
					info = stg.selecttcr();
					// 定义表头
					String[] title = { "姓名", "学号", "组别", "分数"};
					jtb1 = new JTable(info, title);
					// 显示表头
					jth = jtb1.getTableHeader();
					// 将JTable加入到带滚动条的面板中
					scpDemo.getViewport().add(jtb1);
				} catch (Exception sqle) {
					JOptionPane.showMessageDialog(null, "数据操作错误", "错误",
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
					// 定义表头
					String[] title = { "姓名", "学号", "组别", "分数"};
					jtb1 = new JTable(info, title);
					// 显示表头
					jth = jtb1.getTableHeader();
					// 将JTable加入到带滚动条的面板中
					scpDemo.getViewport().add(jtb1);
				} catch (Exception sqle) {
					JOptionPane.showMessageDialog(null, "数据操作错误", "错误",
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
					JOptionPane.showMessageDialog(null,"成绩提交错误","错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbtn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WriteExcel();
				new WriteExcelout();
				new WriteExceltout();
				JOptionPane.showMessageDialog(null,"导出成绩成功");
			}
		});
		this.setBounds((x / 2) - 375, (y / 2) - 260, 750, 530);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

//	// 测试
//	public static void main(String[] args) {
//		new TeacherView();
//	}
}