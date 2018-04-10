package ca.team4152.lib;

import java.util.Timer;
import java.util.TimerTask;

public class Threader {

	private double period;
	private Thread thread;
	private Timer threadUpdater;
	private String name;
	
	public Threader(double period, Thread thread, String name)
	{
		this.period = period;
		this.thread = thread;
		this.name = name;
		
	}
	
	private class ThreadUpdater extends TimerTask
	{
		private Threader threader;
		public ThreadUpdater(Threader threader) {
			// TODO Auto-generated constructor stub
			if(threader == null)
			{
				throw new NullPointerException("no thread");
			}
			this.threader = threader;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			thread.loop();
		}
		
	}
	
	public void start()
	{
		if(this.threadUpdater == null)
		{
			this.threadUpdater = new Timer("Thread: " + this.name);
			this.threadUpdater.schedule(new ThreadUpdater(this), 0L,(long) this.period);
		}
	}
	public void stop()
	{
		if(this.threadUpdater != null)
		{
			this.threadUpdater.cancel();
			this.threadUpdater = null;
		}
	
	}
	public void update()
	{
		thread.loop();
	}
}
