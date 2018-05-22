package com.scheduler;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.*;

public class C3Listen extends Thread{
	
	
	ServerSocket serversocket3;
	Socket socket3;
	
	
	ObjectOutputStream output3;
	ObjectInputStream input3;
	
	
	
	public C3Listen(){
		
		try{
		
		serversocket3 = new ServerSocket(4003);
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
				
				System.out.println("C3 Started.....");
				socket3 = serversocket3.accept();
				output3 = new ObjectOutputStream(socket3.getOutputStream());
				input3 = new ObjectInputStream(socket3.getInputStream());
				
				Packet pacp=(Packet)input3.readObject();
				System.out.println (pacp.getAction());
				
				
				if(pacp.getProcessAction().equals("start"))
				{
					
					String action = pacp.getProcess();
					
					TaskScheduler.jta3.append(action+"\n");
					
					pacp.setAction("Ok");
					
					output3.writeObject(pacp);
					
				}
				
				
				if(pacp.getProcessAction().equals("stop"))
				{
					
					String action = pacp.getProcess();
					
					TaskScheduler.jta3.append(action+"\n");
					
					pacp.setAction("Ok");
					
					output3.writeObject(pacp);
					
				}
			}
			
	}
		catch(Exception ex)
		{
			
		}

}
	
}
