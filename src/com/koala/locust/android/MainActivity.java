package com.koala.locust.android;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
	
	private Button mButton;
	private static final String TAG = "MainActivity";

	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			new DownloadImageTask().execute();
		} catch (Exception ee) {
			Log.e(TAG, ee.toString());
		}

		// /* 设置mButton的onClick事件处理 */
		mButton = (Button) findViewById(R.id.button_normal);
		mButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

//				try {
//					testDevice();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				
				try {
					testWithoutFormLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					testWithFormLogin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	/**
	 * this is a spring App to check request equipment	
	 * @throws Exception
	 */
//	private void testDevice() throws Exception {
//		
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		try {
//		String url = "http://10.0.2.2:8080/lite-showcase/?site_preference=mobile";
//		HttpGet httpget = new HttpGet(url);
//		httpget.addHeader("User-Agent", "ipod");
//		ResponseHandler<String> responseHandler = new BasicResponseHandler();
//		String responseBody = httpclient.execute(httpget, responseHandler);
//		Log.i(TAG, "----------------------------------------");
//		Log.i(TAG, responseBody);
//		Log.i(TAG, "----------------------------------------");
//
//		} finally {
//			httpclient.getConnectionManager().shutdown();
//		}
//		
//	}
	
	private void testWithoutFormLogin() throws Exception {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		try {

//			String url = "http://10.0.2.2:8080/ss334/account/user!input.action";
			String url = "http://10.0.2.2:8060/locust/admin/user/list";
			HttpGet httpget = new HttpGet(url);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);
			Log.i(TAG, "----------------------------------------");
			Log.i(TAG, responseBody);
			Log.i(TAG, "----------------------------------------");

		} finally {
			httpclient.getConnectionManager().shutdown();
		}

	}

	private void testWithFormLogin() throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
//			HttpPost httpost = new HttpPost(
//					"http://10.0.2.2:8080/ss334/j_spring_security_check");
//
//			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//			nvps.add(new BasicNameValuePair("j_username", "admin"));
//			nvps.add(new BasicNameValuePair("j_password", "admin"));

			HttpPost httpost = new HttpPost(
					"http://10.0.2.2:8060/locust/login");

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("username", "admin"));
			nvps.add(new BasicNameValuePair("password", "admin"));

			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = httpclient.execute(httpost);
			Log.i(TAG, "Login form get: " + response.getStatusLine());

//			String url = "http://10.0.2.2:8080/ss334/account/user!input.action";
			String url = "http://10.0.2.2:8060/locust/admin/user/list";
			HttpGet httpget = new HttpGet(url);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httpget, responseHandler);
			Log.i(TAG, "----------------------------------------");
			Log.i(TAG, responseBody);
			Log.i(TAG, "----------------------------------------");

		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

	private void showResult(Bitmap bitmap) {

		TextView textview;
		textview = (TextView) findViewById(R.id.body);
		textview.setText("showResult");

	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

		protected Bitmap doInBackground(String... urls) {

			return null;
		}

		protected void onPostExecute(Bitmap result) {
			showResult(result);
		}
	}
	
}
