package com.org.trackmanager;

/**
 * The zone data type
 * 
 * @author bgates
 *
 */
public class Zone {
  private String name;
  private float minX;
  private float maxX;
  private float minY;
  private float maxY;
  private float minZ;
  private float maxZ;

  public Zone(String name, float minX, float maxX, float minY, float maxY, float minZ, float maxZ) {
    setName(name);
    setMinX(minX);
    setMaxX(maxX);
    setMinY(minY);
    setMaxY(maxY);
    setMinZ(minZ);
    setMaxZ(maxZ);
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Float.floatToIntBits(maxX);
    result = prime * result + Float.floatToIntBits(maxY);
    result = prime * result + Float.floatToIntBits(maxZ);
    result = prime * result + Float.floatToIntBits(minX);
    result = prime * result + Float.floatToIntBits(minY);
    result = prime * result + Float.floatToIntBits(minZ);
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Zone other = (Zone) obj;
    if (Float.floatToIntBits(maxX) != Float.floatToIntBits(other.maxX))
      return false;
    if (Float.floatToIntBits(maxY) != Float.floatToIntBits(other.maxY))
      return false;
    if (Float.floatToIntBits(maxZ) != Float.floatToIntBits(other.maxZ))
      return false;
    if (Float.floatToIntBits(minX) != Float.floatToIntBits(other.minX))
      return false;
    if (Float.floatToIntBits(minY) != Float.floatToIntBits(other.minY))
      return false;
    if (Float.floatToIntBits(minZ) != Float.floatToIntBits(other.minZ))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getMinX() {
    return minX;
  }

  public void setMinX(float min_x) {
    this.minX = min_x;
  }

  public float getMaxX() {
    return maxX;
  }

  public void setMaxX(float max_x) {
    this.maxX = max_x;
  }

  public float getMinY() {
    return minY;
  }

  public void setMinY(float min_y) {
    this.minY = min_y;
  }

  public float getMaxY() {
    return maxY;
  }

  public void setMaxY(float max_y) {
    this.maxY = max_y;
  }

  public float getMinZ() {
    return minZ;
  }

  public void setMinZ(float minZ) {
    this.minZ = minZ;
  }

  public float getMaxZ() {
    return maxZ;
  }

  public void setMaxZ(float maxZ) {
    this.maxZ = maxZ;
  }
}
