package RobotSystem.Interfaces.New;

import java.util.Date;

import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.cpslab.robotino.*;

public interface IExplorationManager {

	Date calculateExplorationTime(StockroomID room);
	
	void explorationStart(StockroomID room);
	
	boolean isExplored(StockroomID room);
}