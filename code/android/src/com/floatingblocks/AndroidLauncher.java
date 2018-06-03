package com.floatingblocks;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.floatingblocks.FloatingBlocks;

public class AndroidLauncher extends AndroidApplication {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		config.useGyroscope = true;  //default is false
		config.useAccelerometer = false;
		config.useCompass = false;

		initialize(new FloatingBlocks(), config);
	}
}
