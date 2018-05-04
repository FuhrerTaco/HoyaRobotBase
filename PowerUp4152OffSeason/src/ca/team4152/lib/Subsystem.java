package ca.team4152.lib;

public abstract class Subsystem {

	public abstract void clearSensors();
	
	public abstract void disableSubsystem();
	
	public abstract void update();
	
	public abstract Subsystem getInstance();
	
}
