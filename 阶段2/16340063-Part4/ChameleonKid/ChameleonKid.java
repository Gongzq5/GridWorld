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

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonKid extends ModifiedChameleonCritter
{
    private static final double DARKENING_FACTOR = 0.05;
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0) {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
            
            setColor(new Color(red, green, blue));     
        } else {
            int r = (int) (Math.random() * n);

            Actor other = actors.get(r);
            setColor(other.getColor());  
        }
    }
    
    /**
     * Gets the actors for processing. Implemented to return the actors that
     * occupy neighboring grid locations. Override this method in subclasses to
     * look elsewhere for actors to process.<br />
     * Postcondition: The state of all actors is unchanged.
     * @return a list of actors that this critter wishes to process.
     */
    public ArrayList<Actor> getActors()
    {
        Location frontLoc = getLocation().getAdjacentLocation(getDirection());
        Location behindLoc = getLocation().getAdjacentLocation(getDirection() + Location.HALF_CIRCLE);
        Actor frontActor = null, behindActor = null;
        if (getGrid().isValid(frontLoc)) {
            frontActor = getGrid().get(frontLoc);
        }
        if (getGrid().isValid(behindLoc)) {
             behindActor = getGrid().get(behindLoc);
        }
               
        ArrayList<Actor> r = new ArrayList<Actor>();
        if (frontActor != null) {
            r.add(frontActor);
        }
        if (behindActor != null) {
            r.add(behindActor);
        }
        return r;
    }
   
}
