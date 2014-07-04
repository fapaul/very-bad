package RobotSystem.Implementation;

import java.util.Date;
import java.util.List;

import Datatypes.Added.CartDestination;
import Datatypes.Added.CartSource;
import Datatypes.Added.RoomPoint;
import Datatypes.Added.RoomPointCartArea;
import Datatypes.Added.RoomPointIssuingPoint;
import Datatypes.Added.Route;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.environment.IWarehouseEnvironment;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.IssuingPoint;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;

public class OrderManager {
// DriverManager, ExplorationManager

	RouteFinder rf;
	DriveManager dm;
	
	private boolean done;
	private Route currentSourceRoute = null;
	private Route currentDestinationRoute = null;
	
	private List<Route> issuingPointRoutes = null;
	private Route firstAreaRoute = null;
	private Route finalAreaRoute = null;
	
	private Order currentOrder = null;
	// For testing we try to drive the current route
	private Route currentRoute = null;
	private Cart currentCart = null;
	private WarehouseRobot robot = null;
	private WarehouseRepresentation rep = null;
	private ExplorationManager em;
	
	public OrderManager(WarehouseRobot r, WarehouseRepresentation wr) {
		robot = r;
		rep = wr;
		dm = new DriveManager(robot, wr);
		rf = new RouteFinder(robot, rep);
		em = new ExplorationManager(r, this.rep);
	}
	/*
	private Route chooseOrderRoute(List<Route> routes, Order order) 	
		return null;
	}
	*/
	
	private Route chooseRoute(List<Route> routes) {
		Route min = routes.get(0);
		for (Route route : routes) {
			if (route.getDistance() < min.getDistance())
				min = route;
		}
		return min;
	}

	public void orderStart(Order order) {
		System.out.println("Starting order");
		// No order nothing to do //TODO an error message might be good
		if(currentOrder == null)
			return;
		System.out.println("Order size is " + currentOrder.getOrderItems().size());
		done = false;
		
		calculateWaysForOrder(order);
		
		dm.drive(firstAreaRoute);
		// Get the cart
		CartSource fistCartArea =  (CartSource) firstAreaRoute.getRoomPoints().get(firstAreaRoute.getRoomPoints().size()-1);
		currentCart = fistCartArea.interact(null, robot);
		
		dm.setCurCart(currentCart);
		System.out.println("test");
		int item = 0;
		for (Route route : issuingPointRoutes) {
			dm.drive(route);
			// Get the last point an interact with the issuing point
			Position pos = route.getRoomPoints().get(route.getRoomPoints().size()-1).getLocation();
			IssuingPoint issuingPoint = ((RoomPointIssuingPoint) route.getRoomPoints().get(route.getRoomPoints().size()-1)).getIssuingPoint();
			RoomPointIssuingPoint issuingpoint = ((RoomPointIssuingPoint) new RoomPointIssuingPoint(pos, issuingPoint));
			issuingpoint.setItemsToTake(order.getOrderItems().get(item).getQuantity());
			issuingpoint.interact(currentCart, robot);
			item++;
		}
		
		// All carts finished return to last element		
		dm.drive(finalAreaRoute);
		CartDestination finalCartArea =  (CartDestination) finalAreaRoute.getRoomPoints().get(finalAreaRoute.getRoomPoints().size()-1);
		currentCart = finalCartArea.interact(currentCart, robot);
		dm.setCurCart(currentCart);
		done = true;
	}

	public boolean orderDone() {
		return done;
	}
	
	
	public void calculateWaysForOrder(Order order) {
		List<Route> routeToCartArea = rf.calculateCartAreaRoutes(robot.getCurrentPosition());
		issuingPointRoutes = rf.calculateIssuingPointsRoutes(robot.getCurrentPosition(), order);
		List<Route> finishRoutes = rf.calculateCartAreaRoutes(robot.getCurrentPosition(), order);
		
		if(routeToCartArea.size() == 0 || issuingPointRoutes.size() == 0 || finishRoutes.size() == 0)
			System.out.println("calculateOrderTime: Something went wrong with getting the routes");
		int lastIndex;
		firstAreaRoute = rf.getShortestRoute(routeToCartArea);
	
		lastIndex = firstAreaRoute.getRoomPoints().size()-1;
		// Setting the last point to a cart source
		firstAreaRoute.getRoomPoints().set(lastIndex, new CartSource(
				((RoomPointCartArea) firstAreaRoute.getRoomPoints().get(lastIndex)).getCartArea()));
		
		
		
		finalAreaRoute = rf.getShortestRoute(finishRoutes);
		lastIndex = finalAreaRoute.getRoomPoints().size()-1;
		System.out.println("this is a test " + (order.getCartArea() == null));
		finalAreaRoute.getRoomPoints().set(lastIndex, new CartDestination(order.getCartArea()));
	}

	public Date calculateOrderTime(Order order) {
		// For testing we save order when we calc the time 
		currentOrder = order;
		int distance = 0;
		int explorationDistance = 0;
		
		calculateWaysForOrder(order);
		/*System.out.println(cartAreaRoutes.size());
		for(Route r : cartAreaRoutes)
			System.out.println("areaRoutes " + r.getDistance());
		System.out.println(issuingPointRoutes.size());
		for(Route r : issuingPointRoutes)
			System.out.println("issuingPoint " + r.getDistance());
		System.out.println(finalCartAreaRoutes.size());
		for(Route r : finalCartAreaRoutes)
			System.out.println("finalCart " + r.getDistance());
		*/
		

			
		// calculate exploring distance if unexplored rooms exist
		for (int i = 0; i < 2; i++) {
			List<RoomPoint> rp = null;
			
			// calculate distance for each subroute
			Route route = null;
			switch (i) {
				case (0):
					route = firstAreaRoute;
					rp = route.getRoomPoints();
					break;
				case (1):
					route = finalAreaRoute;
					rp = route.getRoomPoints();
					break;
			}
			// check if the room is explored
			for (RoomPoint r : rp) {
				if (!em.isExplored(r.getRoom())) {
					Route explorationRoute = rf.calculateExplorationRoute(rf.getPosition(), r.getRoom());
					if(explorationRoute == null)
						continue;
					explorationDistance += rf.getDistance(explorationRoute);
				}
				else {
					distance += rf.getDistance(route);
				}
			}
		}
		
		for (Route route : issuingPointRoutes) {
			for (RoomPoint r : route.getRoomPoints()) {
				if (!em.isExplored(r.getRoom())) {
					Route explorationRoute = rf.calculateExplorationRoute(rf.getPosition(), r.getRoom());
					if(explorationRoute == null)
						continue;
					explorationDistance += rf.getDistance(explorationRoute);
				}
				else {
					distance += rf.getDistance(route);
				}
			}
		}
		
		// time in milli seconds
		int explorationTime = explorationDistance/IWarehouseEnvironment.SAFETY_SPEED * 1000;
		int routeTime = (int) (((float) distance/dm.getMaxSpeed()) * 1000);
		//System.out.println(explorationDistance + " fjdk " + routeTime);
		Date date = new Date(explorationTime + routeTime);
		
		return date;
	}

	

}