package ca.team4152.powerUp;

import ca.team4152.lib.Subsystem;
import ca.team4152.lib.Thread;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4152.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Elevator extends Subsystem implements Thread {
	private TalonSRX leftMotor;
	private TalonSRX rightMotor;

	public static Elevator thisInstance = new Elevator();

	public Elevator() {
		thisInstance = this;
		leftMotor = new TalonSRX(RobotMap.leftElevatorMotorID);
		rightMotor = new TalonSRX(RobotMap.rightElevatorMotorID);
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

		// put debugging info onto the smart dashboard
		SmartDashboard.putNumber("Elevator Encoder", rightMotor.getSelectedSensorPosition(0)/4096.0);
		//
	}

	@Override
	public Subsystem getInstance() {
		// TODO Auto-generated method stub
		return thisInstance;
	}
	

	public void moveUp() {
		if (rightMotor.getSelectedSensorPosition(0)/4096.0 < 8) {
			leftMotor.set(ControlMode.PercentOutput, 1);
			rightMotor.set(ControlMode.PercentOutput, -1);
		} else {
			stop();
		}
	}

	@Override
	public void loop() {
		// TODO Auto-generated method stub
		// add control info here
		if (Robot.joy.getRawButton(RobotMap.RBumper))
		{
			moveUp();
		//System.out.println("Moving up");
		}
		else if(Robot.joy.getRawButton(RobotMap.LBumper))
		{
			moveDown(); 
		}
		else
		{
			stop();
			//System.out.println("Not moving");
		}
	}
	
	private void moveDown() {
		// TODO Auto-generated method stub
		if(rightMotor.getSelectedSensorPosition(0)/4096.0 > 0.1)
		{
		leftMotor.set(ControlMode.PercentOutput, -1);
		rightMotor.set(ControlMode.PercentOutput, 1);
		}else
		{
			stop();
		}
	}

	public void stop()
	{
		leftMotor.set(ControlMode.PercentOutput, 0);
		rightMotor.set(ControlMode.PercentOutput, 0);
	}

}
