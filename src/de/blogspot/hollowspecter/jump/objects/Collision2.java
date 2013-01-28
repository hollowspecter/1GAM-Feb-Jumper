package de.blogspot.hollowspecter.jump.objects;

public class Collision2 {
	
	public static final double EPS = 1e-9;
	
	public static boolean intersectingAxis(float min1, float max1, float min2, float max2)
	{
		if (min2 >= max1 || min1 >= max2)
			return false;
		else if (min1 < min2 && min2 < max1 || min2 < min1 && min1 < max2)
			return true;

		return false;
		
//		if (min2 >= max1 || min1 >= max2)
//			return false;
//		else if (min1 < min2 && min2 < max1)
//			return true;
//		else if (min2 < min1 && min1 < max2)
//			return true;
//		else 
//			return false;
	}
	
	public static boolean intersection(AABB box1, AABB box2)
	{
		//x-Achse
//		if (intersectingAxis(box1.posX, box1.posX+box1.width, box2.posX, box2.posX+box2.width))
//			return true;
//		else 
		
		//only y-axis
		
		boolean xAxis = false, yAxis = false;
		
		if (intersectingAxis(box1.posX, box1.posX+box1.width, box2.posX, box2.posX+box2.width))
			xAxis = true;
		
		if (!intersectingAxis(box1.getPosY(), box1.getPosY()+box1.getHeight(), box2.getPosY(), box2.getPosY()+box2.getHeight()))
			yAxis = true;

		if (xAxis || yAxis)
			return true;
		else
			return false;
	}

}
