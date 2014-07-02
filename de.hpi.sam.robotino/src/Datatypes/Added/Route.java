package Datatypes.Added;

import java.util.List;

public class Route {
	
	private List<RoomPoint> points;
	
	public List<RoomPoint> getRoomPoints() {
		return points;
	}
	
	public void add(RoomPoint roomPoint) {
		points.add(roomPoint);
	}
}
