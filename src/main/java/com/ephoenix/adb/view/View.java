

package com.ephoenix.adb.view;

import com.ephoenix.adb.Device;

public interface View {

  void showNoConnectedDevicesNotification();

  void showConnectedDeviceNotification(Device device);

  void showDisconnectedDeviceNotification(Device device);

  void showErrorConnectingDeviceNotification(Device device);

  void showErrorDisconnectingDeviceNotification(Device device);

  void showADBNotInstalledNotification();
}
