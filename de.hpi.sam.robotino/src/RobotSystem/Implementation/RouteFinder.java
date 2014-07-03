package RobotSystem.Implementation;

import java.util.ArrayList;
import java.util.List;

import Datatypes.Added.*;
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
import de.hpi.sam.warehouse.stock.StockroomManagement;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;
import de.cpslab.robotino.sensor.interfaces.INorthStar;
import de.hpi.sam.warehouse.environment.*;

public class RouteFinder implements IRouteFinder {
	
	private Position CHARGINGSTATION;
	private Position destinationPosition;
	private WarehouseRepresentation warehouseRep;
	private StockroomManagement 	stockMang;
	
	private List<Route> calculateSubRoutes(RoomPoint from, RoomPoint to) {
	       
        StockroomID start = from.getRoom();
        StockroomID end = to.getRoom();
        List<Route> routes = new ArrayList<Route>();
       
        List<Path> paths = warehouseRep.computePaths(start, end);
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

	
	
	
	
	
	
	
	public List<Route> calculateCartAreaRoutes(Position from) {
			List<CartArea> startRoom = warehouseRep.getRoomFor(form);
		
		/*
			StockroomID currentStockroom = warehouseRep.getRoomFor(from);				// jetzigen Stockroom bestimmen.
			List<CartArea> cartAreas =  stockMang.getCartAreas();
			List<CartPosition> temp = new ArrayList<CartPosition>();
			List<CartPosition> allCartPositions = new ArrayList<CartPosition>();	//stelle i korrespondiert zu stelle i von cartAreas
			for (CartArea i : cartAreas) {
				temp = i.getCartPositions();		// von aktueller CartArea all ihre CartPositions
				allCartPositions.add(temp.get(0));	// nur erste CartPosition in jeder CartArea betrachten
			}
			//List<StockroomID> stockrooms = new ArrayList<StockroomID>();	// Stockrooms f�r CartAreas mit ihrer CartPosition
			List<Path>    tempP = new ArrayList<Path>();
			List<Integer> tempP2 = new ArrayList<Integer>();
			List<Route> routes = new ArrayList<Route>();
			for (CartPosition i : allCartPositions) {
				// if CART ?
				StockroomID sID = warehouseRep.getRoomFor(i);		//Stockroom f�r jede CartArea holen
				//currentStockroom.
				tempP = warehouseRep.computePaths(currentStockroom, sID);	// alle Wege zu einem Stockroom
				for (Path p : tempP) {		// k�rzesten Weg/Path zu einem Stockroom w�hlen, entspricht hier Anzahl der am geringsten zu durchfahrenen PathElements
					tempP2.add(p.getPathElements().size());
					int min = tempP2.get(0);
					int minIndex = 0;
					for (int m = 1; m < tempP2.size(); m++) {
						if (min > tempP2.get(m)) {
							min = tempP2.get(m);
							minIndex = m;
						}
					}																	// TODO: weg von t�r zur cartarea hinzuf�gen
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
			return paths; */
		}
		
		public List<Route> calculateCartAreaRoutes(Position from, Order order) {
			
			StockroomID currentStockroom = warehouseRep.getRoomFor(from);
			List<CartPosition> temp = new ArrayList<CartPosition>();
			List<Route> tempP = new ArrayList<Route>();
			List<Integer> tempP2 = new ArrayList<Integer>();
			List<Route> paths = new ArrayList<Route>();
			List<CartPosition> allCartPositions = new ArrayList<CartPosition>();	//stelle i korrespondiert zu stelle i von cartAreas
			
			CartArea cartArea = order.getCartArea();
			temp = cartArea.getCartPositions();		// von aktueller CartArea all ihre CartPositions
			allCartPositions.add(temp.get(0)); // nur erste CartPosition in CartArea betrachten
			StockroomID sID = warehouseRep.getRoomFor(allCartPositions);
			tempP = warehouseRep.computePaths(currentStockroom, sID);	// alle Wege zum Stockroom
			tempP.add(allCartPositions);
			//tempP.add(computePaths(currentStockroom, sID));	
			
			
			
			
			return tempP;			// TODO: weg von t�r zur cartarea hinzuf�gen
		}
		
		public List<Route> calculateIssuingPointsRoutes(Position from, Order order) {
			/*
			 * ordergetCartArea
			 * CartArea.getCartPosition(0)
			 * GetRoomFor(CartPosition)
			 * ComputePath(letztes aus IssuingPointList, Zielstockroom)
			 * gib Pfade zur�ck
			 */
			List<CartPosition> temp = new ArrayList<CartPosition>();
			List<CartPosition> allCartPositions = new ArrayList<CartPosition>();
			List<Route> tempP = new ArrayList<Route>();
			
			CartArea cartArea = order.getCartArea();
			StockroomID currentStockroom = warehouseRep.getRoomFor(from);
			temp = cartArea.getCartPositions();		// von aktueller CartArea all ihre CartPositions
			//allCartPositions.add(temp.get(0)); // nur erste CartPosition in CartArea betrachten
			StockroomID sID = warehouseRep.getRoomFor(temp.get(0));
			tempP = warehouseRep.computePaths(currentStockroom, sID);	// alle Wege zum Stockroom
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
}