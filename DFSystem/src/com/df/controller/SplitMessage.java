package com.df.controller;

public class SplitMessage {
	
	private static String message;
	private static String[] retMes;
	
	public static String[] SplitMessage(String message)
	{
		SplitMessage.message = message;
		retMes = message.split("-");
		return retMes;
	}
	
}
