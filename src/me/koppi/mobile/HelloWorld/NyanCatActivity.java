package me.koppi.mobile.HelloWorld;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.google.ads.AdRequest.Gender;
import com.mopub.mobileads.MoPubView;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class NyanCatActivity extends Activity {
	private static final String MY_AD_UNIT_ID = "a14dff698d8e63f";
	boolean soundStarted = false;
	MediaPlayer mp;
	AnimationDrawable nyanAnim;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.w("XX", "onCreate");

    	requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       // AdView adView = new AdView(this, AdSize.BANNER, MY_AD_UNIT_ID);
        // Lookup your LinearLayout assuming it’s been given
        // the attribute android:id="@+id/mainLayout"
     //   LinearLayout layout = (LinearLayout)findViewById(R.id.LinearLayout1);
        // Add the adView to it
     //   layout.addView(adView);
        // Initiate a generic request to load it with an ad
        AdView ad1 = (AdView)findViewById(R.id.adView1);
        AdRequest ad1r = new AdRequest();
        ad1r.setGender(Gender.MALE);
        ad1.loadAd(ad1r);

        /*
        AdView ad2 = (AdView)findViewById(R.id.adView2);
        AdRequest ad2r = new AdRequest();
        ad2r.setGender(Gender.FEMALE);
        ad2.loadAd(ad2r);
        */
        //  adView.loadAd(new AdRequest());

        MoPubView mpv = (MoPubView) findViewById(R.id.adView3);
        mpv.setAdUnitId("agltb3B1Yi1pbmNyDQsSBFNpdGUYrfn-BAw");
        mpv.loadAd();
        
        if (savedInstanceState == null) {
        	Log.w("XX", " savedInstanceState == null");
    		if (!soundStarted) {
    			mp = MediaPlayer.create(this, R.raw.nyan);
    			try {
    				mp.prepare();
    			} catch (Exception e) {}
    			mp.start();
    			mp.setLooping(true);
    		   // mp.setOnCompletionListener(this);
    			soundStarted = true;
           	}
        } else {
        	soundStarted = false;
        	Log.w("XX", " savedInstanceState != null");
        }
        
        
        ImageView rocketImage = (ImageView) findViewById(R.id.imageView2);
        rocketImage.setBackgroundResource(R.drawable.nyan);
        nyanAnim = (AnimationDrawable) rocketImage.getBackground();
        
        Toast.makeText(this, R.string.hello_world, Toast.LENGTH_LONG).show();
    }

    @Override
	protected void onPause() {
    	Log.w("XX", "onPause");
    	if (soundStarted && mp != null) {
    	mp.pause();
    	}
		super.onPause();
	}
    
	@Override
	protected void onResume() {
		Log.w("XX", "onResume");
		
		if (mp != null)
			mp.start();
		
   		if (!soundStarted) {
			mp = MediaPlayer.create(this, R.raw.nyan);
			try {
				mp.prepare();
			} catch (Exception e) {}
			mp.start();
			mp.setLooping(true);
		   // mp.setOnCompletionListener(this);
			soundStarted = true;
       	}
   		
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		Log.w("XX", "onDestroy");
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
      // Save UI state changes to the savedInstanceState.
      // This bundle will be passed to onCreate if the process is
      // killed and restarted.
	  soundStarted = savedInstanceState.getBoolean("soundStarted");
      Log.w("XX", "onRestoreInstanceState");
      super.onRestoreInstanceState(savedInstanceState);
    }  

	@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
      // Save UI state changes to the savedInstanceState.
      // This bundle will be passed to onCreate if the process is
      // killed and restarted.
      savedInstanceState.putBoolean("soundStarted", soundStarted);
      Log.w("XX", "onSaveInstanceState");
      super.onSaveInstanceState(savedInstanceState);
    }
    
    @Override
	public void onWindowFocusChanged(boolean hasFocus) {
    	Log.w("XX", "onWindowFocusChange");
		super.onWindowFocusChanged(hasFocus);
		if (!soundStarted) {
		       mp.start();
		}
		nyanAnim.start();
    }
}