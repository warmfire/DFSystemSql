package com.df.controller;

import javax.swing.JButton;
import javax.swing.JLabel;

public class IsRTrue {
	public void isrtrue(JLabel jlTS1,JLabel jlTS2,JLabel jlTS3,
			JLabel jlTS4,JLabel jlTS5,JLabel jlTS6,
			JLabel jlTS7,JButton jbtn1){
		if(!jlTS1.isVisible()&&!jlTS2.isVisible()
				&&!jlTS3.isVisible()&&!jlTS4.isVisible()
				&&!jlTS5.isVisible()&&!jlTS6.isVisible()
				&&!jlTS7.isVisible()){
			jbtn1.setEnabled(true);
		}
	}
}
