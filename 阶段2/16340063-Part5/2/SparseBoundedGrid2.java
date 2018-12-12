import java.util.ArrayList;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.*;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E>
{
    private Map<Location, E> occupantMap;
    private int rows, cols;
    
    /**
     * Constructs an empty unbounded grid.
     */
    public SparseBoundedGrid2(int row, int col)
    {
        occupantMap = new HashMap<Location, E>();
        rows = row;
        cols = col;
    }

    /**
     * @return the row of the grid.
     */
    public int getNumRows()
    {
        return rows;
    }

    /**
     * @return the col of the grid.
     */
    public int getNumCols()
    {
        return cols;
    }

    /**
     * If the loc is in the grid, return true.
     * else, return false.
     */
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows() 
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    /**
     * Get all of the locations contains occupant
     * @return the locations
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet()){
            a.add(loc);
        }
        return a;
    }

    public E get(Location loc)
    {
        if (loc == null){
            throw new IllegalArgumentException("loc == null");
        }
        return occupantMap.get(loc);
    }

    /**
    * Put the occupant to the loc in the grid.
    * @return return the old occupant in that loc.
    */
    public E put(Location loc, E obj)
    {
        if (loc == null){
            throw new IllegalArgumentException("loc == null");
        }
        if (obj == null){
            throw new IllegalArgumentException("obj == null");
        }
        return occupantMap.put(loc, obj);
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
        return occupantMap.remove(loc);
    }
}
