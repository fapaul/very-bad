package Datatypes.Added;

import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public class MessageTypeof {
	public static enum t {
		SERVER_WAKEUP, SERVER_SLEEP, SERVER_STATUS, SERVER_ORDERTIME, SERVER_ORDER, ROBOT_STATUS, ROBOT_ORDERTIME, ROBOT_FINISH, ROBOT_CHARGING
	}
}