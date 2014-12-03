package kr.or.jaspersoft.android.wordsympathy.act;

import kr.or.jaspersoft.android.wordsympathy.R;
import kr.or.jaspersoft.android.wordsympathy.common.act.BaseActivity;
import android.os.Bundle;

/**
 * <pre>
 * ###############################################################################
 * 단어 제시 페이지
 * ###############################################################################
 * </pre>
 */
public class WordsPresentActivity extends BaseActivity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_words_present);
    }
}
