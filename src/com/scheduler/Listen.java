package com.scheduler;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.net.*;
import java.sql.*;
//import java.sql.Date;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Listen extends Thread{
	ServerSocket serversocket;
	Socket socket;
	
	ObjectOutputStream output;
	ObjectInputStream input;
	
	Connection conn, conn1;
	Statement stmt1, stmt2;

	int lisport=1000;
	
	
	static int c1word=0;
	static int c2word=0;
	static int c1video=0;
	static int c2video=0;
	static int c1browser=0;
	static int c2browser=0;
	static int c1bikerace=0;
	static int c2bikerace=0;
	static int c1eclipse=0;
	static int c2eclipse=0;
	
	
	static int c1point=0;
	static int c2point=0;
	static int c1music=0;
	static int c2music=0;
	static int c1calc=0;
	static int c2calc=0;
	static int c1skype=0;
	static int c2skype=0;
	
	static int c1notepad=0;
	static int c2notepad=0;
	static int c1paint=0;
	static int c2paint=0;
	static int c1excel=0;
	static int c2excel=0;
	
	
	static int waitingid=0;
	
	
	
/*	int word = 180233;
	int video = 980366;
	int browser = 290634;
	int racecar = 1035642;
	int eclipse = 450691;
	int skype = 503564;
	int calculator = 450063;
	int music=455968;
	int notepad=135635;
	int paint=350025;
	int excel=189562;  */
	
	
	
	int word = 48;
	int video = 98;
	int browser = 49;
	int racecar = 103;
	int eclipse = 45;
	int skype = 45;
	int calculator = 45;
	int music=55;
	int notepad=25;
	int paint=45;
	int excel=55;
	
	
