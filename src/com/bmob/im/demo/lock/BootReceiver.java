package com.bmob.im.demo.lock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver{
	String myPkgName = "com.bmob.im.demo.lock";
	String myActName =  "myService";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v("Receive", "重启");
		Intent myIntent=new Intent();
		myIntent.setAction(myPkgName+"."+myActName);
		context.startService(myIntent);	
	}
}
