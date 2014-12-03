package kr.or.jaspersoft.android.wordsympathy.act;

import kr.or.jaspersoft.android.wordsympathy.R;
import kr.or.jaspersoft.android.wordsympathy.common.Constant;
import kr.or.jaspersoft.android.wordsympathy.common.act.BaseActivity;
import kr.or.jaspersoft.android.wordsympathy.common.util.ToastUtil;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * <pre>
 * ###############################################################################
 * 앱 시작 페이지
 * ###############################################################################
 * </pre>
 */
public class SplashActivity extends BaseActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        
        new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					handler.sendMessage(Message.obtain(null, Constant.HANDLER_WHAT_PROGRESS_SHOW));
					Thread.sleep(2000l);
					handler.sendMessage(Message.obtain(null, 100));
					Thread.sleep(2000l);
					handler.sendMessage(Message.obtain(null, Constant.HANDLER_WHAT_PROGRESS_HIDE));
					Thread.sleep(2000l);
				} catch (InterruptedException e) {}
				Intent next = new Intent(SplashActivity.this, SessionInspectActivity.class);
				startActivity(next);
				finish();
			}
        }).start();
    }
    
    static Handler handler = new SplashHandler();
    static class SplashHandler extends BaseHandler {
    	@Override
    	public void handleMessage(Message msg) {
    		super.handleMessage(msg);/* required */
			switch (msg.what) {
			case 100:
				ToastUtil.show(globalContext, "Handler Test");
				break;
			}
		}
    }
}
