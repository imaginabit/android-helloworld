package me.koppi.mobile.HelloWorld;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.koppi.mobile.HelloWorld.utils.AnalyticsUtils;

import com.mopub.mobileads.MoPubView;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
   // 	requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//	            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.dashboard);
        
        MoPubView mpv = (MoPubView) findViewById(R.id.adViewDrash);
        mpv.setAdUnitId("agltb3B1Yi1pbmNyDQsSBFNpdGUYrfn-BAw");
        mpv.loadAd();
        
        AnalyticsUtils.getInstance(this).trackEvent(
                "Dashboard", "Show", "onCreate", 0);
        
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
    }

	public void onClick(View v) {
		if (v.getId() == R.id.button1) {
			fireTrackerEvent("Button: Say Hello");
			startActivity(new Intent(MainActivity.this, NyanCatActivity.class));
		} else if (v.getId() == R.id.button2) {
			fireTrackerEvent("Button: Get Source Code");
			final Intent intentEmail = new Intent(android.content.Intent.ACTION_SEND);

			intentEmail.setType("plain/text");
			intentEmail.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"jakob.flierl@gmail.com"});
			intentEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, "Request for HelloWorld.zip app source code");
			intentEmail.putExtra(android.content.Intent.EXTRA_TEXT, "Hi Koppi,\n\nplease send me the source code of the HelloWorld android app!\n\nKind regards and thanks in advance,\n");
			startActivity(intentEmail);
		} else if (v.getId() == R.id.button3) {
			fireTrackerEvent("Button: Vote");
	        Intent intentVote = new Intent(Intent.ACTION_VIEW,
	        		Uri.parse("market://details?id=me.koppi.mobile.HelloWorld"));			
	        startActivity(intentVote);
		} else if (v.getId() == R.id.button4) {
			fireTrackerEvent("Button: Uninstall");
			Uri uninstallUri = Uri.parse("package:me.koppi.mobile.HelloWorld");
			Intent intentUninstall = new Intent(Intent.ACTION_DELETE, uninstallUri);
			startActivity(intentUninstall);
		} else if (v.getId() == R.id.button5) {
			fireTrackerEvent("Button: Programs");
			Toast.makeText(this, "To come in the next release of this app.\nStay tuned!", Toast.LENGTH_SHORT).show();
		} else if (v.getId() == R.id.button6) {
			fireTrackerEvent("Button: Exit");
			finish();
		}
	}
	
    public void fireTrackerEvent(String label) {
        AnalyticsUtils.getInstance(this).trackEvent(
                "Dashboard", "Click", label, 0);
    }
}
