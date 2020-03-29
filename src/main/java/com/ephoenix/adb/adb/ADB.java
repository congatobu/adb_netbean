
package com.ephoenix.adb.adb;

import java.util.Collection;
import java.util.List;

import com.ephoenix.adb.Application;
import com.ephoenix.adb.Device;

public class ADB {

	private static final String TCPIP_PORT = "5555";
	private final CommandLine commandLine;
	private final ADBParser adbParser;

	public ADB(CommandLine commandLine, ADBParser adbParser) {
		this.commandLine = commandLine;
		this.adbParser = adbParser;
	}

	public boolean isInstalled() {
		return true;
	}

	public Collection<Device> getDevicesConnected() {
		String getDevicesCommand = getCommand("devices -l");
		
		Application.log(getDevicesCommand);
		
		String adbDevicesOutput = commandLine.executeCommand(getDevicesCommand);
		
		Application.log(adbDevicesOutput);

		return adbParser.parseGetDevicesOutput(adbDevicesOutput);
	}

	public Collection<Device> connectDevices(Collection<Device> devices) {
		for (Device device : devices) {
			boolean connected = connectDeviceByIp(device);
			device.setConnected(connected);
		}
		return devices;
	}

	public List<Device> disconnectDevices(List<Device> devices) {
		for (Device device : devices) {
			boolean disconnected = disconnectDevice(device.getIp());
			device.setConnected(disconnected);
		}
		return devices;
	}

	private boolean connectDeviceByIp(Device device) {
		String deviceIp = getDeviceIp(device);
		if (deviceIp.isEmpty()) {
			return false;
		} else {
			return connectDevice(deviceIp);
		}
	}

	private boolean disconnectDevice(String deviceIp) {
		enableTCPCommand();
		String connectDeviceCommand = getCommand("disconnect " + deviceIp);
		return commandLine.executeCommand(connectDeviceCommand).isEmpty();
	}

	public String getDeviceIp(Device device) {
		String getDeviceIpCommand = getCommand("-s " + device.getId() + " shell ip -f inet addr show wlan0");
		String ipInfoOutput = commandLine.executeCommand(getDeviceIpCommand);
		return adbParser.parseGetDeviceIp(ipInfoOutput);
	}

	private void enableTCPCommand() {
		if (!checkTCPCommandExecuted()) {
			String enableTCPCommand = getCommand("tcpip " + TCPIP_PORT);
			commandLine.executeCommand(enableTCPCommand);
		}
	}

	private boolean checkTCPCommandExecuted() {
		String getPropCommand = getCommand("adb shell getprop | grep adb");
		String getPropOutput = commandLine.executeCommand(getPropCommand);
		String adbTcpPort = adbParser.parseAdbServiceTcpPort(getPropOutput);
		return TCPIP_PORT.equals(adbTcpPort);
	}

	private boolean connectDevice(String deviceIp) {
		String enableTCPCommand = getCommand("tcpip 5555");
		commandLine.executeCommand(enableTCPCommand);
		String connectDeviceCommand = getCommand("connect " + deviceIp);
		String connectOutput = commandLine.executeCommand(connectDeviceCommand);
		return connectOutput.contains("connected");
	}

	private String getAdbPath() {
		String adbPath = "D:/workspace_adb/platform-tools/adb.exe";
//    File adbFile = AndroidSdkUtils.getAdb(project);
//    if (adbFile != null) {
//      adbPath = adbFile.getAbsolutePath();
//    }
		return adbPath;
	}

	private String getCommand(String command) {
		return getAdbPath() + " " + command;
	}
}
