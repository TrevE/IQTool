package com.treve.iqtool;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileTools extends Main {     
	
//is user rooted?	
public static boolean hasRootPermission() {

    Process process = null;
    DataOutputStream os = null;
    boolean rooted = true;
    try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("exit\n");
            os.flush();             
            process.waitFor();
            if (process.exitValue() != 0) {rooted = false;}
    } catch (Exception e) {
        rooted = false;
    }
    finally {
        if (os != null) {
            try {
                    os.close();
                    process.destroy();
            } catch (Exception e) {
            }
        }
    }
    return rooted;
}

//Run commands under SU (root)
public static String doRootCommand(String string) {
Process proc = null;
StringBuffer output = new StringBuffer();
try {
	proc = Runtime.getRuntime().exec("su");
    OutputStreamWriter out = new OutputStreamWriter(proc.getOutputStream());
	out.write(string);
	out.write("\n");       
    out.flush();
	out.write("exit\n");
	out.flush();
	
	final char buf[] = new char[1024];
	// Consume the "stdout"
	InputStreamReader reader = new InputStreamReader(proc.getInputStream());
	int read=0;
	while ((read=reader.read(buf)) != -1) {
		output.append(buf, 0, read);
	}
	
	// Consume the "stderr"
	reader = new InputStreamReader(proc.getErrorStream());
	read=0;
	while ((read=reader.read(buf)) != -1) {
		output.append(buf, 0, read);
	}
	
	//Wait to finish
	proc.waitFor();
	} catch (Exception e) { return e.getMessage(); }

	//kill nulls 
 	if ((output.toString() == "") || (output.length() == 2 ) || (output.toString() == null) || (output.toString() == "  ")|| (output.toString() == "\n")|| (output.toString() == "\n\n")|| (output.toString() == " \n")){
 		return("Empty\n");
 	}
 	
	return output.toString();
}





//Run commands under SH (normal user)
public static String doStandardCommand(String string) {
Process proc = null;
StringBuffer output = new StringBuffer();
try {
	proc = Runtime.getRuntime().exec("sh");
    OutputStreamWriter out = new OutputStreamWriter(proc.getOutputStream());
	out.write(string);
	out.write("\n");       
    out.flush();
	out.write("exit\n");
	out.flush();
	
	final char buf[] = new char[1024];
	// Consume the "stdout"
	InputStreamReader reader = new InputStreamReader(proc.getInputStream());
	int read=0;
	while ((read=reader.read(buf)) != -1) {
		output.append(buf, 0, read);
	}
	
	// Consume the "stderr"
	reader = new InputStreamReader(proc.getErrorStream());
	read=0;
	while ((read=reader.read(buf)) != -1) {
		output.append(buf, 0, read);
	}
	
	//Wait to finish
	proc.waitFor();
	} catch (Exception e) { return e.getMessage(); }

	//kill nulls 
 	if ((output.toString() == "") || (output.length() == 2 ) || (output.toString() == null) || (output.toString() == "  ")|| (output.toString() == "\n")|| (output.toString() == "\n\n")|| (output.toString() == " \n")){
 		return("Empty\n");
 	}
	return output.toString();
}




}