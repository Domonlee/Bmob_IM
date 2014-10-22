
package com.bmob.im.demo.lock;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

@SuppressWarnings("deprecation")
public class myService extends Service {
	private String TAG = "ScreenReceiver Log";
	private KeyguardManager keyguardManager = null;
	private KeyguardManager.KeyguardLock keyguardLock = null;
	Intent toMainIntent;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		toMainIntent = new Intent(myService.this, HomeActivity.class);//#设置Main.class为要跳转到的界面，既当解锁时要打�?��界面
		toMainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//必须得有，不知为�?
		
		keyguardManager = (KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE);
		keyguardLock = keyguardManager.newKeyguardLock("");
		keyguardLock.disableKeyguard();
		
		IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
		registerReceiver(screenReceiver, intentFilter);
		
		
		
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return Service.START_STICKY;//粘�?进程
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(screenReceiver);
		keyguardLock.reenableKeyguard();
		//重启此服
		startActivity(new Intent(myService.this,myService.class));
	}
	
	private BroadcastReceiver screenReceiver = new BroadcastReceiver() {

		@SuppressWarnings("static-access")
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			Log.e(TAG, "intent.action = " + action);

			if (action.equals("android.intent.action.SCREEN_OFF")) {
				//打开主界
				startActivity(toMainIntent);
			}
		}
	};

}
