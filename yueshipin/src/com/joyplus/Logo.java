package com.joyplus;

import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.ConstantsAPI;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.ShowMessageFromWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXAppExtendObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.umeng.analytics.MobclickAgent;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Logo extends Activity{
	private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;
	// IWXAPI 微信实例
    private IWXAPI api;
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MobclickAgent.onError(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 不显示标题
		setContentView(R.layout.logo);// 显示welcom.xml
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // 设置全屏显示
		//微信实例
		api = WXAPIFactory.createWXAPI(this, Constant.APP_ID, false);
//		api.handleIntent(getIntent(), this);
		
		final Intent intent = new Intent(Logo.this, Main.class);// AndroidMainScreen为主界面
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				startActivity(intent);
				finish();
			}
		};
		timer.schedule(task, 1500); // 显示Logo图片2S后，自动跳转到主界面
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
	
}