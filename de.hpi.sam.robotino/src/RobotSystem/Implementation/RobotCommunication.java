import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public class RobotCommunication implements IWlanAdapter, IRobotCommunication {

	private float WLANTHRESHOLD;
	private CommunicationID server;
	private RobotinoID robot;
	private Queue<Message> incoming;		// = new LinkedList<Message>;
	private StockroomID[] stockrooms;
	private RobotinoID[] communicationPartners;
	
	void exchangeInformation() {
	
	}
}