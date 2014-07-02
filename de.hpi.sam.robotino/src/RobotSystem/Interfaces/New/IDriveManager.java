import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public interface IDriveManager {

	void drive(Position position);
	
	boolean isBumped();
	
	void setSpeed(int speed);
	
	int getMaxSpeed();

}