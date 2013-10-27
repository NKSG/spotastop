package requests;

import resources.Resource;

public class Link {

	protected String masterResourceClassifier;
	protected long masterResourceIdentifier;
	protected String slaveResourceClassifier;
	protected long slaveResourceIdentifier;
	protected String operation="link";
	protected String linkProperty;

	public Link(Resource master, Resource slave, String linkProperty) {
		this.masterResourceClassifier=master.getResourceClassifier();
		this.masterResourceIdentifier=master.getResourceIdentifier();
		this.slaveResourceClassifier=slave.getResourceClassifier();
		this.slaveResourceIdentifier=slave.getResourceIdentifier();
		this.linkProperty=linkProperty;
	}
}
