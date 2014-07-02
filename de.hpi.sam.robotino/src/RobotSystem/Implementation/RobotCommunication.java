package RobotSystem.Implementation;

import java.util.Date;
import java.util.List;
import java.util.Queue;

import Datatypes.Added.RobotStatusType;
import RobotSystem.Interfaces.New.IRobotCommunication;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.cpslab.robotino.*;
import de.cpslab.robotino.actuator.communication.CommunicationID;
import de.cpslab.robotino.actuator.communication.Message;
import de.cpslab.robotino.actuator.interfaces.IWLanAdapter;
import de.cpslab.robotino.environment.Position;

public class RobotCommunication implements IWLanAdapter, IRobotCommunication {

	private float WLANTHRESHOLD;
	private CommunicationID server;
	private RobotinoID robot;
	private Queue<Message> incoming;		// = new LinkedList<Message>;
	private StockroomID[] stockrooms;
	private RobotinoID[] communicationPartners;
	
	void exchangeInformation() {
	
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

	@Override
	public void sendRobotStatus(RobotStatusType status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendOrderTime(Date duration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendPosition(Position position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendOrderFinish(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getWorkload() {
		// TODO Auto-generated method stub
		return 0;
	}

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
}