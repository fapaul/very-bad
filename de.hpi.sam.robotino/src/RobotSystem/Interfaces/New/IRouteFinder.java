package RobotSystem.Interfaces.New;

import Datatypes.Added.Route;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.Stockroom;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;

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
