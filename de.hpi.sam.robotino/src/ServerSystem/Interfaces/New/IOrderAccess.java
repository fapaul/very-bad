package ServerSystem.Interfaces.New;

import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.Order;
import de.cpslab.robotino.*;

public interface IOrderAccess {

	void addOrder(Order order);
	
	void removeOrder(Order order);
	
	int getOrderAmount();

}