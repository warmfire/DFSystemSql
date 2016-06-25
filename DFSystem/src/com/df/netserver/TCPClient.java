package com.df.netserver;

import java.net.*;
import java.io.*;

import com.df.controller.StudentsSend;

public class TCPClient
{
	private String address;
	public TCPClient(String address)
	{
		this.address = address;
		Socket s;
		try {
			s = new Socket(address, 6666);
			OutputStream os = s.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeUTF(new StudentsSend().getStu());
			System.out.println(new StudentsSend().getStu());
			dos.flush();
			dos.close();
			s.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
	}
	
	
//	public static void main(String[] args) {
//		new TCPClient();
//	}
}