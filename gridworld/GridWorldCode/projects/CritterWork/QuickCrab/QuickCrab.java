/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.grid.Location;
import 

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
	
	/**
	 * Constructor of the BlusterCritter
	 * @param c the courage of the instance
	 */
	public QuickCrab() {
		super();
		setColor(Color.GREEN);
	}
	
    
    /**
     * Gets a list of possible locations for the next move. These locations must
     * be valid in the grid of this critter. Implemented to return the empty
     * neighboring locations. Override this method in subclasses to look
     * elsewhere for move locations.<br />
     * Postcondition: The state of all actors is unchanged.
     * @return a list of possible locations for the next move
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        
        Location rightOne = getLocation().getAdjacentLocation(getDirection() + Location.RIGHT);
        Location leftOne = getLocation().getAdjacentLocation(getDirection() + Location.LEFT);
       
        if (getGrid().isValid(rightOne) && getGrid().get(rightOne) == null) {
        	Location rightOfTheRight = rightOne.getAdjacentLocation(Location.RIGHT);
        	if (getGrid().isValid(rightOfTheRight)
        		&& getGrid().get(rightOfTheRight) == null) {
        		locs.add(rightOfTheRight);
        	}
        }
        
        if (getGrid().isValid(leftOne) && getGrid().get(leftOne) == null) {
        	Location leftOfTheLeft = leftOne.getAdjacentLocation(Location.LEFT);
        	if (getGrid().isValid(leftOfTheLeft)
        		&& getGrid().get(leftOfTheLeft) == null) {
        		locs.add(leftOfTheLeft);
        	}
        }
        
        if (locs.isEmpty()) {
        	return super.getMoveLocations();
        }
        
        return locs;
    }
}