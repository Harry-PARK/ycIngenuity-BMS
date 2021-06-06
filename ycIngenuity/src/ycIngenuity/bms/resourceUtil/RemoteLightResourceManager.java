package ycIngenuity.bms.resourceUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import com.microsoft.azure.sdk.iot.service.devicetwin.DeviceMethod;
import com.microsoft.azure.sdk.iot.service.devicetwin.MethodResult;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;

public class RemoteLightResourceManager extends ResourceManager<RemoteLight>{
	
	public final Long responseTimeout = TimeUnit.SECONDS.toSeconds(30);
	public final Long connectTimeout = TimeUnit.SECONDS.toSeconds(5);
	
	//Method Name; Instruction 
	public final String lightOn = "lightOn";
	public final String lightOff = "lightOff";

	private String shareAccessKeyToAzure = null;
	
	public RemoteLightResourceManager(ResourceSLU<RemoteLight> rslu, String saka){
		//super(rslu) initiate ResourseFileSystem and load resource
		super(rslu);
		shareAccessKeyToAzure = saka;
	}
	
	public RemoteLight findByDeviceID(String device_id) throws DeviceNotFoundException{
		for (RemoteLight light : resourceList) {
			if(device_id.equals(light.getDevice_id())){
				return light;
			}			
		}
		throw new DeviceNotFoundException();
		
	}
	
	public MethodResult lightOn(RemoteLight light) {
		return lightOnMethod(light);
	}
	public MethodResult lightOn(String device_id) throws DeviceNotFoundException{
		RemoteLight light = findByDeviceID(device_id);
		return lightOnMethod(light);
	}
	
	public MethodResult lightOff(RemoteLight light) {
		return lightOffMethod(light);
	}
	public MethodResult lightOff(String device_id) throws DeviceNotFoundException{
		RemoteLight light = findByDeviceID(device_id);
		return lightOffMethod(light);
	}
	
	public void update(RemoteLight light, int lightOnOff) {
		updateMethod(light, lightOnOff);
	}
	public void update(String device_id, int lightOnOff) throws DeviceNotFoundException{
		RemoteLight light = findByDeviceID(device_id);
		updateMethod(light, lightOnOff);
	}
	
	
	private MethodResult lightOnMethod(RemoteLight light) {
		MethodResult result = sendDirectMethod(light, lightOn, null);
		if(result != null && result.getStatus() == 200) {
			light.setLightByInteger(1);
			light.renewLast_updated();
		}
		return result;
	}
	
	private MethodResult lightOffMethod(RemoteLight light) {
		MethodResult result = sendDirectMethod(light, lightOff, null);
		if(result != null && result.getStatus() == 200) {
			light.setLightByInteger(0);
			light.renewLast_updated();
		}
		return result;
	}
	
	private void updateMethod(RemoteLight light, int lightOnOff) {
		light.setLightByInteger(lightOnOff);
		light.setOnline(true);
		light.renewLast_updated();
	}
	
	public MethodResult sendDirectMethod(RemoteLight light, String methodName, Object payload) {
		MethodResult result = null;
		/*MethodResult status
		 * 
		 * 200 : Success
		 * 400 : Invalid parameter
		 * 404 : Direct method not defined
		 * 
		 */
		
		//TODO rebuild print log
		try {
			// Create a DeviceMethod instance to call a direct method.
			DeviceMethod methodClient = DeviceMethod.createFromConnectionString(shareAccessKeyToAzure);
			// Call the direct method.
			result = methodClient.invoke
				(light.getDevice_id(), 
				methodName, 
				responseTimeout, 
				connectTimeout, 
				payload);
			if (result == null) {
		        throw new IOException("Direct method invoke returns null");
			}
			// Show the acknowledgement from the device.
			System.out.println("Status: " + result.getStatus());
		    System.out.println("Response: " + result.getPayload());
		}catch (IotHubException e) {
	      System.out.println("IotHubException calling direct method:");
	      System.out.println(e.getMessage());
	    } catch (IOException e) {
		  System.out.println("IOException calling direct method:");
		  System.out.println(e.getMessage());
	    }
	
		return result;
	}
	
	
}
