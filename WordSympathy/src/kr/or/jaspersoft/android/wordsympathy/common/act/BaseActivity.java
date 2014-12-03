package kr.or.jaspersoft.android.wordsympathy.common.act;

import kr.or.jaspersoft.android.wordsympathy.common.Constant;
import kr.or.jaspersoft.android.wordsympathy.common.dialog.TaskDoingProgress;
import kr.or.jaspersoft.android.wordsympathy.common.util.LogUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class BaseActivity extends Activity {
	
	/** Context [it's truely Activity] */
	protected static Context globalContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		globalContext = this;
		LogUtil.log(this.getClass().getName() + ".onCreate()");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.log(this.getClass().getName() + ".onStart()");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		LogUtil.log(this.getClass().getName() + ".onRestart()");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.log(this.getClass().getName() + ".onResume()");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.log(this.getClass().getName() + ".onPause()");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		LogUtil.log(this.getClass().getName() + ".onStop()");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		LogUtil.log(this.getClass().getName() + ".onDestroy()");
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		LogUtil.log(this.getClass().getName() + ".onSaveInstanceState()");
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		LogUtil.log(this.getClass().getName() + ".onConfigurationChanged()");	
	}
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	LogUtil.log(this.getClass().getName() + ".onActivityResult()");
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		LogUtil.log(this.getClass().getName() + ".onNewIntent()");
	}
	
	protected static class BaseHandler extends Handler {
		@Override
    	public void handleMessage(Message msg) {
			switch (msg.what) {
			//
			// 작업 진행중 프로그레스 대화창 보이기
			//
    		case Constant.HANDLER_WHAT_PROGRESS_SHOW:
    			TaskDoingProgress.show(globalContext);
				break;
			//
			// 작업 진행중 프로그레스 대화창 숨기기
			//
    		case Constant.HANDLER_WHAT_PROGRESS_HIDE:
    			TaskDoingProgress.hide();
    			break;
			}
		}
	}
}
