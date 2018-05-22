package com.process;

import java.io.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.lang.*;

public class Process extends JFrame{
	
	
	Toolkit tk;
	Dimension dim;
	Container con;
	JPanel pan1,pan2;
	
	static JTextArea jta1,jta2,jta3;
	static JScrollPane jsp1,jsp2,jsp3;
	
	public Process(){
		
		
		
		try
		{
			
			this.setTitle("MULTIPROCESSER");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			dim=tk.getDefaultToolkit().getScreenSize();
			
			con=this.getContentPane();
			con.setBackground(new Color(26,36,33));
			con.setLayout(null);
			
			
			pan1=new JPanel();
			pan1.setBounds(40,60,dim.width-100,dim.height-150);
			pan1.setBackground(new Color(204,204,255));
			pan1.setLayout(null);
			con.add(pan1);
			
			
			jta1=new JTextArea();
			jta1.setEditable(false);
			jsp1=new JScrollPane(jta1);
			jsp1.setBounds(50,100,330,400);
			jsp1.setBackground(new Color(85,85,85));
			jsp1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"PROCESS INFORMATION",2,3,new Font("Georgia",Font.BOLD,12),Color.black));
			pan1.add(jsp1);
			
			
			jta2=new JTextArea();
			jta2.setEditable(false);
			jsp2=new JScrollPane(jta2);
			jsp2.setBounds(460,100,330,400);
			jsp2.setBackground(new Color(85,85,85));
			jsp2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"PROCESS INFORMATION",2,3,new Font("Georgia",Font.BOLD,12),Color.black));
			pan1.add(jsp2);
			
			
			jta3=new JTextArea();
			jta3.setEditable(false);
			jsp3=new JScrollPane(jta3);
			jsp3.setBounds(860,100,330,400);
			jsp3.setBackground(new Color(85,85,85));
			jsp3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"PROCESS INFORMATION",2,3,new Font("Georgia",Font.BOLD,12),Color.black));
			pan1.add(jsp3);
			
			
			
			
			
			this.setSize(dim);
			this.setVisible(true);
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	
	public static void main(String args[])
	{
	new Process();
		
	}
	
	
	
	
}
