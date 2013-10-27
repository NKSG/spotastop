package requests;

import resources.Resource;

public class Unlink extends Link {

	public Unlink(Resource master, Resource slave, String linkProperty) {
		super(master, slave, linkProperty);
		this.operation="unlink"; 
	}
}
