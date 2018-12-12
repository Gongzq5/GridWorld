import System;

public class TEST {
	
	
	public int getDirectionToward()
    {
        int dx = 5 - 3;
        int dy = 6 - 4;
        // y axis points opposite to mathematical orientation
        int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));

        // mathematical angle is counterclockwise from x-axis,
        // compass angle is clockwise from y-axis
        int compassAngle = 90 - angle;
        // prepare for truncating division by 45 degrees
        compassAngle += 45 / 2;
        // wrap negative angles
        if (compassAngle < 0)
            compassAngle += 360;
        // round to nearest multiple of 45
        return (compassAngle / 45) * 45;
    }
	
	public static void main(String[] args)
	{
		System.out.println(getDirectionToward()); 	
	}
	
}

