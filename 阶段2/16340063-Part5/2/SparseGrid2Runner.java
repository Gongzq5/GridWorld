
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public final class SparseGrid2Runner {
    
    private SparseGrid2Runner() {}
    
    public static void main(String args[] ) {
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");
        world.addGridClass("SparseBoundedGrid2");
        world.addGridClass("SparseBoundedGrid3");
        world.addGridClass("AutoExtendUnboundedGrid");
        world.add(new Location(2, 2), new Bug());
        world.show();
    }
}