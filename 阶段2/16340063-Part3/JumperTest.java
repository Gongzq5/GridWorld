/**
 * Junit test for Jumper class 
 * Test for every case in the question list.
 */


import static org.junit.Assert.*;

import org.junit.Test;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class JumperTest {
    private ActorWorld world;
    private Jumper fake;
    private final Location loc44 = new Location(4,4);
    private final Location loc24 = new Location(2,4);
    private final Location loc14 = new Location(1,4);
    private final Location loc04 = new Location(0,4);
    
    /**
     * Test for case a.
     * 
     * What will a jumper do if the location in front of it is 
     * empty, but the location two cells in front 
     * contains a flower or a rock?
     * 
     * If the location two cells in front contains a flower, 
     * the jumper will jump over it.
     * 
     * If the location two cells in front contains a rock, 
     * the jumper will turn 45 degrees.
     */
    @Test
    public void testForCaseA() {
    	fake = new Jumper();
        world = new ActorWorld(); 

        world.add(loc44, fake);
        world.add(loc24, new Rock());
        
        fake.act();
        
        assertEquals(fake.getLocation(), loc44);
        assertEquals(fake.getDirection(), Location.NORTHEAST);
    }
    
    /**
     * Test for case b.
     * 
     * What will a jumper do if the location two 
     * cells in front of the jumper is out of the grid?
     * 
     * It will turn 45 degrees to the right.
     */
    @Test
    public void testForCaseB() {
    	fake = new Jumper();
        world = new ActorWorld(); 

        world.add(loc14, fake);
        
        fake.act();
        
        assertEquals(fake.getLocation(), loc14);
        assertEquals(fake.getDirection(), Location.NORTHEAST);
    }
    
    /**
     * Test for case c.
     * 
     * What will a jumper do if it is facing an edge of the grid?
     * 
     * It will turn 45 degrees to the right.
     */
    @Test
    public void testForCaseC() {
    	fake = new Jumper();
        world = new ActorWorld(); 

        world.add(loc04, fake);
        
        fake.act();
        
        assertEquals(fake.getLocation(), loc04);
        assertEquals(fake.getDirection(), Location.NORTHEAST);
    }
    
    /**
     * Test for case d.
     * 
     * What will a jumper do if another actor (not a flower or a rock) is in 
     * the cell that is two cells in front of the jumper?
     * 
     * It will turn 45 degrees to the right.
     * 
     */
    @Test
    public void testForCaseD() {
    	fake = new Jumper();
        world = new ActorWorld(); 

        world.add(loc44, fake);
        world.add(loc24, new Actor());
        
        fake.act();
        
        assertEquals(fake.getLocation(), loc44);
        assertEquals(fake.getDirection(), Location.NORTHEAST);
    }

    /**
     * Test for case e.
     * 
     * What will a jumper do if it encounters another jumper in its path?
     * 
     * It will turn 45 degrees to the right.
     */
    @Test
    public void testForCaseE() {
    	fake = new Jumper();
        world = new ActorWorld(); 

        world.add(loc44, fake);
        world.add(loc24, new Jumper());
        
        fake.act();
        
        assertEquals(fake.getLocation(), loc44);
        assertEquals(fake.getDirection(), Location.NORTHEAST);
    }
    

}
