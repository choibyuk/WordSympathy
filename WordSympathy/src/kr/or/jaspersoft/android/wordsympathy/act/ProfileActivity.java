package kr.or.jaspersoft.android.wordsympathy.act;

import kr.or.jaspersoft.android.wordsympathy.R;
import kr.or.jaspersoft.android.wordsympathy.common.act.BaseActivity;
import android.os.Bundle;

/**
 * <pre>
 * ###############################################################################
 * 사용자 프로필 페이지
 * ###############################################################################
 * </pre>
 */
public class ProfileActivity extends BaseActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_profile);
    }
}
