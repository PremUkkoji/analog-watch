package applets;

import java.applet.*;
import java.awt.*;
import java.lang.Math;
import java.util.*;

public class analog_clock extends Applet implements Runnable
{
	double o;
	double s,m,h;
	double second,minute,hour;
	Thread t;
	int x1,y1,x2,y2,nx,ny;
	int theta;

	public void start()
	{
		t =new Thread(this);
		t.start();
	}
	
	public void run()
	{
		GregorianCalendar c = new GregorianCalendar();
		
		second=c.get(Calendar.SECOND);
		minute=c.get(Calendar.MINUTE);
		hour=c.get(Calendar.HOUR);
		
		second*=6;
		minute*=6;
		hour*=30;
		
		s=second-90;
		m=minute-90;
		h=hour-90;
		
		try
		{
			while(true)
			{
				repaint();
				
				Thread.sleep(1000);
				/*second=c.get(Calendar.SECOND);
				minute=c.get(Calendar.MINUTE);
				hour=c.get(Calendar.HOUR);
				if(hour>12) hour-=12;*/
				
				s+=6;
				m+=6.0/60;
				h+=30.0/3600;

			}
		}
		catch(Exception e)
		{
			
		}
	}

	public void paint(Graphics g)
	{
		Font f = new Font("menlo",Font.BOLD,20);
		
		g.setColor(Color.black);
		g.fillOval(45,45,610,610);
		
		g.setColor(Color.white);
		g.fillOval(50, 50, 600, 600);
		
		theta=-90;
		int i=12;
		
		g.setColor(Color.black);
		while(theta<=270)
		{
			o = Math.toRadians(theta);
			//x1=(int) (300*Math.cos(o));
			//y1=(int) (300*Math.sin(o));
			
			x2=(int) (270*Math.cos(o));
			y2=(int) (270*Math.sin(o));
			
			nx=(int) (280*Math.cos(o));
			ny=(int) (280*Math.sin(o));
			
				String s=String.valueOf(i);
				g.setFont(f);
				g.drawString(s,340+nx,358+ny);
				i++;
				if(i==13)
					i=1;
				
			//g.drawLine(350+x1,350+y1,350+x2,350+y2);
			
			theta+=30;
		}
		
		theta=-90;
		
		while(theta<=270)
		{
			o =Math.toRadians(theta);
			x1=(int) (300*Math.cos(o));
			y1=(int) (300*Math.sin(o));
			
			x2=(int) (290*Math.cos(o));
			y2=(int) (290*Math.sin(o));
			if(theta%30!=0)
				g.drawLine(350+x1,350+y1,350+x2,350+y2);
			
			theta+=6;
		}
		
		g.drawLine(350,350,350+(int) (260*Math.cos(Math.toRadians(s))),350+(int) (260*Math.sin(Math.toRadians(s))));
		g.drawLine(350,350,350+(int) (230*Math.cos(Math.toRadians(m))),350+(int) (230*Math.sin(Math.toRadians(m))));
		g.drawLine(350,350,350+(int) (200*Math.cos(Math.toRadians(h))),350+(int) (200*Math.sin(Math.toRadians(h))));

		//g.drawString(time,320,330);

	}

	public void stop()
	{
		if(t!=null)
			t=null;
	}
}
