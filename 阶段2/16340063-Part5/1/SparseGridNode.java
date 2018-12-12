/*
 * The node of the SparseGrid
 */

public class SparseGridNode {
    private Object occupant;
    private int col;
    private SparseGridNode next;
    
    public SparseGridNode(int c, Object o) {
        occupant = o;
        col = c;
        next = null;
    }
    
    public void setNext(SparseGridNode n) {
        next = n;
    }
    
    public SparseGridNode getNext() {
        return next;
    }
    
    public Object getOccupant() {
        return occupant;
    }
    
    public int getCol() {
        return col;
    }
}