package requests;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.nicfix.gsoncompatibility.GsonConfigurator;

import resources.Resource;
import responses.AsyncCallback;
import responses.beContentResponse;
import rest.RestApi;

public class LinkerRequest extends beContentRequest {

	public LinkerRequest(Criteria c, ArrayList<Resource> data,
			ArrayList<Link> rels) {
		super(c, data, rels);
	}

	@Override
	public beContentResponse doSync() {
		String responseString = "";
		String urlString = buildRequest();

		HttpURLConnection urlConnection = null;
		URL url = null;

		try {
			url = new URL(urlString.toString());
			Gson parser = GsonConfigurator.getInstance().build();

			/**
			 * Sending data to load resources
			 */
			String urlParameters = "data=" + parser.toJson(this.sendingData);

			System.out.println(urlParameters);

			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("charset", "utf-8");
			urlConnection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			urlConnection.setUseCaches(false);

			urlConnection.connect();

			DataOutputStream wr = new DataOutputStream(
					urlConnection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

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
			Gson parser = GsonConfigurator.getInstance().build();

			/**
			 * Sending data to load resources
			 */
			String urlParameters = "data=" + parser.toJson(this.sendingData);

			System.out.println(urlParameters);

			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("charset", "utf-8");
			urlConnection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			urlConnection.setUseCaches(false);

			urlConnection.connect();

			DataOutputStream wr = new DataOutputStream(
					urlConnection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

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

		String url = RestApi.getServerUrl() + "/linker.php?";

		return url;

	}

}
