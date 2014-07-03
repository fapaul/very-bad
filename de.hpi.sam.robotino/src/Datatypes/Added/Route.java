package Datatypes.Added;

import java.util.List;

public class Route {
	
	private List<RoomPoint> points;
	private double			distance = 0;
	
	public List<RoomPoint> getRoomPoints() {
		return points;
	}
	
	public void add(RoomPoint roomPoint) {
		if(points.size() > 0) {
			distance += points.get(points.size()-1).distance(roomPoint);
		}
		points.add(roomPoint);
	}

	public double getDistance() {
		return distance;
	}	
}
