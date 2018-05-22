package com.scheduler;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Document;
import javax.swing.text.StyleConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class TaskManager extends JFrame {
	
	JTextPane pane;
	StyledDocument doc;
	JScrollPane jsp;
	
	Connection conn;
	Statement stmt1;
	
	
	private static TaskManager obj = null;
	
	private TaskManager()
	{
		
	//	JFrame jf = new JFrame();
		
		
		try{
		conn = DB.getConnection();
		stmt1 = conn.createStatement();
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
		 pane = new JTextPane();
		  pane.setEditable(false);
		   doc = pane.getStyledDocument();
		   
	//	   taskexepanel = new JPanel();
	//	   taskexepanel.setBounds(60, 60, 400, 300);
		   
		   
		   jsp = new JScrollPane(pane);
			  jsp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder("center"),"",1,2,new Font("Verdana",Font.BOLD,14),new Color(119,158,203)));
			  jsp.setBounds(0,0,500,580);
			  jsp.setVisible(true);
		//	  jf.add(jsp);
			  add(jsp);
			  
			  
			  
			 
			  
			  
		//	  jf.setLocation(100,100);
		//	  jf.setSize(500,500); 
			  
			  
			  setLocation(100,100);
			  setSize(500,500);
			  
			  
		     //   jf.setLayout(null);  
		   //     jf.setVisible(true);
			  
	}
	
	public static void main(String args[])
	{
		new TaskManager();
	}

	
	public static TaskManager getObj()
	{
		if(obj==null)
		{
			obj = new TaskManager();
			obj.print();
	} 
		
		else
		{
			
		obj.print();
		
		}
		return obj;
	
	
	
	}
	
	
	
	
	public void print()
	{
		//System.out.println(" This is second press ");
		
		
		try{
		
		doc.remove(0, doc.getLength());
		
		}
		catch(Exception ex)
		{
			System.err.println("Reset failed: " + ex);
		}
		
		
		
		 try
		  {
		  
		  
		  
		  SimpleAttributeSet attributeSet = new SimpleAttributeSet(); 
			StyleConstants.setFontSize(attributeSet, 19);
		    StyleConstants.setForeground(attributeSet, Color.blue);
		    doc.insertString(doc.getLength(), "\n\t\t Process Information \n", attributeSet);
		    
		    

			SimpleAttributeSet attributeSet1 = new SimpleAttributeSet(); 
			StyleConstants.setFontSize(attributeSet1, 19);
		    StyleConstants.setForeground(attributeSet1, Color.black);
		    doc.insertString(doc.getLength(), "\n\tProcess\t\tClient\t\tMemory\n", attributeSet1);
		    doc.insertString(doc.getLength(), "\t---------------------------------------------------\n", attributeSet1);
		    
		    
		  
		  }
		  catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
		
		
		
		SimpleAttributeSet attributeSet2 = new SimpleAttributeSet(); 
	    
	    StyleConstants.setForeground(attributeSet2, Color.black);
	    
	    try
	    {
		
		ResultSet rss = stmt1.executeQuery("select * from process");
		
		while(rss.next())
		{
			String getname = rss.getString("name");
			String getuser = rss.getString("user");
			int getmemory = rss.getInt("memory");
			
			doc.insertString(doc.getLength(), "\n\n\t"+getname+"\t", attributeSet2);
			doc.insertString(doc.getLength(),"\t"+getuser+"\t", attributeSet2);
			doc.insertString(doc.getLength(),"\t"+getmemory+" mb\t", attributeSet2);
			
		}

		
	    }
	    catch(Exception ex)
	    {
	    	ex.printStackTrace();
	    }
		
	}
	
	
}
