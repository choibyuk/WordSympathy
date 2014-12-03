package kr.or.jaspersoft.android.wordsympathy.act;

import kr.or.jaspersoft.android.wordsympathy.R;
import kr.or.jaspersoft.android.wordsympathy.common.act.BaseActivity;
import kr.or.jaspersoft.android.wordsympathy.common.ref.UserInfoManager;
import kr.or.jaspersoft.android.wordsympathy.common.util.StringUtil;
import android.content.Intent;
import android.os.Bundle;

/**
 * <pre>
 * ###############################################################################
 * 세션 검사 페이지
 * 
 * 1. 사용자 인증 체크
 * 2. 공지사항 체크
 * 3. 앱 버전 체크
 * ###############################################################################
 * </pre>
 */
public class SessionInspectActivity extends BaseActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_session_inspect);
        
        new Thread(new Runnable() {
			@Override
			public void run() {
				// 1. 사용자 인증 체크
				if (StringUtil.isBlank(UserInfoManager.get().getAuthId(getApplicationContext()))) {
					Intent next = new Intent(SessionInspectActivity.this, AuthenticationActivity.class);
					startActivity(next);
					finish();
				}
				//TODO 공지사항, 앱버전 체크
				else {
					Intent next = new Intent(SessionInspectActivity.this, WordsPresentActivity.class);
					startActivity(next);
					finish();
				}
			}
        }).start();
    }
}
