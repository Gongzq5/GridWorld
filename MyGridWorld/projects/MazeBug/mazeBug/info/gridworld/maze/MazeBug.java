package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		System.out.println(willMove);
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		} else if (!willMove) {
			goBack();
			stepCount++;
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		int dirs[] = {Location.WEST, Location.NORTH, Location.SOUTH, Location.EAST};
		for (int each : dirs) {
			Location adloc = getLocation().getAdjacentLocation(each);
			if (crossLocation.isEmpty()) {
				crossLocation.push(new ArrayList<Location>());
				crossLocation.peek().add(getLocation());
			}
			if (gr.isValid(adloc)) {
				if (gr.get(adloc) instanceof Rock && gr.get(adloc).getColor().equals(Color.RED)) {
					isEnd = true;
				}
				if (gr.get(adloc) == null &&
					!crossLocation.peek().contains(adloc)) {				
					valid.add(adloc);
				}
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		if (getValid(getLocation()) == null) {
			return false;
		}
		if (getValid(getLocation()).isEmpty()) {
			return false;
		}		
		next = getValid(getLocation()).get(0);
		return true;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();		
		if (gr.isValid(next)) {
			crossLocation.peek().add(next);
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
			ArrayList<Location> newArray = new ArrayList<Location>();
			newArray.add(next);
			crossLocation.push(newArray);
		} else { 
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
	
	public void goBack() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		if (crossLocation.isEmpty()) return;
		crossLocation.pop();
		if (crossLocation.isEmpty()) return;
		ArrayList<Location> previousArray = crossLocation.peek();
		Location returnLocation = previousArray.get(0);
		Location loc = getLocation();
		
		int dir = loc.getDirectionToward(returnLocation);
		setDirection(dir);
		moveTo(returnLocation);
		
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
}
