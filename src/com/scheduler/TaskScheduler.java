package com.scheduler;




import javax.swing.*;
import javax.swing.event.*;

import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.lang.*;
import java.io.*;
//import com.scheduler.MGraph;

import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Document;
import javax.swing.text.StyleConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class TaskScheduler extends JFrame {
	
	Toolkit tk;
	Dimension dim;
	Container con;
	JPanel pan1;
	JPanel panel;
	JPanel pan2,taskpanel,processpanel, taskexepanel;
	
	JLabel title,l1,l2,l3,ls,pan_lab1;
	JTextField jtf1,jtf2;
	JPasswordField jpf1;
	JButton jbutt1,jbutt2,jbut2;
	static JTextArea jta;
	static JTextArea jta1,jta2,jta3;
	static JScrollPane jsp;
	static JScrollPane jsp1,jsp2,jsp3;
	static JList list;
	JLabel jbl1,jbl2,jbl3,imagelb,imagelbb;
	
	JMenuBar mbtask1, mbtask2, mbtask3;
	JMenu mtask1, mtask2, mtask3;
//	JMenuItem m1task1, m1task2, m1task3, m1task4, m1task5;
	JMenuItem m2task1, m2task2, m2task3, m2task4, m2task5;
	JMenuItem m3task1, m3task2, m3task3, m3task4, m3task5;
	
	
	JMenu m1taskword;
	JMenuItem m1taskwordstart;
	JMenuItem m1taskwordstop;
	
	static int c1word=0;
	static int c1video=0;
	static int c1browser=0;
	static int c1bikerace=0;
	static int c1eclipse=0;
	
	int c2word=0;
	int c2music=0;
	int c2calc=0;
	int c2skype=0;
	
	
	
	int c3notepad=0;
	int c3paint=0;
	int c3excel=0;
	
 	
	
	
	
	JMenu m1taskvideo;
	JMenuItem m1taskvideostart;
	JMenuItem m1taskvideostop;
	
	
	JScrollPane tjsp;
	JList lst1;
	JTextPane pane;
	StyledDocument doc;
	private JLabel jbls;
	
	public TaskScheduler()
	{
		try{
			
			this.setTitle("PROCESSOR");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			dim=tk.getDefaultToolkit().getScreenSize();
			
			
			
		//	new Listen();
			
			
		//	new MGraph("Task Scheduling vs Multi CPU Utilization" ,"Multi-Cpu Utilization");
			
			con=this.getContentPane();
			con.setBackground(new Color(64,64,64));
			con.setLayout(null);
			
			pan1=new JPanel();
			pan1.setBounds(0,0,dim.width,100);
			pan1.setBackground(new Color(209,109,146));
			
			
			imagelb=new JLabel(new ImageIcon("aa.jpg"));
			imagelb.setBounds(0,0,dim.width,100);
			con.add(imagelb); 
			
		/*	title=new JLabel("EFFICIENT LOAD BALANCING",SwingConstants.CENTER);
			title.setFont(new Font("Monotype Corsiva",Font.BOLD,45));
			title.setForeground(new Color(105,105,105));
			imagelb.setLayout(new BorderLayout());
			imagelb.add(title);
	*/
			 title = new JLabel(" MULTI-PROCESSOR WITH EFFICIENT LOAD BALANCING",SwingConstants.CENTER);
	           title.setBounds(90,25,900,50);
	          title.setForeground(new Color(0,255,255));
	            title.setFont(new Font("Castellar", Font.BOLD,25));
	        
	            imagelb.add(title);
			
			//////
			
			jta=new JTextArea();
			
			
			imagelbb=new JLabel(new ImageIcon("back2.jpg"));
			imagelbb.setBounds(300,100,400,400);
			
				
			DefaultCaret caret = (DefaultCaret)jta.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			jta.setCaretPosition(jta.getDocument().getLength());
			
			jsp=new JScrollPane(jta);
			jsp.setBounds(350,130,dim.width-800,dim.height-200);
			jsp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"MULTI-PROCESSORS INFORMATION",2,3,new Font("Monotype Corsiva",Font.BOLD,15),Color.black));
			jsp.setBackground(new Color(119,136,153));
		//	jsp.setVisible(false);
			jta.setEditable(false);
			
			
		    jta.append("Task Scheduler is ready and waiting for request...."+"\n");
		    
		    
		    imagelbb.add(jsp);
			
		    taskpanel = new JPanel();
			taskpanel.setBounds(0,100,300,dim.height-170);
			taskpanel.setBackground(new Color(115,109,146));
			taskpanel.setLayout(null);
			con.add(taskpanel);
			
			
			imagelbb=new JLabel(new ImageIcon("back.jpg"));
			imagelbb.setBounds(0,0,300,dim.height-150);
			taskpanel.add(imagelbb);
			
			//imagelbb.add(taskpanel);
			
			
			processpanel = new JPanel();
			processpanel.setBounds(dim.width-400,100,400,dim.height-170);
			processpanel.setBackground(new Color(115,109,146));
			processpanel.setLayout(null);
			con.add(processpanel); 
			
			//imagelbb.add(processpanel);
			//////////////////////////////////////////////////////Task assigner/////////////////////
			
			
			jbl1= new JLabel(new ImageIcon("images.jpg"));
			jbl1.setBounds(80,20,100,100);
			imagelbb.add(jbl1);
			
			
			mbtask1=new JMenuBar();
			mbtask1.setBounds(80,120,100,30);
			mbtask1.setLayout(null);
			taskpanel.add(mbtask1);
	        
	        mtask1 = new JMenu("Select Process");
	        mtask1.setBounds(0,0,100,30);
	        mtask1.setLayout(null);
	    	mbtask1.add(mtask1);
	    	
	    	JMenu m1taskword = new JMenu("C1_Task1");
	    	m1taskword.setForeground(new Color(0,46,99));
	    	JMenuItem m1taskwordstart = new JMenuItem("Start");
	    	m1taskwordstart.setForeground(new Color(0,107,60));
	    	JMenuItem m1taskwordstop = new JMenuItem("Stop");
	    	m1taskwordstop.setForeground(new Color(150,0,24));
	    	
	    	
	    	
	    	mtask1.add(m1taskword);
	    	m1taskword.add(m1taskwordstart);
	    	m1taskword.add(m1taskwordstop);
	    	
	    	JMenu m1taskvideo = new JMenu("C1_Task2");
	    	m1taskvideo.setForeground(new Color(0,46,99));
	    	JMenuItem m1taskvideostart = new JMenuItem("Start");
	    	m1taskvideostart.setForeground(new Color(0,107,60));
	    	JMenuItem m1taskvideostop = new JMenuItem("Stop");
	    	m1taskvideostop.setForeground(new Color(150,0,24));
	    	
	    	
	    	mtask1.add(m1taskvideo);
	    	m1taskvideo.add(m1taskvideostart);
	    	m1taskvideo.add(m1taskvideostop);
	    	
	    	
	    	JMenu m1taskbrowser = new JMenu("C1_Task3");
	    	m1taskbrowser.setForeground(new Color(0,46,99));
	    	JMenuItem m1taskbrowserstart = new JMenuItem("Start");
	    	m1taskbrowserstart.setForeground(new Color(0,107,60));
	    	JMenuItem m1taskbrowserstop = new JMenuItem("Stop");
	    	m1taskbrowserstop.setForeground(new Color(150,0,24));
	    	
	    	
	    	mtask1.add(m1taskbrowser);
	    	m1taskbrowser.add(m1taskbrowserstart);
	    	m1taskbrowser.add(m1taskbrowserstop);
	    	
	    	
	    	
	    	
	    	JMenu m1taskrace = new JMenu("C1_Task4");
	    	m1taskrace.setForeground(new Color(0,46,99));
	    	JMenuItem m1taskracestart = new JMenuItem("Start");
	    	m1taskracestart.setForeground(new Color(0,107,60));
	    	JMenuItem m1taskracestop = new JMenuItem("Stop");
	    	m1taskracestop.setForeground(new Color(150,0,24));
	    	
	    	
	    	
	    	mtask1.add(m1taskrace);
	    	m1taskrace.add(m1taskracestart);
	    	m1taskrace.add(m1taskracestop);
	    	
	    	
	    	
	    	JMenu m1taskeclipse = new JMenu("C1_Task5");
	    	m1taskeclipse.setForeground(new Color(0,46,99));
	    	JMenuItem m1taskeclipsestart = new JMenuItem("Start");
	    	m1taskeclipsestart.setForeground(new Color(0,107,60));
	    	JMenuItem m1taskeclipsestop = new JMenuItem("Stop");
	    	m1taskeclipsestop.setForeground(new Color(150,0,24));
	    	
	    	mtask1.add(m1taskeclipse);
	    	m1taskeclipse.add(m1taskeclipsestart);
	    	m1taskeclipse.add(m1taskeclipsestop);
	    
	    		    	
	   
			
	    	
	    	
			
			///////////////////////////////////////Task Assigner////////////////////////////////////////////////
			
	    	
	    	
	    	jbl2= new JLabel(new ImageIcon("images.jpg"));
			jbl2.setBounds(80,200,100,100);
			imagelbb.add(jbl2);
			
			mbtask2=new JMenuBar();
			mbtask2.setBounds(80,300,100,30);
			mbtask2.setLayout(null);
			taskpanel.add(mbtask2);
	        
	        mtask2 = new JMenu("Select Process");
	        mtask2.setBounds(0,0,100,30);
	        mtask2.setLayout(null);
	    	mbtask2.add(mtask2);
	    	
	    	
	    	JMenu m2taskword = new JMenu("C2_Task1");
	    	m2taskword.setForeground(new Color(0,46,99));
	    	JMenuItem m2taskwordstart = new JMenuItem("Start");
	    	m2taskwordstart.setForeground(new Color(0,107,60));
	    	JMenuItem m2taskwordstop = new JMenuItem("Stop");
	    	m2taskwordstop.setForeground(new Color(150,0,24));
	    	
	    	
	    	
	    	mtask2.add(m2taskword);
	    	m2taskword.add(m2taskwordstart);
	    	m2taskword.add(m2taskwordstop);
	    	
	    	
	    	
	    	
	    	mtask2.add(m2taskword);
	    	m2taskword.add(m2taskwordstart);
	    	m2taskword.add(m2taskwordstop);
	    	
	    	
	    	
	    	JMenu m2taskmusic = new JMenu("C2_Task2");
	    	m2taskmusic.setForeground(new Color(0,46,99));
	    	JMenuItem m2taskmusicstart = new JMenuItem("Start");
	    	m2taskmusicstart.setForeground(new Color(0,107,60));
	    	JMenuItem m2taskmusicstop = new JMenuItem("Stop");
	    	m2taskmusicstop.setForeground(new Color(150,0,24));
	    	
	    	
	    	mtask2.add(m2taskmusic);
	    	m2taskmusic.add(m2taskmusicstart);
	    	m2taskmusic.add(m2taskmusicstop);
	    	
	    	
	    	
	    	JMenu m2taskcalc = new JMenu("C2_Task3");
	    	m2taskcalc.setForeground(new Color(0,46,99));
	    	JMenuItem m2taskcalcstart = new JMenuItem("Start");
	    	m2taskcalcstart.setForeground(new Color(0,107,60));
	    	JMenuItem m2taskcalcstop = new JMenuItem("Stop");
	    	m2taskcalcstop.setForeground(new Color(150,0,24));
	    	
	    	mtask2.add(m2taskcalc);
	    	m2taskcalc.add(m2taskcalcstart);
	    	m2taskcalc.add(m2taskcalcstop);
	    	
	    	JMenu m2tasksky = new JMenu("C2_Task4");
	    	m2tasksky.setForeground(new Color(0,46,99));
	    	JMenuItem m2taskskystart = new JMenuItem("Start");
	    	m2taskskystart.setForeground(new Color(0,107,60));
	    	JMenuItem m2taskskystop = new JMenuItem("Stop");
	    	m2taskskystop.setForeground(new Color(150,0,24));
	    	
	    	
	    	mtask2.add(m2tasksky);
	    	m2tasksky.add(m2taskskystart);
	    	m2tasksky.add(m2taskskystop);
	   
	    	///////////////////////////////////////Task Assigner////////////////////////////////////////////////
			
	    	
	    	jbl3= new JLabel(new ImageIcon("images.jpg"));
			jbl3.setBounds(80,398,100,100);
			imagelbb.add(jbl3);
			
			mbtask3=new JMenuBar();
			mbtask3.setBounds(80,500,100,30);
			mbtask3.setLayout(null);
			taskpanel.add(mbtask3);
	        
	        mtask3 = new JMenu("Choose Process");
	        mtask3.setBounds(0,0,100,30);
	        mtask3.setLayout(null);
	    	mbtask3.add(mtask3);
	    	
	    	JMenu m3tasknpad = new JMenu("C3_Task1");
	    	m3tasknpad.setForeground(new Color(0,46,99));
	    	JMenuItem m3tasknpadstart = new JMenuItem("Start");
	    	m3tasknpadstart.setForeground(new Color(0,107,60));
	    	JMenuItem m3tasknpadstop = new JMenuItem("Stop");
	    	m3tasknpadstop.setForeground(new Color(150,0,24));
	    	
	    	mtask3.add(m3tasknpad);
	    	m3tasknpad.add(m3tasknpadstart);
	    	m3tasknpad.add(m3tasknpadstop);
	    	
	    	
	    	JMenu m3taskpaint = new JMenu("C3_Task2");
	    	m3taskpaint.setForeground(new Color(0,46,99));
	    	JMenuItem m3taskpaintstart = new JMenuItem("Start");
	    	m3taskpaintstart.setForeground(new Color(0,107,60));
	    	JMenuItem m3taskpaintstop = new JMenuItem("Stop");
	    	m3taskpaintstop.setForeground(new Color(150,0,24));
	    	
	    	mtask3.add(m3taskpaint);
	    	m3taskpaint.add(m3taskpaintstart);
	    	m3taskpaint.add(m3taskpaintstop);
	    	
	    	
	    	
	    	JMenu m3taskexcel = new JMenu("C3_Task3");
	    	m3taskexcel.setForeground(new Color(0,46,99));
	    	JMenuItem m3taskexcelstart = new JMenuItem("Start");
	    	m3taskexcelstart.setForeground(new Color(0,107,60));
	    	JMenuItem m3taskexcelstop = new JMenuItem("Stop");
	    	m3taskexcelstop.setForeground(new Color(150,0,24));
	    	mtask3.add(m3taskexcel);
	    	m3taskexcel.add(m3taskexcelstart);
	    	m3taskexcel.add(m3taskexcelstop);
	    	
	    	
	    	////////////////////////////////////////////////////////////////////////////////////
	    	
	    	imagelbb=new JLabel(new ImageIcon("back1.jpg"));
			imagelbb.setBounds(0,-10,390,dim.height-150);
			processpanel.add(imagelbb);
	    	
	    	jta1=new JTextArea();
	 	    jta1.setEditable(false);
	    	
	    	DefaultCaret caret1 = (DefaultCaret)jta1.getCaret();
			caret1.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			jta1.setCaretPosition(jta1.getDocument().getLength());
	    	
			jsp1=new JScrollPane(jta1);
			jsp1.setBounds(20,10,350,180);  
			jsp1.setBackground(new Color(119,136,153));
			jsp1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"PROCESS INFORMATION",2,3,new Font("Monotype Corsiva",Font.BOLD,15),Color.black));
			imagelbb.add(jsp1);
			
			
			jta2=new JTextArea();
			jta2.setEditable(false);
			
			DefaultCaret caret2 = (DefaultCaret)jta2.getCaret();
			caret2.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			jta2.setCaretPosition(jta2.getDocument().getLength());
			
			jsp2=new JScrollPane(jta2);
			jsp2.setBounds(20,205,350,180);  
			jsp2.setBackground(new Color(119,136,153));
			jsp2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"PROCESS INFORMATION",2,3,new Font("Monotype Corsiva",Font.BOLD,15),Color.black));
			imagelbb.add(jsp2);
			
			
			jta3=new JTextArea();
			jta3.setEditable(false);
			
			DefaultCaret caret3 = (DefaultCaret)jta3.getCaret();
			caret3.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
			jta3.setCaretPosition(jta3.getDocument().getLength());
			
			jsp3=new JScrollPane(jta3);
			jsp3.setBounds(20,400,350,180); 
			jsp3.setBackground(new Color(119,136,153));
			jsp3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"PROCESS INFORMATION",2,3,new Font("Monotype Corsiva",Font.BOLD,15),Color.black));
			imagelbb.add(jsp3);
			
	    	
	    	
	    	
	    	
	    	
	    	
	    	///////////////////////////////////////////////////////////////////////////////////////
			
			
			jbutt1=new JButton("Running");
			
			jbutt1.setBounds(30,530,90,30);
		//	jbut1.updateUI();
			jbutt1.setBackground(Color.lightGray);
			taskpanel.add(jbutt1);
			
		/*	jbutt2=new JButton("Graph");
			jbutt2.setBounds(150,550,90,30);
		//	jbut1.updateUI();
			jbutt2.setBackground(Color.lightGray);
			taskpanel.add(jbutt2);
			
			*/
			
			list=new JList();
			jsp1=new JScrollPane(list);
			jsp1.setBounds (dim.width-200,100,200,dim.height-250);
			jsp1.setBorder (BorderFactory.createTitledBorder (BorderFactory.createRaisedBevelBorder (),"LIST OF CLIENTS",2,3,new Font("Adventure Subtitles",Font.BOLD,15),Color.black));
			jsp1.setVisible (false);
			
			
			
		
			
		//	l1=new JLabel("UserName");
		//	l2=new JLabel("Password");
			
		//	jtf1=new JTextField();
		//	jpf1=new JPasswordField();
		//	jbut1=new JButton("Submit");
		//	jbut1.setMnemonic('S');
			
	//		l1.setBounds(350,300,100,20);jtf1.setBounds(500,300,100,20);
	//		l2.setBounds(350,350,100,20);jpf1.setBounds(500,350,100,20);
		//	jbut1.setBounds(400,400,150,20);
		//	jtf1.setText("admin");
		//	jpf1.setText("admin");
			
			
			
			
			
			
			
			pan2=new JPanel();
			pan2.setBounds(0,dim.height-70,dim.width,30);
			pan2.setBackground(new Color(248,248,255));
			
		//	pan1.add(title,BorderLayout.CENTER);
			con.add(pan1);
			con.add(jsp);
			
		
			con.add(pan2);
			
			this.setSize(dim);
			
		
			this.show();
			
			
			
			new Listen();
			
			
			
			
		
	//////////////////////////////////////////////////////////////////////// C1 C1_Task1 //////////////////////////////////////////////////
			
			m1taskwordstart.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
						System.out.println("Great work");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
		//			String task = m1taskwordstart.getActionCommand();
					
		//			System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					// c1word++;
					 
					 pactotask.setAction("word");
					 pactotask.setClient("C1");
			//		 pactotask.setProcessId(c1word);
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
	/////////////////////////////////////////////////////////////// C1_Task1  process stop ///////////////////////////////////////////////////////	
		
			m1taskwordstop.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Great work");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
		//			String task = m1taskwordstart.getActionCommand();
					
		//			System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("c1wordstop");
				//	 pactotask.setAdditionAction("c1wordstop");
					 pactotask.setClient("C1");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
			
			
			
	/////////////////////////////////////////////////////////////// C1_Task2  process /////////////////////////////////////////////////////		
			
			m1taskvideostart.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
						System.out.println("Second workd");
					
					
					InetAddress ip1 = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip1);
					
			//		String task = m1taskvideostart.getActionCommand();
					
			//		System.out.println("task is "+ task);
					
					Socket soc2 = new Socket(ip1,4000);
					
					ObjectOutputStream out2 = new ObjectOutputStream(soc2.getOutputStream());
					 Packet pactotask1 = new Packet();
					 
					 pactotask1.setAction("video");
					 pactotask1.setClient("C1");
					 out2.writeObject(pactotask1);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
		
			
