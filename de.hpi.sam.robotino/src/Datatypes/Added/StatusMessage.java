package Datatypes.Added;

import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

import de.cpslab.robotino.actuator.communication.Message;

public class StatusMessage extends Message {
	
	Object content;
	StateType.message typeOfMessage;
	
	public StatusMessage(StateType.message type) {
		super();
		typeOfMessage = type;
		content = null;
	}
	
	public StatusMessage(StateType.message type, Object con) {
		super();
		typeOfMessage = type;
		content = con;
	}
	
	// Set and get the type of a message
	public StateType.message getTypeOfMessage() {
		return typeOfMessage;
	}

	public void setTypeOfMessage(StateType.message typeOfMessage) {
		this.typeOfMessage = typeOfMessage;
	}

	// Set and get contenct
	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}	
}
