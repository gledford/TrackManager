package com.org.trackmanager;

import com.org.dds.DDS_Message;

public interface MessageObserver {
  public void onMessageReceipt(DDS_Message message);
}
