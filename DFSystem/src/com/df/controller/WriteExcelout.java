package com.df.controller;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import com.df.db.getout;

import jxl.*;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class WriteExcelout {
	public WriteExcelout() {
		// 标题
		String title[] = {"姓名", "学号", "组别", "工作量", "组织", "技术", "美观", "进步", "成绩"};
		// 内容
		String[][] context = null;
		ArrayList<ArrayList<String>> listAll = new getout().setContent();
		
		// 操作执行
		try {
			// t.xls为要新建的文件名
			WritableWorkbook book = Workbook.createWorkbook(new File(
					"d:\\组间成绩表.xls"));
			// 生成名为"组内打分"的工作表
			WritableSheet sheet = book.createSheet("组间打分", 0);

			for(int i = 0; i < 9; i++)
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