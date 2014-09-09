package com.androidexample.uploadtoserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;


//import com.socialapp.util.SocialAppJSONCommonKeywords;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UploadToServer extends Activity {

	TextView messageText;
	Button uploadButton;
	int serverResponseCode = 0;
	ProgressDialog dialog = null;

	String upLoadServerUri = null;

	/********** File Path *************/
//	final String uploadFilePath = "/mnt/sdcard/";
//	final String uploadFileName = "service_lifecycle.png";
	
	final String uploadFilePath = "/storage/emulated/0/Download/";
	 final String uploadFileName = "reload1awe.png"; //test.png video
		
	// inal String uploadFileName = "video.mp4"; //test.png video
	//final String uploadFileName = "test.png"; //test.png

	//storage/emulated/0/Download/video.mp4

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_to_server);

		uploadButton = (Button) findViewById(R.id.uploadButton);
		messageText = (TextView) findViewById(R.id.messageText);

		messageText.setText("Uploading file path :- '/mnt/sdcard/" +"or"+"/storage/emulated/0/Download/"
				+ uploadFileName + "'");

		/*List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("type", "hidden"));
		params.add(new BasicNameValuePair("name", "u_id"));
		params.add(new BasicNameValuePair("value", "12"));*/

		/************* Php script path ****************/
		// upLoadServerUri = "http://www.epicshareapps.com/test/upload.php";
	//	upLoadServerUri = "http://www.Yourwebsitename.com/foldername/api/upload_demo.php";
//		upLoadServerUri = "http://eclicktechnology.com/webservice/messages.php?senderID=23&recID=24&msgTXT=thisismaessage23text&videoUrl=";// ?senderID=1&recID=2";
	//image	
	//	upLoadServerUri = "http://www.Yourwebsitename.com/foldername/api/user_profile_update.php?userid=1";

		//	String	user_id ="1",video_title ="http://Yourwebsitename.com/foldername/api/user_video_upload.php?userid=",video_des="Bunny_video";
	//video					upLoadServerUri ="http://Yourwebsitename.com/foldername/api/user_video_upload.php?userid="+"1"+"&v_title="+"video_tittle"+"&v_desc="+"desc_video";
		upLoadServerUri ="http://Yourwebsitename.com/foldername/api/user_video_upload.php?userid=1&v_title=Bunny&v_desc=BunnyVideo";
		
		//upLoadServerUri = "http://www.Yourwebsitename.com/foldername/api/user_profile_update.php?userid=1&firstname=prince&email=test&last_name=patel&phone=7878798081&country_id=3&birthdate=1991-03-12&city=rajkot&officephone=9898988787&gender=m";
