package com.df.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.df.db.StudentsOperation;
import com.df.model.Students;

public class WindowControl implements WindowListener{
	
	private String user;
	
	public WindowControl(String user) {
		// TODO Auto-generated constructor stub
		
		this.user = user;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("´°¿Ú¹Ø±Õ");
		new StudentsOperation().changeLoginOut(user);
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
