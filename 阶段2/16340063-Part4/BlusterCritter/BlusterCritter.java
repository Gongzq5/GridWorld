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
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.*;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
    private static final double DARKENING_FACTOR = 0.05;
    private static final double LIGHTENING_FACTOR = 0.2;
    private static final int RGBMAX = 255;
    private int courage;

    /**
     * Constructor of the BlusterCritter
     * @param c the courage of the instance
     */
    public BlusterCritter(int c) {
        super();
        courage = c;
    }
    
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n < courage) {
            brighter();
        } else {
            darken();
        }
    }
    
    /**
     * Darken the color.
     */
    public void darken() {
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        
        setColor(new Color(red, green, blue));
    }
    
    /**
     * Brighter the color.
     */
    public void brighter() {
        Color c = getColor();
        int red = (int) (c.getRed() * (1 + LIGHTENING_FACTOR));
        int green = (int) (c.getGreen() * (1 + LIGHTENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 + LIGHTENING_FACTOR));
        
        red++; 
        green++;
        blue++;

        red = red > RGBMAX ? RGBMAX : red;
        green = green > RGBMAX ? RGBMAX : green;
        blue = blue > RGBMAX ? RGBMAX : blue;
        
        setColor(new Color(red, green, blue));
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
        ArrayList<Actor> a = new ArrayList<Actor>();
        Location loc = this.getLocation();
        for (int i = loc.getRow()-2; i < loc.getRow() + 2; i++) {
            for (int j = loc.getCol()-2; j < loc.getCol() + 2; j++) {
                Location tmpLoc = new Location(i,j);
                if (getGrid().isValid(tmpLoc)) {
                    Actor tmpActor = getGrid().get(tmpLoc);
                    if (tmpActor != null && tmpActor != this) {
                        a.add(tmpActor);
                    }
                }
            }
        }
        return a;
    }
    
}
