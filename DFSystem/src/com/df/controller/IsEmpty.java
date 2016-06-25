package com.df.controller;

import javax.swing.JOptionPane;

public class IsEmpty {
	
	public static void isEmpty(String str)
	{
		if("".equals(str) || str == null){
			JOptionPane.showMessageDialog(null, "²»ÄÜÎª¿Õ");
		}
	}
	
}
