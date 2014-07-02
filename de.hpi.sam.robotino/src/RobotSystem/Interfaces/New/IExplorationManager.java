import de.hpi.sam.warehouse.*;
import de.cpslab.robotino.*;

public interface IExplorationManager {

	Date calculateExplorationTime(StockroomID room);
	
	void explorationStart(StockroomID room);
	
	boolean isExplored(StockroomID room);
}