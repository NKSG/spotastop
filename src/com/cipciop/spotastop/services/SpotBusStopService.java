package com.cipciop.spotastop.services;

import requests.Criteria;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.cipciop.spotastop.StopSpotApp;

public class SpotBusStopService extends Service {

	public int onStartCommand(Intent intent, int flags, int startid) {
		new Thread(new Runnable() {
			public void run() {
				Criteria c = new Criteria();
				c.addConstraint("resClass", "cipciop\\spotastop\\Line");
				for (int i = 0; i < StopSpotApp.getInstance()
						.getPendingBusStops().size(); i++)
					StopSpotApp.getInstance().getPendingBusStops().get(i)
							.store();
				StopSpotApp.getInstance().newStopsPushCompleted();
			}
		}).start();
		return 0;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
