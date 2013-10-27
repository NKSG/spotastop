package com.cipciop.spotastop.services;

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

import requests.Criteria;
import resources.Resource;
import responses.beContentResponse;
import rest.RestApi;
import settings.Settings;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.cipciop.spotastop.StopSpotApp;
import com.cipciop.spotastop.domain.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nicfix.gsoncompatibility.GsonConfigurator;

public class JarvisDynDnsService extends Service {

	private String realUrl = "";

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	public int onStartCommand(Intent intent, int flags, int startid) {
		new Thread(new Runnable() {
			public void run() {
				String responseString = "";
				String urlString = Settings.getJarvisProxyServerUrl();

				HttpURLConnection urlConnection = null;
				URL url = null;

				try {
					url = new URL(urlString.toString());
					Gson parser = GsonConfigurator.getInstance().build();

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

					JsonObject response = parser.fromJson(responseString,
							JsonObject.class);
					JarvisDynDnsService.this.realUrl = response.get("address")
							.getAsString();
					if (Settings.isJarvisMode())
						Settings.setServerUrl(JarvisDynDnsService.this.realUrl
								+ ":" + Settings.getJarvisProxyServerPort());
				} catch (MalformedURLException e) {
				} catch (ProtocolException e) {
				} catch (IOException e) {
				}

			}
		}).start();
		return 0;
	}
}
