package com.task;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public class Check extends JFrame{
	
	JButton b1;
	
	public Check()
	{
		final JTextField tf=new JTextField(); 
		tf.setBounds(50,50, 150,20);  
		 b1 =new JButton("Click Here"); 
		 b1.setBounds(50,100,95,30); 
		 
		 
		 b1.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){  
			             tf.setText("Welcome to Javatpoint.");  
			         }  
			     }); 
		 
		 this.add(b1);
		 this.add(tf);
		 this.setSize(400,400);  
		    this.setLayout(null);  
		    this.setVisible(true);
	}
	
	public static void main(String args[])
	{
		new Check();
	}
	

}
