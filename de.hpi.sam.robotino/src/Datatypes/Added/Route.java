package Datatypes.Added;

import java.util.LinkedList;
import java.util.List;

public class Route {
	
	private List<RoomPoint> points = new LinkedList<RoomPoint>();
	private double			distance = 0;
	
	public List<RoomPoint> getRoomPoints() {
		return points;
	}
	
	public void add(RoomPoint roomPoint) {
		if(points.size() > 1) {
			distance += points.get(points.size()-1).distance(roomPoint);
		}
		points.add(roomPoint);
	}
	
	public void concat(Route otherRoute) {
		// Concats a route to another
		for(RoomPoint roomPoint : otherRoute.getRoomPoints()) {
			if(points.size() > 1) {
				distance += points.get(points.size()-1).distance(roomPoint);
			}
			points.add(roomPoint);
		}
	}
	
	public double getDistance() {
		return distance;
	}	
}
