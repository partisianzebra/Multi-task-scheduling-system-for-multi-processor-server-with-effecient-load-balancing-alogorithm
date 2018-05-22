package com.scheduler;

public class Packet implements java.io.Serializable{
	
	String process, action, user, pro, client, proac,addac;
	String forDate;
	int id;
	
	
	
	
	public void setAction(String act)
	{
		this.action=act;
	}
	public String getAction()
	{
		return action;
	}

	
	public void setClient(String cli)
	{
		this.client=cli;
	}
	public String getClient()
	{
		return client;
		
	}
	
	
	public void setProcess(String pro)
	{
		this.pro=pro;
	}
	public String getProcess()
	{
		return pro;
		
	}
	
	
	public void setProcessAction(String proc)
	{
		this.proac=proc;
	}
	public String getProcessAction()
	{
		return proac;
		
	}
	
	public void setAdditionAction(String addac)
	{
		this.addac=addac;
	}
	public String getAdditionAction()
	{
		return addac;
		
	}
	
	
	
	public void setProcessId(int id)
	{
		this.id=id;
	}
	
	public int getProcessId()
	{
		return id;
	}
	
	
	
	public void setDate(String forDate)
	{
		this.forDate=forDate;
	}
	
	public String getDate()
	{
		return forDate;
	}
	
	
	
}
