package kr.or.jaspersoft.android.wordsympathy.common.util;

import android.util.Log;

public class LogUtil {

	private static final String _TAG = "WordSympathy";

	/**
	 * ���߿� �α� ���
	 * @param msg
	 */
	public static void log(String msg) {
		//���� ���Ͽ� ��� �ÿ��� �Ʒ� �ڵ带 �ּ����� ������ �Ѵ�.
		Log.v(_TAG, msg);
	}
}
