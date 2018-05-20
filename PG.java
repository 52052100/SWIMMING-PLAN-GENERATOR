
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class PG extends JPanel{
	private JFrame frame = new JFrame("Plan Generator");
	public static String time=null;
	public static int distance;
	public static ArrayList<String> timelist = new ArrayList<String> (); 
	public static ArrayList<Integer> distancelist = new ArrayList<Integer> (); 
	public static ArrayList<String> list = new ArrayList<String> (); 	
	public static Set<String> planpacket = new HashSet<String> (); 
	static int plantime;
	static int plandistance;
	static int t=0;
	static String plan=null;
	static int totaldistance=600;
	static int totaltime=10;
	
    private JTextField input;
    private JLabel inputchoice;      
	private JTextArea taContent;
	
		public PG() {
			 File p = new File("swimmingplan.txt");
				Scanner scan;
				try {
					scan = new Scanner(p);
					String	s;
					while(scan.hasNextLine())
					{
						s = scan.nextLine();
						list.add(s);
					    time= s.substring(39,41);
					     distance = Integer.parseInt(s.substring(49));
					     timelist.add(time);
					     distancelist.add(distance);
					
					}		
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
				
			
		
		
      }
		public static void main(String[] args) {
			
			PG plan = new PG();
			plan.start();
			
				
		}
		public void start() {
			inputchoice = new JLabel("choose time or distance");
			input= new JTextField(5);
			
		    taContent = new JTextArea();
		    taContent.setLineWrap(true);
		    taContent.setVisible(true);
		    JButton buttontime = new JButton();
		    buttontime.setText("time");
		    buttontime.addActionListener(new GUIButtontimeListener());
		    buttontime.setBackground(Color.getHSBColor(40, 108, 95));
		    buttontime.setOpaque(true);
		    buttontime.setBorderPainted(false);
		    
		    JButton buttondistance = new JButton();
		    buttondistance.setText("distance");
		    buttondistance.addActionListener(new GUIButtondistanceListener());
		    buttondistance.setBackground(Color.getHSBColor(191,305,95));
		    buttondistance.setOpaque(true);
		    buttondistance.setBorderPainted(false);
		    
		    JButton button = new JButton();
		    button.setText("generate");
		    button.addActionListener(new GUIButtonListener());
		    //button.setBackground(Color.getHSBColor(222,555,222));
		    //button.setOpaque(true);
		    //button.setBorderPainted(false);
		    
	        setLayout(null);
	        
	        add(buttontime);
			add(buttondistance);
			add(button);
			add(inputchoice);
			add(input);
			add(taContent);
			
			buttontime.setBounds(150,50,200,50);
			buttondistance.setBounds(450,50,200,50);
			button.setBounds(550,200,100,50);
			inputchoice.setBounds(300,70,400,150);
			input.setBounds(150,200,400,50);
			taContent.setBounds(150,270,400,200);
			frame.add(this);
			frame.pack();
			frame.setVisible(true);
			frame.setSize(800, 550);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
      private class GUIButtonListener implements ActionListener {

         public void actionPerformed(ActionEvent event) {
        	 if(inputchoice.getText().equals("Input time below: ")) {
        		 		time();
     		}else if (inputchoice.getText().equals("Input distance below: ")) {
     				distance();
     		}
        

         }
    }
    
      private class GUIButtontimeListener implements ActionListener {

          public void actionPerformed(ActionEvent event) {
        	  inputchoice.setText("Input time below: ");
        	  	
          }

       } 
      private class GUIButtondistanceListener implements ActionListener {

          public void actionPerformed(ActionEvent event) {
        	  inputchoice.setText("Input distance below: ");
        	  		
        	  	     
          }

 		

       } 
      public void time() {
  		
         int totaltime = Integer.parseInt(input.getText()) ;
  		
         if(totaltime<=10){
        	 taContent.setText("Are you kidding me? Try again!");
      	   		time();
         }else{ 
      	   plantime   = totaltime-10;
      	
      	 taContent.setText("Today's plan: "+'\n');
      	 taContent.setText(taContent.getText()+"200m warmup"+'\n');
      	 taContent.setText(taContent.getText()+"4*100m medley"+'\n');
  	     
  	       timeplanSelect(plantime);
  		}
  		
  	}
      public void distance() {
  		
         int totaldistance = Integer.parseInt(input.getText()) ;
         if(totaldistance<1000){
        	 taContent.setText("Are you kidding me? At least 1000m for today!");
  	   		this.distance();
         }else {
      	   plandistance =totaldistance-600;
      	 taContent.setText("Today's plan: "+'\n');
      	 taContent.setText(taContent.getText()+"200m warmup"+'\n');
      	 taContent.setText(taContent.getText()+"4*100m medley"+'\n');
  	   distanceplanSelect(plandistance);
         }
      	   
  	}
      public void timeplanSelect(int n) {
  		this.plantime=n;
  		
  		while(plantime>10)
  		{	
  			
  			t =	getRandom(timelist);
  			if(t<=plantime)
  			{
  				
  				
  				for(int i =0;i<list.size();i++)
  				{	
  				 	plan=""+t;
  				 	if(plan.equals(list.get(i).substring(39,41))) 
  				 	{	
  				 		plantime=plantime-t;
  				 		if(planpacket.contains(list.get(i)))
  				 			continue;
  				 		else {
  				 			planpacket.add(list.get(i));
  				 			
  							break;
  				 		}
  				 		
  				 	}
  				}	
  			}
  			
  		}
  		for(String i:planpacket)
  		{
  			
  			taContent.setText(taContent.getText()+i.substring(0,39)+'\n');
  		totaldistance=totaldistance+ Integer.parseInt(i.substring(49,53));
  		}
  			if(plantime<=3)
  				taContent.setText(taContent.getText()+plantime+" mins rest time"+'\n');
  			else
  			{
  				int water=plantime-3;
  				int rest=plantime-water;
  				taContent.setText(taContent.getText()+water+" mins feel of water exercise"+'\n');
  				taContent.setText(taContent.getText()+rest+" mins rest time"+'\n');
  				
  			}
  			taContent.setText(taContent.getText()+"Today's total distance is "+totaldistance+"m"+'\n');
  		 

  		
  	}
  	public void distanceplanSelect(int n) {
  		this.plandistance=n;  
  		while(plandistance>200)
  		{
  				int max=0;
  				int maxindex=0;
  				for(int i=0;i<list.size();i++)
  				{	
  					int d=Integer.parseInt(list.get(i).substring(49,53));
  					if(d>max&&d<=plandistance)
  					{	
  						max=d;
  						maxindex=i;
  					}
  				}
  				plandistance=plandistance-max;
  				planpacket.add(list.get(maxindex));
  		
  		}								
  		for(String i:planpacket)
  		{
  			taContent.setText(taContent.getText()+i.substring(0,39)+'\n');
  			totaltime=totaltime+ Integer.valueOf(i.substring(39,41)).intValue();
  		}
  			if(plandistance<=100)
  				taContent.setText(taContent.getText()+plandistance+" m cool down"+'\n');
  			else
  			{
  				int water=plandistance-100;
  				totaltime+=5;
  				int rest=plandistance-water;
  				totaltime+=5;
  				taContent.setText(taContent.getText()+water+" m feel of water exercise"+'\n');
  				taContent.setText(taContent.getText()+rest+" m cool down"+'\n');
  				
  			}
  			taContent.setText(taContent.getText()+"Today's total time is "+totaltime+"mins"+'\n');
  		
  		
  	}
  	
  	 public static int getRandom(ArrayList<String>list){  
  		 	Random random = new Random();
  		 	int ran = random.nextInt(list.size());
  		 	int i=Integer.parseInt(list.get(ran));
  	        return i;  
  	    }

    }


