package com.cipciop.spotastop.services;

import java.util.ArrayList;

import requests.Criteria;
import resources.Resource;
import rest.RestApi;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.cipciop.spotastop.StopSpotApp;

public class RetrieveLinesListService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	public int onStartCommand(Intent intent, int flags, int startid) {
		new Thread(new Runnable() {
			public void run() {
				Criteria c=new Criteria();
				c.addConstraint("resClass", "cipciop\\spotastop\\Line");
				ArrayList<Resource> lines=RestApi.queryResources(c);
				System.out.println(lines.size());
				StopSpotApp.getInstance().updateLinesList(lines);
			}
		}).start();
		return 0;
	}

}
