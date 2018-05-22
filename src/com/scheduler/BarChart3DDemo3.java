package com.scheduler;




import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import demo.*;
import java.sql.*;

public class BarChart3DDemo3 extends ApplicationFrame
{
	
	
	static Statement stmt;
	 static Connection conn;
	
	 static double core1 = 0;
	static double core2 = 0;
	static double core3 = 0;
	
	
    public BarChart3DDemo3(String s)
    {
        super(s);
        JPanel jpanel = createDemoPanel();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
        
        
    }  
        
        

    private static JFreeChart createChart()
    {
       
    	
    	
    	 DefaultCategoryDataset defaultcategorydataset1 = new DefaultCategoryDataset();
    	
    	 try{
             
             
    		 conn = DB.getConnection();
             
             stmt = conn.createStatement();
         
    		 
    		 ResultSet rss = stmt.executeQuery("select * from usagememory where pname='core1'");
             
             if(rss.next())
             {
             	int totcore1 = rss.getInt("totalspace");
             	int occcore1 = rss.getInt("occupyspace");
             	
             	
             	System.out.println(totcore1);
             	System.out.println(occcore1);
             	
             	
             	
             	core1 = (double)occcore1/totcore1 * 100;
             	
             	System.out.println(core1);  
             	
          //   	double one = 52;
             	
             	defaultcategorydataset1.setValue(core1, "Process", "Processer 1");
             	
             }
             
             
             ResultSet rss1 = stmt.executeQuery("select * from usagememory where pname='core2'");
             
             if(rss1.next())
             {
             	int totcore2 = rss1.getInt("totalspace");
             	int occcore2 = rss1.getInt("occupyspace");
             	
             	
             	System.out.println(totcore2);
             	System.out.println(occcore2);
             	
             	
             	
             	core2 = (double)occcore2/totcore2 * 100;
             	
             	System.out.println(core1);  
             	
          //   	double one = 52;
             	
             	defaultcategorydataset1.setValue(core2, "Process", "Processer 2");
             	
             }
             
             
             
             
             
             
             ResultSet rss2 = stmt.executeQuery("select * from usagememory where pname='core3'");
             
             if(rss2.next())
             {
             	int totcore3 = rss2.getInt("totalspace");
             	int occcore3 = rss2.getInt("occupyspace");
             	
             	
             	System.out.println(totcore3);
             	System.out.println(occcore3);
             	
             	
             	
             	core3 = (double)occcore3/totcore3 * 100;
             	
             	System.out.println(core3);  
             	
          //   	double one = 52;
             	
             	defaultcategorydataset1.setValue(core3, "Process", "Processer 3");
             	
             }
             
             
    	
    	
    	 }catch(Exception ex)
    	 {
    		 ex.printStackTrace();
    	 }
    	
    	
    	JFreeChart jfreechart = ChartFactory.createBarChart3D("Multi-Processer", "Processer", "Usage Value", defaultcategorydataset1, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot categoryplot = (CategoryPlot)jfreechart.getPlot();
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.39269908169872414D));
        CategoryItemRenderer categoryitemrenderer = categoryplot.getRenderer();
        categoryitemrenderer.setBaseItemLabelsVisible(true);
        BarRenderer barrenderer = (BarRenderer)categoryitemrenderer;
        barrenderer.setItemMargin(0.20000000000000001D);
        return jfreechart;
    }

    public static JPanel createDemoPanel()
    {
     //   JFreeChart jfreechart = createChart(createDataset());
    	
    	JFreeChart jfreechart = createChart();
        return new ChartPanel(jfreechart);
    }

    public static void main(String args[])
    {
    	BarChart3DDemo3 barchart3ddemo3 = new BarChart3DDemo3("3D Free Chart");
        barchart3ddemo3.pack();
        RefineryUtilities.centerFrameOnScreen(barchart3ddemo3);
        barchart3ddemo3.setVisible(true);
    }
}
