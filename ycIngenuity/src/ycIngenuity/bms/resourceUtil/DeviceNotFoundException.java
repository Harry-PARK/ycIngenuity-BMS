package ycIngenuity.bms.resourceUtil;

public class DeviceNotFoundException extends RuntimeException {
	public DeviceNotFoundException() {
		super("Can not find the specific device. Please check your device_id again.");
	}
}
