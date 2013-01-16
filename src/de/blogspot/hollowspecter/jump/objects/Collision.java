package de.blogspot.hollowspecter.jump.objects;

public class Collision {
	
	public static final double EPS = 1e-9;
	
	public static boolean intersectAxis(float min1, float max1, float min2, float max2, float spdAxis, float tEnter, float tLeave)
	{
		/* Check is spdAxis is zero using epsilon */
		if (spdAxis < EPS) {
			if ((min1 >= max2) || (max1 <= min2)) {
				/* No speed and no overlapping => false */
				return false;
			} else {
				/* No movement, but they do overlap */
				return true;
			}
		} else {
			// see comment X below
			float start = (min1 - max2) / spdAxis;
			float leave = (max1 - min2) / spdAxis;
			
			// swapping if start>leave
			if (start > leave){
				float swap = start;
				start = leave;
				leave = swap;
			}
			
			if (start > tEnter)
				tEnter = start;
			if (leave < tLeave)
				tLeave = leave;
			if (tEnter > tLeave)
				return false;
		}
		return true;
	}
	
	public static boolean intersect(AABB box1, AABB box2, float spdX, float spdY, float tEnter, float tLeave)
	{
		tEnter = 0.0f;
		tLeave = 1.0f;
		
		// X-Axis
		if (intersectAxis(box1.xMin, box1.xMax, box2.xMin, box2.xMax, spdX, tEnter, tLeave))
			return false;
		
		// Y-Axis
		else if (intersectAxis(box1.yMin, box1.yMax, box2.yMin, box2.yMax, spdY, tEnter, tLeave))
			return false;
		
		else
			return true;
	}
	
	/*
	 * Comment X
	 * 
	 * (for the start of the intersection along D)
	 * Bmax + tEnter*D = Amin
	 * tEnter*D = Amin - Bmax
	 * tEnter = (Amin - Bmax) / D
	 * 
	 * (for the end of the intersection along D; the back side of A)
	 * Bmin + tLeave*D = Amax
	 * tLeave*D = Amax - Bmin
	 * tLeave = (Amax - Bmin) / D
	 */
}
