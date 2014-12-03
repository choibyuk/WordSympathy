package kr.or.jaspersoft.android.wordsympathy.common.util;

import android.util.Log;

public class LogUtil {

	private static final String _TAG = "WordSympathy";

	/**
	 * 개발용 로그 출력
	 * @param msg
	 */
	public static void log(String msg) {
		//앱을 마켓에 등록 시에는 아래 코드를 주석으로 막아햐 한다.
		Log.v(_TAG, msg);
	}
}
