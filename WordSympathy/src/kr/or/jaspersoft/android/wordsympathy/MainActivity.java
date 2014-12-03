package kr.or.jaspersoft.android.wordsympathy;

import kr.or.jaspersoft.android.wordsympathy.act.AuthenticationActivity;
import kr.or.jaspersoft.android.wordsympathy.common.act.BaseActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends BaseActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent auth = new Intent(this, AuthenticationActivity.class);
        startActivity(auth);
        finish();
    }
}
