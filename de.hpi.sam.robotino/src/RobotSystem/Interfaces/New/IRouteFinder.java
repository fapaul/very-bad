import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public interface IRouteFinder {
	Route[] calculateCartAreaRoutes(Position from);
	Route[] calculateCartAreaRoutes(Position from, Order order);
	Route[] calculateIssuingPointsRoutes(Position from, Order order);
	Route calculateExplorationRoute(Position from, Stockroom room);	//StockroomID?
	Route calculateChargingRoute(Position from);
	Position getPosition();
	int getDistance(Route route);	
	StockroomID[] getNearRooms();
}
