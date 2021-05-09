package ycIngenuity.bms.resourceUtil;

import java.util.List;

public abstract class ResourceManager<T> {
	//ResourceSLU is an Interface handling resource; save, load, and update
	private ResourceSLU<T> resourceFileSystem;
	protected List<T> resourceList;
	
	
	public ResourceManager(ResourceSLU<T> rslu) {
		resourceFileSystem = rslu;
		load_resource();
	}
	
	
	//TODO print task
	//ResourceSLU is an Interface handling resource; save, load, and update
	public void load_resource() {
		resourceList = resourceFileSystem.load_resource();
	}
	public Boolean save_resource() {
		resourceFileSystem.save_resource(resourceList);
		return null;
	}
	public Boolean update_resource() {
		resourceFileSystem.update_resource(resourceList);
		return null;
	}
	public List<T> getResource() {
		return resourceList;
	}
	
}
