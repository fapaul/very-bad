import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public interface IOrderAccess {

	void addOrder(Order order);
	
	void removeOrder(Order order);
	
	int getOrderAmount();

}