package ServerSystem.Interfaces.New;

import Datatypes.Added.StateType;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.environment.Position;

public interface IAdminControl {

	void startup();
	
	void shutdown();
	
	float setEnergyConsumption();
	
	void addRobot(RobotinoID robot);
	
	void removeRobot(RobotinoID robot);
	
	RobotinoID[] getRobots();
	
	RobotinoID[] getRobots(StateType.robot status);
	
	StateType.robot getRobotStatus(RobotinoID robot);
	
	Position getRobotPosition(RobotinoID robot);

}