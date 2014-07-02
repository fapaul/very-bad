package RobotSystem.Interfaces.New;

import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;

public interface IDriveManager {

	void drive(Position position);
	
	boolean isBumped();
	
	void setSpeed(int speed);
	
	int getMaxSpeed();

}