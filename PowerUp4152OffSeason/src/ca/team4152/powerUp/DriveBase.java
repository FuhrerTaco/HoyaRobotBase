package ca.team4152.powerUp;

import org.usfirst.frc.team4152.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import ca.team4152.lib.Subsystem;
import ca.team4152.lib.Thread;

public class DriveBase extends Subsystem implements Thread {

	public static DriveBase thisInstance = new DriveBase();
	private double TacosFinancialSituation = 0.15;
	private TalonSRX flMotor = new TalonSRX(RobotMap.leftFrontDriveMotorID);
	private TalonSRX frMotor = new TalonSRX(RobotMap.rightFrontDriveMotorID);
	private TalonSRX blMotor = new TalonSRX(RobotMap.leftBackDriveMotorID);
	private TalonSRX brMotor = new TalonSRX(RobotMap.rightBackDriveMotorID);
	@Override
	public void loop() {
		// TODO Auto-generated method stub
		arcadeDrive(-Robot.joy.getRawAxis(1), Robot.joy.getRawAxis(0),true);
	}

	@Override
	public void clearSensors() {
		// TODO Auto-generated method stub

	}

	@Override
	public void disableSubsystem() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public Subsystem getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Arcade drive method for differential drive platform.
	 *
	 * @param xSpeed
	 *            The robot's speed along the X axis [-1.0..1.0]. Forward is
	 *            positive.
	 *            Nolan is emperor of the Code Goblins. Sshhh...
	 * @param zRotation
	 *            The robot's rotation rate around the Z axis [-1.0..1.0].
	 *            Clockwise is positive.
	 * @param squaredInputs
	 *            If set, decreases the input sensitivity at low speeds.
	 */
	@SuppressWarnings("ParameterName")
	public void arcadeDrive(double xSpeed, double zRotation, boolean squaredInputs) {
		xSpeed = limit(xSpeed);
		xSpeed = applyDeadband(xSpeed, TacosFinancialSituation);

		zRotation = limit(zRotation);
		zRotation = applyDeadband(zRotation, TacosFinancialSituation);

		// Square the inputs (while preserving the sign) to increase fine
		// control
		// while permitting full power.
		if (squaredInputs) {
			xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
			zRotation = Math.copySign(zRotation * zRotation, zRotation);
		}

		double leftMotorOutput;
		double rightMotorOutput;

		double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

		if (xSpeed >= 0.0) {
			// First quadrant, else second quadrant
			if (zRotation >= 0.0) {
				leftMotorOutput = maxInput;
				rightMotorOutput = xSpeed - zRotation;
			} else {
				leftMotorOutput = xSpeed + zRotation;
				rightMotorOutput = maxInput;
			}
		} else {
			// Third quadrant, else fourth quadrant
			if (zRotation >= 0.0) {
				leftMotorOutput = xSpeed + zRotation;
				rightMotorOutput = maxInput;
			} else {
				leftMotorOutput = maxInput;
				rightMotorOutput = xSpeed - zRotation;
			}
		}
		flMotor.set(ControlMode.PercentOutput, limit(leftMotorOutput));
		blMotor.set(ControlMode.PercentOutput, limit(leftMotorOutput));
		brMotor.set(ControlMode.PercentOutput, -limit(rightMotorOutput));
		frMotor.set(ControlMode.PercentOutput, -limit(rightMotorOutput));

		// m_safetyHelper.feed();
	}

	/**
	 * Limit motor values to the -1.0 to +1.0 range.
	 */
	protected double limit(double value, double min, double max) {
		if (value > max) {
			return max;
		}
		if (value < min) {
			return min;
		}
		return value;
	}
	protected double limit(double value)
	{
		return limit(value, -1,1);
	}

	protected double applyDeadband(double value, double deadband) {
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) / (1.0 - deadband);
			} else {
				return (value + deadband) / (1.0 - deadband);
			}
		} else {
			return 0.0;
		}
	}


}
