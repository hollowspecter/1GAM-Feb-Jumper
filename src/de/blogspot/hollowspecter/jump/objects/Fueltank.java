package de.blogspot.hollowspecter.jump.objects;

public class Fueltank {
	
	/*
	 * fuel tank
	 * reduce fuel
	 * incrfuel
	 * get fuel
	 * or tank up fuel
	 * state: hasFuel, empty! hasFuel=1, empty=0;
	 */
	
	protected float tank_capacity;
	protected float fuel;
	
	public Fueltank(float tank_capacity, float fuel_you_start_with) {
		this.tank_capacity = tank_capacity;
		fuel = fuel_you_start_with;
		update();
	}
	
	public void useFuel(float fuel) {
		this.fuel -= fuel;
		update();
	}
	
	public boolean checkFuel() {
		return (fuel > 0);
	}
	
	public void gasUp(float fuel) {
		this.fuel += fuel;
		update();
	}
	
	public void update() {
		normalizeFuel();
	}
	
	public void normalizeFuel() {
		if (fuel > tank_capacity)
			fuel = tank_capacity;
		if (fuel < 0)
			fuel = 0;
	}
	
	public void resetTank() {
		fuel = tank_capacity;
	}
	
	//getter und setter
	public float getFuel() {
		return fuel;
	}
	
	public float getTank_capacity() {
		return tank_capacity;
	}
}
