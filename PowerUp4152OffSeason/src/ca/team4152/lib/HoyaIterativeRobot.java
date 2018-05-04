/*
 * 
 */
package ca.team4152.lib;

import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.hal.HAL;

public class HoyaIterativeRobot extends HoyaRobotBase {

	public HoyaIterativeRobot() {
		HAL.report(tResourceType.kResourceType_Framework, tInstances.kFramework_Iterative);
	}
	
	
	@Override
	public void startCompetition() {
		//initializes the robot
		robotInit();
		
		//Tell the Driverstation that the robot is ready to be enabled
		HAL.observeUserProgramStarting();
		
		//loop while the robot is on, calling the appropriate mode-dependent function
		while(true)
		{
			m_ds.waitForData();
			
			loopFunc();
		}
	}

}
