package com.org.trackmanager;

public class TrackZoneVerifier {

	public boolean isWithin2DZone(Track track, Zone zone) {
		return track.getX() > zone.getMinX() && track.getX() < zone.getMaxX() && track.getY() < zone.getMaxY() && track.getY() > zone.getMinY();
	}

}
