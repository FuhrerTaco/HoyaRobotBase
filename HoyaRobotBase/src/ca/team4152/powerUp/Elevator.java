package ca.team4152.powerUp;

import ca.team4152.lib.Subsystem;
import ca.team4152.lib.Thread;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Elevator extends Subsystem implements Thread{
	private TalonSRX leftMotor;
	private TalonSRX rightMotor;
	
	public static Elevator thisInstance = new Elevator();
	
	public Elevator()
	{
		thisInstance = this;
		leftMotor = new TalonSRX(RobotMap.leftElevatorMotorID); 
		rightMotor = new TalonSRX(RobotMap.rightElevatorMotorID);
	}
	
	@Override
	public void loop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearSensors() {
		// TODO Auto-generated method stub
		rightMotor.setSelectedSensorPosition(0, 0, 0);
	}

	@Override
	public void disableSubsystem() {
		// TODO Auto-generated method stub
		leftMotor.set(ControlMode.Disabled, 0);
		rightMotor.set(ControlMode.Disabled, 0);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		//put debugging info onto the smart dashboard
		
		//
	}

}
