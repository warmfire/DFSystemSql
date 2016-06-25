package com.df.controller;

import com.df.model.Students;

public class StudentsSend {
	
	private static String stuInf;
	
	public void setStu(String stuInf)
	{
		this.stuInf = stuInf;
	}
	public static String getStu()
	{
		return stuInf;
	}

}
