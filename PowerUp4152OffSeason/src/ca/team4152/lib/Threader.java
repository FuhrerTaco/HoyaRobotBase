package ca.team4152.lib;

import java.util.Timer;
import java.util.TimerTask;

public class Threader {

	private double period;
	private Thread thread;
	private Timer threadUpdater;
	private String name;
	
	/**
	 * 
	 * @param period in milliseconds
	 * @param thread
	 * @param name of thread
	 */
	public Threader(double period, Thread thread, String name)
	{
		this.period = period;
		this.thread = thread;
		this.name = name;
		
	}
	//private subclass handles the scheduling.
	private class ThreadUpdater extends TimerTask
	{
		private Threader threader;
		public ThreadUpdater(Threader threader) {
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
		System.out.println("New Threader" + this.name + ", period: " + this.period);
		if(this.threadUpdater == null)
		{
			this.threadUpdater = new Timer("Thread: " + this.name);
			System.out.println("New Threader" + this.name + ", period: " + this.period);
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
