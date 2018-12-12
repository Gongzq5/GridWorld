/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import java.util.ArrayList;

import info.gridworld.grid.*;

import java.util.*;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class AutoExtendUnboundedGrid<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray; 
    // store the entries
    private static final int INITSIZE = 16;
    private int currentSide;
    
    /**
     * Constructs an empty unbounded grid.
     */
    public AutoExtendUnboundedGrid()
    {
        currentSide = INITSIZE;
        occupantArray = new Object[currentSide][currentSide];
    }

    /**
     * @return the row of the grid.
     */
    public int getNumRows()
    {
        return currentSide;
    }

    /**
     * @return the col of the grid.
     */
    public int getNumCols()
    {
        return currentSide;
    }

    /**
     * If the loc is in the grid, return true.
     * else, return false.
     */
    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    /**
     * Get all of the locations contains occupant
     * @return the locations
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        
        for (int i = 0; i < currentSide; i++) {
            for (int j = 0; j < currentSide; j++) {
                Location loc = new Location(i, j);
                if (get(loc) != null) {
                    a.add(loc);
                }
            }
        }

        return a;
    }



    public E get(Location loc)
    {
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }

        if (loc.getRow() >= currentSide || loc.getCol() >= currentSide) {
            return null;
        }
        
        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }


    /**
    * Put the occupant to the loc in the grid.
    * @return return the old occupant in that loc.
    */
    public E put(Location loc, E obj)
    {
        if (loc == null) {
            throw new IllegalArgumentException("loc == null");
        }
        if (obj == null) {
            throw new IllegalArgumentException("obj == null");
        }
        
        int newSide = currentSide;
        while (loc.getRow() >= newSide || loc.getCol() >= newSide) {
            newSide *= 2;
        }
        
        // which means the size should be changed.
        if (newSide != currentSide) {
            extendTheSize(newSide);
        }
        
        E oldEle = (E) occupantArray[loc.getRow()][loc.getCol()];
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldEle;
    }

    /**
    * Remove the occupant in loc from the grid
    * If null, do nothing.
    * @return return the old occupant in that loc.
    */
    public E remove(Location loc)
    {
        if (loc == null){
            throw new IllegalArgumentException("loc == null");
        }
        if (loc.getRow() >= currentSide || loc.getCol() >= currentSide) {
            return null;
        }
        E oldEle = (E) occupantArray[loc.getRow()][loc.getCol()];
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return oldEle;
    }
    
    /**
     * Extend the grid's size to the newSide * newSide
     */
    public void extendTheSize(int newSide) {
        Object newArray[][] = new Object[newSide][newSide];
        for (int i=0; i<currentSide; i++) {
            for (int j=0; j<currentSide; j++) {
                newArray[i][j] = occupantArray[i][j];
            }
        }
        occupantArray = newArray;
        currentSide = newSide;
        
    }
}
