package com.df.netserver;

import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;

public class TCPServer
{
	private String str;
	public TCPServer()
	{
		
		try {
			ServerSocket ss = new ServerSocket(6666);
			while (true)
			{
				Socket s = ss.accept(); 
				DataInputStream dis = new DataInputStream(s.getInputStream()); 
				str = dis.readUTF();
				if(str != null){					
					int num = JOptionPane.showConfirmDialog(null, str + "申请入组，是否确认其请求？", "提示",
	                        JOptionPane.YES_NO_OPTION);
					
					if(num == 0){
						JOptionPane.showMessageDialog(null, str + ":对方已加入组");
						dis.close();  
						s.close();
						ss.close();
						break;
					}else{
						JOptionPane.showMessageDialog(null, str + ":拒绝对方加入组");
						dis.close();  
						s.close();
						ss.close();
						break;
					}
				}
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getMessage()
	{
		return str;
	}
	
//	public static void main(String[] args) {
//		new TCPServer();
//	}
}
	