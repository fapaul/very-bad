package ServerSystem.Implementation;

import java.util.Date;
import java.util.List;
import java.util.Queue;

import Datatypes.Added.RobotStatusType;
import ServerSystem.Interfaces.New.IServerCommunication;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.Order;
import de.cpslab.robotino.*;
import de.cpslab.robotino.actuator.communication.CommunicationID;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.actuator.interfaces.IWLanAdapter;
import de.cpslab.robotino.environment.Position;

public class ServerCommunication implements IServerCommunication, IWLanAdapter {

	private Queue<Message> incoming; // = new LinkedList..

	@Override
	public boolean register() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CommunicationID> scanForCommunicationPartnerInRange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sendMessage(CommunicationID receiver, Message message) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Message> receiveMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RobotStatusType requestRobotStatus(RobotinoID robot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date requestOrderTime(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position requestPosition(RobotinoID root) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendSleep(RobotinoID robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendWakeup(RobotinoID robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendOrderStart(RobotinoID robot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasMessage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Message readMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	

}