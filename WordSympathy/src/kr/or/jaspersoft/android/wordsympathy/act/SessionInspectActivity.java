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
 * ���� �˻� ������
 * 
 * 1. ����� ���� üũ
 * 2. �������� üũ
 * 3. �� ���� üũ
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
				// 1. ����� ���� üũ
				if (StringUtil.isBlank(UserInfoManager.get().getAuthId(getApplicationContext()))) {
					Intent next = new Intent(SessionInspectActivity.this, AuthenticationActivity.class);
					startActivity(next);
					finish();
				}
				//TODO ��������, �۹��� üũ
				else {
					Intent next = new Intent(SessionInspectActivity.this, WordsPresentActivity.class);
					startActivity(next);
					finish();
				}
			}
        }).start();
    }
}
