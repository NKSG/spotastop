package requests;

import java.util.HashMap;

public class Criteria {


	private HashMap<String, String> constraints = new HashMap<String, String>();

	public HashMap<String, String> getConstraints() {
		return constraints;
	}

	public void addConstraint(String label, String condition) {
		this.constraints.put(label, condition);
	}

}
