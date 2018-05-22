package com.task;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


import java.net.*;
import java.sql.*;
import java.util.*;
import java.lang.*;
//import com.scheduler.TaskScheduler;

public class CTask extends JFrame{
	
	Toolkit tk;
	Dimension dim;
	Container con;
	JPanel pan1,pan2,taskpanel,processpanel;
	
	JLabel title,l1,l2,l3;
	JTextField jtf1,jtf2;
	JPasswordField jpf1;
	JButton jbut1,jbut2;
	static JTextArea jta;
	static JTextArea jta1,jta2,jta3;
	static JScrollPane jsp;
	static JScrollPane jsp1,jsp2,jsp3;
	static JList list;
	JLabel jbl1,jbl2,jbl3;
	
	JMenuBar mbtask1, mbtask2, mbtask3;
	JMenu mtask1, mtask2, mtask3;
	JMenuItem mitask1, mitask2, mitask3;
	
	
	public CTask()
	{
		
		
try{
			
			this.setTitle("SCHEDULER");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			dim=tk.getDefaultToolkit().getScreenSize();
			
			
		//	new Listen();
			
			con=this.getContentPane();
			con.setBackground(new Color(255,240,245));
			con.setLayout(null);
			
			pan1=new JPanel();
			pan1.setBounds(0,0,dim.width,100);
			pan1.setBackground(new Color(248,248,255));
			
			
			title=new JLabel("TASK SCHEDULER",SwingConstants.CENTER);
			title.setFont(new Font("Verdana",Font.BOLD,20));
			title.setForeground(new Color(105,105,105));
			pan1.setLayout(new BorderLayout());
			
			jta=new JTextArea();
			jsp=new JScrollPane(jta);
			jsp.setBounds(300,100,dim.width-700,dim.height-170);
			jsp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"SCHEDULER INFORMATION",2,3,new Font("Verdana",Font.BOLD,15),Color.black));
			jsp.setBackground(new Color(85,85,85));
		//	jsp.setVisible(false);
			jta.setEditable(false);
			
			
		    jta.append("Task Scheduler is ready and waiting for request...."+"\n");
		    
		    
		    
		    taskpanel = new JPanel();
			taskpanel.setBounds(0,100,300,dim.height-170);
			taskpanel.setBackground(new Color(85,85,85));
			taskpanel.setLayout(null);
			con.add(taskpanel);
			
			
			processpanel = new JPanel();
			processpanel.setBounds(dim.width-400,100,400,dim.height-170);
			processpanel.setBackground(new Color(85,85,85));
			processpanel.setLayout(null);
			con.add(processpanel); 
			
			
			//////////////////////////////////////////////////////Task assigner/////////////////////
			
			
			jbl1= new JLabel(new ImageIcon("index.jpg"));
			jbl1.setBounds(80,20,120,100);
			taskpanel.add(jbl1);
			
			mbtask1=new JMenuBar();
			mbtask1.setBounds(80,130,100,30);
			mbtask1.setLayout(null);
			taskpanel.add(mbtask1);
	        
	        mtask1 = new JMenu("Choose Process");
	        mtask1.setBounds(0,0,100,30);
	        mtask1.setLayout(null);
	    	mbtask1.add(mtask1);
	    	
	    	
	    	mitask1 = new JMenuItem("Open Word");
	    	mitask1.setBounds(0,5,100,40);
	    	mitask1.setBackground(new Color(174,198,207));
	    	mtask1.add(mitask1);
	    	
	    	
	    	mitask1 = new JMenuItem("Play Video");
	    	mitask1.setBounds(0,5,100,40);
	    	mtask1.add(mitask1);
	    	
	    	
	    	mitask1 = new JMenuItem("Open Browser");
	    	mitask1.setBounds(0,5,100,40);
	    	mtask1.add(mitask1);
	    	
	    	mitask1 = new JMenuItem("Open Racecar");
	    	mitask1.setBounds(0,5,100,40);
	    	mtask1.add(mitask1);
	        
	      	mitask1 = new JMenuItem("Eclipse");
	    	mitask1.setBounds(0,5,100,40);
	    	mtask1.add(mitask1);
			
	    	
	    	
			
			///////////////////////////////////////////////////////////////////////////////////////
			
	    	
	    	
	    	jbl2= new JLabel(new ImageIcon("index.jpg"));
			jbl2.setBounds(80,210,120,100);
			taskpanel.add(jbl2);
			
			mbtask2=new JMenuBar();
			mbtask2.setBounds(80,320,100,30);
			mbtask2.setLayout(null);
			taskpanel.add(mbtask2);
	        
	        mtask2 = new JMenu("Choose Process");
	        mtask2.setBounds(0,0,100,30);
	        mtask2.setLayout(null);
	    	mbtask2.add(mtask2);
	    	
	    	
	    	mitask2 = new JMenuItem("Open Word");
	    	mitask2.setBounds(0,5,100,40);
	    	mitask2.setBackground(new Color(174,198,207));
	    	mtask2.add(mitask2);
	    	
	    	
	    	mitask2 = new JMenuItem("Play Video");
	    	mitask2.setBounds(0,5,100,40);
	    	mtask2.add(mitask2);
	        
	    	
	    	
	    	mitask2 = new JMenuItem("Open Browser");
	    	mitask2.setBounds(0,5,100,40);
	    	mtask2.add(mitask2);
	    	
	    	mitask2 = new JMenuItem("Calculator");
	    	mitask2.setBounds(0,5,100,40);
	    	mtask2.add(mitask2);
	        
	      	mitask2 = new JMenuItem("Skype call");
	    	mitask2.setBounds(0,5,100,40);
	    	mtask2.add(mitask2);
	    	
	    	
	    	
