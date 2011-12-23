package com.treve.iqtool;

import java.io.File;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    	/** Setup Layout */
    	EditText txtoutput = (EditText) findViewById(R.id.output);
    	Button checkciq = (Button) findViewById(R.id.checkForCIQ);
    	Button findpro = (Button) findViewById(R.id.findProfiles);
    	Button emaileff = (Button) findViewById(R.id.sendEmail);
    	Button copyprotosd = (Button) findViewById(R.id.copyProfiles);
    	
		/** Setup known CIQ File Locations */
		//SAMSUNG
		final File iqmsdfile=new File("/system/bin/iqmsd");
		final File libiqfile=new File("/system/lib/libiq_client.so");
		final File libiqservicefile=new File("/system/lib/libiq_service.so");
		 
		 //HTC
		final File iqfdfile=new File("/system/bin/iqfd");
		final File iqdfile=new File("/system/bin/iqd");
		final File libciqfile=new File("/system/lib/libciq_client.so");
		final File libciqhtcfile=new File("/system/lib/libciq_htc.so");
		final File libciqagentfile=new File("/system/lib/libhtciqagent.so");
		final File iqprofile=new File("/system/etc/iqprofile.pro");
		final File HtcIQAgentfile=new File("/system/app/HtcIQAgent.apk");
		final File HtcIQAgentodexfile=new File("/system/app/HtcIQAgent.odex");
		final File IQRDfile=new File("/system/app/IQRD.apk");
		final File IQRDodexfile=new File("/system/app/IQRD.odex");
		final File appcacheiqserver=new File("/app-cache/iqserver/");
		final File appcacheciq=new File("/app-cache/ciq/");
		final File pmemciq=new File("/dev/pmem_ciq/");
		 
		 //TMOBO
		final File libiq_service_tmobile=new File("/system/lib/libiq_service_tmobile_2.2.so");
		final File tmobiledata=new File("/data/data/com.carrieriq.tmobile/");
		final File IQtmobilereleasev11=new File("/system/app/IQ-tmobile-release-v1.1.apk");

		/** Check root */
		//TODO: Make this do something
		if(!FileTools.hasRootPermission()){
			Toast.makeText(getBaseContext(), "Switching to non root mode....",Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getBaseContext(), "You are rooted, using full capabilities....",Toast.LENGTH_LONG).show();
		}
		
		/** Check Busybox */
		File busyboxxbin=new File("/system/xbin/busybox");
		boolean busyboxxbinexist = busyboxxbin.exists();
		File busyboxbin=new File("/system/bin/busybox");
		boolean busyboxbinexist = busyboxbin.exists();
		if ((!busyboxxbinexist) || (!busyboxbinexist)){
			Toast.makeText(getBaseContext(), "You do not have busybox! Searching will not work",Toast.LENGTH_LONG).show();
			//TODO: Add Busybox Copy code here
		}
		
		 
		/** Make Check CIQ Button work */
		checkciq.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view){

		 EditText txtoutput = (EditText) findViewById(R.id.output);
		 boolean iqprofileexists = iqprofile.exists();
		 boolean iqfdexists = iqfdfile.exists();
		 boolean iqdexists = iqdfile.exists();
		 boolean iqmsdexists = iqmsdfile.exists();
		 boolean libciqfileexists = libciqfile.exists();
		 boolean libciqhtcfileexists = libciqhtcfile.exists();
		 boolean libiqfileexists = libiqfile.exists();
		 boolean libiqservicefileexists = libiqservicefile.exists();
		 boolean libciqagentfileexists = libciqagentfile.exists();
		 boolean HtcIQAgentfileexists = HtcIQAgentfile.exists();
		 boolean HtcIQAgentodexfileexists = HtcIQAgentodexfile.exists();
		 boolean IQRDfileexists = IQRDfile.exists();
		 boolean IQRDodexfileexists = IQRDodexfile.exists();
		 String appcacheciqls = FileTools.doRootCommand("ls /app-cache/ciq/");
		 boolean appcacheciqexists = appcacheciq.exists();
		 boolean appcacheiqserverexists = appcacheiqserver.exists();
		 boolean tmobiledataexists = tmobiledata.exists();
		 boolean libiq_service_tmobileexists = libiq_service_tmobile.exists();
		 boolean IQtmobilereleasev11exists = IQtmobilereleasev11.exists();
		 
		 if ((iqprofileexists) || (tmobiledataexists) || (libiq_service_tmobileexists) || (IQtmobilereleasev11exists) || (iqfdexists) || (iqdexists) || (iqmsdexists) || (libciqfileexists) || (libciqhtcfileexists) || (libiqfileexists) || (libiqservicefileexists) || (libciqagentfileexists)
				 || (HtcIQAgentfileexists) || (HtcIQAgentodexfileexists) || (IQRDfileexists) || (IQRDodexfileexists) || (appcacheiqserverexists))
		 {
			txtoutput.setText("CIQ FOUND! File List:\n ");
			if (HtcIQAgentfileexists) {txtoutput.append(HtcIQAgentfile.toString() + " exists!\n");};
			if (HtcIQAgentodexfileexists) {txtoutput.append(HtcIQAgentodexfile.toString() + " exists!\n");};
			if (IQRDfileexists) {txtoutput.append(IQRDfile.toString() + " exists!\n");};
			if (IQRDodexfileexists) {txtoutput.append(IQRDodexfile.toString() + " exists!\n");};
			if (iqfdexists) {txtoutput.append(iqfdfile.toString() + " exists!\n");};
			if (iqdexists) {txtoutput.append(iqdfile.toString() + " exists!\n");};
			if (iqmsdexists) {txtoutput.append(iqmsdfile.toString() + " exists!\n");};
			if (libciqfileexists) {txtoutput.append(libciqfile.toString() + " exists!\n");};
			if (libciqhtcfileexists) {txtoutput.append(libciqhtcfile.toString() + " exists!\n");};
			if (libiqfileexists) {txtoutput.append(libiqfile.toString() + " exists!\n");};
			if (libiqservicefileexists) {txtoutput.append(libiqservicefile.toString() + " exists!\n");};
			if (libciqagentfileexists) {txtoutput.append(libciqagentfile.toString() + " exists!\n");};
			if (iqprofileexists) {txtoutput.append(iqprofile.toString() + " exists!\n");};
			if (tmobiledataexists) {txtoutput.append(tmobiledata.toString() + " exists!\n");};
			if (libiq_service_tmobileexists) {txtoutput.append(libiq_service_tmobile.toString() + " exists!\n");};
			if (IQtmobilereleasev11exists) {txtoutput.append(IQtmobilereleasev11.toString() + " exists!\n");};
	
			if (appcacheiqserverexists) {txtoutput.append(appcacheiqserver.toString() + " exists!\n");};
			if(FileTools.hasRootPermission()){if ((appcacheciqls != "Empty\n") && (appcacheciqexists)){txtoutput.append("/app-cache/ciq/" + appcacheciq.toString() + " exists!\n");};}
			txtoutput.append("\nCIQ Exists\n");
		} else {txtoutput.setText("\nCIQ not found\n");}
	}});
		
		
		
		/** Setup Button 2 to search for profiles */
		findpro.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view){
			scanProfiles();
		}});
		
		
		
		/** Setup Button 3 to Send profiles **/ 
		emaileff.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view){
			EditText txtoutput = (EditText) findViewById(R.id.output);
			Intent sendIntent = new Intent(Intent.ACTION_SEND);
			sendIntent.setType("*/*");
			sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"iqiq@eff.org"}); 
			sendIntent.putExtra(Intent.EXTRA_SUBJECT, "IQIQ Profile");
			
			//TODO: Fix attachments to work for profiles
			sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/IQTool_CarrierIQ_Archive.img"));
			sendIntent.putExtra(Intent.EXTRA_TEXT, txtoutput.getText().toString()); 
			startActivity(Intent.createChooser(sendIntent, "Email:"));
		}});
		
		
		/** copyprotosd **/
		copyprotosd.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view){
			EditText txtoutput = (EditText) findViewById(R.id.output);
			
			//TODO: use real copy method & get profile from find result
			txtoutput.setText(FileTools.doStandardCommand("busybox cp /data/data/com.carrieriq.tmobile/app_iq_archive/archive.img /sdcard/IQTool_CarrierIQ_Archive.img"));
		}});
		
    }

    
    
    
