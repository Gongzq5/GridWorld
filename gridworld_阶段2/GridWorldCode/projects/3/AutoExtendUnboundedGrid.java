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
	private Object[][] occupantArray; // store the entries
	private int currentSide;
	
    /**
     * Constructs an empty unbounded grid.
     */
    public AutoExtendUnboundedGrid()
    {
    	currentSide = 16;
    	occupantArray = new Object[currentSide][currentSide];
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

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
        if (loc == null)
            throw new NullPointerException("loc == null");


        if (loc.getRow() >= currentSide || loc.getCol() >= currentSide) {
        	return null;
        }
        
        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }

    public E put(Location loc, E obj)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");
        
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

    public E remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");

        if (loc.getRow() >= currentSide || loc.getCol() >= currentSide) {
        	return null;
        }
        E oldEle = (E) occupantArray[loc.getRow()][loc.getCol()];
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return oldEle;
    }
    
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