	    	////////////////////////////////////////////////////////////////////////////////////
	    	
	    	jbl3= new JLabel(new ImageIcon("index.jpg"));
			jbl3.setBounds(80,390,120,100);
			taskpanel.add(jbl3);
			
			mbtask3=new JMenuBar();
			mbtask3.setBounds(80,500,100,30);
			mbtask3.setLayout(null);
			taskpanel.add(mbtask3);
	        
	        mtask3 = new JMenu("Choose Process");
	        mtask3.setBounds(0,0,100,30);
	        mtask3.setLayout(null);
	    	mbtask3.add(mtask3);
	    	
	    	
	    	mitask3 = new JMenuItem("Downloading");
	    	mitask3.setBounds(0,5,100,40);
	    	mitask3.setBackground(new Color(174,198,207));
	    	mtask3.add(mitask3);
	    	
	    	
	    	mitask3 = new JMenuItem("Play Video");
	    	mitask3.setBounds(0,5,100,40);
	    	mtask3.add(mitask3);
	        
	    	
	    	
	    	
	    	////////////////////////////////////////////////////////////////////////////////////
	    	
	    	
	    	jta1=new JTextArea();
			jta1.setEditable(false);
			jsp1=new JScrollPane(jta1);
			jsp1.setBounds(50,10,300,180);
			jsp1.setBackground(new Color(85,85,85));
			jsp1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"PROCESS INFORMATION",2,3,new Font("Georgia",Font.BOLD,12),Color.black));
			processpanel.add(jsp1);
			
			
			jta2=new JTextArea();
			jta2.setEditable(false);
			jsp2=new JScrollPane(jta2);
			jsp2.setBounds(50,200,300,180);
			jsp2.setBackground(new Color(85,85,85));
			jsp2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"PROCESS INFORMATION",2,3,new Font("Georgia",Font.BOLD,12),Color.black));
			processpanel.add(jsp2);
			
			
			jta3=new JTextArea();
			jta3.setEditable(false);
			jsp3=new JScrollPane(jta3);
			jsp3.setBounds(50,400,300,180);
			jsp3.setBackground(new Color(85,85,85));
			jsp3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"PROCESS INFORMATION",2,3,new Font("Georgia",Font.BOLD,12),Color.black));
			processpanel.add(jsp3);
			
	    	
	    	
	    	
	    	
	    	
	    	
	    	///////////////////////////////////////////////////////////////////////////////////////
			
			
			jbut1=new JButton("Process");
			jbut1.setBounds(80,550,90,30);
			jbut1.updateUI();
			jbut1.setBackground(Color.lightGray);
			taskpanel.add(jbut1);
			
			
			list=new JList();
			jsp1=new JScrollPane(list);
			jsp1.setBounds (dim.width-200,100,200,dim.height-250);
			jsp1.setBorder (BorderFactory.createTitledBorder (BorderFactory.createRaisedBevelBorder (),"LIST OF CLIENTS",2,3,new Font("Verdana",Font.BOLD,15),Color.black));
			jsp1.setVisible (false);
			
			l1=new JLabel("UserName");
			l2=new JLabel("Password");
			
			jtf1=new JTextField();
			jpf1=new JPasswordField();
			jbut1=new JButton("Submit");
			jbut1.setMnemonic('S');
			
			l1.setBounds(350,300,100,20);jtf1.setBounds(500,300,100,20);
			l2.setBounds(350,350,100,20);jpf1.setBounds(500,350,100,20);
			jbut1.setBounds(400,400,150,20);
			jtf1.setText("admin");
			jpf1.setText("admin");
			
			
			
			
			
			
			
			pan2=new JPanel();
			pan2.setBounds(0,dim.height-70,dim.width,30);
			pan2.setBackground(new Color(248,248,255));
			
			pan1.add(title,BorderLayout.CENTER);
			con.add(pan1);
			con.add(jsp);
			
		
			con.add(pan2);
			
			this.setSize(dim);
			
		
			this.setVisible(true);
			
		
			
		/*	mitask1.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
					String task = mitask1.getActionCommand();
					
					System.out.println("task is "+ task);
					
					Socket soc = new Socket(ip,4000);
					
					ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("word");
					 pactotask.setClient("C1");
					 out.writeObject(pactotask); 
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			}
					
					
					);  */
			
			
			
			jbut1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					
					
				
					System.out.println("task performing");
				//	new Listen();
					
					
				}
			});
			
		/*	jbut1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					try
					{
						char[] ch=jpf1.getPassword();
						if(jtf1.getText().equals(String.valueOf(ch)))
						{
							JOptionPane.showMessageDialog(null,"TASK SCHEDULER SUCCESSFULLY STARTED ","SERVER REPLY",1);
							
							l1.setVisible(false);jtf1.setVisible(false);
							l2.setVisible(false);jpf1.setVisible(false);
							jsp.setVisible(true);jbut1.setVisible(false);
							jsp1.setVisible(true);
							
							System.out.println ("Waiting for the request....");
						    jta.append("Task Scheduler is ready and waiting for request...."+"\n");
						    
						//	new Listen();
						}else
						{
							JOptionPane.showMessageDialog(null,"SORRY INVALID DATA","SERVER REPLY",0);
							jtf1.setText("");
							jpf1.setText("");
							jtf1.requestFocus();
						}
						
					}catch(Exception e)
					{
						JOptionPane.showMessageDialog(null,"ERROR IN VALIDATING : "+ e);
					}
				}
			});   */
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		
	
	}
	
	public static void main(String args[])
	{
		CTask ts =	new CTask();
		
	}
	
	
	
}
