package ServerSystem.Interfaces.New;

import Datatypes.Added.RobotStatusType;
import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;

public interface IAdminControl {

	void startup();
	
	void stutdown();
	
	float setEnergyConsumption();
	
	void addRobot(RobotinoID robot);
	
	void removeRobot(RobotinoID robot);
	
	RobotinoID[] getRobots();
	
	RobotinoID[] getRobots(RobotStatusType status);
	
	RobotStatusType getRobotStatus(RobotinoID robot);
	
	Position getRobotPosition(RobotinoID robot);

}