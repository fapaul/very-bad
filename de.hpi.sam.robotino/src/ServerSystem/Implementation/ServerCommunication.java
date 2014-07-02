package ServerSystem.Implementation;

import java.util.Date;
import java.util.Queue;

import Datatypes.Added.MessageTypeof;
import Datatypes.Added.RobotStatusType;
import Datatypes.Added.StatusMessage;
import ServerSystem.Interfaces.New.IServerCommunication;
import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.communication.WarehouseCommunicationServer;
import de.hpi.sam.warehouse.order.Order;

public class ServerCommunication implements  IServerCommunication {

	private Queue<Message> incoming; // = new LinkedList..
	private WarehouseCommunicationServer serverCommServ; 
		
	@Override
	public RobotStatusType requestRobotStatus(RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(MessageTypeof.t.ROBOT_STATUS);
		serverCommServ.sendMessage(robot, messToSend);
		
		// TODO wait for incoming message
		return null;
	}

	@Override
	public Date requestOrderTime(Order order) {
		
		return null;
	}

	@Override
	public Position requestPosition(RobotinoID root) {
		// TODO missing status for getting position
		//	StatusMessage messToSend = new StatusMessage(MessageTypeof.t.);
	//	serverCommServ.sendMessage(robot, messToSend);
		// TODO wait for incoming message
		return null;
	}

	@Override
	public void sendSleep(RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(MessageTypeof.t.SERVER_SLEEP);
		serverCommServ.sendMessage(robot, messToSend);
	}

	@Override
	public void sendWakeup(RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(MessageTypeof.t.SERVER_WAKEUP);
		serverCommServ.sendMessage(robot, messToSend);
	}

	@Override
	public void sendOrderStart(RobotinoID robot) {
		StatusMessage messToSend = new StatusMessage(MessageTypeof.t.SERVER_ORDER);
		// TODO add order message 
		serverCommServ.sendMessage(robot, messToSend);
	}

	@Override
	public boolean hasMessage() {
		int oldLength = incoming.size();
		incoming.addAll(serverCommServ.receiveMessages());
		return oldLength < incoming.size();
	}

	@Override
	public Message readMessage() {
		return incoming.poll();
	}
	

}