//	int avaspace;
//	int ocspace;
	
	//String w1 ="c1task1.exe";
	
	
	
	public Listen()
	{
		try
		{
		serversocket = new ServerSocket(4000);
		
		conn = DB.getConnection();
		conn1 = DB.getConnection();
		
		stmt1 = conn.createStatement();
		stmt2 = conn1.createStatement();
		stmt1.executeUpdate("delete from multicore.process");
		stmt2.executeUpdate("delete from multicore.usagememory");
		stmt2.executeUpdate("delete from multicore.waiting");
		stmt2.executeUpdate("delete from multicore.priwaiting");
		
		stmt1.executeUpdate("insert into multicore.usagememory values('core1',105,105,0)");
		
	//	stmt1.executeUpdate("insert into multicore.usagememory values('core1',130,130,0)");
		
	//	stmt1.executeUpdate("insert into multicore.usagememory values('core2',2034321,2034321,0)");
		
		stmt1.executeUpdate("insert into multicore.usagememory values('core2',170,170,0)");
		
		
	//	stmt1.executeUpdate("insert into multicore.usagememory values('core3',1801235,1801235,0)");
		
		
		
		
		stmt1.executeUpdate("insert into multicore.usagememory values('core3',130,130,0)");
		
		new C1Listen();
		new C2Listen();
		new C3Listen();
		
		
		
		
		start();
		
		
		
		Thread ta = new Thread(new ForDate());
		
		ta.start();
		
		
		
		
		
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"ERROR IN TASK SCHEDULER LISTEN : "+ e);
		}
		
	}
	
	public void run()
	{
		try
		{
			
			while(true)
			{
				
				System.out.println("Task schedular start listening..");
				System.out.println("I am Main Thread..");
				
				
				socket = serversocket.accept();
				input=new ObjectInputStream(socket.getInputStream());
				output=new ObjectOutputStream(socket.getOutputStream());
				
				Packet pac=(Packet)input.readObject();
				System.out.println ("Checking "+pac.getAction());
				
	////////////////////////////////////////////////////// C1_Task1  ///////////////////////////////////////////////////			
				if(pac.getAction().equals("word"))
				
				{
					
					String client = pac.getClient();
					String task = pac.getAction();
					int id = pac.getProcessId();
					
					
					TaskScheduler.jta.append(client+" request task to C1_Task1\n");
					TaskScheduler.jta.append("request is waiting for scheduling\n");
				
					ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
					
					if(rs.next()==true)
					{
						
						int avaspace =rs.getInt("freespace");
						int ocspace = rs.getInt("occupyspace"); 
						
						if(avaspace>word)
							
						{
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4002);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							 pacfor.setProcessAction("start");
							 pacfor.setProcess("C1_Task1 is executing");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C1_Task1 is running....\n");
									
									
									c1word++;
									String spro="p2";
									
									
									stmt2.executeUpdate("insert into multicore.process values('c1task1.exe','"+client+"',"+word+","+c1word+",'"+spro+"')");
									
								//	p2cnword++;
									
								//	p2alword.add(p2cnword);
									
							//		stmt2.executeUpdate("insert into multicore.process values('c1task1.exe','"+client+"',"+word+","+p2alword.get(p2cnword-1)+")");
									
									
									int updatespace = avaspace - word;
									int upocc = ocspace+word;
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core2'");							
									}
								
								
								
						}
				
						
						else
						{
							ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
							
							
							
						if(rs1.next()==true)
						{
							
								int avaspace1 =rs1.getInt("freespace");
								int ocspace1 = rs1.getInt("occupyspace"); 
								
							if(avaspace1>word)
							{
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								// pacfor.setAction("word");
								 pacfor.setProcessAction("start");
								 pacfor.setProcess("C1_Task1 is executing");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										TaskScheduler.jta.append("C1_Task1 is running....");
										
										
										c2word++;
										String spro="p1";
										
										stmt2.executeUpdate("insert into multicore.process values('c1task1.exe','"+client+"',"+word+","+c2word+",'"+spro+"')");
										
								//		p1cnword++;
								//		p1alword.add(p1cnword);
										
								//		stmt2.executeUpdate("insert into multicore.process values('c1task1.exe','"+client+"',"+word+","+p1alword.get(p1cnword-1)+")");
										
										
										int updatespace1 = avaspace1 - word;
										int upocc1 = ocspace1+word;
										stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");		
										
									}
								
								
								
							  }
							
							else{
								
								waitingid++;
							
								stmt2.executeUpdate("insert into multicore.waiting values('c1task1.exe','"+waitingid+"','waiting')");
								TaskScheduler.jta.append("C1_Task1 is waiting for scheduling....");
								
								
							}
							
							
						}
							
					}		
					
					
					
					
				}
				
				
				
				}
				
				
				
		//////////////////////////////////////////////// C1_Task2 start ////////////////////////////////////////////////////		
				
				
				if(pac.getAction().equals("video"))
				{
					
					
					String client = pac.getClient();
					String task = pac.getAction();
					
					
					
					TaskScheduler.jta.append(client+" request task to C1_Task2\n");
					TaskScheduler.jta.append("request is waiting for scheduling\n");
					
					
					ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
					
					if(rs.next()==true)
					{
						
						int avaspace = rs.getInt("freespace");
						int ocspace = rs.getInt("occupyspace"); 
						
						if(avaspace>video)
						{
							
							
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4001);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							 
							 pacfor.setProcessAction("start");
							 pacfor.setProcess("C1_Task2 is executing");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C1_Task2 is running....\n");
									
									c1video++;
									String spro="p1";
									
									stmt2.executeUpdate("insert into multicore.process values('c1Task2.exe','"+client+"',"+video+","+c1video+",'"+spro+"')");
									
								//	p1cnvideo++;
								//	p1alvideo.add(p1cnvideo);
									
							//		stmt2.executeUpdate("insert into multicore.process values('c1Task2.exe','"+client+"',"+video+","+p1alvideo.get(p1cnvideo-1)+")");
									
									
									int updatespace = avaspace - video;
									int upocc = ocspace+video;
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core1'");	
									
									
									
									}
							 
						}
						
						
						
						else
						{
							ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
							
							
							
						if(rs1.next()==true)
						{
							
								int avaspace1 =rs1.getInt("freespace");
								int ocspace1 = rs1.getInt("occupyspace"); 
							if(avaspace1>video)
							{
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4003);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								// pacfor.setAction("word");
								 pacfor.setProcessAction("start");
								 pacfor.setProcess("C1_Task2 is executing");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										TaskScheduler.jta.append("C1_Task2 is running....\n");
										
										
										c2video++;
										
										String spro="p3";
										
										stmt2.executeUpdate("insert into multicore.process values('c1Task2.exe','"+client+"',"+video+","+c2video+",'"+spro+"')");
										
										
									//	p3cnvideo++;
									//	p3alvideo.add(p3cnvideo);
										
									//	stmt2.executeUpdate("insert into multicore.process values('c1Task2.exe','"+client+"',"+video+","+p3alvideo.get(p3cnvideo-1)+")");
										
										
										
									//	stmt2.executeUpdate("insert into multicore.process values('c1Task2.exe','"+client+"',"+video+")");
										
										
										int updatespace1 = avaspace1 - video;
										int upocc1 = ocspace1+video;
										
										stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core3'");		
										
									}
								
								
								
							  }
							
							else{
								
								waitingid++;
								
								stmt2.executeUpdate("insert into multicore.waiting values('c1Task2.exe','"+waitingid+"','waiting')");
								TaskScheduler.jta.append("C1_Task2 is waiting for scheduling....");
								
							}
							
							
						}
							
					}
						
						
						
						
						
						
						
						
							
							
						}
					
				}  
				
				
				
				
				
	////////////////////////////////////////////////////////C1_Task3 Start/////////////////////////////////////////////////////////////
				
				
				
				if(pac.getAction().equals("game"))
				{
					
					
					String client = pac.getClient();
					String task = pac.getAction();
					
					
					
					TaskScheduler.jta.append(client+" request task to C1_Task3\n");
					TaskScheduler.jta.append("request is waiting for scheduling\n");
					
					
					ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
					
					if(rs.next()==true)
					{
						
						int avaspace =rs.getInt("freespace");
						int ocspace = rs.getInt("occupyspace");
						
						if(avaspace>racecar)
						{
							
							
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4003);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							 
							 pacfor.setProcessAction("start");
							 pacfor.setProcess("C1_Task3 is executing");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C1_Task3 is running....\n");
									
									
									c1bikerace++;
									String spro="p3";
									
									
									stmt2.executeUpdate("insert into multicore.process values('c1Task3.exe','"+client+"',"+racecar+","+c1bikerace+",'"+spro+"')");
									
									
								//	p3cngame++;
								//	p3algame.add(p3cngame);
									
									
								//	stmt2.executeUpdate("insert into multicore.process values('game.exe','"+client+"',"+racecar+","+p3algame.get(p3cngame-1)+")");
									
									
									int updatespace = avaspace - racecar;
									int upocc = ocspace+racecar;
									
									
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core3'");	
									
									
									
									}
							 
						}
						
						
						
						else
						{
							ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
							
							
							
						if(rs1.next()==true)
						{
							
								int avaspace1 =rs1.getInt("freespace");
								int ocspace1 = rs1.getInt("occupyspace");
								
							if(avaspace1>racecar)
							{
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								// pacfor.setAction("word");
								 pacfor.setProcessAction("start");
								 pacfor.setProcess("C1_Task3 is executing");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										TaskScheduler.jta.append("C1_Task3 is running....\n");
										
										
										c2bikerace++;
										
										String spro="p1";
										
										
										stmt2.executeUpdate("insert into multicore.process values('c1Task3.exe','"+client+"',"+racecar+","+c2bikerace+",'"+spro+"')");
										
									//	p1cngame++;
								//		p1algame.add(p1cngame);
										
										
									//	stmt2.executeUpdate("insert into multicore.process values('game.exe','"+client+"',"+racecar+","+p1algame.get(p1cngame-1)+")");
										
										
										int updatespace1 = avaspace1 - racecar;
										int upocc1 = ocspace1+racecar;
										
										
										stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");		
										
									}
								
								
								
							  }
							
							else{
								
								waitingid++;
								
								stmt2.executeUpdate("insert into multicore.waiting values('c1Task3.exe','"+waitingid+"','waiting')");
								TaskScheduler.jta.append("C1_Task3 is waiting for scheduling....");
								
							}
							
							
						}
							
					}
						
						
						
						
						
						
						
						
							
							
						}
					
				}  
				
				
				
				
				
				
	///////////////////////////////////////////////////////////////C1_Task4 start //////////////////////////////////////////////////////////////	
				
				
				

				if(pac.getAction().equals("browser"))
				{
					
					
					String client = pac.getClient();
					String task = pac.getAction();
					
					
					
					TaskScheduler.jta.append(client+" request task to C1_Task4\n");
					TaskScheduler.jta.append("request is waiting for scheduling\n");
					
					
					ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
					
					if(rs.next()==true)
					{
						
						int avaspace =rs.getInt("freespace");
						int ocspace = rs.getInt("occupyspace");
						
						if(avaspace>browser)
						{
							
							
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc1 = new Socket(loc,4002);
							 ObjectOutputStream dowoutput1 = new ObjectOutputStream(soc1.getOutputStream());
							 
						
							 
							 Packet pacfor1 = new Packet();
							 
							 pacfor1.setProcessAction("start");
							 pacfor1.setProcess("C1_Task4 is executing");
							  dowoutput1.writeObject(pacfor1);
							  
						
							  
							  ObjectInputStream in1=new ObjectInputStream(soc1.getInputStream());
							  
						//	  System.out.println("Checking browser with socket");
								
							  Packet rpacfor=(Packet)in1.readObject();
								
								 
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C1_Task4 is running....\n");
									
								
									
									c1browser++;
									
									String spro="p2";
									
									stmt2.executeUpdate("insert into multicore.process values('c1Task4.exe','"+client+"',"+browser+","+c1browser+",'"+spro+"')");
									
									
									
								//	p2cnbrowser++;
								//	p2albrowser.add(p2cnbrowser);
									
									
							//		stmt2.executeUpdate("insert into multicore.process values('c1Task4.exe','"+client+"',"+racecar+","+p2albrowser.get(p2cnbrowser-1)+")");
									
									
									
									
									int updatespace = avaspace-browser;
									int upocc = ocspace+browser;
									
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core2'");	
									
									
									
									}
							 
						}
						
						
						
						else
						{
							ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
							
							
							
						if(rs1.next()==true)
						{
							
								int avaspace1 =rs1.getInt("freespace");
								int ocspace1 = rs1.getInt("occupyspace");
								
							if(avaspace1>browser)
							{
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								// pacfor.setAction("word");
								 pacfor.setProcessAction("start");
								 pacfor.setProcess("C1_Task4 is executing");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										TaskScheduler.jta.append("C1_Task4 is running....\n");
										
										
										c2browser++;
										String spro="p1";
										
										stmt2.executeUpdate("insert into multicore.process values('c1Task4.exe','"+client+"',"+browser+","+c2browser+",'"+spro+"')");
										
									//	stmt2.executeUpdate("insert into multicore.process values('c1Task4.exe','"+client+"',"+browser+","+c1browser+")");
										
									//	p1cnbrowser++;
									//	p1albrowser.add(p1cnbrowser);
										
										
									//	stmt2.executeUpdate("insert into multicore.process values('c1Task4.exe','"+client+"',"+racecar+","+p2albrowser.get(p2cnbrowser-1)+")");
										
									//	stmt2.executeUpdate("insert into multicore.process values('c1Task4.exe','"+client+"',"+browser+")");
										
										
										int updatespace1 = avaspace1 - browser;
										int upocc1 = ocspace1+browser;
										stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");		
										
									}
								
								
								
							  }
							
							else{
								
								waitingid++;
								
								stmt2.executeUpdate("insert into multicore.waiting values('c1Task4.exe','"+waitingid+"','waiting')");
								TaskScheduler.jta.append("C1_Task4 is waiting for scheduling....");
								
							}
							
							
						}
							
					}
						
						
						
						
						
						
						
						
							
							
						}
					
				}  
				
	/////////////////////////////////////////////////////////////////C1_Task5 ////////////////////////////////////////////////////////////			
				
	
				
				
				if(pac.getAction().equals("eclipse"))
				{
					
					
					String client = pac.getClient();
					String task = pac.getAction();
					
					
					
					TaskScheduler.jta.append(client+" request task to C1_Task5\n");
					TaskScheduler.jta.append("request is waiting for scheduling\n");
					
					
					ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
					
					if(rs.next()==true)
					{
						
						int avaspace =rs.getInt("freespace");
						int ocspace = rs.getInt("occupyspace");
						
						if(avaspace>eclipse)
						{
							
							
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc1 = new Socket(loc,4002);
							 ObjectOutputStream dowoutput1 = new ObjectOutputStream(soc1.getOutputStream());
							 
						
							 
							 Packet pacfor1 = new Packet();
							 
							 pacfor1.setProcessAction("start");
							 pacfor1.setProcess("C1_Task5 is executing");
							  dowoutput1.writeObject(pacfor1);
							  
						
							  
							  ObjectInputStream in1=new ObjectInputStream(soc1.getInputStream());
							  
							//  System.out.println("Checking browser with socket");
								
							  Packet rpacfor=(Packet)in1.readObject();
								
								 
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C1_Task5 is running....\n");
									
									
									c1eclipse++;
									
									String spro="p2";
									
									stmt2.executeUpdate("insert into multicore.process values('c1Task5.exe','"+client+"',"+eclipse+","+c1eclipse+",'"+spro+"')");
									
									
								//	stmt2.executeUpdate("insert into multicore.process values('c1Task5.exe','"+client+"',"+eclipse+")");
									
									
									int updatespace = avaspace-eclipse;
									int upocc = ocspace+eclipse;
									
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core2'");	
									
									
									
									}
							 
						}
						
						
						
						else
						{
							ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
							
							
							
						if(rs1.next()==true)
						{
							
								int avaspace1 =rs1.getInt("freespace");
								int ocspace1 = rs1.getInt("occupyspace");
								
							if(avaspace1>eclipse)
							{
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								// pacfor.setAction("word");
								 pacfor.setProcessAction("start");
								 pacfor.setProcess("C1_Task5 is executing");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										TaskScheduler.jta.append("C1_Task5 is running....\n");
										
										
										c2eclipse++;
										
										String spro="p1";
										
										stmt2.executeUpdate("insert into multicore.process values('c1Task5.exe','"+client+"',"+eclipse+","+c2eclipse+",'"+spro+"')");
										
										
										
										
									//	stmt2.executeUpdate("insert into multicore.process values('c1Task5.exe','"+client+"',"+eclipse+")");
										
										
										int updatespace1 = avaspace1 - eclipse;
										int upocc1 = ocspace1+eclipse;
										stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");		
										
									}
								
								
								
							  }
							
							else{
								
								waitingid++;
								
								stmt2.executeUpdate("insert into multicore.waiting values('c1Task5.exe','"+waitingid+"','waiting')");
								TaskScheduler.jta.append("C1_Task5 is waiting for scheduling....");
								
							}
							
							
						}
							
					}
						
						
						
						
						
						
						
						
							
							
						}
					
				}  
				
				
				
				
	////////////////////////////////////////////////////////////////////////C2_Task1////////////////////////////////////////////////////			
				
				
				if(pac.getAction().equals("point2"))
					
				{
					
					String client = pac.getClient();
					String task = pac.getAction();
					
					
					TaskScheduler.jta.append(client+" request task to C2_Task1\n");
					TaskScheduler.jta.append("request is waiting for scheduling\n");
				
					ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
					
					if(rs.next()==true)
					{
						
						int avaspace =rs.getInt("freespace");
						int ocspace = rs.getInt("occupyspace"); 
						
						if(avaspace>word)
							
						{
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4002);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							 pacfor.setProcessAction("start");
							 pacfor.setProcess("C2_Task1 is executing");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C2_Task1 is running....\n");
									
									
									c1point++;
									String spro="p2";
									
									
									stmt2.executeUpdate("insert into multicore.process values('c2Task1.exe','"+client+"',"+word+","+c1point+",'"+spro+"')");
									
									
									int updatespace = avaspace - word;
									int upocc = ocspace+word;
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core2'");							
									}
								
								
								
						}
				
						
						else
						{
							ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
							
							
							
						if(rs1.next()==true)
						{
							
								int avaspace1 =rs1.getInt("freespace");
								int ocspace1 = rs1.getInt("occupyspace"); 
								
							if(avaspace1>word)
							{
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								// pacfor.setAction("word");
								 pacfor.setProcessAction("start");
								 pacfor.setProcess("C2_Task1 is executing");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										TaskScheduler.jta.append("C2_Task1 is running....");
										
										
										
										c2point++;
										String spro="p1";
										
										
										stmt2.executeUpdate("insert into multicore.process values('c2Task1.exe','"+client+"',"+word+","+c2point+",'"+spro+"')");
										
									//	stmt2.executeUpdate("insert into multicore.process values('c2Task1.exe','"+client+"',"+word+")");
										
										
										int updatespace1 = avaspace1 - word;
										int upocc1 = ocspace1+word;
										stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");		
										
									}
								
								
								
							  }
							
							else{
								
								waitingid++;
								
							
								stmt2.executeUpdate("insert into multicore.waiting values('c2Task1.exe','"+waitingid+"','waiting')");
								TaskScheduler.jta.append("C2_Task1 is waiting for scheduling....");
								
								
							}
							
							
						}
							
					}		
					
					
					
					
				}
				
				
				
				}
				
				
				
				
				
				
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C2_Task2 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5//	
				
				if(pac.getAction().equals("music2"))
					
				{
					
					String client = pac.getClient();
					String task = pac.getAction();
					
					
					TaskScheduler.jta.append(client+" request task to C2_Task2\n");
					TaskScheduler.jta.append("request is waiting for scheduling\n");
				
					ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
					
					if(rs.next()==true)
					{
						
						int avaspace =rs.getInt("freespace");
						int ocspace = rs.getInt("occupyspace"); 
						
						if(avaspace>music)
							
						{
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4003);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							 pacfor.setProcessAction("start");
							 pacfor.setProcess("C2_Task2 is executing");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C2_Task2 is running....\n");
									
									
									
									c1music++;
									String spro="p3";
									
									
									stmt2.executeUpdate("insert into multicore.process values('c2Task2.exe','"+client+"',"+music+","+c1music+",'"+spro+"')");
									
									
									
								//	stmt2.executeUpdate("insert into multicore.process values('c2Task2.exe','"+client+"',"+music+")");
									
									
									int updatespace = avaspace - music;
									int upocc = ocspace+music;
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core3'");							
									}
								
								
								
						}
				
						
						else
						{
							ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
							
							
							
						if(rs1.next()==true)
						{
							
								int avaspace1 =rs1.getInt("freespace");
								int ocspace1 = rs1.getInt("occupyspace"); 
								
							if(avaspace1>music)
							{
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								// pacfor.setAction("word");
								 pacfor.setProcessAction("start");
								 pacfor.setProcess("C2_Task2 is executing");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										TaskScheduler.jta.append("C2_Task2 is running....");
										
										c2music++;
										String spro="p1";
										
										
										stmt2.executeUpdate("insert into multicore.process values('c2Task2.exe','"+client+"',"+music+","+c2music+",'"+spro+"')");
										
								//		stmt2.executeUpdate("insert into multicore.process values('c2Task2.exe','"+client+"',"+music+")");
										
										
										int updatespace1 = avaspace1 - music;
										int upocc1 = ocspace1+music;
										stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");		
										
									}
								
								
								
							  }
							
							else{
								
								waitingid++;
							
								stmt2.executeUpdate("insert into multicore.waiting values('c2Task2.exe','"+waitingid+"','waiting')");
								TaskScheduler.jta.append("C2_Task2 is waiting for scheduling....");
								
								
							}
							
							
						}
							
					}		
					
					
					
					
				}
				
				
				
				}
				
				
				
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C2_Task3 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//	
				
				
				if(pac.getAction().equals("calc2"))
					
				{
					
					String client = pac.getClient();
					String task = pac.getAction();
					
					
					TaskScheduler.jta.append(client+" request task to C2_Task3\n");
					TaskScheduler.jta.append("request is waiting for scheduling\n");
				
					ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
					
					if(rs.next()==true)
					{
						
						int avaspace =rs.getInt("freespace");
						int ocspace = rs.getInt("occupyspace"); 
						
						if(avaspace>calculator)
							
						{
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4001);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							 pacfor.setProcessAction("start");
							 pacfor.setProcess("C2_Task3 is executing");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C2_Task3 is running....\n");
									
									
									c1calc++;
									String spro="p1";
									
									
									stmt2.executeUpdate("insert into multicore.process values('c2Task3.exe','"+client+"',"+calculator+","+c1calc+",'"+spro+"')");
									
								//	stmt2.executeUpdate("insert into multicore.process values('c2Task3.exe','"+client+"',"+calculator+")");
									
									
									int updatespace = avaspace - calculator;
									int upocc = ocspace+calculator;
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core1'");							
									}
								
								
								
						}
				
						
						else
						{
							ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
							
							
							
						if(rs1.next()==true)
						{
							
								int avaspace1 =rs1.getInt("freespace");
								int ocspace1 = rs1.getInt("occupyspace"); 
								
							if(avaspace1>calculator)
							{
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4002);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								// pacfor.setAction("word");
								 pacfor.setProcessAction("start");
								 pacfor.setProcess("C2_Task3 is executing");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										TaskScheduler.jta.append("C2_Task3 is running....");
										
										c2calc++;
										String spro="p2";
										
										
										stmt2.executeUpdate("insert into multicore.process values('c2Task3.exe','"+client+"',"+calculator+","+c2calc+",'"+spro+"')");
										
									//	stmt2.executeUpdate("insert into multicore.process values('c2Task3.exe','"+client+"',"+calculator+")");
										
										
										int updatespace1 = avaspace1 - calculator;
										int upocc1 = ocspace1+calculator;
										stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core2'");		
										
									}
								
								
								
							  }
							
							else{
								
								
								waitingid++;
							
								stmt2.executeUpdate("insert into multicore.waiting values('c2Task3.exe','"+waitingid+"','waiting')");
								TaskScheduler.jta.append("C2_Task3 is waiting for scheduling....");
								
								
							}
							
							
						}
							
					}		
					
					
					
					
				}
				
				
				
				}
				
				
				
				
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C2_Task4  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//	
				
				
				if(pac.getAction().equals("skype2"))
					
				{
					
					String client = pac.getClient();
					String task = pac.getAction();
					
					
					TaskScheduler.jta.append(client+" request task to C2_Task4\n");
					TaskScheduler.jta.append("request is waiting for scheduling\n");
				
					ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
					
					if(rs.next()==true)
					{
						
						int avaspace =rs.getInt("freespace");
						int ocspace = rs.getInt("occupyspace"); 
						
						if(avaspace>skype)
							
						{
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4003);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							 pacfor.setProcessAction("start");
							 pacfor.setProcess("C2_Task4 is executing");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C2_Task4 is running....\n");
									
									c1skype++;
									String spro="p3";
									
									
									stmt2.executeUpdate("insert into multicore.process values('c2Task4.exe','"+client+"',"+skype+","+c1skype+",'"+spro+"')");
									
									
								//	stmt2.executeUpdate("insert into multicore.process values('c2Task4.exe','"+client+"',"+skype+")");
									
									
									int updatespace = avaspace - skype;
									int upocc = ocspace+skype;
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core3'");							
									}
								
								
								
						}
				
						
						else
						{
							ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
							
							
							
						if(rs1.next()==true)
						{
							
								int avaspace1 =rs1.getInt("freespace");
								int ocspace1 = rs1.getInt("occupyspace"); 
								
							if(avaspace1>skype)
							{
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								// pacfor.setAction("word");
								 pacfor.setProcessAction("start");
								 pacfor.setProcess("C2_Task4 is executing");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										TaskScheduler.jta.append("C2_Task4 is running....");
										
										
										c2skype++;
										String spro="p1";
										
										
										stmt2.executeUpdate("insert into multicore.process values('c2Task4.exe','"+client+"',"+skype+","+c2skype+",'"+spro+"')");
										
										
									//	stmt2.executeUpdate("insert into multicore.process values('c2Task4.exe','"+client+"',"+skype+")");
										
										
										int updatespace1 = avaspace1 - skype;
										int upocc1 = ocspace1+skype;
										stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");		
										
									}
								
								
								
							  }
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							else{
								
								ResultSet rrs = stmt1.executeQuery("select * from process where processer='p2' and id=0");
								
								if(rrs.next()==true)
								{
									JOptionPane.showMessageDialog(null," Request Again ");
									
									TaskScheduler.jta.append(" Request Again  \n");
								}
								
								
								else
								{
								
								
								ResultSet prs = stmt1.executeQuery("select * from process where processer='p2'");
								
								if(prs.next()==true)
								{
									String getname = prs.getString("name");
									String getuser = prs.getString("user");
									int getmemory = prs.getInt("memory");
									int getid = prs.getInt("id");
									String getprocesser = prs.getString("processer");
									
									
								//	if(!getname.equals("c2Task4.exe"))
										
									
								//	{
									
									stmt2.executeUpdate("insert into priwaiting values('"+getname+"','"+getuser+"',"+getmemory+","+getid+",'"+getprocesser+"')");
									
									
									stmt1.executeUpdate("delete from process where name='"+getname+"' and id="+getid+" and processer='"+getprocesser+"'");  
									
									ResultSet prss = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
									
									if(prss.next()==true)
									{
										
										int occ = prss.getInt("occupyspace");
										int upocc = occ-getmemory;
										int free = prss.getInt("freespace");
										int upfree = free+getmemory;
										
										stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core2'");
										
										
										
										
										InetAddress loc = InetAddress.getLocalHost();
										Socket soc = new Socket(loc,4002);
										 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
										 
										 Packet pacfor = new Packet();
										 pacfor.setProcessAction("stop");
										 pacfor.setProcess(getuser+" "+getname+" is stoppedddd");
										  dowoutput.writeObject(pacfor);
										  
										  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
											Packet rpacfor=(Packet) in.readObject();
											
											if(rpacfor.getAction().equals("Ok"))
											{
												
												TaskScheduler.jta.append(getuser+" "+getname+" is stoppedddd \n");
											}
										
										
									}
									
									
									
									System.out.println(" This is running");
									
									ResultSet rs2 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
									
									if(rs2.next()==true)
									{
										
										int pavaspace =rs2.getInt("freespace");
										int pocspace = rs2.getInt("occupyspace"); 
										
										if(pavaspace>skype)
											
										{
											
											System.out.println(" I am entered for priority ");
											
											InetAddress loc = InetAddress.getLocalHost();
											Socket soc = new Socket(loc,4002);
											 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
											 
											 Packet pacfor = new Packet();
											 pacfor.setProcessAction("start");
											 pacfor.setProcess("C2_Task4 is executing");
											  dowoutput.writeObject(pacfor);
											  
											  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
												Packet rpacfor=(Packet) in.readObject();
												
												if(rpacfor.getAction().equals("Ok"))
												{
													TaskScheduler.jta.append("C2_Task4 priority process is running....\n");
													
												//	c1skype++;
													String spro="p2";
													int tempid = 0;
													
													
													stmt2.executeUpdate("insert into multicore.process values('c2Task4.exe','"+client+"',"+skype+","+tempid+",'"+spro+"')");
													
													
												//	stmt2.executeUpdate("insert into multicore.process values('c2Task4.exe','"+client+"',"+skype+")");
													
													
													int updatespace = pavaspace - skype;
													int upocc = pocspace+skype;
													
													stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core2'");							
													}
												
												
												
										}
										
								//	}
									
									
									
									
									
									}
									
									else
									{
										
										
										TaskScheduler.jta.append("Wait and Request again for skype....\n");
										
									}
									
								}
								
							}
								
							//	System.out.println(" I did not enter into priority ");
								
							//	waitingid++;
								
							
							//	stmt2.executeUpdate("insert into multicore.waiting values('c2Task4.exe','"+waitingid+"','waiting')");
							//	TaskScheduler.jta.append("C2 c2Task4.exe is waiting for scheduling....");
								
								
							}
							
							
						}
							
					}		
					
					
					
					
				}
				
				
				
				}
				
				
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C3_Task1 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//	
				
				if(pac.getAction().equals("notepad3"))
					
				{
					
					String client = pac.getClient();
					String task = pac.getAction();
					
					
					TaskScheduler.jta.append(client+" request task to C3_Task1\n");
					TaskScheduler.jta.append("request is waiting for scheduling\n");
				
					ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
					
					if(rs.next()==true)
					{
						
						int avaspace =rs.getInt("freespace");
						int ocspace = rs.getInt("occupyspace"); 
						
						if(avaspace>notepad)
							
						{
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4001);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							 pacfor.setProcessAction("start");
							 pacfor.setProcess("C3_Task1 is executing");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C3_Task1 is running....\n");
									
									
									c1notepad++;
									String spro="p1";
									
									
									stmt2.executeUpdate("insert into multicore.process values('c3Task1.exe','"+client+"',"+notepad+","+c1notepad+",'"+spro+"')");
									
									
									
								//	stmt2.executeUpdate("insert into multicore.process values('c3Task1.exe','"+client+"',"+notepad+")");
									
									
									int updatespace = avaspace - notepad;
									int upocc = ocspace+notepad;
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core1'");							
									}
								
								
								
						}
				
						
						else
						{
							ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
							
							
							
						if(rs1.next()==true)
						{
							
								int avaspace1 =rs1.getInt("freespace");
								int ocspace1 = rs1.getInt("occupyspace"); 
								
							if(avaspace1>notepad)
							{
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4003);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								// pacfor.setAction("word");
								 pacfor.setProcessAction("start");
								 pacfor.setProcess("C3_Task1 is executing");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										TaskScheduler.jta.append("C3_Task1 is running....");
										
										c2notepad++;
										String spro="p3";
										
										
										stmt2.executeUpdate("insert into multicore.process values('c3Task1.exe','"+client+"',"+notepad+","+c2notepad+",'"+spro+"')");
										
										
									//	stmt2.executeUpdate("insert into multicore.process values('c3Task1.exe','"+client+"',"+notepad+")");
										
										
										int updatespace1 = avaspace1 - notepad;
										int upocc1 = ocspace1+notepad;
										stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core3'");		
										
									}
								
								
								
							  }
							
							else{
								
								waitingid++;
								
							
								stmt2.executeUpdate("insert into multicore.waiting values('c3Task1.exe','"+waitingid+"','waiting')");
								TaskScheduler.jta.append("C3_Task1 is waiting for scheduling....");
								
								
							}
							
							
						}
							
					}		
					
					
					
					
				}
				
				
				
				}
				
				
				
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C3_Task2  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//	
				
				if(pac.getAction().equals("paint3"))
					
				{
					
					String client = pac.getClient();
					String task = pac.getAction();
					
					
					TaskScheduler.jta.append(client+" request task to C3_Task2 \n");
					TaskScheduler.jta.append("request is waiting for scheduling \n");
				
					ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
					
					if(rs.next()==true)
					{
						
						int avaspace =rs.getInt("freespace");
						int ocspace = rs.getInt("occupyspace"); 
						
						if(avaspace>paint)
							
						{
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4003);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							 pacfor.setProcessAction("start");
							 pacfor.setProcess("C3_Task2 is executing");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C3_Task2 is running....\n");
									
									
									c1paint++;
									String spro="p3";
									
									
									stmt2.executeUpdate("insert into multicore.process values('c3Task2.exe','"+client+"',"+paint+","+c1paint+",'"+spro+"')");
									
									
								//	stmt2.executeUpdate("insert into multicore.process values('c3Task2.exe','"+client+"',"+paint+")");
									
									
									int updatespace = avaspace - paint;
									int upocc = ocspace+paint;
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core3'");							
									}
								
								
								
						}
				
						
						else
						{
							ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
							
							
							
						if(rs1.next()==true)
						{
							
								int avaspace1 =rs1.getInt("freespace");
								int ocspace1 = rs1.getInt("occupyspace"); 
								
							if(avaspace1>paint)
							{
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								// pacfor.setAction("word");
								 pacfor.setProcessAction("start");
								 pacfor.setProcess("C3_Task2 is executing");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										TaskScheduler.jta.append("C3_Task2 is running....");
										
										
										
										c2paint++;
										String spro="p1";
										
										
										stmt2.executeUpdate("insert into multicore.process values('c3Task2.exe','"+client+"',"+paint+","+c2paint+",'"+spro+"')");
										
									//	stmt2.executeUpdate("insert into multicore.process values('c3Task2.exe','"+client+"',"+paint+")");
										
										
										int updatespace1 = avaspace1 - paint;
										int upocc1 = ocspace1+paint;
										stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");		
										
									}
								
								
								
							  }
							
							else{
								
								waitingid++;
								
							
								stmt2.executeUpdate("insert into multicore.waiting values('c3Task2.exe','"+waitingid+"','waiting')");
								TaskScheduler.jta.append("C3_Task2 is waiting for scheduling....");
								
								
							}
							
							
						}
							
					}		
					
					
					
					
				}
				
				
				
				}
				
				
				
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C3_Task3 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
				
				
				
				if(pac.getAction().equals("excel3"))
					
				{
					
					String client = pac.getClient();
					String task = pac.getAction();
					
					
					TaskScheduler.jta.append(client+" request task to C3_Task3 \n");
					TaskScheduler.jta.append("request is waiting for scheduling \n");
					
					
					
					
					
				
					ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
					
					
					
					
					if(rs.next()==true)
					{
						
						int avaspace =rs.getInt("freespace");
						int ocspace = rs.getInt("occupyspace"); 
						
						if(avaspace>excel)
							
						{
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4002);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							 pacfor.setProcessAction("start");
							 pacfor.setProcess("C3_Task3 is executing");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C3_Task3 is running....\n");
									
									c1excel++;
									String spro="p2";
									
									
									
									stmt2.executeUpdate("insert into multicore.process values('c3Task3.exe','"+client+"',"+excel+","+c1excel+",'"+spro+"')");
									
									
									
									
									
								//	stmt2.executeUpdate("insert into multicore.process values('c3Task3.exe','"+client+"',"+excel+")");
									
									
									int updatespace = avaspace - excel;
									int upocc = ocspace+excel;
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core2'");
									
									
									}
								
								
								
						}
				
						
						else
						{
							ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
							
							
							
						if(rs1.next()==true)
						{
							
								int avaspace1 =rs1.getInt("freespace");
								int ocspace1 = rs1.getInt("occupyspace"); 
								
							if(avaspace1>excel)
							{
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								// pacfor.setAction("word");
								 pacfor.setProcessAction("start");
								 pacfor.setProcess("C3_Task3 is executing");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										TaskScheduler.jta.append("C3_Task3 is running....");
										
										
										c2excel++;
										String spro="p1";
										
										
										stmt2.executeUpdate("insert into multicore.process values('c3Task3.exe','"+client+"',"+excel+","+c2excel+",'"+spro+"')");
										
									//	stmt2.executeUpdate("insert into multicore.process values('c3Task2.exe','"+client+"',"+paint+")");
										
										
										int updatespace1 = avaspace1 - excel;
										int upocc1 = ocspace1+excel;
										stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");		
										
									}
								
								
								
							  }
							
							else{
								
								waitingid++;
								
							
								stmt2.executeUpdate("insert into multicore.waiting values('c3Task3.exe','"+waitingid+"','waiting')");
								TaskScheduler.jta.append("C3_Task3 is waiting for scheduling....");
								
								
							}
							
							
						}
							
					}		
					
					
					
					
				}
				
				
				
				}
				
				
				
				
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% All Stop Process  C1_Task1  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//	
				
				
				if(pac.getAction().equals("c1wordstop"))
					
				{
					
					
					String client = pac.getClient();
					String task = pac.getAdditionAction();
					
				//	if(task.equals("c1wordstop"))
					
				//	{
						
						
						
					
						
						
						//ResultSet rss = stmt2.executeQuery("select * from process where name='"+c1task1.exe+"'"+ and processer="'"+p1+"'");
						
						
						ResultSet srss = stmt1.executeQuery("select * from process where name='c1task1.exe'");
						
						if(srss.next()==true)
						{
						
						ResultSet rss = stmt2.executeQuery("select * from process where name='c1task1.exe' and processer='p1'");
						
						if(rss.next()==true)
						{
							
							ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c1task1.exe' and processer='p1'");
							
							if(rss1.next()==true)
							{
								int count = rss1.getInt(1);
								
								stmt2.executeUpdate("delete from process where name='c1task1.exe' and processer='p1' and id="+count);
								
								c2word--;
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								 pacfor.setProcessAction("stop");
								 pacfor.setProcess("C1_Task1 is stopped");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										
										
										
										ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
										
										if(rs.next()==true)
										{
											
											int occ = rs.getInt("occupyspace");
											int upocc = occ-word;
											int free = rs.getInt("freespace");
											int upfree = free+word;
											
											stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
										}
										
										TaskScheduler.jta.append("C1_Task1 is stopped \n");
										
										waiting();
										
									}
								
								
								
								
								
								
							}
							
							
						}
						
						else
						{
							ResultSet erss = stmt2.executeQuery("select * from process where name='c1task1.exe' and processer='p2'");
							
							if(erss.next()==true)
							{
								ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c1task1.exe' and processer='p2'");
								
								if(rss1.next()==true)
								{
									int count = rss1.getInt(1);
									
									System.out.println("Count :"+count);
									
									stmt2.executeUpdate("delete from process where name='c1task1.exe' and processer='p2' and id="+count);
									
									c1word--;
									
									InetAddress loc = InetAddress.getLocalHost();
									Socket soc = new Socket(loc,4002);
									 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
									 
									 Packet pacfor = new Packet();
									 pacfor.setProcessAction("stop");
									 pacfor.setProcess("C1_Task1 is stopped");
									  dowoutput.writeObject(pacfor);
									  
									  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
										Packet rpacfor=(Packet) in.readObject();
										
										if(rpacfor.getAction().equals("Ok"))
										{
											
											
										
											ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
											
											if(rs.next()==true)
											{
												
												int occ = rs.getInt("occupyspace");
												int upocc = occ-word;
												int free = rs.getInt("freespace");
												int upfree = free+word;
												
												stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core2'");
											}
											
											
											
											
											TaskScheduler.jta.append("C1_Task1 is stopped \n");
											
											waiting();
											
										}
									
									
									
									
									
									
									
									
								}
								
							}
						}
					}
						
						else
						{
							JOptionPane.showMessageDialog(null, "There is no process running currently");
						}
					
						
						
				//	}
					
					
					
					
					
				}
				
				
				
				
				//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C1_Task2 Stop %%%%%%%%%%%%%%%%%%%%%%%%%
				
				
				
				if(pac.getAction().equals("c1videostop"))
					
				{
					
					
					String client = pac.getClient();
					String task = pac.getAdditionAction();
					
				//	if(task.equals("c1wordstop"))
					
				//	{
						
						
						
					
						
						
						//ResultSet rss = stmt2.executeQuery("select * from process where name='"+c1task1.exe+"'"+ and processer="'"+p1+"'");
						
						
						ResultSet srss = stmt1.executeQuery("select * from process where name='c1Task2.exe'");
						
						if(srss.next()==true)
						{
						
						ResultSet rss = stmt2.executeQuery("select * from process where name='c1Task2.exe' and processer='p3'");
						
						if(rss.next()==true)
						{
							
							ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c1Task2.exe' and processer='p3'");
							
							if(rss1.next()==true)
							{
								int count = rss1.getInt(1);
								
								stmt2.executeUpdate("delete from process where name='c1Task2.exe' and processer='p3' and id="+count);
								
								
								c2video--;
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4003);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								 pacfor.setProcessAction("stop");
								 pacfor.setProcess("C1_Task2 is stopped");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										
										
										ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
										
										if(rs.next()==true)
										{
											
											int occ = rs.getInt("occupyspace");
											int upocc = occ-video;
											int free = rs.getInt("freespace");
											int upfree = free+video;
											
											stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core3'");
										}
										
										
										
										TaskScheduler.jta.append("C1_Task2 is stopped \n");
										
										waiting();
										
									}
								
								
								
								
								
								
							}
							
							
						}
						
						else
						{
							ResultSet erss = stmt2.executeQuery("select * from process where name='c1Task2.exe' and processer='p1'");
							
							if(erss.next()==true)
							{
								ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c1Task2.exe' and processer='p1'");
								
								if(rss1.next()==true)
								{
									int count = rss1.getInt(1);
									
									System.out.println("Count :"+count);
									
									stmt2.executeUpdate("delete from process where name='c1Task2.exe' and processer='p1' and id="+count);
									
									c1video--;
									
									InetAddress loc = InetAddress.getLocalHost();
									Socket soc = new Socket(loc,4001);
									 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
									 
									 Packet pacfor = new Packet();
									 pacfor.setProcessAction("stop");
									 pacfor.setProcess("C1_Task2 is stopped");
									  dowoutput.writeObject(pacfor);
									  
									  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
										Packet rpacfor=(Packet) in.readObject();
										
										if(rpacfor.getAction().equals("Ok"))
										{
											
											
											
											
											ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
											
											if(rs.next()==true)
											{
												
												int occ = rs.getInt("occupyspace");
												int upocc = occ-video;
												int free = rs.getInt("freespace");
												int upfree = free+video;
												
												stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
											}
											
											
											TaskScheduler.jta.append("C1_Task2 is stopped \n");
											
											waiting();
											
										}
									
									
									
									
									
									
									
									
								}
								
							}
						}
					}
						
						else
						{
							JOptionPane.showMessageDialog(null, "There is no process running currently");
						}
					
						
						
				//	}
					
					
					
					
					
				}
				
				
				
				
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C1_Task3 Stopped %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
				
				
				if(pac.getAction().equals("c1racecarstop"))
					
				{
					
					
					String client = pac.getClient();
					String task = pac.getAdditionAction();
					
				//	if(task.equals("c1wordstop"))
					
				//	{
						
						
						
					
						
						
						//ResultSet rss = stmt2.executeQuery("select * from process where name='"+c1task1.exe+"'"+ and processer="'"+p1+"'");
						
						
						ResultSet srss = stmt1.executeQuery("select * from process where name='c1Task3.exe'");
						
						if(srss.next()==true)
						{
						
						ResultSet rss = stmt2.executeQuery("select * from process where name='c1Task3.exe' and processer='p1'");
						
						if(rss.next()==true)
						{
							
							ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c1Task3.exe' and processer='p1'");
							
							if(rss1.next()==true)
							{
								int count = rss1.getInt(1);
								
								stmt2.executeUpdate("delete from process where name='c1Task3.exe' and processer='p1' and id="+count);
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								 pacfor.setProcessAction("stop");
								 pacfor.setProcess("C1_Task3 is stopped");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										
										
									c2bikerace--;
										
										ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
										
										if(rs.next()==true)
										{
											
											int occ = rs.getInt("occupyspace");
											int upocc = occ-racecar;
											int free = rs.getInt("freespace");
											int upfree = free+racecar;
											
											stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
										}
										
										
										
										
										TaskScheduler.jta.append("C1_Task3 is stopped \n");
										
										
										waiting();
										
									}
								
								
								
								
								
								
							}
							
							
						}
						
						else
						{
							ResultSet erss = stmt2.executeQuery("select * from process where name='c1Task3.exe' and processer='p3'");
							
							if(erss.next()==true)
							{
								ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c1Task3.exe' and processer='p3'");
								
								if(rss1.next()==true)
								{
									int count = rss1.getInt(1);
									
									System.out.println("Count :"+count);
									
									stmt2.executeUpdate("delete from process where name='c1Task3.exe' and processer='p3' and id="+count);
									
									c1bikerace--;
									
									InetAddress loc = InetAddress.getLocalHost();
									Socket soc = new Socket(loc,4003);
									 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
									 
									 Packet pacfor = new Packet();
									 pacfor.setProcessAction("stop");
									 pacfor.setProcess("C1_Task3 is stopped");
									  dowoutput.writeObject(pacfor);
									  
									  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
										Packet rpacfor=(Packet) in.readObject();
										
										if(rpacfor.getAction().equals("Ok"))
										{
											
											
											
											
											ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
											
											if(rs.next()==true)
											{
												
												int occ = rs.getInt("occupyspace");
												int upocc = occ-racecar;
												int free = rs.getInt("freespace");
												int upfree = free+racecar;
												
												stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core3'");
											}
											
											
											TaskScheduler.jta.append("C1_Task3 is stopped \n");
											
											waiting();
											
										}
									
									
									
									
									
									
									
									
								}
								
							}
						}
					}
						
						else
						{
							JOptionPane.showMessageDialog(null, "There is no process running currently");
						}
					
						
						
				//	}
					
					
					
					
					
				}
				
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C1_Task4 stopped %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
				
				
				if(pac.getAction().equals("c1browserstop"))
					
				{
					
					
					String client = pac.getClient();
					String task = pac.getAdditionAction();
					
				//	if(task.equals("c1wordstop"))
					
				//	{
						
						
						
					
						
						
						//ResultSet rss = stmt2.executeQuery("select * from process where name='"+c1task1.exe+"'"+ and processer="'"+p1+"'");
						
						
						ResultSet srss = stmt1.executeQuery("select * from process where name='c1Task4.exe'");
						
						if(srss.next()==true)
						{
						
						ResultSet rss = stmt2.executeQuery("select * from process where name='c1Task4.exe' and processer='p1'");
						
						if(rss.next()==true)
						{
							
							ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c1Task4.exe' and processer='p1'");
							
							if(rss1.next()==true)
							{
								int count = rss1.getInt(1);
								
								stmt2.executeUpdate("delete from process where name='c1Task4.exe' and processer='p1' and id="+count);
								
								c2browser--;
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								 pacfor.setProcessAction("stop");
								 pacfor.setProcess("C1_Task4 is stopped");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										
										
										ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
										
										if(rs.next()==true)
										{
											
											int occ = rs.getInt("occupyspace");
											int upocc = occ-browser;
											int free = rs.getInt("freespace");
											int upfree = free+browser;
											
											stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
										}
										
										
										
										TaskScheduler.jta.append("C1_Task4 is stopped \n");
										
										
										waiting();
										
									}
								
								
								
								
								
								
							}
							
							
						}
						
						else
						{
							ResultSet erss = stmt2.executeQuery("select * from process where name='c1Task4.exe' and processer='p2'");
							
							if(erss.next()==true)
							{
								ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c1Task4.exe' and processer='p2'");
								
								if(rss1.next()==true)
								{
									int count = rss1.getInt(1);
									
									System.out.println("Count :"+count);
									
									stmt2.executeUpdate("delete from process where name='c1Task4.exe' and processer='p2' and id="+count);
									
									c1browser--;
									
									InetAddress loc = InetAddress.getLocalHost();
									Socket soc = new Socket(loc,4002);
									 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
									 
									 Packet pacfor = new Packet();
									 pacfor.setProcessAction("stop");
									 pacfor.setProcess("C1_Task4 is stopped");
									  dowoutput.writeObject(pacfor);
									  
									  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
										Packet rpacfor=(Packet) in.readObject();
										
										if(rpacfor.getAction().equals("Ok"))
										{
											
											
											ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
											
											if(rs.next()==true)
											{
												
												int occ = rs.getInt("occupyspace");
												int upocc = occ-browser;
												int free = rs.getInt("freespace");
												int upfree = free+browser;
												
												stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core2'");
											}
											
											
											
											TaskScheduler.jta.append("C1_Task4 is stopped \n");
											
											
											waiting();
											
										}
									
									
									
									
									
									
									
									
								}
								
							}
						}
					}
						
						else
						{
							JOptionPane.showMessageDialog(null, "There is no process running currently");
						}
					
						
						
				//	}
					
					
					
					
					
				}
				
				
				
				//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  C1_Task5 Stopped %%%%%%%%%%%%%%%%%%%%%%%%%
				
				if(pac.getAction().equals("c1eclipsestop"))
					
				{
					
					
					String client = pac.getClient();
					String task = pac.getAdditionAction();
					
				//	if(task.equals("c1wordstop"))
					
				//	{
						
						
						
					
						
						
						//ResultSet rss = stmt2.executeQuery("select * from process where name='"+c1task1.exe+"'"+ and processer="'"+p1+"'");
						
						
						ResultSet srss = stmt1.executeQuery("select * from process where name='c1Task5.exe'");
						
						if(srss.next()==true)
						{
						
						ResultSet rss = stmt2.executeQuery("select * from process where name='c1Task5.exe' and processer='p1'");
						
						if(rss.next()==true)
						{
							
							ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c1Task5.exe' and processer='p1'");
							
							if(rss1.next()==true)
							{
								int count = rss1.getInt(1);
								
								stmt2.executeUpdate("delete from process where name='c1Task5.exe' and processer='p1' and id="+count);
								
								
								c2eclipse--;
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								 pacfor.setProcessAction("stop");
								 pacfor.setProcess("C1_Task5 is stopped");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										
										
										ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
										
										if(rs.next()==true)
										{
											
											int occ = rs.getInt("occupyspace");
											int upocc = occ-eclipse;
											int free = rs.getInt("freespace");
											int upfree = free+eclipse;
											
											stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
										}
										
										
										
										
										TaskScheduler.jta.append("C1_Task5 is stopped \n");
										
										waiting();
										
									}
								
								
								
								
								
								
							}
							
							
						}
						
						else
						{
							ResultSet erss = stmt2.executeQuery("select * from process where name='c1Task5.exe' and processer='p2'");
							
							if(erss.next()==true)
							{
								ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c1Task5.exe' and processer='p2'");
								
								if(rss1.next()==true)
								{
									int count = rss1.getInt(1);
									
									System.out.println("Count :"+count);
									
									stmt2.executeUpdate("delete from process where name='c1Task5.exe' and processer='p2' and id="+count);
									
									
									c1eclipse--;
									
									InetAddress loc = InetAddress.getLocalHost();
									Socket soc = new Socket(loc,4002);
									 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
									 
									 Packet pacfor = new Packet();
									 pacfor.setProcessAction("stop");
									 pacfor.setProcess("C1_Task5 is stopped");
									  dowoutput.writeObject(pacfor);
									  
									  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
										Packet rpacfor=(Packet) in.readObject();
										
										if(rpacfor.getAction().equals("Ok"))
										{
											
											
											
											
											
											ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
											
											if(rs.next()==true)
											{
												
												int occ = rs.getInt("occupyspace");
												int upocc = occ-eclipse;
												int free = rs.getInt("freespace");
												int upfree = free+eclipse;
												
												stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core2'");
											}
											
											
											TaskScheduler.jta.append("C1_Task5 is stopped \n");
											
											waiting();
											
										}
									
									
									
									
									
									
									
									
								}
								
							}
						}
					}
						
						else
						{
							JOptionPane.showMessageDialog(null, "There is no process running currently");
						}
					
						
						
				//	}
					
					
					
					
					
				}
				
				
				
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C2_Task1 stoped %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
				
				if(pac.getAction().equals("c2pointstop"))
					
				{
					
					
					String client = pac.getClient();
					String task = pac.getAdditionAction();
					
				//	if(task.equals("c1wordstop"))
					
				//	{
						
						
						
					
						
						
						//ResultSet rss = stmt2.executeQuery("select * from process where name='"+c1task1.exe+"'"+ and processer="'"+p1+"'");
						
						
						ResultSet srss = stmt1.executeQuery("select * from process where name='c2Task1.exe'");
						
						if(srss.next()==true)
						{
						
						ResultSet rss = stmt2.executeQuery("select * from process where name='c2Task1.exe' and processer='p1'");
						
						if(rss.next()==true)
						{
							
							ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c2Task1.exe' and processer='p1'");
							
							if(rss1.next()==true)
							{
								int count = rss1.getInt(1);
								
								stmt2.executeUpdate("delete from process where name='c2Task1.exe' and processer='p1' and id="+count);
								
								c2point--;
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								 pacfor.setProcessAction("stop");
								 pacfor.setProcess("C2_Task1 is stopped");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										
										
										
										ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
										
										if(rs.next()==true)
										{
											
											int occ = rs.getInt("occupyspace");
											int upocc = occ-word;
											int free = rs.getInt("freespace");
											int upfree = free+word;
											
											stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
										}
										
										
										
										
										TaskScheduler.jta.append("C2_Task1 is stopped \n");
										
										
										waiting();
										
									}
								
								
								
								
								
								
							}
							
							
						}
						
						else
						{
							ResultSet erss = stmt2.executeQuery("select * from process where name='c2Task1.exe' and processer='p2'");
							
							if(erss.next()==true)
							{
								ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c2Task1.exe' and processer='p2'");
								
								if(rss1.next()==true)
								{
									int count = rss1.getInt(1);
									
									System.out.println("Count :"+count);
									
									stmt2.executeUpdate("delete from process where name='c2Task1.exe' and processer='p2' and id="+count);
									
									c1point--;
									
									InetAddress loc = InetAddress.getLocalHost();
									Socket soc = new Socket(loc,4002);
									 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
									 
									 Packet pacfor = new Packet();
									 pacfor.setProcessAction("stop");
									 pacfor.setProcess("C2_Task1 is stopped");
									  dowoutput.writeObject(pacfor);
									  
									  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
										Packet rpacfor=(Packet) in.readObject();
										
										if(rpacfor.getAction().equals("Ok"))
										{
											
										
											

											ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
											
											if(rs.next()==true)
											{
												
												int occ = rs.getInt("occupyspace");
												int upocc = occ-word;
												int free = rs.getInt("freespace");
												int upfree = free+word;
												
												stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core2'");
											}
											
											
											
											
											TaskScheduler.jta.append("C2_Task1 is stopped \n");
											
											waiting();
											
										}
									
									
									
									
									
									
									
									
								}
								
							}
						}
					}
						
						else
						{
							JOptionPane.showMessageDialog(null, "There is no process running currently");
						}
					
						
						
				//	}
					
					
					
					
					
				}
				
				
				
				//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C2_Task2 stop %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
				
				
				if(pac.getAction().equals("c2musicstop"))
					
				{
					
					
					String client = pac.getClient();
					String task = pac.getAdditionAction();
					
				//	if(task.equals("c1wordstop"))
					
				//	{
						
						
						
					
						
						
						//ResultSet rss = stmt2.executeQuery("select * from process where name='"+c1task1.exe+"'"+ and processer="'"+p1+"'");
						
						
						ResultSet srss = stmt1.executeQuery("select * from process where name='c2Task2.exe'");
						
						if(srss.next()==true)
						{
						
						ResultSet rss = stmt2.executeQuery("select * from process where name='c2Task2.exe' and processer='p1'");
						
						if(rss.next()==true)
						{
							
							ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c2Task2.exe' and processer='p1'");
							
							if(rss1.next()==true)
							{
								int count = rss1.getInt(1);
								
								stmt2.executeUpdate("delete from process where name='c2Task2.exe' and processer='p1' and id="+count);
								
								
								c2music--;
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								 pacfor.setProcessAction("stop");
								 pacfor.setProcess("C2_Task2 is stopped");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										
										
										
										ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
										
										if(rs.next()==true)
										{
											
											int occ = rs.getInt("occupyspace");
											int upocc = occ-music;
											int free = rs.getInt("freespace");
											int upfree = free+music;
											
											stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
										}
										
										
										
										
										TaskScheduler.jta.append("C2_Task2 is stopped \n");
										waiting();
										
									}
								
								
								
								
								
								
							}
							
							
						}
						
						else
						{
							ResultSet erss = stmt2.executeQuery("select * from process where name='c2Task2.exe' and processer='p3'");
							
							if(erss.next()==true)
							{
								ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c2Task2.exe' and processer='p3'");
								
								if(rss1.next()==true)
								{
									int count = rss1.getInt(1);
									
									System.out.println("Count :"+count);
									
									stmt2.executeUpdate("delete from process where name='c2Task2.exe' and processer='p3' and id="+count);
									
									
									c1music--;
									
									InetAddress loc = InetAddress.getLocalHost();
									Socket soc = new Socket(loc,4003);
									 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
									 
									 Packet pacfor = new Packet();
									 pacfor.setProcessAction("stop");
									 pacfor.setProcess("C2_Task2 is stopped");
									  dowoutput.writeObject(pacfor);
									  
									  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
										Packet rpacfor=(Packet) in.readObject();
										
										if(rpacfor.getAction().equals("Ok"))
										{
											
											
											
											ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
											
											if(rs.next()==true)
											{
												
												int occ = rs.getInt("occupyspace");
												int upocc = occ-music;
												int free = rs.getInt("freespace");
												int upfree = free+music;
												
												stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core3'");
											}
											
											
											
											TaskScheduler.jta.append("C2_Task2 is stopped \n");
											
											waiting();
											
										}
									
									
									
									
									
									
									
									
								}
								
							}
						}
					}
						
						else
						{
							JOptionPane.showMessageDialog(null, "There is no process running currently");
						}
					
						
						
				//	}
					
					
					
					
					
				}
				
				//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  C2_Task3 stopped %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
				
				
				
				
				
				if(pac.getAction().equals("c2calcstop"))
					
				{
					
					
					String client = pac.getClient();
					String task = pac.getAdditionAction();
					
				//	if(task.equals("c1wordstop"))
					
				//	{
						
						
						
					
						
						
						//ResultSet rss = stmt2.executeQuery("select * from process where name='"+c1task1.exe+"'"+ and processer="'"+p1+"'");
						
						
						ResultSet srss = stmt1.executeQuery("select * from process where name='c2Task3.exe'");
						
						if(srss.next()==true)
						{
						
						ResultSet rss = stmt2.executeQuery("select * from process where name='c2Task3.exe' and processer='p2'");
						
						if(rss.next()==true)
						{
							
							ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c2Task3.exe' and processer='p2'");
							
							if(rss1.next()==true)
							{
								int count = rss1.getInt(1);
								
								stmt2.executeUpdate("delete from process where name='c2Task3.exe' and processer='p2' and id="+count);
								
								
								
								c2calc--;
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4002);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								 pacfor.setProcessAction("stop");
								 pacfor.setProcess("C2_Task3 is stopped");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										
										
										ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
										
										if(rs.next()==true)
										{
											
											int occ = rs.getInt("occupyspace");
											int upocc = occ-calculator;
											int free = rs.getInt("freespace");
											int upfree = free+calculator;
											
											stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core2'");
										}
										
										
										
										
										TaskScheduler.jta.append("C2_Task3 is stopped \n");
										
										waiting();
										
									}
								
								
								
								
								
								
							}
							
							
						}
						
						else
						{
							ResultSet erss = stmt2.executeQuery("select * from process where name='c2Task3.exe' and processer='p1'");
							
							if(erss.next()==true)
							{
								ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c2Task3.exe' and processer='p1'");
								
								if(rss1.next()==true)
								{
									int count = rss1.getInt(1);
									
									System.out.println("Count :"+count);
									
									stmt2.executeUpdate("delete from process where name='c2Task3.exe' and processer='p1' and id="+count);
									
									
									c1calc--;
									
									
									InetAddress loc = InetAddress.getLocalHost();
									Socket soc = new Socket(loc,4001);
									 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
									 
									 Packet pacfor = new Packet();
									 pacfor.setProcessAction("stop");
									 pacfor.setProcess("C2_Task3 is stopped");
									  dowoutput.writeObject(pacfor);
									  
									  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
										Packet rpacfor=(Packet) in.readObject();
										
										if(rpacfor.getAction().equals("Ok"))
										{
											
											
											
											ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
											
											if(rs.next()==true)
											{
												
												int occ = rs.getInt("occupyspace");
												int upocc = occ-calculator;
												int free = rs.getInt("freespace");
												int upfree = free+calculator;
												
												stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
											}
											
											
											
											
											TaskScheduler.jta.append("C2_Task3 is stopped \n");
											
											waiting();
											
										}
									
									
									
									
									
									
									
									
								}
								
							}
						}
					}
						
						else
						{
							JOptionPane.showMessageDialog(null, "There is no process running currently");
						}
					
						
						
				//	}
					
					
					
					
					
				}
				
				
				
				
				//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C2_Task4  stop %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
				
				
				if(pac.getAction().equals("c2skypestop"))
					
				{
					
					
					String client = pac.getClient();
					String task = pac.getAdditionAction();
					
					
					
					int getpid=0;
					int getdeletememory = 0;
					String getdeleteprocesser=null;
					
					ResultSet prs = stmt1.executeQuery(" select * from process where name='c2Task4.exe' and id=0");
							
					if(prs.next()==true)
					{
						
						
						getpid = prs.getInt("id");
						

						getdeletememory = prs.getInt("memory");
						getdeleteprocesser = prs.getString("processer");
						
						
						if(getpid==0)
						{
							
							
							
							ResultSet prss = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
							
							if(prss.next()==true)
							{
							
							int occ = prss.getInt("occupyspace");
							int upocc = occ-getdeletememory;
							int free = prss.getInt("freespace");
							int upfree = free+getdeletememory;
							
							stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core2'");
							
							
							stmt2.executeUpdate("delete from process where id="+getpid+" and processer='"+getdeleteprocesser+"'"); 
							
							
						//	TaskScheduler.jta.append("C2 c2Task4.exe is stopped \n");
							
							
						//	int upocc1 = occ-getmemory;
							
						//	int upfree1 = free+getmemory;
							
							
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4002);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							 pacfor.setProcessAction("stop");
							 pacfor.setProcess("C2_Task4 is stopped");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									
									TaskScheduler.jta.append("C2_Task4 is stopped \n");
									
									//ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
									
								//	if(rs.next()==true)
								//	{
										
								//		int occ = rs.getInt("occupyspace");
								//		int upocc = occ-skype;
								//		int free = rs.getInt("freespace");
								//		int upfree = free+skype;
										
								//		stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
									}
							
							
							
							
							ResultSet prs1 = stmt1.executeQuery(" select * from priwaiting where processer='p2'");
							
							
							if(prs1.next()==true)
							{
								
								
								String getname = prs1.getString("name");
								String getuser = prs1.getString("user");
								int getmemory = prs1.getInt("memory");
								int getid = prs1.getInt("id");
								String getprocesser = prs1.getString("processer");
								
								
								
								stmt2.executeUpdate("insert into process values('"+getname+"','"+getuser+"',"+getmemory+","+getid+",'"+getprocesser+"')");
								
								
								stmt1.executeUpdate("delete from priwaiting where id="+getid+" and processer='"+getprocesser+"'"); 
								
								
								
								ResultSet prss1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
								
								if(prss1.next()==true)
								{
								
								int occ1 = prss1.getInt("occupyspace");
								int upocc1 = occ1+getmemory;
								int free1 = prss1.getInt("freespace");
								int upfree1 = free1-getmemory;
								
								
								
								stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree1+",occupyspace="+upocc1+" where pname='core2'");
								
								
								InetAddress loc1 = InetAddress.getLocalHost();
								Socket soc1 = new Socket(loc1,4002);
								 ObjectOutputStream dowoutput1 = new ObjectOutputStream(soc1.getOutputStream());
								 
								 Packet pacfor1 = new Packet();
								 pacfor1.setProcessAction("start");
								 pacfor1.setProcess("C2 "+getname+" process is again started");
								  dowoutput1.writeObject(pacfor1);
								  
								  ObjectInputStream in1=new ObjectInputStream(soc1.getInputStream());
									Packet rpacfor1=(Packet) in1.readObject();
									
									if(rpacfor1.getAction().equals("Ok"))
									{
										
										TaskScheduler.jta.append("C2 "+getname+" is again started \n");
										
										//ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
										
									//	if(rs.next()==true)
									//	{
											
									//		int occ = rs.getInt("occupyspace");
									//		int upocc = occ-skype;
									//		int free = rs.getInt("freespace");
									//		int upfree = free+skype;
											
									//		stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
										}
								
								
								
								
								
										
										
								
								
								
								
								
								}
								
								}
								
								
							}
							
							
						}
						
						
					}
						
					
					
				//	if(task.equals("c1wordstop"))
					
				//	{
						
						
						
						else
						
						{
						//ResultSet rss = stmt2.executeQuery("select * from process where name='"+c1task1.exe+"'"+ and processer="'"+p1+"'");
						
						
						ResultSet srss = stmt1.executeQuery("select * from process where name='c2Task4.exe'");
						
						if(srss.next()==true)
						{
						
						ResultSet rss = stmt2.executeQuery("select * from process where name='c2Task4.exe' and processer='p1'");
						
						if(rss.next()==true)
						{
							
						
							
							
							ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c2Task4.exe' and processer='p1'");
							
							if(rss1.next()==true)
							{
								int count = rss1.getInt(1);
								
								stmt2.executeUpdate("delete from process where name='c2Task4.exe' and processer='p1' and id="+count);
								
								
								c2skype--;
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								 pacfor.setProcessAction("stop");
								 pacfor.setProcess("C2_Task4 is stopped");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										
										ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
										
										if(rs.next()==true)
										{
											
											int occ = rs.getInt("occupyspace");
											int upocc = occ-skype;
											int free = rs.getInt("freespace");
											int upfree = free+skype;
											
											stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
										}
										
										TaskScheduler.jta.append("C2_Task4 is stopped \n");
										
										waiting();
										
										
										
										
										
										
									}
								
								
								
								
								
								
							}
							
							
						}
						
						else
						{
							ResultSet erss = stmt2.executeQuery("select * from process where name='c2Task4.exe' and processer='p3'");
							
							if(erss.next()==true)
							{
								ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c2Task4.exe' and processer='p3'");
								
								if(rss1.next()==true)
								{
									int count = rss1.getInt(1);
									
									System.out.println("Count :"+count);
									
									stmt2.executeUpdate("delete from process where name='c2Task4.exe' and processer='p3' and id="+count);
									
									
									c1skype--;
									
									InetAddress loc = InetAddress.getLocalHost();
									Socket soc = new Socket(loc,4003);
									 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
									 
									 Packet pacfor = new Packet();
									 pacfor.setProcessAction("stop");
									 pacfor.setProcess("C2_Task4 is stopped");
									  dowoutput.writeObject(pacfor);
									  
									  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
										Packet rpacfor=(Packet) in.readObject();
										
										if(rpacfor.getAction().equals("Ok"))
										{
											
											
										
											ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
											
											if(rs.next()==true)
											{
												
												int occ = rs.getInt("occupyspace");
												int upocc = occ-skype;
												int free = rs.getInt("freespace");
												int upfree = free+skype;
												
												stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core3'");
											}
											
											
											
											
											TaskScheduler.jta.append("C2_Task4 is stopped \n");
											
											waiting();
											
										}
									
									
									
									
									
									
									
									
								}
								
							}
						}
					}
						
						
						else
						{
							JOptionPane.showMessageDialog(null, "There is no process running currently");
					
						}
						
						
				}
						
						
						
						
				//	}
					
					
					
					
					
				}
				
				
	
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C3_Task1 Stop %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
				
				
				
				

				if(pac.getAction().equals("c3npadstop"))
					
				{
					
					
					String client = pac.getClient();
					String task = pac.getAdditionAction();
					
				//	if(task.equals("c1wordstop"))
					
				//	{
						
						
						
					
						
						
						//ResultSet rss = stmt2.executeQuery("select * from process where name='"+c1task1.exe+"'"+ and processer="'"+p1+"'");
						
						
						ResultSet srss = stmt1.executeQuery("select * from process where name='c3Task1.exe'");
						
						if(srss.next()==true)
						{
						
						ResultSet rss = stmt2.executeQuery("select * from process where name='c3Task1.exe' and processer='p3'");
						
						if(rss.next()==true)
						{
							
							ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c3Task1.exe' and processer='p3'");
							
							if(rss1.next()==true)
							{
								int count = rss1.getInt(1);
								
								stmt2.executeUpdate("delete from process where name='c3Task1.exe' and processer='p3' and id="+count);
								
								c2notepad--;
								
								
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4003);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								 pacfor.setProcessAction("stop");
								 pacfor.setProcess("C3_Task1 is stopped");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										
										
										
										

										ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
										
										if(rs.next()==true)
										{
											
											int occ = rs.getInt("occupyspace");
											int upocc = occ-notepad;
											int free = rs.getInt("freespace");
											int upfree = free+notepad;
											
											stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core3'");
										}
										
										
										
										
										TaskScheduler.jta.append("C3_Task1 is stopped \n");
										
										waiting();
										
									}
								
								
								
								
								
								
							}
							
							
						}
						
						else
						{
							ResultSet erss = stmt2.executeQuery("select * from process where name='c3Task1.exe' and processer='p1'");
							
							if(erss.next()==true)
							{
								ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c3Task1.exe' and processer='p1'");
								
								if(rss1.next()==true)
								{
									int count = rss1.getInt(1);
									
									System.out.println("Count :"+count);
									
									stmt2.executeUpdate("delete from process where name='c3Task1.exe' and processer='p1' and id="+count);
									
									c1notepad--;
									
									InetAddress loc = InetAddress.getLocalHost();
									Socket soc = new Socket(loc,4001);
									 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
									 
									 Packet pacfor = new Packet();
									 pacfor.setProcessAction("stop");
									 pacfor.setProcess("C3_Task1 is stopped");
									  dowoutput.writeObject(pacfor);
									  
									  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
										Packet rpacfor=(Packet) in.readObject();
										
										if(rpacfor.getAction().equals("Ok"))
										{
											
											
											ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
											
											if(rs.next()==true)
											{
												
												int occ = rs.getInt("occupyspace");
												int upocc = occ-notepad;
												int free = rs.getInt("freespace");
												int upfree = free+notepad;
												
												stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
											}
											
											
											
											TaskScheduler.jta.append("C3_Task1 is stopped \n");
											
											
											waiting();
											
										}
									
									
									
									
									
									
									
									
								}
								
							}
						}
					}
						
						else
						{
							JOptionPane.showMessageDialog(null, "There is no process running currently");
						}
					
						
						
				//	}
					
					
					
					
					
				}
				
				
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C3_Task2  Stop %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
				
				
				if(pac.getAction().equals("c3paintstop"))
					
				{
					
					
					String client = pac.getClient();
					String task = pac.getAdditionAction();
					
				//	if(task.equals("c1wordstop"))
					
				//	{
						
						
						
					
						
						
						//ResultSet rss = stmt2.executeQuery("select * from process where name='"+c1task1.exe+"'"+ and processer="'"+p1+"'");
						
						
						ResultSet srss = stmt1.executeQuery("select * from process where name='c3Task2.exe'");
						
						if(srss.next()==true)
						{
						
						ResultSet rss = stmt2.executeQuery("select * from process where name='c3Task2.exe' and processer='p1'");
						
						if(rss.next()==true)
						{
							
							ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c3Task2.exe' and processer='p1'");
							
							if(rss1.next()==true)
							{
								int count = rss1.getInt(1);
								
								stmt2.executeUpdate("delete from process where name='c3Task2.exe' and processer='p1' and id="+count);
								
								
								c2paint--;
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								 pacfor.setProcessAction("stop");
								 pacfor.setProcess("C3_Task2 is stopped");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										
										
										
										
										ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
										
										if(rs.next()==true)
										{
											
											int occ = rs.getInt("occupyspace");
											int upocc = occ-paint;
											int free = rs.getInt("freespace");
											int upfree = free+paint;
											
											stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
										}
										
										
										
										
										
										TaskScheduler.jta.append("C3_Task2 is stopped \n");
										
										waiting();
										
									}
								
								
								
								
								
								
							}
							
							
						}
						
						else
						{
							ResultSet erss = stmt2.executeQuery("select * from process where name='c3Task2.exe' and processer='p3'");
							
							if(erss.next()==true)
							{
								ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c3Task2.exe' and processer='p3'");
								
								if(rss1.next()==true)
								{
									int count = rss1.getInt(1);
									
									System.out.println("Count :"+count);
									
									stmt2.executeUpdate("delete from process where name='c3Task2.exe' and processer='p3' and id="+count);
									
									
									c1paint--;
									
									InetAddress loc = InetAddress.getLocalHost();
									Socket soc = new Socket(loc,4003);
									 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
									 
									 Packet pacfor = new Packet();
									 pacfor.setProcessAction("stop");
									 pacfor.setProcess("C3_Task2 is stopped");
									  dowoutput.writeObject(pacfor);
									  
									  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
										Packet rpacfor=(Packet) in.readObject();
										
										if(rpacfor.getAction().equals("Ok"))
										{
											
											
											

											ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
											
											if(rs.next()==true)
											{
												
												int occ = rs.getInt("occupyspace");
												int upocc = occ-paint;
												int free = rs.getInt("freespace");
												int upfree = free+paint;
												
												stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core3'");
											}
											
											
											
											
											TaskScheduler.jta.append("C3_Task2 is stopped \n");
											
											waiting();
											
										}
									
									
									
									
									
									
									
									
								}
								
							}
						}
					}
						
						else
						{
							JOptionPane.showMessageDialog(null, "There is no process running currently");
						}
					
						
						
				//	}
					
					
					
					
					
				}
				
	
				
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5 C3_Task3 Stop %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
				
				
if(pac.getAction().equals("c3excelstop"))
					
				{
					
					
					String client = pac.getClient();
					String task = pac.getAdditionAction();
					
				//	if(task.equals("c1wordstop"))
					
				//	{
						
						
						
					
						
						
						//ResultSet rss = stmt2.executeQuery("select * from process where name='"+c1task1.exe+"'"+ and processer="'"+p1+"'");
						
						
						ResultSet srss = stmt1.executeQuery("select * from process where name='c3Task3.exe'");
						
						if(srss.next()==true)
						{
						
						ResultSet rss = stmt2.executeQuery("select * from process where name='c3Task3.exe' and processer='p1'");
						
						if(rss.next()==true)
						{
							
							ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c3Task3.exe' and processer='p1'");
							
							if(rss1.next()==true)
							{
								int count = rss1.getInt(1);
								
								stmt2.executeUpdate("delete from process where name='c3Task3.exe' and processer='p1' and id="+count);
								
								
								
								c2excel--;
								
								InetAddress loc = InetAddress.getLocalHost();
								Socket soc = new Socket(loc,4001);
								 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
								 
								 Packet pacfor = new Packet();
								 pacfor.setProcessAction("stop");
								 pacfor.setProcess("C3_Task3 is stopped");
								  dowoutput.writeObject(pacfor);
								  
								  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
									Packet rpacfor=(Packet) in.readObject();
									
									if(rpacfor.getAction().equals("Ok"))
									{
										
										
									
										ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
										
										if(rs.next()==true)
										{
											
											int occ = rs.getInt("occupyspace");
											int upocc = occ-excel;
											int free = rs.getInt("freespace");
											int upfree = free+excel;
											
											stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core1'");
										}
										
										
										
										
										TaskScheduler.jta.append("C3_Task3 is stopped \n");
										
										waiting();
										
									}
								
								
								
								
								
								
							}
							
							
						}
						
						else
						{
							ResultSet erss = stmt2.executeQuery("select * from process where name='c3Task3.exe' and processer='p2'");
							
							if(erss.next()==true)
							{
								ResultSet rss1 = stmt1.executeQuery("select count(*) from process where name='c3Task3.exe' and processer='p2'");
								
								if(rss1.next()==true)
								{
									int count = rss1.getInt(1);
									
									System.out.println("Count :"+count);
									
									stmt2.executeUpdate("delete from process where name='c3Task3.exe' and processer='p2' and id="+count);
									
									
									c1excel--;
									
									
									InetAddress loc = InetAddress.getLocalHost();
									Socket soc = new Socket(loc,4002);
									 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
									 
									 Packet pacfor = new Packet();
									 pacfor.setProcessAction("stop");
									 pacfor.setProcess("C3_Task3 is stopped");
									  dowoutput.writeObject(pacfor);
									  
									  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
										Packet rpacfor=(Packet) in.readObject();
										
										if(rpacfor.getAction().equals("Ok"))
										{
											
											
											
											
											ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
											
											if(rs.next()==true)
											{
												
												int occ = rs.getInt("occupyspace");
												int upocc = occ-excel;
												int free = rs.getInt("freespace");
												int upfree = free+excel;
												
												stmt1.executeUpdate("update multicore.usagememory set freespace="+upfree+",occupyspace="+upocc+" where pname='core2'");
											}
											
											
											
											
											TaskScheduler.jta.append("C3_Task3 is stopped \n");
											
											waiting();
											
										}
									
									
									
									
									
									
									
									
								}
								
							}
						}
					}
						
						else
						{
							JOptionPane.showMessageDialog(null, "There is no process running currently");
						}
					
						
						
				//	}
					
					
					
					
					
				}
				
				
				
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5//			
				
				
			}   
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Waiting process  try good%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
	////////////////////////////////////// C1_Task1 ////////////////////////////////////
	public void waiting()
	{
		try
		{
		
		ResultSet rss = stmt1.executeQuery("Select process, min(prtid) from waiting group by prtid");
		
		if(rss.next()==true)
		{
			String getprocess = rss.getString("process");
			String minprtid = rss.getString(2);
			
			System.out.println(" minimum priority "+minprtid);
			
			
			if(getprocess.equals("c1task1.exe"))
			{
				
				ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
				
				if(rs.next()==true)
				{
					
					int avaspace =rs.getInt("freespace");
					int ocspace = rs.getInt("occupyspace"); 
					
					if(avaspace>word)
						
					{
						InetAddress loc = InetAddress.getLocalHost();
						Socket soc = new Socket(loc,4002);
						 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
						 
						 Packet pacfor = new Packet();
						 pacfor.setProcessAction("start");
						 pacfor.setProcess("C1_Task1 is executing");
						  dowoutput.writeObject(pacfor);
						  
						  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
							Packet rpacfor=(Packet) in.readObject();
							
							if(rpacfor.getAction().equals("Ok"))
							{
								TaskScheduler.jta.append("C1_Task1 is sheduled and running....\n");
								
								
								c1word++;
								String spro="p2";
								
								String client = "C1";
								
								
								stmt2.executeUpdate("insert into multicore.process values('c1task1.exe','"+client+"',"+word+","+c1word+",'"+spro+"')");
								
							//	p2cnword++;
								
							//	p2alword.add(p2cnword);
								
						//		stmt2.executeUpdate("insert into multicore.process values('c1task1.exe','"+client+"',"+word+","+p2alword.get(p2cnword-1)+")");
								
								
								int updatespace = avaspace - word;
								int upocc = ocspace+word;
								
								stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core2'");
								
								
								stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
								
								}
							
							
							
					}
			
					
					else
					{
						ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
						
						
						
					if(rs1.next()==true)
					{
						
							int avaspace1 =rs1.getInt("freespace");
							int ocspace1 = rs1.getInt("occupyspace"); 
							
						if(avaspace1>word)
						{
							
							
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4001);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							// pacfor.setAction("word");
							 pacfor.setProcessAction("start");
							 pacfor.setProcess("C1_Task1 is executing");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C1_Task1 scheduled is running....");
									
									
									c2word++;
									String spro="p1";
									
									String client = "C1";
									
									stmt2.executeUpdate("insert into multicore.process values('c1task1.exe','"+client+"',"+word+","+c2word+",'"+spro+")");
									
							//		p1cnword++;
							//		p1alword.add(p1cnword);
									
							//		stmt2.executeUpdate("insert into multicore.process values('c1task1.exe','"+client+"',"+word+","+p1alword.get(p1cnword-1)+")");
									
									
									int updatespace1 = avaspace1 - word;
									int upocc1 = ocspace1+word;
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");	
									
									
									
									stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
									
								}
							
							
							
						  }
						
						else{
							
						//	waitingid++;
						
						//	stmt2.executeUpdate("insert into multicore.waiting values('c1task1.exe','"+waitingid+"','waiting')");
						//	TaskScheduler.jta.append("C1 c1task1.exe is waiting for scheduling....");
							
							
						}
						
						
					}
						
				}		
				
				
				
				
			}
				
				
				
			}
			
			//%%%%%%%%%%%%%%%%%%%%%%%%%% waiting process for C1_Task2 %%%%%%%%%%%%%%%%%%%%
			
			
			else if(getprocess.equals("c1Task2.exe"))
			{
				
				
				ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
				
				if(rs.next()==true)
				{
					
					int avaspace = rs.getInt("freespace");
					int ocspace = rs.getInt("occupyspace"); 
					
					if(avaspace>video)
					{
						
						
						InetAddress loc = InetAddress.getLocalHost();
						Socket soc = new Socket(loc,4001);
						 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
						 
						 Packet pacfor = new Packet();
						 
						 pacfor.setProcessAction("start");
						 pacfor.setProcess("C1_Task2 is executing");
						  dowoutput.writeObject(pacfor);
						  
						  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
							Packet rpacfor=(Packet) in.readObject();
							
							if(rpacfor.getAction().equals("Ok"))
							{
								TaskScheduler.jta.append("C1_Task2 scheduled and running....\n");
								
								c1video++;
								String spro="p1";
								
								String client="C1";
								
								stmt2.executeUpdate("insert into multicore.process values('c1Task2.exe','"+client+"',"+video+","+c1video+",'"+spro+"')");
								
							//	p1cnvideo++;
							//	p1alvideo.add(p1cnvideo);
								
						//		stmt2.executeUpdate("insert into multicore.process values('c1Task2.exe','"+client+"',"+video+","+p1alvideo.get(p1cnvideo-1)+")");
								
								
								int updatespace = avaspace - video;
								int upocc = ocspace+video;
								
								stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core1'");
								
								
								stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
								
								
								
								}
						 
					}
					
					
					
					else
					{
						ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
						
						
						
					if(rs1.next()==true)
					{
						
							int avaspace1 =rs1.getInt("freespace");
							int ocspace1 = rs1.getInt("occupyspace"); 
						if(avaspace1>video)
						{
							
							
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4003);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							// pacfor.setAction("word");
							 pacfor.setProcessAction("start");
							 pacfor.setProcess("C1_Task2 is executing");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C1_Task2 scheduled and running....\n");
									
									
									c2video++;
									
									String spro="p3";
									
									String client="C1";
									
									stmt2.executeUpdate("insert into multicore.process values('c1Task2.exe','"+client+"',"+video+","+c2video+",'"+spro+"')");
									
									
								//	p3cnvideo++;
								//	p3alvideo.add(p3cnvideo);
									
								//	stmt2.executeUpdate("insert into multicore.process values('c1Task2.exe','"+client+"',"+video+","+p3alvideo.get(p3cnvideo-1)+")");
									
									
									
								//	stmt2.executeUpdate("insert into multicore.process values('c1Task2.exe','"+client+"',"+video+")");
									
									
									int updatespace1 = avaspace1 - video;
									int upocc1 = ocspace1+video;
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core3'");	
									
									
									stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
									
								}
							
							
							
						  }
						
						else{
							
						//	waitingid++;
							
						//	stmt2.executeUpdate("insert into multicore.waiting values('c1Task2.exe','"+waitingid+"','waiting')");
						//	TaskScheduler.jta.append("C1 c1Task2.exe is waiting for scheduling....");
							
						}
						
						
					}
						
				}
					
					
					
						
					}
				
			}
			
			
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C1_Task3 waiting %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5	
			
			else if(getprocess.equals("c1Task3.exe"))
				
			{
				
				
				
				
				
				ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
				
				if(rs.next()==true)
				{
					
					int avaspace =rs.getInt("freespace");
					int ocspace = rs.getInt("occupyspace");
					
					if(avaspace>racecar)
					{
						
						
						InetAddress loc = InetAddress.getLocalHost();
						Socket soc = new Socket(loc,4003);
						 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
						 
						 Packet pacfor = new Packet();
						 
						 pacfor.setProcessAction("start");
						 pacfor.setProcess("C1_Task3 is executing");
						  dowoutput.writeObject(pacfor);
						  
						  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
							Packet rpacfor=(Packet) in.readObject();
							
							if(rpacfor.getAction().equals("Ok"))
							{
								TaskScheduler.jta.append("C1_Task3 is running....\n");
								
								
								c1bikerace++;
								String spro="p3";
								
								String client="C1";
								
								
								stmt2.executeUpdate("insert into multicore.process values('c1Task3.exe','"+client+"',"+racecar+","+c1bikerace+",'"+spro+"')");
								
								
							//	p3cngame++;
							//	p3algame.add(p3cngame);
								
								
							//	stmt2.executeUpdate("insert into multicore.process values('game.exe','"+client+"',"+racecar+","+p3algame.get(p3cngame-1)+")");
								
								
								int updatespace = avaspace - racecar;
								int upocc = ocspace+racecar;
								
								
								
								stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core3'");	
								
								
								stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
								
								
								
								}
						 
					}
					
					
					
					else
					{
						ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
						
						
						
					if(rs1.next()==true)
					{
						
							int avaspace1 =rs1.getInt("freespace");
							int ocspace1 = rs1.getInt("occupyspace");
							
						if(avaspace1>racecar)
						{
							
							
							InetAddress loc = InetAddress.getLocalHost();
							Socket soc = new Socket(loc,4001);
							 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
							 
							 Packet pacfor = new Packet();
							// pacfor.setAction("word");
							 pacfor.setProcessAction("start");
							 pacfor.setProcess("C1_Task3 is executing");
							  dowoutput.writeObject(pacfor);
							  
							  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
								Packet rpacfor=(Packet) in.readObject();
								
								if(rpacfor.getAction().equals("Ok"))
								{
									TaskScheduler.jta.append("C1_Task3 is running....\n");
									
									
									c2bikerace++;
									
									String spro="p1";
									
									String client="C1";
									
									
									stmt2.executeUpdate("insert into multicore.process values('c1Task3.exe','"+client+"',"+racecar+","+c2bikerace+",'"+spro+"')");
									
								//	p1cngame++;
							//		p1algame.add(p1cngame);
									
									
								//	stmt2.executeUpdate("insert into multicore.process values('game.exe','"+client+"',"+racecar+","+p1algame.get(p1cngame-1)+")");
									
									
									int updatespace1 = avaspace1 - racecar;
									int upocc1 = ocspace1+racecar;
									
									
									stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");		
									
									stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
									
								}
							
							
							
						  }
						
						else{
							
						//	waitingid++;
							
						//	stmt2.executeUpdate("insert into multicore.waiting values('c1Task3.exe','"+waitingid+"','waiting')");
						//	TaskScheduler.jta.append("C1 c1Task3.exe is waiting for scheduling....");
							
						}
						
						
					}
						
				}
					
					
					
					
						
						
					}
				
				
			}
			
			
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% waiting C1_Task4  %%%%%%%%%%%%%%%%%%%%%%%%%%		
			
			
		else if(getprocess.equals("c1Task4.exe"))
			
		{
			ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
			
			if(rs.next()==true)
			{
				
				int avaspace =rs.getInt("freespace");
				int ocspace = rs.getInt("occupyspace");
				
				if(avaspace>browser)
				{
					
					
					InetAddress loc = InetAddress.getLocalHost();
					Socket soc1 = new Socket(loc,4002);
					 ObjectOutputStream dowoutput1 = new ObjectOutputStream(soc1.getOutputStream());
					 
				
					 
					 Packet pacfor1 = new Packet();
					 
					 pacfor1.setProcessAction("start");
					 pacfor1.setProcess("C1_Task4 is executing");
					  dowoutput1.writeObject(pacfor1);
					  
				
					  
					  ObjectInputStream in1=new ObjectInputStream(soc1.getInputStream());
					  
				//	  System.out.println("Checking browser with socket");
						
					  Packet rpacfor=(Packet)in1.readObject();
						
						 
						
						if(rpacfor.getAction().equals("Ok"))
						{
							TaskScheduler.jta.append("C1_Task4 scheduled and running....\n");
							
						
							
							c1browser++;
							
							String spro="p2";
							
							
							String client = "C1";
							
							stmt2.executeUpdate("insert into multicore.process values('c1Task4.exe','"+client+"',"+browser+","+c1browser+",'"+spro+"')");
							
							
							
						//	p2cnbrowser++;
						//	p2albrowser.add(p2cnbrowser);
							
							
					//		stmt2.executeUpdate("insert into multicore.process values('c1Task4.exe','"+client+"',"+racecar+","+p2albrowser.get(p2cnbrowser-1)+")");
							
							
							
							
							int updatespace = avaspace-browser;
							int upocc = ocspace+browser;
							
							
							stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core2'");	
							
							
							
							}
					 
				}
				
				
				
				else
				{
					ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
					
					
					
				if(rs1.next()==true)
				{
					
						int avaspace1 =rs1.getInt("freespace");
						int ocspace1 = rs1.getInt("occupyspace");
						
					if(avaspace1>browser)
					{
						
						
						InetAddress loc = InetAddress.getLocalHost();
						Socket soc = new Socket(loc,4001);
						 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
						 
						 Packet pacfor = new Packet();
						// pacfor.setAction("word");
						 pacfor.setProcessAction("start");
						 pacfor.setProcess("C1_Task4 is executing");
						  dowoutput.writeObject(pacfor);
						  
						  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
							Packet rpacfor=(Packet) in.readObject();
							
							if(rpacfor.getAction().equals("Ok"))
							{
								TaskScheduler.jta.append("C1_Task4 is running....\n");
								
								
								c2browser++;
								String spro="p1";
								
								String client ="C1";
								
								stmt2.executeUpdate("insert into multicore.process values('c1Task4.exe','"+client+"',"+browser+","+c2browser+",'"+spro+"')");
								
							
								
								
								int updatespace1 = avaspace1 - browser;
								int upocc1 = ocspace1+browser;
								stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");		
								
							}
						
						
						
					  }
					
					else{
						
					//	waitingid++;
						
					//	stmt2.executeUpdate("insert into multicore.waiting values('c1Task4.exe','"+waitingid+"','waiting')");
					//	TaskScheduler.jta.append("C1 c1Task4.exe is waiting for scheduling....");
						
					}
					
					
				}
					
			}
				
						
				}
			

		}   
			
			
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C1_Task5  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			
		else if(getprocess.equals("c1Task5.exe"))
		{
			
			
			ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
			
			if(rs.next()==true)
			{
				
				int avaspace =rs.getInt("freespace");
				int ocspace = rs.getInt("occupyspace");
				
				if(avaspace>eclipse)
				{
					
					
					InetAddress loc = InetAddress.getLocalHost();
					Socket soc1 = new Socket(loc,4002);
					 ObjectOutputStream dowoutput1 = new ObjectOutputStream(soc1.getOutputStream());
					 
				
					 
					 Packet pacfor1 = new Packet();
					 
					 pacfor1.setProcessAction("start");
					 pacfor1.setProcess("C1_Task5 is executing");
					  dowoutput1.writeObject(pacfor1);
					  
				
					  
					  ObjectInputStream in1=new ObjectInputStream(soc1.getInputStream());
					  
					//  System.out.println("Checking browser with socket");
						
					  Packet rpacfor=(Packet)in1.readObject();
						
						 
						
						if(rpacfor.getAction().equals("Ok"))
						{
							TaskScheduler.jta.append("C1_Task5 scheduled and running....\n");
							
							
							c1eclipse++;
							
							String spro="p2";
							
							String client = "C1";
							
							stmt2.executeUpdate("insert into multicore.process values('c1Task5.exe','"+client+"',"+eclipse+","+c1eclipse+",'"+spro+"')");
							
							
						//	stmt2.executeUpdate("insert into multicore.process values('c1Task5.exe','"+client+"',"+eclipse+")");
							
							
							int updatespace = avaspace-eclipse;
							int upocc = ocspace+eclipse;
							
							
							stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core2'");
							
							
							stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
							
							
							
							}
					 
				}
				
				
				
				else
				{
					ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
					
					
					
				if(rs1.next()==true)
				{
					
						int avaspace1 =rs1.getInt("freespace");
						int ocspace1 = rs1.getInt("occupyspace");
						
					if(avaspace1>eclipse)
					{
						
						
						InetAddress loc = InetAddress.getLocalHost();
						Socket soc = new Socket(loc,4001);
						 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
						 
						 Packet pacfor = new Packet();
						// pacfor.setAction("word");
						 pacfor.setProcessAction("start");
						 pacfor.setProcess("C1_Task5 is executing");
						  dowoutput.writeObject(pacfor);
						  
						  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
							Packet rpacfor=(Packet) in.readObject();
							
							if(rpacfor.getAction().equals("Ok"))
							{
								TaskScheduler.jta.append("C1_Task5 is running....\n");
								
								
								c2eclipse++;
								
								String spro="p1";
								
								String client = "C1";
								
								stmt2.executeUpdate("insert into multicore.process values('c1Task5.exe','"+client+"',"+eclipse+","+c2eclipse+",'"+spro+"')");
								
								
								
								
							//	stmt2.executeUpdate("insert into multicore.process values('c1Task5.exe','"+client+"',"+eclipse+")");
								
								
								int updatespace1 = avaspace1 - eclipse;
								int upocc1 = ocspace1+eclipse;
								stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");	
								
								
								stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
								
							}
						
						
						
					  }
					
					else{
						
					//	waitingid++;
						
					//	stmt2.executeUpdate("insert into multicore.waiting values('c1Task5.exe','"+waitingid+"','waiting')");
					//	TaskScheduler.jta.append("C1 c1Task5.exe is waiting for scheduling....");
						
					}
					
					
				}
					
			}
				
				
				
				
				
				
				
				
					
					
				}
		
			
			
			
			
			
		}
			
		
			
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C2_Task1 P  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			
			
		else if(getprocess.equals("c2Task1.exe"))
		{
			
			
			ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
			
			if(rs.next()==true)
			{
				
				int avaspace =rs.getInt("freespace");
				int ocspace = rs.getInt("occupyspace"); 
				
				if(avaspace>word)
					
				{
					InetAddress loc = InetAddress.getLocalHost();
					Socket soc = new Socket(loc,4002);
					 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
					 
					 Packet pacfor = new Packet();
					 pacfor.setProcessAction("start");
					 pacfor.setProcess("C2_Task1 is executing");
					  dowoutput.writeObject(pacfor);
					  
					  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
						Packet rpacfor=(Packet) in.readObject();
						
						if(rpacfor.getAction().equals("Ok"))
						{
							TaskScheduler.jta.append("C2_Task1 sheduled and running....\n");
							
							
							c1point++;
							String spro="p2";
							
							String client ="C2";
							
							
							stmt2.executeUpdate("insert into multicore.process values('c2Task1.exe','"+client+"',"+word+","+c1point+",'"+spro+"')");
							
							
							int updatespace = avaspace - word;
							int upocc = ocspace+word;
							
							stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core2'");	
							
							
							stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
							}
						
						
						
				}
		
				
				else
				{
					ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
					
					
					
				if(rs1.next()==true)
				{
					
						int avaspace1 =rs1.getInt("freespace");
						int ocspace1 = rs1.getInt("occupyspace"); 
						
					if(avaspace1>word)
					{
						
						
						InetAddress loc = InetAddress.getLocalHost();
						Socket soc = new Socket(loc,4001);
						 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
						 
						 Packet pacfor = new Packet();
						// pacfor.setAction("word");
						 pacfor.setProcessAction("start");
						 pacfor.setProcess("C2_Task1 is executing");
						  dowoutput.writeObject(pacfor);
						  
						  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
							Packet rpacfor=(Packet) in.readObject();
							
							if(rpacfor.getAction().equals("Ok"))
							{
								TaskScheduler.jta.append("C2_Task1 scheduled and running....");
								
								
								
								c2point++;
								String spro="p1";
								
								String client = "C2";
								
								
								stmt2.executeUpdate("insert into multicore.process values('c2Task1.exe','"+client+"',"+word+","+c2point+",'"+spro+"')");
								
							//	stmt2.executeUpdate("insert into multicore.process values('c2Task1.exe','"+client+"',"+word+")");
								
								
								int updatespace1 = avaspace1 - word;
								int upocc1 = ocspace1+word;
								stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");	
								
								
								stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
								
							}
						
						
						
					  }
					
					else{
						
					//	waitingid++;
						
					
					//	stmt2.executeUpdate("insert into multicore.waiting values('c2Task1.exe','"+waitingid+"','waiting')");
					//	TaskScheduler.jta.append("C2 c1task1.exe is waiting for scheduling....");
						
						
					}
					
					
				}
					
			}		
			
			
			
			
		}
			
		}
			
			
			
			
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%   C2_Task2 stopped %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
			
		else if(getprocess.equals("c2Task2.exe"))
		{
			
			
			ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
			
			if(rs.next()==true)
			{
				
				int avaspace =rs.getInt("freespace");
				int ocspace = rs.getInt("occupyspace"); 
				
				if(avaspace>music)
					
				{
					InetAddress loc = InetAddress.getLocalHost();
					Socket soc = new Socket(loc,4003);
					 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
					 
					 Packet pacfor = new Packet();
					 pacfor.setProcessAction("start");
					 pacfor.setProcess("C2_Task2 is executing");
					  dowoutput.writeObject(pacfor);
					  
					  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
						Packet rpacfor=(Packet) in.readObject();
						
						if(rpacfor.getAction().equals("Ok"))
						{
							TaskScheduler.jta.append("C2_Task2 scheduled and running....\n");
							
							
							
							c1music++;
							String spro="p3";
							
							String client="C2";
							
							
							stmt2.executeUpdate("insert into multicore.process values('c2Task2.exe','"+client+"',"+music+","+c1music+",'"+spro+"')");
							
							
							
						//	stmt2.executeUpdate("insert into multicore.process values('c2Task2.exe','"+client+"',"+music+")");
							
							
							int updatespace = avaspace - music;
							int upocc = ocspace+music;
							
							stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core3'");
							
							stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
							}
						
						
						
				}
		
				
				else
				{
					ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
					
					
					
				if(rs1.next()==true)
				{
					
						int avaspace1 =rs1.getInt("freespace");
						int ocspace1 = rs1.getInt("occupyspace"); 
						
					if(avaspace1>music)
					{
						
						
						InetAddress loc = InetAddress.getLocalHost();
						Socket soc = new Socket(loc,4001);
						 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
						 
						 Packet pacfor = new Packet();
						// pacfor.setAction("word");
						 pacfor.setProcessAction("start");
						 pacfor.setProcess("C2_Task2 is executing");
						  dowoutput.writeObject(pacfor);
						  
						  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
							Packet rpacfor=(Packet) in.readObject();
							
							if(rpacfor.getAction().equals("Ok"))
							{
								TaskScheduler.jta.append("C2_Task2 scheduled and running....");
								
								c2music++;
								String spro="p1";
								
								String client = "C2";
								
								
								stmt2.executeUpdate("insert into multicore.process values('c2Task2.exe','"+client+"',"+music+","+c2music+",'"+spro+"')");
								
						//		stmt2.executeUpdate("insert into multicore.process values('c2Task2.exe','"+client+"',"+music+")");
								
								
								int updatespace1 = avaspace1 - music;
								int upocc1 = ocspace1+music;
								stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");	
								
								
								stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
								
							}
						
						
						
					  }
					
					else{
						
					//	waitingid++;
					
					//	stmt2.executeUpdate("insert into multicore.waiting values('c2Task2.exe','"+waitingid+"','waiting')");
					//	TaskScheduler.jta.append("C2 c2Task2.exe is waiting for scheduling....");
						
						
					}
					
					
				}
					
			}		
			
			
			
			
		}
			
			
			
			
			
			
		}
			
			
			
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	waiting C2_Task3  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			
			
			
		else if(getprocess.equals("c2Task3.exe"))
		{
			
			
			
			ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
			
			if(rs.next()==true)
			{
				
				int avaspace =rs.getInt("freespace");
				int ocspace = rs.getInt("occupyspace"); 
				
				if(avaspace>calculator)
					
				{
					InetAddress loc = InetAddress.getLocalHost();
					Socket soc = new Socket(loc,4001);
					 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
					 
					 Packet pacfor = new Packet();
					 pacfor.setProcessAction("start");
					 pacfor.setProcess("C2_Task3 is executing");
					  dowoutput.writeObject(pacfor);
					  
					  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
						Packet rpacfor=(Packet) in.readObject();
						
						if(rpacfor.getAction().equals("Ok"))
						{
							TaskScheduler.jta.append("C2_Task3 scheduled and running....\n");
							
							
							c1calc++;
							String spro="p1";
							
							String client ="C2";
							
							
							stmt2.executeUpdate("insert into multicore.process values('c2Task3.exe','"+client+"',"+calculator+","+c1calc+",'"+spro+"')");
							
						//	stmt2.executeUpdate("insert into multicore.process values('c2Task3.exe','"+client+"',"+calculator+")");
							
							
							int updatespace = avaspace - calculator;
							int upocc = ocspace+calculator;
							
							stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core1'");	
							
							
							stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
							}
						
						
						
				}
		
				
				else
				{
					ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
					
					
					
				if(rs1.next()==true)
				{
					
						int avaspace1 =rs1.getInt("freespace");
						int ocspace1 = rs1.getInt("occupyspace"); 
						
					if(avaspace1>calculator)
					{
						
						
						InetAddress loc = InetAddress.getLocalHost();
						Socket soc = new Socket(loc,4002);
						 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
						 
						 Packet pacfor = new Packet();
						// pacfor.setAction("word");
						 pacfor.setProcessAction("start");
						 pacfor.setProcess("C2_Task3 is executing");
						  dowoutput.writeObject(pacfor);
						  
						  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
							Packet rpacfor=(Packet) in.readObject();
							
							if(rpacfor.getAction().equals("Ok"))
							{
								TaskScheduler.jta.append("C2_Task3 scheduled and running....");
								
								c2calc++;
								String spro="p2";
								
								String client ="C2";
								
								stmt2.executeUpdate("insert into multicore.process values('c2Task3.exe','"+client+"',"+calculator+","+c2calc+",'"+spro+"')");
								
							//	stmt2.executeUpdate("insert into multicore.process values('c2Task3.exe','"+client+"',"+calculator+")");
								
								
								int updatespace1 = avaspace1 - calculator;
								int upocc1 = ocspace1+calculator;
								stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core2'");
								
								
								stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
								
							}
						
						
						
					  }
					
					else{
						
						
					//	waitingid++;
					
					//	stmt2.executeUpdate("insert into multicore.waiting values('c2Task3.exe','"+waitingid+"','waiting')");
					//	TaskScheduler.jta.append("C2 c2Task3.exe is waiting for scheduling....");
					//	
						
					}
					
					
				}
					
			}		
			
			
			
			
		}
			
			
			
			
		}
			
			
		
			
			
			
			
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% waiting  C3_Task1  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
			
			
			
		else if(getprocess.equals("c3Task1.exe"))
		{
			
			
			ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
			
			if(rs.next()==true)
			{
				
				int avaspace =rs.getInt("freespace");
				int ocspace = rs.getInt("occupyspace"); 
				
				if(avaspace>notepad)
					
				{
					InetAddress loc = InetAddress.getLocalHost();
					Socket soc = new Socket(loc,4001);
					 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
					 
					 Packet pacfor = new Packet();
					 pacfor.setProcessAction("start");
					 pacfor.setProcess("C3_Task1 is executing");
					  dowoutput.writeObject(pacfor);
					  
					  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
						Packet rpacfor=(Packet) in.readObject();
						
						if(rpacfor.getAction().equals("Ok"))
						{
							TaskScheduler.jta.append("C3_Task1 scheduled and running....\n");
							
							
							c1notepad++;
							String spro="p1";
							
							String client = "C3";
							
							
							stmt2.executeUpdate("insert into multicore.process values('c3Task1.exe','"+client+"',"+notepad+","+c1notepad+",'"+spro+"')");
							
							
							
						//	stmt2.executeUpdate("insert into multicore.process values('c3Task1.exe','"+client+"',"+notepad+")");
							
							
							int updatespace = avaspace - notepad;
							int upocc = ocspace+notepad;
							
							stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core1'");	
							
							
							
							stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
							
							}
						
						
						
				}
		
				
				else
				{
					ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
					
					
					
				if(rs1.next()==true)
				{
					
						int avaspace1 =rs1.getInt("freespace");
						int ocspace1 = rs1.getInt("occupyspace"); 
						
					if(avaspace1>notepad)
					{
						
						
						InetAddress loc = InetAddress.getLocalHost();
						Socket soc = new Socket(loc,4003);
						 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
						 
						 Packet pacfor = new Packet();
						// pacfor.setAction("word");
						 pacfor.setProcessAction("start");
						 pacfor.setProcess("C3_Task1 is executing");
						  dowoutput.writeObject(pacfor);
						  
						  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
							Packet rpacfor=(Packet) in.readObject();
							
							if(rpacfor.getAction().equals("Ok"))
							{
								TaskScheduler.jta.append("C3_Task1 is running....");
								
								c2notepad++;
								String spro="p3";
								
								String client = "C3";
								
								
								stmt2.executeUpdate("insert into multicore.process values('c3Task1.exe','"+client+"',"+notepad+","+c2notepad+",'"+spro+"')");
								
								
							//	stmt2.executeUpdate("insert into multicore.process values('c3Task1.exe','"+client+"',"+notepad+")");
								
								
								int updatespace1 = avaspace1 - notepad;
								int upocc1 = ocspace1+notepad;
								stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core3'");	
								
								stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
								
							}
						
						
						
					  }
					
					else{
						
					//	waitingid++;
						
					
					//	stmt2.executeUpdate("insert into multicore.waiting values('c3Task1.exe','"+waitingid+"','waiting')");
					//	TaskScheduler.jta.append("C3 c3Task1.exe is waiting for scheduling....");
						
						
					}
					
					
				}
					
			}		
			
			
			
			
		}
			
			
			
			
		}
			
			
			
			
			
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% waiting C3_Task2 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			
			
		else if(getprocess.equals("c3Task2.exe"))
			
		{
			
			ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core3'");
			
			if(rs.next()==true)
			{
				
				int avaspace =rs.getInt("freespace");
				int ocspace = rs.getInt("occupyspace"); 
				
				if(avaspace>paint)
					
				{
					InetAddress loc = InetAddress.getLocalHost();
					Socket soc = new Socket(loc,4003);
					 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
					 
					 Packet pacfor = new Packet();
					 pacfor.setProcessAction("start");
					 pacfor.setProcess("C3_Task2 is executing");
					  dowoutput.writeObject(pacfor);
					  
					  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
						Packet rpacfor=(Packet) in.readObject();
						
						if(rpacfor.getAction().equals("Ok"))
						{
							TaskScheduler.jta.append("C3_Task2 scheduled and running....\n");
							
							
							c1paint++;
							String spro="p3";
							
							String client = "C3";
							
							
							stmt2.executeUpdate("insert into multicore.process values('c3Task2.exe','"+client+"',"+paint+","+c1paint+",'"+spro+"')");
							
							
						//	stmt2.executeUpdate("insert into multicore.process values('c3Task2.exe','"+client+"',"+paint+")");
							
							
							int updatespace = avaspace - paint;
							int upocc = ocspace+paint;
							
							stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core3'");
							
							
							stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
							}
						
						
						
				}
		
				
				else
				{
					ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
					
					
					
				if(rs1.next()==true)
				{
					
						int avaspace1 =rs1.getInt("freespace");
						int ocspace1 = rs1.getInt("occupyspace"); 
						
					if(avaspace1>paint)
					{
						
						
						InetAddress loc = InetAddress.getLocalHost();
						Socket soc = new Socket(loc,4001);
						 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
						 
						 Packet pacfor = new Packet();
						// pacfor.setAction("word");
						 pacfor.setProcessAction("start");
						 pacfor.setProcess("C3_Task2 is executing");
						  dowoutput.writeObject(pacfor);
						  
						  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
							Packet rpacfor=(Packet) in.readObject();
							
							if(rpacfor.getAction().equals("Ok"))
							{
								TaskScheduler.jta.append("C3_Task2 is running....");
								
								
								
								c2paint++;
								String spro="p1";
								
								String client = "C3";
								
								
								stmt2.executeUpdate("insert into multicore.process values('c3Task2.exe','"+client+"',"+paint+","+c2paint+",'"+spro+"')");
								
							//	stmt2.executeUpdate("insert into multicore.process values('c3Task2.exe','"+client+"',"+paint+")");
								
								
								int updatespace1 = avaspace1 - paint;
								int upocc1 = ocspace1+paint;
								stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");	
								
								
								stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
								
							}
						
						
						
					  }
					
					else{
						
					//	waitingid++;
						
					
					//	stmt2.executeUpdate("insert into multicore.waiting values('c3Task2.exe','"+waitingid+"','waiting')");
					//	TaskScheduler.jta.append("C3 c3Task2.exe is waiting for scheduling....");
						
						
					}
					
					
				}
					
			}		
			
			
			
			
		}
			
			
		}
			
			
			
			
			
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% waiting C3_Task3 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	
			
			
		else if(getprocess.equals("c3Task3.exe"))
			
		{
			
			
			ResultSet rs = stmt1.executeQuery("Select * from multicore.usagememory where pname='core2'");
			
			if(rs.next()==true)
			{
				
				int avaspace =rs.getInt("freespace");
				int ocspace = rs.getInt("occupyspace"); 
				
				if(avaspace>excel)
					
				{
					InetAddress loc = InetAddress.getLocalHost();
					Socket soc = new Socket(loc,4002);
					 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
					 
					 Packet pacfor = new Packet();
					 pacfor.setProcessAction("start");
					 pacfor.setProcess("C3_Task3 is executing");
					  dowoutput.writeObject(pacfor);
					  
					  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
						Packet rpacfor=(Packet) in.readObject();
						
						if(rpacfor.getAction().equals("Ok"))
						{
							TaskScheduler.jta.append("C3_Task3 is running....\n");
							
							c1excel++;
							String spro="p2";
							
							String client = "C3";
							
							
							stmt2.executeUpdate("insert into multicore.process values('c3Task3.exe','"+client+"',"+excel+","+c1excel+",'"+spro+"')");
							
							
							
							
						//	stmt2.executeUpdate("insert into multicore.process values('c3Task3.exe','"+client+"',"+excel+")");
							
							
							int updatespace = avaspace - excel;
							int upocc = ocspace+excel;
							
							stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace+",occupyspace="+upocc+" where pname='core2'");
							
							
							
							
							stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
							
							}
						
						
						
				}
		
				
				else
				{
					ResultSet rs1 = stmt1.executeQuery("Select * from multicore.usagememory where pname='core1'");
					
					
					
				if(rs1.next()==true)
				{
					
						int avaspace1 =rs1.getInt("freespace");
						int ocspace1 = rs1.getInt("occupyspace"); 
						
					if(avaspace1>excel)
					{
						
						
						InetAddress loc = InetAddress.getLocalHost();
						Socket soc = new Socket(loc,4001);
						 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
						 
						 Packet pacfor = new Packet();
						// pacfor.setAction("word");
						 pacfor.setProcessAction("start");
						 pacfor.setProcess("C3_Task3 is executing");
						  dowoutput.writeObject(pacfor);
						  
						  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
							Packet rpacfor=(Packet) in.readObject();
							
							if(rpacfor.getAction().equals("Ok"))
							{
								TaskScheduler.jta.append("C3_Task3 is running....");
								
								
								c2excel++;
								String spro="p1";
								
								String client = "C3";
								
								
								stmt2.executeUpdate("insert into multicore.process values('c3Task3.exe','"+client+"',"+excel+","+c2excel+",'"+spro+"')");
								
							//	stmt2.executeUpdate("insert into multicore.process values('c3Task2.exe','"+client+"',"+paint+")");
								
								
								int updatespace1 = avaspace1 - excel;
								int upocc1 = ocspace1+excel;
								stmt1.executeUpdate("update multicore.usagememory set freespace="+updatespace1+",occupyspace="+upocc1+" where pname='core1'");	
								
								
								
								
								stmt2.executeUpdate("delete from waiting where prtid='"+minprtid+"'");
								
							}
						
						
						
					  }
					
					else{
						
					//	waitingid++;
						
					
					//	stmt2.executeUpdate("insert into multicore.waiting values('c3Task3.exe','"+waitingid+"','waiting')");
					//	TaskScheduler.jta.append("C3 c3Task3.exe is waiting for scheduling....");
						
						
					}
					
					
				}
					
			}		
			
			
			
			
		}
		
			
			
		}
			
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% All waiting task process is finished %%%%%%%%%%%%%%%%%%%%%%%%%%
			
			
			
			
			
			
			
		}
		
		}
		catch(Exception ex)
		{
			
			
			ex.printStackTrace();
		}
		
	}
	
	
	
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  Cyclic Task     %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
	
	
	
	
}












