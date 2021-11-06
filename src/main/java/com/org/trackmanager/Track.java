package com.org.trackmanager;

/**
 * The track data type
 * 
 * @author emusk
 *
 */
public class Track {

	private int id;
	private int sensor;
	private float x;
	private float y;
	private float z;
	private boolean delete;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSensor() {
		return sensor;
	}
	
	public void setSensor(int sensor) {
		this.sensor = sensor;
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getZ() {
		return z;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
	public boolean isDelete() {
		return delete;
	}
	
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
}
