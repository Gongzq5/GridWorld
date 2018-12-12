/**
 * Junit test for Jumper class 
 * Test for every case in the question list.
 */


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

class JumperTest {
	ActorWorld world;
	Jumper fake;
	final Location Loc_4_4 = new Location(4,4);
	final Location Loc_2_4 = new Location(2,4);
	final Location Loc_1_4 = new Location(1,4);
	final Location Loc_0_4 = new Location(0,4);
	
//	@BeforeEach
//	void BeforeEachFuntion() {
//		fake = new Jumper();
//		world = new ActorWorld(); 
//	}
	
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
	void TestForCase_a() {
		fake = new Jumper();
		world = new ActorWorld(); 
		
		world.add(Loc_4_4, fake);
		world.add(Loc_2_4, new Rock());
		
		fake.act();
		
		assertEquals(fake.getLocation(), Loc_4_4);
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
	void TestForCase_b() {
		world.add(Loc_1_4, fake);
		
		fake.act();
		
		assertEquals(fake.getLocation(), Loc_1_4);
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
	void TestForCase_c() {
		world.add(Loc_0_4, fake);
		
		fake.act();
		
		assertEquals(fake.getLocation(), Loc_0_4);
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
	void TestForCase_d() {
		world.add(Loc_4_4, fake);
		world.add(Loc_2_4, new Actor());
		
		fake.act();
		
		assertEquals(fake.getLocation(), Loc_4_4);
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
	void TestForCase_e() {
		world.add(Loc_4_4, fake);
		world.add(Loc_2_4, new Jumper());
		
		fake.act();
		
		assertEquals(fake.getLocation(), Loc_4_4);
		assertEquals(fake.getDirection(), Location.NORTHEAST);
	}
	

}
