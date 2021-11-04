package com.org.trackmanager;

import java.util.ArrayList;

import com.org.dds.DDS_Alert;
import com.org.dds.DDS_Track;

public class TrackManager {

	private ArrayList<Track> qualifiedTracks;
	private Zone zone;
	private MessageService messageService;
	
	public TrackManager() {
		zone = new Zone("DangerClose", 0.0F, 5.0F, 0.0F, 10.0F);
		qualifiedTracks = new ArrayList<Track>();
		messageService = new MessageService();
	}
	
	public void processTrack(DDS_Track dds_track) {
		//get the track out of the message
		Track track = dds_track.track;
		
		if (!track.isDelete() ) {
			boolean exists = false;
			for (Track existingTrack : qualifiedTracks) {
				if ((existingTrack.getId() == track.getId()) && (existingTrack.getSensor() == track.getSensor())) {
					exists = true;
					break;
				}
			}
			
			if (exists) {
				//check if it still qualified in the zone
				//this is done by checking if the track is within the 2D zone
				if (track.getX() > zone.getMinX() && track.getX() < zone.getMaxX() && track.getY() < zone.getMaxY() && track.getY() > zone.getMinY()) {
					//get index of track, and use that to overwrite the previous report
					for (int i = 0; i < qualifiedTracks.size(); i++) {
						if (qualifiedTracks.get(i).getId() == track.getId() && qualifiedTracks.get(i).getSensor() == track.getSensor()) {
							//overwrite the track in slot i
							qualifiedTracks.set(i, track);
						}
					}
				} else {
					//if track exists and no longer qualified, remove it from the list and alert the user
					//get index of track, and use that to overwrite the previous report
					for (int i = 0; i < qualifiedTracks.size(); i++) {
						if (qualifiedTracks.get(i).getId() == track.getId() && qualifiedTracks.get(i).getSensor() == track.getSensor()) {
							qualifiedTracks.remove(i);
							
							//send an alert to the user that the track is no longer in the zone
							DDS_Alert alert = new DDS_Alert();
							alert.alertText = "Track ID : " + track.getId() + " - No Longer Qualifies";
							messageService.send(alert);
						}
					}
				}
			} else {
				//check if the track is in the zone - this is done by checking if the track is within the 2D zone
				if (track.getX() > zone.getMinX() && track.getX() < zone.getMaxX() && track.getY() < zone.getMaxY() && track.getY() > zone.getMinY()) {
					qualifiedTracks.add(track);
					
					//send an alert to the user that the track is in the zone
					DDS_Alert alert = new DDS_Alert();
					alert.alertText = "Track ID : " + track.getId() + " - Qualified";
					messageService.send(alert);
				}
			}
		} else {
			//if the track exists, remove it because it has been removed by the sensor
			for (int i = 0; i < qualifiedTracks.size(); i++) {
				//verify that the track exists in our qualified list
				if (qualifiedTracks.get(i).getId() == track.getId() && qualifiedTracks.get(i).getSensor() == track.getSensor()) {
					qualifiedTracks.remove(i);
				}
			}
		}
	}
}
