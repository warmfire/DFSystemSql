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
					int num = JOptionPane.showConfirmDialog(null, str + "�������飬�Ƿ�ȷ��������", "��ʾ",
	                        JOptionPane.YES_NO_OPTION);
					
					if(num == 0){
						JOptionPane.showMessageDialog(null, str + ":�Է��Ѽ�����");
						dis.close();  
						s.close();
						ss.close();
						break;
					}else{
						JOptionPane.showMessageDialog(null, str + ":�ܾ��Է�������");
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
	