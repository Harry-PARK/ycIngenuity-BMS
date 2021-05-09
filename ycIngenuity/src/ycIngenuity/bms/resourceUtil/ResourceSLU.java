package ycIngenuity.bms.resourceUtil;

import java.util.List;

public interface ResourceSLU<T> {

	//load, update, save
		public abstract List<T> load_resource();
		public abstract Boolean save_resource(List<T> resources);
		public abstract Boolean update_resource(List<T> resources);
		
}
