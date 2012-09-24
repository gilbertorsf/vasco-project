package com.netvasconoticias;

import android.R.color;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class DisplayMessageActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Put your code here

	    // Get the message from the intent
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	   //String summary = "<html><body>" +message+ "</body></html>";
	    
	    
	    //Uri uri = Uri.parse(message);
	    //Intent intents = new Intent(Intent.ACTION_VIEW, uri);
	    //startActivity(intents);
	    
	    
	    //WebView webview = new WebView(this);
	   // webview.loadData(summary, "text/html", null);

	    //setContentView(webview);
	    // Create the text view
	    TextView textView = new TextView(this);
	    textView.setTextSize(20);
	    textView.setBackgroundColor(Color.WHITE);
	    textView.setTextColor(Color.BLACK);
	    textView.setText(message);

	    // Set the text view as the activity layout
	    setContentView(textView);
	}
}
