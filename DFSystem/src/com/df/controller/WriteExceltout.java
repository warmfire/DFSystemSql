package com.df.controller;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import com.df.db.teacherout;

import jxl.*;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class WriteExceltout {
	public WriteExceltout() {
		// 标题
		String title[] = {"姓名", "学号", "组别", "成绩"};
		// 内容
		String[][] context = null;
		ArrayList<ArrayList<String>> listAll = new teacherout().setContent();
		
		// 操作执行
		try {
			// t.xls为要新建的文件名
			WritableWorkbook book = Workbook.createWorkbook(new File(
					"d:\\教师分数表.xls"));
			// 生成名为"组内打分"的工作表
			WritableSheet sheet = book.createSheet("教师打分", 0);

			for(int i = 0; i < 4; i++)
				sheet.addCell(new Label(i, 0, title[i]));//加入title
			
			for(int i = 0;i < listAll.size();i++){
			    for(int j = 0; j < listAll.get(i).size(); j++)
			    {
//			    	System.out.println(listAll.get(i).get(j));
			    	sheet.addCell(new Label(j, i+1, listAll.get(i).get(j)));
			    }
//			    System.out.println("11111111111111111111111111111111111111");
			}

			// 写入数据
			book.write();
			// 关闭文件
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}