package Datatypes.Added;

import java.util.List;

import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.CartPosition;
import de.hpi.sam.warehouse.stock.CartArea;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;

public class RoomPointCartArea extends RoomPoint {
	/**
	 * Cart positions wihtin this area.
	 */
	List<CartPosition> carts;

	/*
	 * ##########################################################################
	 * 
	 * Constructor
	 * 
	 * ##########################################################################
	 */

	/**
	 * Create a new cart area that maintains the given positions.
	 * 
	 * @param cartPositions
	 *            A non empty list of cart position that corresponds to the
	 *            created area.
	 */
	public RoomPointCartArea(CartArea cartArea)
	{
		super(cartArea.getCartPositions().get(0));
		carts = cartArea.getCartPositions();
	}
}
