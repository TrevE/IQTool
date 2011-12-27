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
    	//LG Non-Embedded
		//SAMSUNG Embedded 
		final File iagentfile=new File("/system/app/IQAgent.apk");
		final File iagentodexfile=new File("/system/app/IQAgent.odex");
		final File iqmsdfile=new File("/system/bin/iqmsd");
		final File libiqfile=new File("/system/lib/libiq_client.so");
		final File libiqservicefile=new File("/system/lib/libiq_service.so");
		final File iqagentdata=new File("/data/data/com.carrieriq.iqagent/");

		 //TMOBO
		final File IQtmobilereleasev11=new File("/system/app/IQ-tmobile-release-v1.1.apk");
		final File IQtmobilezipreleasev11=new File("/system/app/IQ-tmobile-release-signed-zipaligned-v1.1.apk");
		final File libiq_service_tmobile=new File("/system/lib/libiq_service_tmobile_2.2.so");
		final File tmobiledata=new File("/data/data/com.carrieriq.tmobile/");
		
		//ATT
		final File ATTIQAgentfile=new File("/system/app/IQ-ota-release.apk");
		final File attdata=new File("/data/data/com.carrieriq.attrom/");
		
		 //HTC
		final File HtcIQAgentfile=new File("/system/app/HtcIQAgent.apk");
		final File HtcIQAgentodexfile=new File("/system/app/HtcIQAgent.odex");
		final File IQRDfile=new File("/system/app/IQRD.apk");
		final File IQRDodexfile=new File("/system/app/IQRD.odex");
		final File iqfdfile=new File("/system/bin/iqfd");
		final File iqdfile=new File("/system/bin/iqd");
		final File libciqfile=new File("/system/lib/libciq_client.so");
		final File libciqhtcfile=new File("/system/lib/libciq_htc.so");
		final File libciqagentfile=new File("/system/lib/libhtciqagent.so");
		final File iqprofile=new File("/system/etc/iqprofile.pro");
		final File appcacheiqserver=new File("/app-cache/iqserver/");
		final File appcacheciq=new File("/app-cache/ciq/");
		final File pmemciq=new File("/dev/pmem_ciq/");
		final File pmemciq1=new File("/dev/pmem_ciq1/");
		final File pmemciq2=new File("/dev/pmem_ciq2/");
		final File pmemciq3=new File("/dev/pmem_ciq3/");
		 

		/** Check root */
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
		if ((!busyboxxbinexist) && (!busyboxbinexist)){
			Toast.makeText(getBaseContext(), "You do not have busybox! Searching will not work",Toast.LENGTH_LONG).show();
			//TODO: Add Busybox Copy code here
		}
		
		 
		/** Make Check CIQ Button work */
		checkciq.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view){

		EditText txtoutput = (EditText) findViewById(R.id.output);

	    //LG Non-Embedded
		//SAMSUNG Embedded 
		boolean iagentodexfileexists = iagentodexfile.exists();
		boolean iagentfileexists = iagentfile.exists();
		boolean iqmsdexists = iqmsdfile.exists();
		boolean libiqfileexists = libiqfile.exists();
		boolean libiqservicefileexists = libiqservicefile.exists();
		 
		//ATT
		boolean ATTIQAgentfileexists = ATTIQAgentfile.exists();
		boolean attdataexists = attdata.exists();
		 
		 
		//HTC Embedded
		boolean iqprofileexists = iqprofile.exists();
		boolean iqfdexists = iqfdfile.exists();
		boolean iqdexists = iqdfile.exists();
		boolean libciqfileexists = libciqfile.exists();
		boolean libciqhtcfileexists = libciqhtcfile.exists();
		boolean libciqagentfileexists = libciqagentfile.exists();
		boolean HtcIQAgentfileexists = HtcIQAgentfile.exists();
		boolean HtcIQAgentodexfileexists = HtcIQAgentodexfile.exists();
		boolean IQRDfileexists = IQRDfile.exists();
		boolean IQRDodexfileexists = IQRDodexfile.exists();
		String appcacheciqls = FileTools.doRootCommand("ls /app-cache/ciq/");
		boolean appcacheciqexists = appcacheciq.exists();
		boolean appcacheiqserverexists = appcacheiqserver.exists();
		boolean pmemciqexists = pmemciq.exists();
		boolean pmemciq1exists = pmemciq1.exists();
		boolean pmemciq2exists = pmemciq2.exists();
		boolean pmemciq3exists = pmemciq3.exists();
		 
		//Tmobile non embedded APK
		boolean tmobiledataexists = tmobiledata.exists();
		boolean iqagentdataexists = iqagentdata.exists();
		boolean libiq_service_tmobileexists = libiq_service_tmobile.exists();
		boolean IQtmobilereleasev11exists = IQtmobilereleasev11.exists();
		boolean IQtmobilezipreleasev11exists = IQtmobilezipreleasev11.exists();
		  
		//IQtmobilezipreleasev11
		//EFF Wanted specs on model / where profile found / network, so here it is
		txtoutput.setText("Phone Specs--\n");
		txtoutput.append("Phone Model: (ro.product.name): "+ FileTools.doStandardCommand("getprop ro.product.name"));
		txtoutput.append("CarrierID: (ro.cid): "+ FileTools.doStandardCommand("getprop ro.cid")); 
		txtoutput.append("Operator Alpha Tag: (gsm.operator.alpha): "+ FileTools.doStandardCommand("getprop gsm.operator.alpha"));
		txtoutput.append("Operator Alpha Tag2: (gsm.sim.operator.alpha): "+ FileTools.doStandardCommand("getprop gsm.sim.operator.alpha"));
		 
		if ((iqprofileexists) || (ATTIQAgentfileexists) || (attdataexists) || (tmobiledataexists) || (iqagentdataexists) || (iagentodexfileexists) || (iagentfileexists) || (libiq_service_tmobileexists) || 
			(IQtmobilereleasev11exists) || (iqfdexists) || (iqdexists) || (pmemciqexists) || (iqmsdexists) || (libciqfileexists) || (libciqhtcfileexists) || (libiqfileexists) || (libiqservicefileexists) || 
			(libciqagentfileexists) || (HtcIQAgentfileexists) || (HtcIQAgentodexfileexists) || (IQRDfileexists) || (IQRDodexfileexists) || (appcacheiqserverexists))
		 {
			txtoutput.append("\nCIQ FOUND! File List--\n ");
			if (HtcIQAgentfileexists) {txtoutput.append(HtcIQAgentfile.toString() + " exists!\n");};
			if (HtcIQAgentodexfileexists) {txtoutput.append(HtcIQAgentodexfile.toString() + " exists!\n");};
			if (IQRDfileexists) {txtoutput.append(IQRDfile.toString() + " exists!\n");};
			if (IQRDodexfileexists) {txtoutput.append(IQRDodexfile.toString() + " exists!\n");};
			if (iqfdexists) {txtoutput.append(iqfdfile.toString() + " exists!\n");};
			if (iagentfileexists) {txtoutput.append(iagentfile.toString() + " exists!\n");};
			if (iagentodexfileexists) {txtoutput.append(iagentodexfile.toString() + " exists!\n");};
			if (iqdexists) {txtoutput.append(iqdfile.toString() + " exists!\n");};
			if (iqmsdexists) {txtoutput.append(iqmsdfile.toString() + " exists!\n");};
			if (libciqfileexists) {txtoutput.append(libciqfile.toString() + " exists!\n");};
			if (libciqhtcfileexists) {txtoutput.append(libciqhtcfile.toString() + " exists!\n");};
			if (libiqfileexists) {txtoutput.append(libiqfile.toString() + " exists!\n");};
			if (libiqservicefileexists) {txtoutput.append(libiqservicefile.toString() + " exists!\n");};
			
			if (ATTIQAgentfileexists) {txtoutput.append(ATTIQAgentfile.toString() + " exists!\n");};
			//attdataexists
			
			if (libciqagentfileexists) {txtoutput.append(libciqagentfile.toString() + " exists!\n");};
			if (libiq_service_tmobileexists) {txtoutput.append(libiq_service_tmobile.toString() + " exists!\n");};
			if (IQtmobilereleasev11exists) {txtoutput.append(IQtmobilereleasev11.toString() + " exists!\n");};
			if (IQtmobilezipreleasev11exists) {txtoutput.append(IQtmobilezipreleasev11.toString() + " exists!\n");};
			if (appcacheiqserverexists) {txtoutput.append(appcacheiqserver.toString() + " exists!\n");};
			if(FileTools.hasRootPermission()){if ((appcacheciqls != "Empty\n") && (appcacheciqexists)){txtoutput.append("/app-cache/ciq/" + appcacheciq.toString() + " exists!\n");};}

			//possible profile locs
			if (iqprofileexists) {txtoutput.append(iqprofile.toString() + " exists! May be a stock profile...\n");};
			if (tmobiledataexists) {txtoutput.append(tmobiledata.toString() + " exists.  Archive.img likely here!\n");};
			if (attdataexists) {txtoutput.append(attdata.toString() + " exists!   Archive.img likely here!\n");};
			if (iqagentdataexists) {txtoutput.append(iqagentdata.toString() + " exists.  Archive.img likely here!\n");};
			if (pmemciqexists) {txtoutput.append(pmemciq.toString() + " exists. Whats in these?\n");};;
			if (pmemciq1exists) {txtoutput.append(pmemciq1.toString() + " exists.\n");};
			if (pmemciq2exists) {txtoutput.append(pmemciq2.toString() + " exists.\n");};
			if (pmemciq3exists) {txtoutput.append(pmemciq3.toString() + " exists.\n");};
			
		} else {txtoutput.setText("\nCIQ not found\n");}
	}});
		
		
		
		/** Setup Button 2 to async search for profiles */
		findpro.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view){
			scanProfiles();
		}});
		
		
		/** Step 3 - Copy found profiles to SDCard **/
		copyprotosd.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view){
			EditText txtoutput = (EditText) findViewById(R.id.output);
		    //find what to copy
			File tmobarchive=new File("/data/data/com.carrieriq.tmobile/app_iq_archive/archive.img");
			boolean tmobarchvepresent = tmobarchive.exists();	
			File iqagentarchive=new File("/data/data/com.carrieriq.iqagent/iq_archive/archive.img");
			boolean iqagentarchivepresent = iqagentarchive.exists();	
			File attarchive=new File("/data/data/com.carrieriq.attrom/iq_archive/archive.img");
			boolean attarchivepresent = attarchive.exists();	
			File evoprofile=new File("/system/etc/iqprofile.pro");
			boolean evoprofilepresent = evoprofile.exists();	
			//TODO: Add in busybox search result with a regext or something.
			
			//copy stuff
			//why is there no cp in toolbox :|
			//files might not be world readable.  Lets try as root if possible
			if(FileTools.hasRootPermission()){
				if(evoprofilepresent){
					FileTools.doRootCommand("cat /system/etc/iqprofile.pro >/sdcard/IQTool_Sprint_Evo_System.pro");	
				}
				if(iqagentarchivepresent) {
					FileTools.doRootCommand("cat /data/data/com.carrieriq.iqagent/iq_archive/archive.img >/sdcard/IQTool_CIQ_Archive.img");
				}
				if(attarchivepresent) {
					FileTools.doRootCommand("cat /data/data/com.carrieriq.attrom/iq_archive/archive.img >/sdcard/IQTool_ATT_CIQ_Archive.img");
				}
				if(tmobarchvepresent) {
					FileTools.doRootCommand("cat /data/data/com.carrieriq.tmobile/app_iq_archive/archive.img >/sdcard/IQTool_Tmo_CIQ_Archive.img");
				}
			}else{
				if(evoprofilepresent){
					FileTools.doStandardCommand("cat /system/etc/iqprofile.pro >/sdcard/IQTool_Sprint_Evo_System.pro");	
				}
				if(iqagentarchivepresent) {
					FileTools.doStandardCommand("cat /data/data/com.carrieriq.iqagent/iq_archive/archive.img >/sdcard/IQTool_CIQ_Archive.img");
				}
				if(attarchivepresent) {
					FileTools.doStandardCommand("cat /data/data/com.carrieriq.attrom/iq_archive/archive.img >/sdcard/IQTool_ATT_CIQ_Archive.img");
				}
				if(tmobarchvepresent) {
					FileTools.doStandardCommand("cat /data/data/com.carrieriq.tmobile/app_iq_archive/archive.img >/sdcard/IQTool_Tmo_CIQ_Archive.img");
				}
			}
			
			//Set SDCard Files
			File sdtmobarchive=new File("/sdcard/IQTool_Tmo_CIQ_Archive.img");
			boolean sdtmobarchvepresent = sdtmobarchive.exists();	
			File sdiqarchive=new File("/sdcard/IQTool_CIQ_Archive.img");
			boolean sdiqarchvepresent = sdiqarchive.exists();	
			File sdattarchive=new File("/sdcard/IQTool_ATT_CIQ_Archive.img");
			boolean sdattarchvepresent = sdattarchive.exists();	
			File sdevoprofile=new File("/sdcard/IQTool_Sprint_Evo_System.pro");
			boolean sdevoprofilepresent = sdevoprofile.exists();	
			
			//Put message up
			if(sdtmobarchvepresent) {
				Toast.makeText(getBaseContext(), "Tmobile archive copied to sdcard.  DELETE THIS LATER!",Toast.LENGTH_LONG).show();
				txtoutput.append("\narchive.img copied to\n/sdcard/IQTool_Tmo_CIQ_Archive.img\nThis archive could contain sensitive data, including location and call history, URLs, or text messages.\nDo not send it to EFF if there may be private information on this handset.");
			}
			if(sdiqarchvepresent) {
				Toast.makeText(getBaseContext(), "IQ archive copied to sdcard.  DELETE THIS LATER!",Toast.LENGTH_LONG).show();
				txtoutput.append("\narchive.img copied to\n/sdcard/IQTool_CIQ_Archive.img\nThis archive could contain sensitive data, including location and call history, URLs, or text messages.\nDo not send it to EFF if there may be private information on this handset.");
			}	
			if(sdattarchvepresent) {
				Toast.makeText(getBaseContext(), "ATT archive copied to sdcard.  DELETE THIS LATER!",Toast.LENGTH_LONG).show();
				txtoutput.append("\narchive.img copied to\n/sdcard/IQTool_ATT_CIQ_Archive.img\nThis archive could contain sensitive data, including location and call history, URLs, or text messages.\nDo not send it to EFF if there may be private information on this handset.");
			}
			if(sdevoprofilepresent){
				Toast.makeText(getBaseContext(), "Evo Sprint system pro copied to sdcard.  DELETE THIS LATER!",Toast.LENGTH_LONG).show();
				txtoutput.append("\niqprofile.pro copied to\n/sdcard/IQTool_Sprint_Evo_System.pro\n");
			}
			
			//Check if files made it
			if ((!sdtmobarchvepresent) && (!sdevoprofilepresent) && (!sdiqarchvepresent) && (!sdattarchvepresent)){
				Toast.makeText(getBaseContext(), "Error Copying files. :( Either your SDCard is mounted or files cannot be read (try as root)",Toast.LENGTH_LONG).show(); 
				txtoutput.append("\nError Copying files.  Do not report this to EFF.");
			}
			}});
		
		
		/** Setup Button 4 to Send Email with profiles **/ 
		emaileff.setOnClickListener(new View.OnClickListener() {
		public void onClick(View view){
			EditText txtoutput = (EditText) findViewById(R.id.output);
			Intent sendIntent = new Intent(Intent.ACTION_SEND);
			sendIntent.setType("*/*");
			sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"iqiq@eff.org"}); 
			sendIntent.putExtra(Intent.EXTRA_SUBJECT, "IQIQ Profile");
			
			//look for profiles user copied to attach
			File tmobarchive=new File("/sdcard/IQTool_Tmo_CIQ_Archive.img");
			boolean tmobarchvepresent = tmobarchive.exists();	
			File sdiqarchive=new File("/sdcard/IQTool_CIQ_Archive.img");
			boolean sdiqarchvepresent = sdiqarchive.exists();	
			File sdattarchive=new File("/sdcard/IQTool_ATT_CIQ_Archive.img");
			boolean sdattarchvepresent = sdattarchive.exists();	
			File sprintsysprofile=new File("/sdcard/IQTool_Sprint_Evo_System.pro");
			boolean sprintsysprofilepresent = sprintsysprofile.exists();	

			//Attach Profiles, use warning if archive.img
			if(tmobarchvepresent) {
				Toast.makeText(getBaseContext(), "Remember, Do not hit the send button if you are uncomfortable sending what (could be) sensitive information to the EFF!",Toast.LENGTH_LONG).show();
				sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/IQTool_Tmo_CIQ_Archive.img"));
			}	
			if(sdiqarchvepresent) {
				Toast.makeText(getBaseContext(), "Remember, Do not hit the send button if you are uncomfortable sending what (could be) sensitive information to the EFF!",Toast.LENGTH_LONG).show();
				sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/IQTool_CIQ_Archive.img"));
			}	
			if(sdattarchvepresent) {
				Toast.makeText(getBaseContext(), "Remember, Do not hit the send button if you are uncomfortable sending what (could be) sensitive information to the EFF!",Toast.LENGTH_LONG).show();
				sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/IQTool_ATT_CIQ_Archive.img"));
			} 
			if(sprintsysprofilepresent){
				Toast.makeText(getBaseContext(), "Attached Sprint Evo System Profile!",Toast.LENGTH_LONG).show();
				sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/IQTool_Sprint_Evo_System.pro"));
			}
			
			sendIntent.putExtra(Intent.EXTRA_TEXT, txtoutput.getText().toString()); 
			startActivity(Intent.createChooser(sendIntent, "Email:"));
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
        Dialog.setMessage("Scanning... If rooted this may take awhile");
        Dialog.show();
    }

    protected String doInBackground(String... arg0) {
		try {
			String prosearchreturn = null;
			
			//Root Users Search
			if(FileTools.hasRootPermission()){
				prosearchreturn = FileTools.doRootCommand("busybox find / -iname \"*.pro\"");
				if (prosearchreturn  != "Empty\n"){
					output.append("\nProfile List.  You will need to manually attach anything found (ignore permission denied)\n");
					output.append(prosearchreturn); }
			} else { output.append("\nNo Root Cannot Search\n"); };
			
			//IQAgent Search (no root)
			File iqarchive=new File("/data/data/com.carrieriq.iqagent/iq_archive/archive.img");
			boolean iqarchvepresent = iqarchive.exists();	
			output.append("\n\n");
			if (iqarchvepresent){
				output.append("\nIQ Archive Found:\n");
				output.append(iqarchive.toString());
			} else { output.append("\nIQ archive NOT Found\n");};
			

			//ATT IQAgent Search (no root)
			File attiqarchive=new File("/data/data/com.carrieriq.attrom/iq_archive/archive.img");
			boolean attiqarchvepresent = attiqarchive.exists();	
			output.append("\n\n");
			if (attiqarchvepresent){
				output.append("\nATT IQ Archive Found:\n");
				output.append(attiqarchive.toString());
			} else { output.append("\nATT IQ archive NOT Found\n");};
			
			
			//Tmobile search (no root)
			File tmobarchive=new File("/data/data/com.carrieriq.tmobile/app_iq_archive/archive.img");
			boolean tmobarchvepresent = tmobarchive.exists();	
			output.append("\n\n");
			if (tmobarchvepresent){
				output.append("\nTmobile Archive Found:\n");
				output.append(tmobarchive.toString());
			} else { output.append("\nTmobile archive NOT Found\n");};
			
			
	 	} catch (Exception e) {output.append("Error:\n" + e.getMessage());}
		return output.toString();
	}
	

    protected void onPostExecute(String result) {
    	txtoutput.append(output.toString());
    	Dialog.dismiss();
    }}
}
