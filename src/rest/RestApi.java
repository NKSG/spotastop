package rest;

import java.util.ArrayList;

import requests.Criteria;
import requests.EditorRequest;
import requests.Link;
import requests.LinkerRequest;
import requests.LoaderRequest;
import requests.StorerRequest;
import resources.Resource;
import responses.AsyncCallback;
import responses.beContentResponse;
import settings.Settings;

public class RestApi {

	/**
	 * Returns all the instances of the resource classified with
	 * resourceClassifier and identified as resourceIdentifier
	 * 
	 * @param resourceClassifier
	 * @param resourceIdentifier
	 * @return
	 */
	public static ArrayList<Resource> requestResource(
			String resourceClassifier, long resourceIdentifier) {

		Criteria requestResourceCriteria = new Criteria();

		requestResourceCriteria.addConstraint("resClass", resourceClassifier);

		requestResourceCriteria.addConstraint("resId",
				String.valueOf(resourceIdentifier));

		LoaderRequest request = new LoaderRequest(requestResourceCriteria, null,null);

		beContentResponse response = request.doSync();

		return response.getResources();
	}

	/**
	 * 
	 * @param resources
	 * @param callback
	 */
	public static void storeResourcesAsync(ArrayList<Resource> resources,
			AsyncCallback callback) {
		if (resources.size() > 0) {
			StorerRequest request = new StorerRequest(null, resources,null);
			request.doAsync(callback);
		}
	}

	/**
	 * 
	 * @param resources
	 */
	public static void storeResources(ArrayList<Resource> resources) {
		if (resources.size() > 0) {
			StorerRequest request = new StorerRequest(null, resources,null);
			request.doSync();
		}
	}
	
	/**
	 * 
	 * @param resources
	 * @param callback
	 */
	public static void editResourcesAsync(ArrayList<Resource> resources,ArrayList<Link> links,
			AsyncCallback callback) {
		if (resources.size() > 0) {
			EditorRequest request = new EditorRequest(null, resources,links);
			request.doAsync(callback);
		}
	}

	/**
	 * 
	 * @param resources
	 */
	public static void editResources(ArrayList<Resource> resources,ArrayList<Link> links) {
		if (resources.size() > 0) {
			EditorRequest request = new EditorRequest(null, resources,links);
			request.doSync();
		}
	}

	/**
	 * 
	 * @param masterResource
	 * @param slaveResource
	 * @param linkProperty
	 * @param callback
	 */
	public static void linkResourcesAsync(Resource masterResource,
			Resource slaveResource, String linkProperty, AsyncCallback callback) {
		
		Link link=new Link(masterResource, slaveResource, linkProperty);
		ArrayList<Link> links=new ArrayList<Link>();
		links.add(link);
		LinkerRequest req=new LinkerRequest(null,null, links);
		req.doAsync(callback);
	}

	/**
	 * 
	 * @param masterResource
	 * @param slaveResource
	 * @param linkProperty
	 */
	public static void linkResources(Resource masterResource,
			Resource slaveResource, String linkProperty) {
		Link link=new Link(masterResource, slaveResource, linkProperty);
		ArrayList<Link> links=new ArrayList<Link>();
		links.add(link);
		LinkerRequest req=new LinkerRequest(null,null, links);
		req.doSync();
	}

	/**
	 * 
	 * @param masterResource
	 * @param slaveResource
	 * @param linkProperty
	 * @param callback
	 */
	public static void unlinkResourcesAsync(Resource masterResource,
			Resource slaveResource, String linkProperty, AsyncCallback callback) {

	}

	/**
	 * 
	 * @param masterResource
	 * @param slaveResource
	 * @param linkProperty
	 */
	public static void unlinkResources(Resource masterResource,
			Resource slaveResource, String linkProperty) {
	}

	/**
	 * 
	 * @param criteria
	 * @param callback
	 * @return
	 */
	public static ArrayList<Resource> queryResourcesAsync(Criteria criteria,
			AsyncCallback callback) {
		ArrayList<Resource> results = null;
		return results;
	}

	/**
	 * 
	 * @param criteria
	 * @return
	 */
	public static ArrayList<Resource> queryResources(Criteria criteria) {
		ArrayList<Resource> results = null;

		LoaderRequest request = new LoaderRequest(criteria,null,null);

		beContentResponse response = request.doSync();

		return response.getResources();
	}

	/**
	 * Returns the actually used serverUrl
	 * 
	 * @return
	 */
	public static String getServerUrl() {
		return Settings.getServerUrl();
	}

	/**
	 * Allows to configure the actual serverUrl of all requests
	 * 
	 * @param serverUrl
	 */
	public static void setServerUrl(String serverUrl) {
		Settings.setServerUrl(serverUrl);
	}
}
