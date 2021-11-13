package com.org.trackmanager;

import com.org.dds.DDS_Message;

/**
 * The service used to send the DDS message
 * 
 * @author emusk
 *
 */
public class MessageService {

  public void send(DDS_Message message) {
    //send message here
  }
  
  public void registerObserver(Class<?> message, MessageObserver observer) {
    //register observer here for the input message
  }
  
  public void deregisterObserver(Class<?> message, MessageObserver observer) {
    //deregister observer here for the input message
  }
}
