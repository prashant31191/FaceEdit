package selva.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("InlinedApi")
public class IncomingCallActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            Log.d("IncomingCallActivity: onCreate: ", "flag2");
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
         
            /*
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
            my
            */
            
            // WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL - 

            Log.d("IncomingCallActivity: onCreate: ", "flagy");
            
            {
                //	Intent dialogIntent = new Intent(getBaseContext(), ShowActivity.class);
                //	dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //	getApplication().startActivity(dialogIntent);
            	// AlertDialog.Builder builder = new AlertDialog.Builder(this);
                 
            	//AlertDialog dialog = builder.create();
             //   dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
               
           
         //   dialog.show();
            	
            	//start
            	/*
                	
                	 AlertDialog.Builder builder = new AlertDialog.Builder(this);
                     builder.setCancelable(true);
                     builder.setTitle(" ");
                     builder.setIcon(R.drawable.resl_estate);
                     
                     String number2 = getIntent().getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                     
                     builder.setMessage("caller number is :- "+number2);
                     builder.setInverseBackgroundForced(true);

                     builder.setNeutralButton("Ok",new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int whichButton){
                         dialog.dismiss();
                        // finish();
                         setContentView(R.layout.main);

                         Log.d("IncomingCallActivity: onCreate: ", "flagz");
                         
                         Toast.makeText(getApplicationContext(), "Call Recever", 200).show();

                         String number = getIntent().getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                         TextView text = (TextView) findViewById(R.id.text);
                         text.setText("Incoming call from " + number);
                       }
                     });
                     builder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                         public void onClick(DialogInterface dialog, int whichButton){
                           dialog.dismiss();
                           finish();
                         }
                       });
                    // builder.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                     
                     AlertDialog dialog2 = builder.create();
                     dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                     WindowManager.LayoutParams wmlp = dialog2.getWindow().getAttributes();

                     wmlp.gravity = Gravity.TOP | Gravity.LEFT;
                     wmlp.x = 10;   //x position
                     wmlp.y = 10;   //y position

                     dialog2.show();
           //end
                     */
                     
                	
                   /*  Dialog dialog = new Dialog(this);
                     dialog.setTitle("This is virus dialog");
                     dialog.setCancelable(true);
                     
                     dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                     dialog.show();*/
                }
           
            
            

            setContentView(R.layout.main);

            Log.d("IncomingCallActivity: onCreate: ", "flagz");
            
            Toast.makeText(getApplicationContext(), "Call Recever", Toast.LENGTH_LONG).show();

            String number = getIntent().getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            TextView text = (TextView) findViewById(R.id.text);
            text.setText("Incoming call from  " + number);
        } catch (Exception e) {
            Log.d("Exception", e.toString());
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
