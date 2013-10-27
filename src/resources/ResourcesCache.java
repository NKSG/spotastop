package resources;

import java.util.Date;
import java.util.HashMap;

public class ResourcesCache {

	private static ResourcesCache instance;

	public static ResourcesCache getInstance() {
		if (instance == null) {
			instance = new ResourcesCache();
		}
		return instance;
	}

	private ResourcesCache() {

	}

	private HashMap<String, Date> lastUpdateTimes = new HashMap<String, Date>();

	private HashMap<String, HashMap<Long, Resource>> cachedResources = new HashMap<String, HashMap<Long, Resource>>();

	public HashMap<String, Date> getLastUpdateTimes() {
		return lastUpdateTimes;
	}

	public void setLastUpdateTimes(HashMap<String, Date> lastUpdateTimes) {
		this.lastUpdateTimes = lastUpdateTimes;
	}

	public HashMap<String, HashMap<Long, Resource>> getCachedResources() {
		return cachedResources;
	}

	public void setCachedResources(
			HashMap<String, HashMap<Long, Resource>> cachedResources) {
		this.cachedResources = cachedResources;
	}

	public void storeResource(Resource res) {
		HashMap<Long, Resource> resources;
		if (!cachedResources.containsKey(res.resourceClassifier)) {
			resources = new HashMap<Long, Resource>();
			cachedResources.put(res.getResourceClassifier(), resources);
		} else {
			resources = cachedResources.get(res.getResourceClassifier());
		}
		resources.put(res.getResourceIdentifier(), res);
	}
}
