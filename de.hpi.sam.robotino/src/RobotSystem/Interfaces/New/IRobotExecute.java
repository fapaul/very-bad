package RobotSystem.Interfaces.New;

import java.util.Date;

import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.Order;
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