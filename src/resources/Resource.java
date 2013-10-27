package resources;

import java.lang.reflect.Type;
import java.util.ArrayList;

import rest.RestApi;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.nicfix.gsoncompatibility.GsonConfigurator;

public class Resource implements JsonDeserializer<Resource>,
		JsonSerializer<Resource> {

	protected String resourceClassifier = "becontent\\resource\\entity\\Resource";

	protected long resourceIdentifier;

	public long getResourceIdentifier() {
		return resourceIdentifier;
	}

	public void setResourceIdentifier(long resourceIdentifier) {
		this.resourceIdentifier = resourceIdentifier;
	}

	public String getResourceClassifier() {
		return resourceClassifier;
	}

	public Resource() {
		ResourcesMapper.getInstance().registerResClass(resourceClassifier,
				this.getClass());
		ResourcesCache.getInstance().storeResource(this);
		GsonConfigurator.getInstance().registerDeserializer(this,
				this.getClass());
	}

	public void store() {
		ArrayList<Resource> resources = new ArrayList<Resource>();
		resources.add(this);
		RestApi.editResources(resources,null);
	}

	public Resource(String resourceClassifier) {
		this.resourceClassifier = resourceClassifier;
		ResourcesMapper.getInstance().registerResClass(resourceClassifier,
				this.getClass());
		ResourcesCache.getInstance().storeResource(this);
		GsonConfigurator.getInstance().registerDeserializer(this,
				this.getClass());
	}

	@Override
	public Resource deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		Resource turnback = new Resource();
		turnback.resourceClassifier = arg0.getAsJsonObject()
				.get("resourceClassifier").getAsString();
		if (arg0.getAsJsonObject().get("resourceIdentifier") != null)
			turnback.resourceClassifier = arg0.getAsJsonObject()
					.get("resourceIdentifier").getAsString();
		return turnback;
	}

	@Override
	public JsonElement serialize(Resource arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();
		obj.addProperty("resourceClassifier", this.resourceClassifier);
		obj.addProperty("id", this.resourceIdentifier);
		return obj;
	}

}
