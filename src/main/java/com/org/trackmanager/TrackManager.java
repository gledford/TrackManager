/*
 * Name:  Elon Musk
 * JIRA-ID: TM-103
 * Date:  04/23/1999
 *
 * Name:  Bill Gates
 * JIRA-ID: TM-345
 * Date:  06/20/2005
 *
 * @abstract
 * The TrackManager handles incoming tracks from external systems. These
 * tracks are then checked against the current defense zone to see if
 * they break the plane of the zone. If they do, then this class holds
 * onto the tracks and notifies the users of the threat.
 */

package com.org.trackmanager;

import com.org.dds.DDS_Alert;
import com.org.dds.DDS_Message;
import com.org.dds.DDS_Track;
import java.util.ArrayList;

/**
 * The TrackManager class is used to retain all tracks that are
 * marked as threats due to entering the zones.
 * @author emusk
 *
 */
public class TrackManager implements MessageObserver {

  private ArrayList<Track> qualifiedTracks = new ArrayList<Track>();
  private Zone zone = new Zone("DangerClose", 0.0F, 5.0F, 0.0F, 10.0F, 0.0F, 0.0F);
  private MessageService messageService = new MessageService();

  /**
   * This is a constructor.
   */
  public TrackManager() {
    messageService.registerObserver(DDS_Track.class, this);
  }
  
  /**
   * Callback from message service.
   * @param message is the DDS message
   */
  public void onMessageReceipt(DDS_Message message) {
    if (message instanceof DDS_Track) {
      processTrack((DDS_Track) message);
    }
  }
  
  /**
   * Handles the input from the message service. This is the main entry point to the class.
   * @param dds_track is the input track message
   */
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
        if (isWithin2DZone(track)) {
          //get index of track, and use that to overwrite the previous report
          for (int i = 0; i < qualifiedTracks.size(); i++) {
            if (qualifiedTracks.get(i).getId() == track.getId() && qualifiedTracks.get(i).getSensor() == track.getSensor()) {
              //delete it
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
        if (isWithin2DZone(track)) {
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

private boolean isWithin2DZone(Track track) {
	return track.getX() > zone.getMinX() && track.getX() < zone.getMaxX() && track.getY() < zone.getMaxY() && track.getY() > zone.getMinY();
}
}
