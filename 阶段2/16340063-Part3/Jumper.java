
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
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author (should I write my name here? may be gzq?)
 */

import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Actor {
    
    /**
     * Constructs a box bug that traces a square of a given side length
     * 
     * @param length
     *            the side length
     */
    public Jumper() {
        setColor(Color.GRAY);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        if (canMove()) {
            move();
        } else {
            turn();             
        }
    }
    
    /**
     * Turn 45 degrees to the right.
     */
    private void turn() {
        setDirection(getDirection()+Location.HALF_RIGHT);
    }

    /**
     * Test if a jumper can move to a empty location.
     * 
     * @return true if the jumper can move.
     */
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null){
            return false;
        }
        Location next = getLocation().getAdjacentLocation(getDirection());
        next = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        }
        Actor jumperAim = gr.get(next);
        return (jumperAim == null) || (jumperAim instanceof Flower);
        // ok to move into a location empty or onto a flower.
        // not ok to move onto any other actor.
    }
    

    /**
     * Moves the bug to two location away.
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null){
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        next = next.getAdjacentLocation(getDirection());
        if (gr.isValid(next)){
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
    }
}
















