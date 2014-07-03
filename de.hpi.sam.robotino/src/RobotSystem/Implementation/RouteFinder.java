package RobotSystem.Implementation;

import java.util.ArrayList;
import java.util.List;

import Datatypes.Added.*;
import Datatypes.Added.Route;
import RobotSystem.Interfaces.New.IRouteFinder;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.environment.Path;
import de.hpi.sam.warehouse.interfaces.IStockroom;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.order.ProductType;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.CartArea;
import de.hpi.sam.warehouse.stock.CartPosition;
import de.hpi.sam.warehouse.stock.Door;
import de.hpi.sam.warehouse.stock.IssuingPoint;
import de.hpi.sam.warehouse.stock.Stockroom;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;
import de.cpslab.robotino.sensor.interfaces.INorthStar;
import de.hpi.sam.warehouse.environment.*;

public class RouteFinder implements INorthStar, IStockroom, IRouteFinder {
	
	private Position CHARGINGSTATION;
	private Position destinationPosition;
	
	private List<Route> calculateSubRoute(RoomPoint from, RoomPoint to) {
	       
        StockroomID start = from.getRoom();
        StockroomID end = to.getRoom();
        List<Route> routes = new ArrayList<Route>();
       
        List<Path> paths = computePaths(start, end);
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
            routes.add(route);
        }
        return routes;
    }
	
	private Position chooseCartArea() {
	
		return null;
	}

	/*@Override
	public Route[] calculateCartAreaRoutes(Position from) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Route[] calculateCartAreaRoutes(Position from, Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Route[] calculateIssuingPointsRoutes(Position from, Order order) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	public List<Route> calculateCartAreaRoutes(Position from) {
			
			StockroomID currentStockroom = getRoomFor(from);				// jetzigen Stockroom bestimmen
			List<CartArea> cartAreas =  getCartAreas();
			List<CartPosition> temp = new ArrayList<CartPosition>();
			List<CartPosition> allCartPositions = new ArrayList<CartPosition>();	//stelle i korrespondiert zu stelle i von cartAreas
			for (CartArea i : cartAreas) {
				temp = i.getCartPositions();		// von aktueller CartArea all ihre CartPositions
				allCartPositions.add(temp.get(0));	// nur erste CartPosition in jeder CartArea betrachten
			}
			//List<StockroomID> stockrooms = new ArrayList<StockroomID>();	// Stockrooms für CartAreas mit ihrer CartPosition
			List<Path> tempP = new ArrayList<Path>();
			List<Integer> tempP2 = new ArrayList<Integer>();
			List<Route> routes = new ArrayList<Route>();
			for (CartPosition i : allCartPositions) {
				// if CART ?
				StockroomID sID = getRoomFor(i);		//Stockroom für jede CartArea holen
				//currentStockroom.
				tempP = computePaths(currentStockroom, sID);	// alle Wege zu einem Stockroom
				for (Path p : tempP) {		// kürzesten Weg/Path zu einem Stockroom wählen, entspricht hier Anzahl der am geringsten zu durchfahrenen PathElements
					tempP2.add(p.getPathElements().size());
					int min = tempP2.get(0);
					int minIndex = 0;
					for (int m = 1; m < tempP2.size(); m++) {
						if (min > tempP2.get(m)) {
							min = tempP2.get(m);
							minIndex = m;
						}
					}																	// TODO: weg von tür zur cartarea hinzufügen
					//tempP.get(minIndex).getPathElements()
					Path tempPath = tempP.get(minIndex);
					tempPath.
					routes.add(tempP.get(minIndex));			
					routes.add(allCartPositions.get(minIndex));
				}
				//List<Path> f = tempP.get(tempP.size()-1);
				//Door d = f.getPathElements().getDoors(stockroom).getDoor();	
				//CartPosition lastPos = allCartPositions.get(callCartPositions.size()-1);
				//Path f = tempP.get(tempP.size()-1);
				//List<PathElements> pe = new ArrayList<PathElements>();
			}								
			return paths;
		}
		
		public List<Route> calculateCartAreaRoutes(Position from, Order order) {
			
			StockroomID currentStockroom = getRoomFor(from);
			List<CartPosition> temp = new ArrayList<CartPosition>();
			List<Route> tempP = new ArrayList<Route>();
			List<Integer> tempP2 = new ArrayList<Integer>();
			List<Route> paths = new ArrayList<Route>();
			List<CartPosition> allCartPositions = new ArrayList<CartPosition>();	//stelle i korrespondiert zu stelle i von cartAreas
			
			CartArea cartArea = order.getCartArea();
			temp = cartArea.getCartPositions();		// von aktueller CartArea all ihre CartPositions
			allCartPositions.add(temp.get(0)); // nur erste CartPosition in CartArea betrachten
			StockroomID sID = getRoomFor(allCartPositions);
			tempP = computePaths(currentStockroom, sID);	// alle Wege zum Stockroom
			tempP.add(allCartPositions);
			//tempP.add(computePaths(currentStockroom, sID));	
			
			
			
			
			return tempP;			// TODO: weg von tür zur cartarea hinzufügen
		}
		
		public List<Route> calculateIssuingPointsRoutes(Position from, Order order) {
			/*
			 * ordergetCartArea
			 * CartArea.getCartPosition(0)
			 * GetRoomFor(CartPosition)
			 * ComputePath(letztes aus IssuingPointList, Zielstockroom)
			 * gib Pfade zurück
			 */
			List<CartPosition> temp = new ArrayList<CartPosition>();
			List<CartPosition> allCartPositions = new ArrayList<CartPosition>();
			List<Route> tempP = new ArrayList<Route>();
			
			CartArea cartArea = order.getCartArea();
			StockroomID currentStockroom = getRoomFor(from);
			temp = cartArea.getCartPositions();		// von aktueller CartArea all ihre CartPositions
			//allCartPositions.add(temp.get(0)); // nur erste CartPosition in CartArea betrachten
			StockroomID sID = getRoomFor(temp.get(0));
			tempP = computePaths(currentStockroom, sID);	// alle Wege zum Stockroom
			return tempP;
		}

	@Override
	public Route calculateExplorationRoute(Position from, Stockroom room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Route calculateChargingRoute(Position from) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDistance(Route route) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public StockroomID[] getNearRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean load(int numberOfProducts, IssuingPoint issuingPoint,
			Cart cart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cart takeCart(CartPosition cartPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean returnCart(Cart cart, CartPosition cartPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<StockroomID> getStockrooms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IssuingPoint> getIssuingPoints(ProductType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IssuingPoint> getAllIssueingPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartArea> getCartAreas() {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public List<Path> computePaths(StockroomID roomSrc, StockroomID roomDst) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public StockroomID getRoomFor(Position position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Door> getDoors(StockroomID stockroom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getCurrentPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCurrentOrientation() {
		// TODO Auto-generated method stub
		return 0;
	}
}