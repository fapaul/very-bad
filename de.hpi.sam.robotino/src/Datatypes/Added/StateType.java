package Datatypes.Added;

import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public class StateType {
	
	public static enum message {
		SERVER_WAKEUP, SERVER_SLEEP, SERVER_STATUS,  SERVER_ORDERTIME,
		SERVER_ORDER, SERVER_POSITION, ROBOT_STATUS, 
		ROBOT_POSITION, ROBOT_ORDERTIME, ROBOT_FINISH, ROBOT_CHARGING,
		
	}
	public static enum robot {
		IDLE, BUMPED, EXPLORING, EXECUTING, SLEEPING, CHARGING	
	}
}