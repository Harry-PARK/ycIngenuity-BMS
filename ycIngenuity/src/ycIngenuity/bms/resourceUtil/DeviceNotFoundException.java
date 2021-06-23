package ycIngenuity.bms.resourceUtil;

public class DeviceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5615109833515728839L;

	public DeviceNotFoundException() {
		super("Can not find the specific device. Please check your device_id again.");
	}
}
