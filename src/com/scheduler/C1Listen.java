package com.scheduler;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.*;

public class C1Listen extends Thread {
	
	ServerSocket serversocket1;
	Socket socket1;
	
	ObjectInputStream input1;
	ObjectOutputStream output1;
	
	public C1Listen(){
		
		try{
		
		serversocket1 = new ServerSocket(4001);
		start();
		
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
	public void run(){
		try{
			
			
			
			while(true)
			{
				
				System.out.println("C1 Started.....");
				socket1 = serversocket1.accept();
				input1=new ObjectInputStream(socket1.getInputStream());
				output1=new ObjectOutputStream(socket1.getOutputStream());
				
				
				Packet pac=(Packet)input1.readObject();
				System.out.println (pac.getAction());
				
				if(pac.getProcessAction().equals("start"))
				{
					
					String action = pac.getProcess();
					
					TaskScheduler.jta1.append(action+"\n");
					
					pac.setAction("Ok");
					
					output1.writeObject(pac);
					
				}
				
				
				if(pac.getProcessAction().equals("stop"))
				{
					
					String action = pac.getProcess();
					
					TaskScheduler.jta1.append(action+"\n");
					
					pac.setAction("Ok");
					
					output1.writeObject(pac);
					
				}
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