/** Setup Async task for scanning in background */
public void scanProfiles() {
    new scanProfiles().execute();
}

private class scanProfiles extends AsyncTask<String,String,String>{
	@SuppressWarnings("unused")
	private String Content;
    private ProgressDialog Dialog = new ProgressDialog(Main.this);
    StringBuilder output = new StringBuilder();
	EditText txtoutput = (EditText) findViewById(R.id.output);
	
    protected void onPreExecute() {
        Dialog.setMessage("Scanning...");
        Dialog.show();
    }

    protected String doInBackground(String... arg0) {
		try {
			String prosearchreturn = FileTools.doRootCommand("busybox find / -iname \"*.pro\"");
			String tmoarchivesearchreturn = FileTools.doRootCommand("ls -l /data/data/com.carrieriq.tmobile/app_iq_archive/archive.img");
			
			if (prosearchreturn  != "Empty\n"){
				output.append("\nProfile Search Results:\n");
				output.append(prosearchreturn);
			} else { output.append("\nProfile NOT Found\n");};
			output.append("\n\n");
			if (tmoarchivesearchreturn  != "Empty\n"){
				output.append("\nTmobile Archive Found:\n");
				output.append(tmoarchivesearchreturn);
			} else { output.append("\nTmobile archive NOT Found\n");};
			
			
	 	} catch (Exception e) {output.append("Error:\n" + e.getMessage());}
		return output.toString();
	}
	

    protected void onPostExecute(String result) {
    	txtoutput.append(output.toString());
    	Dialog.dismiss();
    }}
}
