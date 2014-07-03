package RobotSystem.Interfaces.New;

import java.util.List;

import Datatypes.Added.Route;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.Stockroom;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;

public interface IRouteFinder {
	List<Route> calculateCartAreaRoutes(Position from);
	List<Route> calculateCartAreaRoutes(Position from, Order order);
	List<Route> calculateIssuingPointsRoutes(Position from, Order order);
	Route calculateExplorationRoute(Position from, StockroomID room);	//StockroomID?
	Route calculateChargingRoute(Position from);
	Position getPosition();
	double getDistance(Route route);	
	StockroomID[] getNearRooms();
}
