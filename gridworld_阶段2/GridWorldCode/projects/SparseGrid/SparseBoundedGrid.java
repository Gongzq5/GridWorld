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

import info.gridworld.grid.*;

import java.util.ArrayList;

/**
 * A <code>BoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private Object[] sparseArray;
    private int row;
    private int col;
    
    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        sparseArray = new Object[rows];
        row = rows;
        col = cols;
    }

    public int getNumRows()
    {
        return row;
    }

    public int getNumCols()
    {
        return col;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
        	SparseGridNode actorInRow = (SparseGridNode) sparseArray[r];
            while (actorInRow != null) {
            	theLocations.add(new Location(r, actorInRow.getCol()));
            	actorInRow = actorInRow.getNext();
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        SparseGridNode actorInRow = (SparseGridNode) sparseArray[loc.getRow()];
        while (actorInRow != null) {
        	if (actorInRow.getCol() == loc.getCol()) {
        		return (E) actorInRow.getOccupant();
        	}  
        	actorInRow = actorInRow.getNext();
        }
       	return null;
        // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
        
        remove(loc);
        // Add the object to the grid.
        E oldOccupant = get(loc);
        SparseGridNode actorInRow = (SparseGridNode) sparseArray[loc.getRow()];
        if (actorInRow == null) {
        	sparseArray[loc.getRow()] = new SparseGridNode(loc.getCol(), obj);
        } else {
        	while (actorInRow.getNext() != null) {
        		actorInRow = actorInRow.getNext();
        	}
        	actorInRow.setNext(new SparseGridNode(loc.getCol(), obj));
        }
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        SparseGridNode actorInRow = (SparseGridNode) sparseArray[loc.getRow()];

        SparseGridNode previousNode = null;
        
        while (actorInRow != null) {
        	if (actorInRow.getCol() == loc.getCol()) {
        		// right in the place, remove it.
        		
        		if (previousNode == null) {
        			sparseArray[loc.getRow()] = actorInRow.getNext();
        		} else {
        			previousNode.setNext(actorInRow.getNext());
        		}
        		
        		break;
        	}
        	
        	previousNode = actorInRow;
        	actorInRow = actorInRow.getNext();
        }
        
        return r;
    }
}
