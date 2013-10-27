package com.cipciop.spotastop;

import java.io.FileNotFoundException;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cipciop.spotastop.services.RegistrationService;

public class RegisterActivity extends Activity {

	private View mRegisterFormView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		findViewById(R.id.fotoProfilo).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(
								Intent.ACTION_PICK,
								android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
						startActivityForResult(intent, 0);
					}
				});
		findViewById(R.id.do_registration_button).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						StopSpotApp.getInstance().setInsertedUsername(
								((EditText) findViewById(R.id.email)).getText()
										.toString());
						StopSpotApp.getInstance().setInsertedPassword(
								((EditText) findViewById(R.id.password))
										.getText().toString());
						
						showProgress(true);
						Intent intent = new Intent(RegisterActivity.this,
								RegistrationService.class);
						startService(intent);
					}
				});
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mRegisterFormView = findViewById(R.id.register_form);
			mRegisterFormView.setVisibility(View.VISIBLE);
			mRegisterFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mRegisterFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
		if(show)
			((TextView)findViewById(R.id.registration_status_message)).setText(getResources().getString(R.string.registering_new_user));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			Uri targetUri = data.getData();
			// textTargetUri.setText(targetUri.toString());
			Bitmap bitmap;
			try {
				bitmap = BitmapFactory.decodeStream(getContentResolver()
						.openInputStream(targetUri));
				((ImageButton) findViewById(R.id.fotoProfilo))
						.setImageBitmap(bitmap);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	private BroadcastReceiver loginDoneReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (StopSpotApp.getInstance().getLoggedUser() != null) {
				Intent i = new Intent(RegisterActivity.this,
						SelectBusLine.class);
				startActivity(i);
			} else {
				Intent i = new Intent(RegisterActivity.this,
						ErrorActivity.class);
				startActivity(i);
			}
		}

	};

	/* Request updates at startup */
	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter focusChangedFilter = new IntentFilter();
		focusChangedFilter.addAction("com.cipciop.spotastop.loginDone");
		this.registerReceiver(this.loginDoneReceiver, focusChangedFilter);
	}

	/* Remove the locationlistener updates when Activity is paused */
	@SuppressLint("NewApi")
	@Override
	protected void onPause() {
		super.onPause();
		this.unregisterReceiver(this.loginDoneReceiver);
	}

}