//		upLoadServerUri="http://Yourwebsitename.com/foldername/api/user_video_upload.php?userid=14&v_title=test&v_desc=test_dec";
		uploadButton.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {

				dialog = ProgressDialog.show(UploadToServer.this, "",
						"Uploading file...", true);

				new Thread(new Runnable() 
				{
					public void run() 
					{
						runOnUiThread(new Runnable() 
						{
							public void run() 
							{
								messageText.setText("uploading started.....");
							}
						});

						uploadFile(uploadFilePath + "" + uploadFileName);

					}
				}).start();
			}
		});
	}

	public int uploadFile(String sourceFileUri) {

		String fileName = sourceFileUri;

		HttpURLConnection conn = null;
		DataOutputStream dos = null;
		String lineEnd = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";
		int bytesRead, bytesAvailable, bufferSize;
		byte[] buffer;
		int maxBufferSize = 1 * 1024 * 1024;
		File sourceFile = new File(sourceFileUri);

		if (!sourceFile.isFile()) {

			dialog.dismiss();

			Log.e("uploadFile", "Source File not exist :" + uploadFilePath + ""
					+ uploadFileName);

			runOnUiThread(new Runnable() {
				public void run() {
					messageText.setText("Source File not exist :"
							+ uploadFilePath + "" + uploadFileName);
				}
			});

			return 0;

		} else {
			try {

				// open a URL connection to the Servlet
				FileInputStream fileInputStream = new FileInputStream(
						sourceFile);
			/*
				String ProfileUrlPath="";
	        	 ProfileUrlPath=Define.url_updateprofile+"?"+"user_id="+ProfileActivity.user_id+"&fisrtname="+et_name.getText().toString()
	        			 +"&email="+ et_mail.getText().toString()+"&phone="+ et_mo.getText().toString()+"&country_id"+country_id;
*/
				URL url = new URL(upLoadServerUri);

				// Open a HTTP connection to the URL
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoInput(true); // Allow Inputs
				conn.setDoOutput(true); // Allow Outputs
				conn.setUseCaches(false); // Don't use a Cached Copy
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Connection", "Keep-Alive");
				conn.setRequestProperty("ENCTYPE", "multipart/form-data");
				conn.setRequestProperty("User-Agent",
						"Android Multipart HTTP Client 1.0");

				conn.setRequestProperty("Content-Type",
						"multipart/form-data;boundary=" + boundary);
			//	profilepic
				conn.setRequestProperty("file", fileName);  //image - file ^&*   video_manage to file  profilepic

				dos = new DataOutputStream(conn.getOutputStream());
				
				Log.e("Boundary value", "Boundary == " + boundary.toString());

				Log.e("File Name", uploadFileName.toString());

				dos.writeBytes(twoHyphens + boundary + lineEnd);

				dos.writeBytes("Content-Disposition: form-data; name=\"file\";filename=\""
						+ fileName + "\"" + lineEnd); //filename=  - file=   file= -  name=

			//	dos.writeBytes("Content-Type: image/*" + lineEnd);
				dos.writeBytes("Content-Type: video/*" + lineEnd);
				
				dos.writeBytes("Content-Transfer-Encoding: binary" + lineEnd);

				/*
				 * main dos.writeBytes(
				 * "Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
				 * + fileName + "\"" + lineEnd);
				 */

				Log.e("My data ",
						"Content-Disposition: form-data; name=\"file\";filename=\""
								+ fileName + "\"" + lineEnd);

				dos.writeBytes(lineEnd);

				// create a buffer of maximum size
				bytesAvailable = fileInputStream.available();

				bufferSize = Math.min(bytesAvailable, maxBufferSize);
				buffer = new byte[bufferSize];

				// read file and write it into form...
				bytesRead = fileInputStream.read(buffer, 0, bufferSize);

				while (bytesRead > 0) {

					dos.write(buffer, 0, bufferSize);
					bytesAvailable = fileInputStream.available();
					bufferSize = Math.min(bytesAvailable, maxBufferSize);
					bytesRead = fileInputStream.read(buffer, 0, bufferSize);

				}

				// send multipart form data necesssary after file data...
				dos.writeBytes(lineEnd);
				dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

				Log.v("My data writeBytes ",
						"\n<<twoHyphens>>=" + twoHyphens.toString() + ""
								+ "\n <<boundary>>=" + boundary.toString()
								+ "\n<<twoHyphens>>=" + twoHyphens.toString()
								+ "\n<<lineEnd>>" + lineEnd.toString());

				// Responses from the server (code and message)
				serverResponseCode = conn.getResponseCode();
				String serverResponseMessage = conn.getResponseMessage();

				Log.i("uploadFile", "HTTP Response is : "
						+ serverResponseMessage + ": " + serverResponseCode);

				if (serverResponseCode == 200) 
				{
					InputStream inputStream = null;
					HttpURLConnection httpConn = (HttpURLConnection) conn;
					inputStream = httpConn.getInputStream();					

					BufferedReader reader = new BufferedReader(	new InputStreamReader(inputStream, "iso-8859-1"), 8);
					StringBuilder sb = new StringBuilder();
					String line = null;
					while ((line = reader.readLine()) != null) {
						sb.append(line);
					}
					inputStream.close();
					System.out.println("Server Responce is == >> "+sb.toString()+"<< == Finish");
					

					runOnUiThread(new Runnable() {
						public void run() {

							String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
									+ " http://www.androidexample.com/media/uploads/"
									+ uploadFileName;

							messageText.setText(msg);
							Toast.makeText(UploadToServer.this,
									"File Upload Complete.", Toast.LENGTH_SHORT)
									.show();
						}
					});
				}

				// close the streams //
				fileInputStream.close();
				dos.flush();
				dos.close();

			} catch (MalformedURLException ex) {

				dialog.dismiss();
				ex.printStackTrace();

				runOnUiThread(new Runnable() {
					public void run() {
						messageText
								.setText("MalformedURLException Exception : check script url.");
						Toast.makeText(UploadToServer.this,
								"MalformedURLException", Toast.LENGTH_SHORT)
								.show();
					}
				});

				Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
			} catch (Exception e) {

				dialog.dismiss();
				e.printStackTrace();

				runOnUiThread(new Runnable() {
					public void run() {
						messageText.setText("Got Exception : see logcat ");
						Toast.makeText(UploadToServer.this,
								"Got Exception : see logcat ",
								Toast.LENGTH_SHORT).show();
					}
				});
				Log.e("Upload file to server Exception",
						"Exception : " + e.getMessage(), e);
			}
			dialog.dismiss();
			return serverResponseCode;

		} // End else block
	}
}
