package requests;

import java.util.ArrayList;

import resources.Resource;

public class Data {

	private Resource[] resources;

	private Link[] relations;

	public Data(ArrayList<Resource> res, ArrayList<Link> rels) {
		if (res != null) {
			resources = new Resource[res.size()];
			for (int i = 0; i < res.size(); i++) {
				resources[i] = res.get(i);
			}
		}
		if (rels != null) {
			relations = new Link[rels.size()];
			for (int i = 0; i < rels.size(); i++) {
				relations[i] = rels.get(i);
			}
		}
	}

}
