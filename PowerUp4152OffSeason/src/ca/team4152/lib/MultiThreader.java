package ca.team4152.lib;

import java.util.Vector;

public class MultiThreader implements Thread {

	private Threader threader;
	private Vector<Thread> threads = new Vector<Thread>();
	
	public MultiThreader(double period, String name) {
		// TODO Auto-generated constructor stub
		threader = new Threader(period, this, name);
	}
	
	
	
	@Override
	public void loop() {
		// TODO Auto-generated method stub
		for(int i = 0; i < threads.size(); i++)
		{
			Thread c = threads.elementAt(i);
			if(c!=null) c.loop();
		}
	}
	
	public void start()
	{
		threader.start();
	}
	public void stop()
	{
		threader.stop();
	}
	public void addThread(Thread t)
	{
		threads.add(t);
	}

}
