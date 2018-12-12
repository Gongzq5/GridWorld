public class SparseGridNode {
	private Object occupant;
	private int col;
	private SparseGridNode next;
	
	public SparseGridNode(int _col, Object _occupant) {
		occupant = _occupant;
		col = _col;
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