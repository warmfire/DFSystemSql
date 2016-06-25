package com.df.controller;

public class TemMessage {
	
	private static String temMessage = StudentsSend.getStu() + "-";
	
	public void setTemMessage(String temMessage)
	{
		this.temMessage = this.temMessage + temMessage + "-";
	}
	
	public String getTemMessage()
	{
		return temMessage;
	}
}
