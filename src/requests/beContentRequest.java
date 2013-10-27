package requests;

import java.util.ArrayList;

import resources.Resource;
import responses.AsyncCallback;
import responses.beContentResponse;


public abstract class beContentRequest {

	protected Criteria criteria;

	protected Data sendingData;
	
	protected beContentResponse responseHandler = new beContentResponse();
	
	public beContentRequest(Criteria c, ArrayList<Resource> data,ArrayList<Link> rels){
		this.criteria=c;
		this.sendingData=new Data(data,rels);
	}
	
	public abstract String buildRequest();
	
	public abstract beContentResponse doSync();
	
	public abstract void doAsync(AsyncCallback callback);
}
