package com.cipciop.spotastop.services;

import java.util.ArrayList;

import requests.Criteria;
import resources.Resource;
import rest.RestApi;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.cipciop.spotastop.StopSpotApp;
import com.cipciop.spotastop.domain.User;

public class LoginService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	public int onStartCommand(Intent intent, int flags, int startid) {
		new Thread(new Runnable() {
			public void run() {
				User newUser = new User();
				Criteria c = new Criteria();
				c.addConstraint("resClass", "cipciop\\spotastop\\User");
				c.addConstraint("username", StopSpotApp.getInstance()
						.getInsertedUsername());

				ArrayList<Resource> users = RestApi.queryResources(c);
				if (users.size() > 0) {
					if (((User) users.get(0)).login(StopSpotApp.getInstance()
							.getInsertedUsername(), StopSpotApp.getInstance()
							.getInsertedPassword())) {
						StopSpotApp.getInstance().setLoggedUser(
								(User) users.get(0));
					}
				} else {
					
					newUser.setUsername(StopSpotApp.getInstance()
							.getInsertedUsername());
					newUser.setPassword(StopSpotApp.getInstance()
							.getInsertedPassword());
					newUser.store();
					System.out.println("Stored");
					
					c.addConstraint("resClass", "cipciop\\spotastop\\User");
					c.addConstraint("username", StopSpotApp.getInstance()
							.getInsertedUsername());

					users = RestApi.queryResources(c);
					if (users.size() > 0) {
						if (((User) users.get(0))
								.login(StopSpotApp.getInstance()
										.getInsertedUsername(), StopSpotApp
										.getInstance().getInsertedPassword())) {
							StopSpotApp.getInstance().setLoggedUser(
									(User) users.get(0));
						}
					}
				}

			}
		}).start();
		
		return 0;
	}
}
