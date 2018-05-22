package com.scheduler;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.*;

public class C2Listen extends Thread{
	
	ServerSocket serversocket2;
	Socket socket2;
	
	ObjectInputStream input2;
	ObjectOutputStream output2;
	
	public C2Listen(){
		
		try{
			
			serversocket2 = new ServerSocket(4002);
			start();
			
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
	}
	
	public void run(){
		
		try{
			
			while(true)
			{
			
			
			System.out.println("C2 Started.....");
			socket2 = serversocket2.accept();
			output2 = new ObjectOutputStream(socket2.getOutputStream());
			input2 = new ObjectInputStream(socket2.getInputStream());
			
			Packet pac = (Packet)input2.readObject();
			
		//	System.out.println(pac.getAction());
			
			  
			
			if(pac.getProcessAction().equals("start"))
			{
				
				System.out.println("Checking browser in processer side");
				
				String action = pac.getProcess();
				
				TaskScheduler.jta2.append(action+"\n");
				
				pac.setAction("Ok");
				
				output2.writeObject(pac);
				
			}
			
			
			if(pac.getProcessAction().equals("stop"))
			{
				
				String action = pac.getProcess();
				
				TaskScheduler.jta2.append(action+"\n");
				
				pac.setAction("Ok");
				
				output2.writeObject(pac);
				
			}
			
			
			
			
			}
			
			
		}
		
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	
	
}
	
	

}