/////////////////////////////////////////////////////////////// C1_Task2  process ended /////////////////////////////////////////////////////	
			
	
			
			m1taskvideostop.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Great work");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
		//			String task = m1taskwordstart.getActionCommand();
					
		//			System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("c1videostop");
				//	 pactotask.setAdditionAction("c1videostop");
					 pactotask.setClient("C1");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
			
			
/////////////////////////////////////////////////////////////////C1_Task3  process /////////////////////////////////////		
			
			
			m1taskracestart.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
						System.out.println("Second workd");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
			//		String task = m1taskvideostart.getActionCommand();
					
			//		System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("game");
					 pactotask.setClient("C1");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
		
			
			
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C1_Task3 stopped %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			
			
			
			
			m1taskracestop.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Great work");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
		//			String task = m1taskwordstart.getActionCommand();
					
		//			System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("c1racecarstop");
				//	 pactotask.setAdditionAction("c1videostop");
					 pactotask.setClient("C1");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
			

			
///////////////////////////////////////////////////////////////	C1_Task4 	Browser /////////////////////////////////////////////////////////	
			
			m1taskbrowserstart.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Second workd");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
			//		String task = m1taskvideostart.getActionCommand();
					
			//		System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("browser");
					 pactotask.setClient("C1");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C1_Task4 stop %%%%%%%%%%%%%%%%%%%%%%%%%%%	
			
			
			m1taskbrowserstop.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Great work");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
		//			String task = m1taskwordstart.getActionCommand();
					
		//			System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("c1browserstop");
				//	 pactotask.setAdditionAction("c1videostop");
					 pactotask.setClient("C1");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
			
	////////////////////////////////////////////////////////////////C1_Task5 ///////////////////////////
			
		
			m1taskeclipsestart.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Second workd");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
			//		String task = m1taskvideostart.getActionCommand();
					
			//		System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("eclipse");
					 pactotask.setClient("C1");
					 out1.writeObject(pactotask);    
					 
					 System.out.println("I send eclipse Request");
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
		////////////////////////////////////////////////////////////   C1_Task5 stop /////////////////////////////////////	
		
			m1taskeclipsestop.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Great work");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
		//			String task = m1taskwordstart.getActionCommand();
					
		//			System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("c1eclipsestop");
				//	 pactotask.setAdditionAction("c1videostop");
					 pactotask.setClient("C1");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
			
	////////////////////////////////////////////////////////////////////////////////C2_Task1  /////////////////////////////////////////////////
			
			
			m2taskwordstart.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Second workd");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
			//		String task = m1taskvideostart.getActionCommand();
					
			//		System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("point2");
					 pactotask.setClient("C2");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C2_Task1  %%%%%%%%%%%%%%%%%%%%%%%%%%%//
			
			
			
			
			
			m2taskwordstop.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Great work");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
		//			String task = m1taskwordstart.getActionCommand();
					
		//			System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("c2pointstop");
				//	 pactotask.setAdditionAction("c1videostop");
					 pactotask.setClient("C2");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
			
			
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%C2_Task2 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//	
			
		
			m2taskmusicstart.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Second workd");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
			//		String task = m1taskvideostart.getActionCommand();
					
			//		System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("music2");
					 pactotask.setClient("C2");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
	
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C2_Task2  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			
			
			

			m2taskmusicstop.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Great work");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
		//			String task = m1taskwordstart.getActionCommand();
					
		//			System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("c2musicstop");
				//	 pactotask.setAdditionAction("c1videostop");
					 pactotask.setClient("C2");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
			
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C2_Task3 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//	
			
			
			m2taskcalcstart.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Second workd");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
			//		String task = m1taskvideostart.getActionCommand();
					
			//		System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("calc2");
					 pactotask.setClient("C2");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			

	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C2_Task3 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			
			
			
			m2taskcalcstop.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Great work");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
		//			String task = m1taskwordstart.getActionCommand();
					
		//			System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("c2calcstop");
				//	 pactotask.setAdditionAction("c1videostop");
					 pactotask.setClient("C2");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
	
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  C2_Task4  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//		
			
			m2taskskystart.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Second workd");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
			//		String task = m1taskvideostart.getActionCommand();
					
			//		System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("skype2");
					 pactotask.setClient("C2");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  C2_Task4 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
			
			
			
			m2taskskystop.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Great work");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
		//			String task = m1taskwordstart.getActionCommand();
					
		//			System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("c2skypestop");
				//	 pactotask.setAdditionAction("c1videostop");
					 pactotask.setClient("C2");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);

	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  	C3_Task1    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
			
			m3tasknpadstart.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Second workd");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
			//		String task = m1taskvideostart.getActionCommand();
					
			//		System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("notepad3");
					 pactotask.setClient("C3");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
	
			
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%	C3_Task1  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			
			
			
			
			m3tasknpadstop.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Great work");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
		//			String task = m1taskwordstart.getActionCommand();
					
		//			System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("c3npadstop");
				//	 pactotask.setAdditionAction("c1videostop");
					 pactotask.setClient("C3");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  C3_Task2  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
			
			
			
			m3taskpaintstart.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Second workd");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
			//		String task = m1taskvideostart.getActionCommand();
					
			//		System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("paint3");
					 pactotask.setClient("C3");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C3_Task2  stop %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			
			m3taskpaintstop.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Great work");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
		//			String task = m1taskwordstart.getActionCommand();
					
		//			System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("c3paintstop");
				//	 pactotask.setAdditionAction("c1videostop");
					 pactotask.setClient("C3");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
			
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% C3_Task3  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//
			
			
	
			
			m3taskexcelstart.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Second workd");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
			//		String task = m1taskvideostart.getActionCommand();
					
			//		System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("excel3");
					 pactotask.setClient("C3");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5C2_Task3 Stop %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
			
			
			
			
			
			m3taskexcelstop.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{	
						
						
						
					//	System.out.println("Great work");
					
					
					InetAddress ip = InetAddress.getLocalHost();
					
					System.out.println("task is "+ ip);
					
		//			String task = m1taskwordstart.getActionCommand();
					
		//			System.out.println("task is "+ task);
					
					Socket soc1 = new Socket(ip,4000);
					
					ObjectOutputStream out1 = new ObjectOutputStream(soc1.getOutputStream());
					 Packet pactotask = new Packet();
					 
					 pactotask.setAction("c3excelstop");
				//	 pactotask.setAdditionAction("c1videostop");
					 pactotask.setClient("C3");
					 out1.writeObject(pactotask);    
					 
					 
					 
					 
					
				}
				catch(Exception ex)
				{
				ex.printStackTrace();
				}
			}
			}
					
					
					);
			
			
					
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% process status %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%//	
			
			jbutt1.addActionListener(new ActionListener()
			
			{
					public void actionPerformed(ActionEvent ae)
			{
				
					try{
						
						System.out.println("I am enterd in process");
						
						TaskManager.getObj().setVisible(true);
						
						
					}
					catch(Exception ex)
					{
					ex.printStackTrace();
					}
				}
				}
						
						
						);
			
			
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%   %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5//		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		
	}
	
	public static void main(String args[])
	{
		TaskScheduler ts =	new TaskScheduler();
		
	}
	
	static void app(String str)
	{
		jta.append(str);
	}

}
