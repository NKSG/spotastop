package requests;

import java.util.ArrayList;

import resources.Resource;
import responses.AsyncCallback;
import responses.beContentResponse;

public class CreatorRequest extends beContentRequest {

	public CreatorRequest(Criteria c, ArrayList<Resource> data,
			ArrayList<Link> rels) {
		super(c, data, rels);
	}

	@Override
	public beContentResponse doSync() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doAsync(AsyncCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String buildRequest() {
		return null;
		// TODO Auto-generated method stub
		
	}

}
