package com.mygdx.game;

import android.os.Bundle;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.firebase.client.Firebase;

public class AndroidLauncher extends AndroidApplication {
	private Firebase mref;
	private Button mSendData;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Mnemonica(), config);

		Firebase.setAndroidContext(this);

		mref = new Firebase("https://mnemonica-15b7e.firebaseio.com/");
		//mSendData = (Button) findViewById(R.id.sendData);

		mSendData.addListener(new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event,
									 float x,
									 float y,
									 int pointer,
									 int button)
			{
				if(mSendData.isPressed())
				{
					Firebase mRefChild  = mref.child("Name");
					mRefChild.setValue("Hande");
				}

				return false;
			}});




	}
}

