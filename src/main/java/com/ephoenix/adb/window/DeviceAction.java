
package com.ephoenix.adb.window;

import com.ephoenix.adb.Device;

public interface DeviceAction {
  void connectDevice(Device device);

  void disconnectDevice(Device device);
}
