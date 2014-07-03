package RobotSystem.Implementation;

import java.util.Date;
import java.util.List;
import java.util.Queue;

import Datatypes.Added.StateType;
import Datatypes.Added.StatusMessage;
import RobotSystem.Interfaces.New.IRobotCommunication;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.actuator.communication.CommunicationID;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.actuator.communication.RobotinoWLanAdapter;
import de.cpslab.robotino.actuator.interfaces.IWLanAdapter;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.StockroomID;

public class RobotCommunication implements IRobotCommunication {

	private float WLANTHRESHOLD;
	private CommunicationID server;
	private RobotinoID robot;
	private Queue<Message> incoming;		// = new LinkedList<Message>;
	private List<StockroomID> stockrooms;
	private List <RobotinoID> communicationPartners;
	private RobotinoWLanAdapter robotComm;
	
	
	public void exchangeInformation() {
		List<CommunicationID> communicationPartnerIPs = robotComm.scanForCommunicationPartnerInRange();
		for (CommunicationID robot : communicationPartnerIPs){
			communicationPartners.add(new RobotinoID(robot.getName()));
			
			
		}
	
	}

	@Override
	public boolean hasMessage() {
		fetchMessage();
		return incoming.size() > 0;
	}

	@Override
	public Message readMessage() {
		return incoming.poll();
	}

	@Override
	public void sendRobotStatus(StateType.robot status) {
		StatusMessage messToSend = new StatusMessage(StateType.message.ROBOT_STATUS);
		robotComm.sendMessage(server, messToSend);
		
	}

	@Override
	public void sendOrderTime(Date duration) {
		StatusMessage messToSend = new StatusMessage(StateType.message.ROBOT_ORDERTIME);
		robotComm.sendMessage(server, messToSend);
		
	}

	@Override
	public void sendPosition(Position position) {
		StatusMessage messToSend = new StatusMessage(StateType.message.ROBOT_POSITION);
		robotComm.sendMessage(server, messToSend);
	}

	@Override
	public void sendOrderFinish(Order order) {
		StatusMessage messToSend = new StatusMessage(StateType.message.ROBOT_FINISH);
		robotComm.sendMessage(server, messToSend);
	}

	@Override
	public float getWorkload() {
		return 0;
	}
	
	public void fetchMessage(){
		incoming.addAll(robotComm.receiveMessages());
	}

	@Override
	public void requestForRobots() {
		StatusMessage messToSend = new StatusMessage(StateType.message.ROBOT_REQUEST);
		robotComm.sendMessage(server, messToSend);
		
		List<RobotinoID> robots = null;
		while(robots == null){
			//Wait for message
			List<Message> rec = robotComm.receiveMessages();
			
			
		}
		
	}
	

}