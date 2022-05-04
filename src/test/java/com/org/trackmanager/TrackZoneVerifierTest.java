package com.org.trackmanager;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TrackZoneVerifierTest {

	@Test
	public void givenTrackWithinZone_whenIsWithin2DZone_thenReturnTrue() {
		//set up
		Track track = new Track();
		track.setX(1.0f);
		track.setY(1.0f);
		
		Zone zone = new Zone("DangerClose", 0.0F, 5.0F, 0.0F, 10.0F, 0.0F, 0.0F);
		
		//CUT
		TrackZoneVerifier verifier = new TrackZoneVerifier();
		
		//Act
		boolean isInZone = verifier.isWithin2DZone(track, zone);
		
		//Verify
		assertTrue(isInZone);
		
	}
	
	@Test
	public void givenTrackWithXOverMaxNotWithinZone_whenIsWithin2DZone_thenReturnFalse() {
		//set up
		Track track = new Track();
		track.setX(10.0f);
		track.setY(1.0f);
		
		Zone zone = new Zone("DangerClose", 0.0F, 5.0F, 0.0F, 10.0F, 0.0F, 0.0F);
		
		//CUT
		TrackZoneVerifier verifier = new TrackZoneVerifier();
		
		//Act
		boolean isInZone = verifier.isWithin2DZone(track, zone);
		
		//Verify
		assertFalse(isInZone);
		
	}
	
	@Test
	public void givenTrackWithYOverMaxNotWithinZone_whenIsWithin2DZone_thenReturnFalse() {
		//set up
		Track track = new Track();
		track.setX(1.0f);
		track.setY(11.0f);
		
		Zone zone = new Zone("DangerClose", 0.0F, 5.0F, 0.0F, 10.0F, 0.0F, 0.0F);
		
		//CUT
		TrackZoneVerifier verifier = new TrackZoneVerifier();
		
		//Act
		boolean isInZone = verifier.isWithin2DZone(track, zone);
		
		//Verify
		assertFalse(isInZone);
		
	}

	@Test
	public void givenTrackWithXUnderMinNotWithinZone_whenIsWithin2DZone_thenReturnFalse() {
		//set up
		Track track = new Track();
		track.setX(-1.0f);
		track.setY(1.0f);
		
		Zone zone = new Zone("DangerClose", 0.0F, 5.0F, 0.0F, 10.0F, 0.0F, 0.0F);
		
		//CUT
		TrackZoneVerifier verifier = new TrackZoneVerifier();
		
		//Act
		boolean isInZone = verifier.isWithin2DZone(track, zone);
		
		//Verify
		assertFalse(isInZone);
		
	}

	@Test
	public void givenTrackWithYUnderMinNotWithinZone_whenIsWithin2DZone_thenReturnFalse() {
		//set up
		Track track = new Track();
		track.setX(1.0f);
		track.setY(-1.0f);
		
		Zone zone = new Zone("DangerClose", 0.0F, 5.0F, 0.0F, 10.0F, 0.0F, 0.0F);
		
		//CUT
		TrackZoneVerifier verifier = new TrackZoneVerifier();
		
		//Act
		boolean isInZone = verifier.isWithin2DZone(track, zone);
		
		//Verify
		assertFalse(isInZone);
	}


}