class ForDate implements Runnable{
	
	public void run(){
		
		System.out.println(" Now it shos current Data and time: ");
		
		try{
		Thread.sleep(2000);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			
		}
		
		for(int i=1; i<=15; i++)
		{
			
			try{
			
				
				Thread.sleep(15000);
				
		DateFormat data = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		Date date = new Date();
		
		String ddd = data.format(date);
		
		TaskScheduler.jta.append("Regular  task is running....\n");
		
		InetAddress loc = InetAddress.getLocalHost();
		Socket soc = new Socket(loc,4002);
		 ObjectOutputStream dowoutput = new ObjectOutputStream(soc.getOutputStream());
		 
		 Packet pacfor = new Packet();
		 pacfor.setProcessAction("start");
		 pacfor.setProcess("Regular  processing ");
	//	 pacfor.setDate(ddd);
		  dowoutput.writeObject(pacfor);
		  
		  ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
			Packet rpacfor=(Packet) in.readObject();
			
			if(rpacfor.getAction().equals("Ok"))
			{
				
				
				TaskScheduler.jta.append("Regular  task finished....\n");
				
				
				
			}
		
		
		
		
		
		
		
		
		
		
		System.out.println("Current Data and Time: "+data.format(date));
		
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		
		}
		
		
	}
}


