import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public class OrderManager implements IStockroom, IRobotExecute, IRouteFinder {
// DriverManager, ExplorationManager

	private boolean done;
	private Route currentSourceRoute;
	private Route currentDestinationRoute;
	private Order currentOrder;
	private Cart currentCart;
	
	private Route chooseOrderRoute(Route[] routes, Order order) {
	
		return null;
	}
	
	private Route chooseRoute(Route[] routes) {
	
		return null;
	}
}