import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public interface IRobotExecute {

	void orderStart();
	
	boolean orderDone();
	
	Date calculateOrderTime(Order order);
	
	void explorationStart();
	
	boolean explorationDone();
	
	boolean hasUnexploredRooms();
	
	void chargingStart();
	
	boolean chargingDone();
	
	boolean isBumped();

}