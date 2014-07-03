package RobotSystem.Implementation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Datatypes.Added.RoomPoint;
import Datatypes.Added.RoomPointCartArea;
import Datatypes.Added.RoomPointDoor;
import Datatypes.Added.Route;
import RobotSystem.Interfaces.New.IRouteFinder;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.environment.Path;
import de.hpi.sam.warehouse.environment.PathElement;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.order.OrderItem;
import de.hpi.sam.warehouse.stock.CartArea;
import de.hpi.sam.warehouse.stock.Door;
import de.hpi.sam.warehouse.stock.IssuingPoint;
import de.hpi.sam.warehouse.stock.Stockroom;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.hpi.sam.warehouse.stock.StockroomManagement;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;

public class RouteFinder implements IRouteFinder {
	
	private Position CHARGINGSTATION;
	private Position destinationPosition;
	private WarehouseRobot robot;
	private WarehouseRepresentation representation = new WarehouseRepresentation();
	private StockroomManagement 	stockMang = StockroomManagement.INSTANCE;
	
	public RouteFinder(WarehouseRobot robot, WarehouseRepresentation rep) {
		this.robot = robot;
		this.representation = rep;
	}
	
	private List<Route> calculateSubRoutes(RoomPoint from, RoomPoint to) {
	       
        StockroomID start = from.getRoom();
        StockroomID end = to.getRoom();
        List<Route> routes = new ArrayList<Route>();
       
        List<Path> paths = representation.computePaths(start, end);
        for (Path i : paths) {
            Route route = new Route();
            route.add(from);
           
            List<PathElement> pe = new ArrayList<PathElement>();
            pe = i.getPathElements();
            for (PathElement m : pe) {
                Door door = m.getDoor();
                RoomPointDoor rpDoor = new RoomPointDoor(door);
                route.add(rpDoor);
            }
            route.add(to);
            routes.add(route);
        }
        return routes;
    }
	
	public Route getShortestRoute(List<Route> routes) {
		Route smallest = routes.get(0);
		double smallDist = getDistance(smallest);
		for(Route r : routes) {
			if(getDistance(r) < smallDist) {
				smallest = r;
				smallDist = getDistance(smallest);
			}				
		}
		return smallest;
	}
	
	
	
	public List<Route> calculateCartAreaRoutes(Position from) {
		List<Route> cartAreaRoutes = new LinkedList<Route>();
		RoomPoint fromPoint = new RoomPoint(from);
		//List<CartPosition> cartAreaPos = new LinkedList<CartPosition>();
		for(CartArea area : stockMang.getCartAreas()) {
			if(area.getCartPositions().size() == 0) 
				continue;
			cartAreaRoutes.add(getShortestRoute(calculateSubRoutes(fromPoint, new RoomPointCartArea(area))));
			
		}
		return cartAreaRoutes;
	}
	
	public List<Route> calculateCartAreaRoutes(Position from, Order order) {
		RoomPoint fromPoint = new RoomPoint(from);
		RoomPoint toPoint = new RoomPointCartArea(order.getCartArea());
		return calculateSubRoutes(fromPoint, toPoint);
	}
	
	/* (non-Javadoc)
	 * @see RobotSystem.Interfaces.New.IRouteFinder#calculateIssuingPointsRoutes(de.cpslab.robotino.environment.Position, de.hpi.sam.warehouse.order.Order)
	 */
	@Override
	public List<Route> calculateIssuingPointsRoutes(Position from, Order order) {
		RoomPoint fromPoint = new RoomPoint(from);
		RoomPoint cartPoint = new RoomPointCartArea(order.getCartArea());
		List<Route> allRoutes = new LinkedList<Route>();
		allRoutes.add(getShortestRoute(calculateSubRoutes(fromPoint, cartPoint)));
		
		// Calculate for all the issung points the routes 
		RoomPoint lastPoint = cartPoint;
		for(int i = 1; i < order.getOrderItems().size(); i++) {
			OrderItem curItem = order.getOrderItems().get(i);
			// Calculate the next point to go
			List<Route> routesToIssuingPoints = new LinkedList<Route>();
			for(IssuingPoint point : stockMang.getIssuingPoints(curItem.getProductType())) {
				routesToIssuingPoints.add(getShortestRoute(calculateSubRoutes(lastPoint, new RoomPoint(point.getXPosition(), point.getZPosition()))));
			}
			Route bestRouteToIssue = getShortestRoute(routesToIssuingPoints);
			allRoutes.add(bestRouteToIssue);
			lastPoint = bestRouteToIssue.getRoomPoints().get(bestRouteToIssue.getRoomPoints().size()-1);
		}
		
		return allRoutes;
	}
	
	/*
	 * Calculate route to stockroom and to all points in room 
	 * (non-Javadoc)
	 * @see RobotSystem.Interfaces.New.IRouteFinder#calculateExplorationRoute(de.cpslab.robotino.environment.Position, de.hpi.sam.warehouse.stock.StockroomID)
	 */
	@Override
	public Route calculateExplorationRoute(Position from, StockroomID room) {
		if(representation.getExplorationStatus(room) == 100)
			return new Route();
			
		List<Door> roomDoors = representation.getDoors(room);
		RoomPoint fromPoint = new RoomPoint(from);
		//Choose next door
		List<Route> routeToRoom = new LinkedList<Route>();
		for(Door d: roomDoors) {
			routeToRoom.add(getShortestRoute(calculateSubRoutes(fromPoint, new RoomPointDoor(d))));
		}
		Route explRoute = getShortestRoute(routeToRoom);
		
		// Check which issuing points are in 
		for(IssuingPoint ip: stockMang.getAllIssueingPoints())
			if(room == representation.getRoomFor(ip))
				explRoute.add(new RoomPoint(ip));
		return explRoute;
	}

	/* Calculate route charging station if set
	 * (non-Javadoc)
	 * @see RobotSystem.Interfaces.New.IRouteFinder#calculateChargingRoute(de.cpslab.robotino.environment.Position)
	 */
	@Override
	public Route calculateChargingRoute(Position from) {
		// TODO Auto-generated method stub
		return null;
	}

	/* Access to INorthStar
	 * (non-Javadoc)
	 * @see RobotSystem.Interfaces.New.IRouteFinder#getPosition()
	 */
	@Override
	public Position getPosition() {
		return robot.getCurrentPosition();
	}

	/* Calculates the 2d distance for the route
	 * (non-Javadoc)
	 * @see RobotSystem.Interfaces.New.IRouteFinder#getDistance(Datatypes.Added.Route)
	 */
	@Override
	public double getDistance(Route route) {
		return route.getDistance();
	}

	/* Return a list of stockrooms ordered by distance
	 * (non-Javadoc)
	 * @see RobotSystem.Interfaces.New.IRouteFinder#getNearRooms()
	 */
	@Override
	public StockroomID[] getNearRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	
}