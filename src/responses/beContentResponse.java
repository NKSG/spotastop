package responses;

import java.lang.reflect.Type;
import java.util.ArrayList;

import resources.Resource;
import resources.ResourcesMapper;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.nicfix.gsoncompatibility.GsonConfigurator;

public class beContentResponse implements JsonSerializer<beContentResponse>,
		JsonDeserializer<beContentResponse> {

	protected transient String responseString = "";

	protected Resource[] resources;

	protected boolean result = false;

	protected String reason;

	protected long serverUpdateTime;

	protected transient long lastUpdateTime;

	public ArrayList<Resource> getResources() {
		ArrayList<Resource> resList = new ArrayList<Resource>();
		if (resources != null) {
			for (int i = 0; i < resources.length; i++) {
				resList.add(resources[i]);
			}
		}
		return resList;
	};

	public boolean getResult() {
		return result;
	};

	public String getReason() {
		return responseString;
	}

	@Override
	public JsonElement serialize(beContentResponse arg0, Type arg1,
			JsonSerializationContext arg2) {

		return arg2.serialize(arg0);
	}

	@Override
	public beContentResponse deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {

		beContentResponse turnback = new beContentResponse();
		if (arg0.getAsJsonObject().get("resources") != null) {

			JsonArray resources = arg0.getAsJsonObject().get("resources")
					.getAsJsonArray();
			JsonArray subject = null;
			if (arg0.getAsJsonObject().get("subject") != null)
				subject = arg0.getAsJsonObject().get("subject")
						.getAsJsonArray();
			Gson parser = GsonConfigurator.getInstance().build();
			turnback.resources = new Resource[resources.size()];
			for (int i = 0; i < resources.size(); i++) {
				JsonObject resourceAsJsonObj = resources.get(i)
						.getAsJsonObject();

				/**
				 * Instancing an object in order to be sure of
				 */
				try {
					ResourcesMapper
							.getInstance()
							.getResClass(
									resourceAsJsonObj.get("resourceClassifier")
											.getAsString()).newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Parsing a "
						+ resourceAsJsonObj.get("resourceClassifier"));
				System.out.println("Mapper says "
						+ ResourcesMapper.getInstance().getResClass(
								resourceAsJsonObj.get("resourceClassifier")
										.getAsString()));
				turnback.resources[i] = parser.fromJson(
						resourceAsJsonObj,
						ResourcesMapper.getInstance().getResClass(
								resourceAsJsonObj.get("resourceClassifier")
										.getAsString()));
				if (subject != null)
					turnback.resources[i].setResourceIdentifier(resources
							.get(i).getAsJsonObject().get("id")
							.getAsLong());
			}
		}
		if (arg0.getAsJsonObject().get("result") != null)
			this.result = arg0.getAsJsonObject().get("result").getAsBoolean();
		if (arg0.getAsJsonObject().get("reason") != null)
			this.reason = arg0.getAsJsonObject().get("reason").getAsString();

		return turnback;
	}
}
