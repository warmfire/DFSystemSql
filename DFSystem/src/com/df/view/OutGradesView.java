package com.df.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
import com.df.controller.WindowControl;
import com.df.db.Dao;
import com.df.db.IntoGrades;
import com.df.db.IntoOutGrades;
import com.df.db.selectNum;
import com.df.db.selectOutnum;
import com.df.model.InGrades;
import com.df.model.OutGrades;
import com.df.model.Students;

public class OutGradesView extends JFrame{
	private JScrollPane scpDemo;
	private JTableHeader jth;
	private int x = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int y = Toolkit.getDefaultToolkit().getScreenSize().height;
	private Image icon = this.getToolkit().getImage("img/IconImage.png");
	private JTable jtb1;
	private JPanel jp = new JPanel();
	private JButton jbtn1 = new JButton("提交分数"),
			jbtn2 = new JButton("开始打分"),
			jbtn3 = new JButton("返回");
	private JComboBox jcb;
	private Object[][] info;
	private JLabel jlb = new JLabel("组别：");
	private String stuInf;
	
	public OutGradesView() {
		setTitle("组间打分");
		this.setIconImage(icon);
		this.add(jp);
		
		stuInf = new StudentsSend().getStu();
		
		this.scpDemo = new JScrollPane();
		jp.add(jbtn1);
		jp.add(jbtn2);
		jp.add(jbtn3);
		jp.add(scpDemo);
		
		selectOutnum iog = new selectOutnum();
		int x1 = iog.selectgn();
		System.out.println(x1);
		int q = 1;
		jcb = new JComboBox();
		for(int j = 1; j <= x1; j++){
			jcb.addItem(String.valueOf(j));
		}
		jcb.setEditable(false);
		jp.add(jcb);
		jp.add(jlb);
		jp.setLayout(null);
		scpDemo.setBounds(20,70,550,120);
		jbtn1.setBounds(250, 210, 90, 25);
		jbtn2.setBounds(150, 210, 90, 25);
		jbtn3.setBounds(350, 210, 90, 25);
		jbtn1.setEnabled(false);
		jcb.setBounds(100, 20, 100, 20);
		jlb.setBounds(50,20,50,20);
		jbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					selectOutnum snum = new selectOutnum();
					info = snum.selectOutnum(jcb.getSelectedItem().toString());
					// 定义表头
					String[] title = {"姓名","学号","组别","工作量25","组织20","技术20","美观20","进步15","总分100"};
					jtb1 = new JTable(info,title);
					// 显示表头
					jth = jtb1.getTableHeader();
					// 将JTable加入到带滚动条的面板中
					scpDemo.getViewport().add(jtb1);		
					jbtn1.setEnabled(true);
				}catch(Exception sqle){
					JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dao d = new Dao();
				Connection conn = d.getConnection();
				selectOutnum snum = new selectOutnum();
				int allnum = snum.selectonum(jcb.getSelectedItem().toString());
				int i,x,y;
				boolean tf=true;
				for(x=0;x<allnum;x++){
					for(y=0;y<9;y++){
						if(info[x][y].toString().equals("0")) tf=false;
					}
				}
				if(tf){
					try{
						for(int i1 = 0;i1<allnum; i1++)
						{
							OutGrades og = new OutGrades();
							og.setsName(info[i1][0].toString());
							og.setsId(info[i1][1].toString());
							og.setGroupId(Integer.parseInt(info[i1][2].toString()));
							og.setWorkloads(Integer.parseInt(info[i1][3].toString()));
							og.setOriginal(Integer.parseInt(info[i1][4].toString()));
							og.setTechnology(Integer.parseInt(info[i1][5].toString()));
							og.setBeauty(Integer.parseInt(info[i1][6].toString()));
							og.setExpress(Integer.parseInt(info[i1][7].toString()));
							og.setAllGrades(Integer.parseInt(info[i1][8].toString()));
							og.setGrader(StudentsSend.getStu());
							IntoOutGrades itog = new IntoOutGrades();
							int res = itog.IntoOutGrades(og);						
						}
					}catch(Exception sqle){
						JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"未完成评分","错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				CaptainIndex mv = new CaptainIndex();
				mv.show();
			}
		});
		this.setBounds((x / 2) - 305, (y / 2) - 175, 610, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowControl(stuInf));//实现当点击窗口关闭按钮时改变isLogin状态
	}
	
	
	public static void main(String[] args) {
		new OutGradesView();
	}
	
}
