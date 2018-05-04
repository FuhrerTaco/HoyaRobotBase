package ca.team4152.powerUp;

import org.usfirst.frc.team4152.robot.Robot;

import ca.team4152.lib.Subsystem;
import ca.team4152.lib.Thread;
import edu.wpi.first.wpilibj.Spark;

public class Intake extends Subsystem implements Thread {

	public static Intake thisInstance = new Intake();
	enum direction {in, out, none};
	Spark lm = new Spark(RobotMap.leftIntakeMotorID);
	Spark rm = new Spark(RobotMap.rightIntakeMotorID);
	Spark winchMotor = new Spark(RobotMap.winchMotorID);
	@Override
	public void loop() {
		// TODO Auto-generated method stub
		//control stuff
		if(Robot.joy.getRawButton(RobotMap.buttonA))
		{
			intakeCube();
		}
		else if(Robot.joy.getRawButton(RobotMap.buttonX))
		{
			outputCube();
		}
		else dont();
		
		if(Robot.joy.getPOV() == 180)
		{
			windWinch(direction.in);
		}
		else if(Robot.joy.getPOV() == 0)
		{
			windWinch(direction.out);
		}
		else
		{
			windWinch(direction.none);
		}
	}

	private void intakeCube() {
		// TODO Auto-generated method stub
		lm.set(-1);
		rm.set(1);
	}

	private void outputCube() {
		// TODO Auto-generated method stub
		lm.set(1);
		rm.set(-1);
	}

	private void dont() {
		// TODO Auto-generated method stub
		lm.set(0);
		rm.set(0);
	}

	@Override
	public void clearSensors() {
		// TODO Auto-generated method stub
		//what are sensors?
		
	}

	@Override
	public void disableSubsystem() {
		// TODO Auto-generated method stub
		lm.set(0);
		rm.set(0);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//Again what are sensors? Do you mean Censors? 
	}

	protected void windWinch(direction d)
	{
		switch (d) {
		case in:
			winchMotor.set(0.5);
			break;
		case out:
			winchMotor.set(-0.5);
			break;
			
		default:
			winchMotor.set(0);
			break;
		}
	}
	
	
	@Override
	public Subsystem getInstance() {
		// TODO Auto-generated method stub
		return thisInstance;
	}

}
