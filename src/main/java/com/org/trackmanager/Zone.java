package com.org.trackmanager;

public class Zone {
	private String name;
	private float min_x;
	private float max_x;
	private float min_y;
	private float max_y;
	
	public Zone(String name, float min_x, float max_x, float min_y, float max_y) {
		setName(name);
		setMinX(min_x);
		setMaxX(max_x);
		setMinY(min_y);
		setMaxY(max_y);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getMinX() {
		return min_x;
	}
	
	public void setMinX(float min_x) {
		this.min_x = min_x;
	}
	
	public float getMaxX() {
		return max_x;
	}
	
	public void setMaxX(float max_x) {
		this.max_x = max_x;
	}
	
	public float getMinY() {
		return min_y;
	}
	
	public void setMinY(float min_y) {
		this.min_y = min_y;
	}
	
	public float getMaxY() {
		return max_y;
	}
	
	public void setMaxY(float max_y) {
		this.max_y = max_y;
	}
}
