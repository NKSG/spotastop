package requests;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import resources.Resource;
import resources.ResourcesCache;
import responses.AsyncCallback;
import responses.beContentResponse;
import rest.RestApi;

import com.google.gson.Gson;
import com.nicfix.gsoncompatibility.GsonConfigurator;

public class LoaderRequest extends beContentRequest {

	public LoaderRequest(Criteria c, ArrayList<Resource> data,
			ArrayList<Link> rels) {
		super(c, data, rels);
	}

	@Override
	public beContentResponse doSync() {
		String responseString = "";
		String urlString = buildRequest();
		System.out.println("REQUEST " + urlString);
		HttpURLConnection urlConnection = null;
		URL url = null;

		try {
			url = new URL(urlString.toString());

			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);

			urlConnection.connect();

			InputStream inStream = urlConnection.getInputStream();

			// ///PERFORMANCE CRITICAL
			BufferedInputStream bis = new BufferedInputStream(inStream);
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			int result = bis.read();
			while (result != -1) {
				byte b = (byte) result;
				buf.write(b);
				result = bis.read();
			}
			responseString = buf.toString();
			// ///PERFORMANCE CRITICAL
			
			inStream.close();
			urlConnection.disconnect();
			System.out.println("RESPONSE   " + responseString);
			Gson parser =GsonConfigurator.getInstance().build();
			this.responseHandler = parser.fromJson(responseString,
					beContentResponse.class);

		} catch (MalformedURLException e) {
		} catch (ProtocolException e) {
		} catch (IOException e) {
		}
		return this.responseHandler;
	}

	@Override
	public void doAsync(AsyncCallback callback) {
		String responseString = "";
		String urlString = buildRequest();

		HttpURLConnection urlConnection = null;
		URL url = null;

		try {
			url = new URL(urlString.toString());

			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);

			urlConnection.connect();

			InputStream inStream = urlConnection.getInputStream();

			// ///PERFORMANCE CRITICAL
			BufferedInputStream bis = new BufferedInputStream(inStream);
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			int result = bis.read();
			while (result != -1) {
				byte b = (byte) result;
				buf.write(b);
				result = bis.read();
			}
			responseString = buf.toString();
			// ///PERFORMANCE CRITICAL

			inStream.close();
			urlConnection.disconnect();

			System.out.println("RESPONSE   " + responseString);
			Gson parser =GsonConfigurator.getInstance().build();
			this.responseHandler = parser.fromJson(responseString,
					beContentResponse.class);

		} catch (MalformedURLException e) {
		} catch (ProtocolException e) {
		} catch (IOException e) {
		}

		callback.callback(responseHandler);

	}

	@Override
	public String buildRequest() {

		String url = RestApi.getServerUrl() + "/loader.php?";

		Gson parser =GsonConfigurator.getInstance().build();

		/**
		 * Sending data to load resources
		 */
		url += "data=" + parser.toJson(this.criteria.getConstraints());

		/**
		 * Sending the lastUpdateTime for the requested resClass
		 */
		if (ResourcesCache.getInstance().getLastUpdateTimes()
				.get(this.criteria.getConstraints().get("resClass")) == null) {
			ResourcesCache
					.getInstance()
					.getLastUpdateTimes()
					.put(this.criteria.getConstraints().get("resClass"),
							new Date(0));
		}

		url += "&lastUpdateTime="
				+ ResourcesCache.getInstance().getLastUpdateTimes()
						.get(this.criteria.getConstraints().get("resClass"))
						.getTime();

		return url;

	}

}
