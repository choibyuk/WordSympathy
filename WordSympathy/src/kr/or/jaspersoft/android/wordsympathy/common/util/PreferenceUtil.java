package kr.or.jaspersoft.android.wordsympathy.common.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * <pre>
 * ###############################################################################
 * Preference �����ϴ� ��ƿ Ŭ����
 * ###############################################################################
 * </pre>
 */
public final class PreferenceUtil {

	/**
	 * ����
	 * @param ctx
	 * @param prefName
	 * @param key
	 * @param value
	 */
	public static void save(Context ctx, String prefName, String key, Object value) {
		SharedPreferences prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		if (value instanceof String) {
			editor.putString(key, (String) value);	
		} else if (value instanceof Long) {
			editor.putLong(key, (Long) value);	
		} else if (value instanceof Float) {
			editor.putFloat(key, (Float) value);	
		} else if (value instanceof Integer) {
			editor.putInt(key, (Integer) value);	
		} else if (value instanceof Boolean) {
			editor.putBoolean(key, (Boolean) value);	
		}
		editor.commit();
	}

	/**
	 * �б�
	 * @param ctx
	 * @param prefName
	 * @param key
	 * @return String Ÿ���� ��
	 */
	public static String readString(Context ctx, String prefName, String key) {
		SharedPreferences prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);
		String value = prefs.getString(key, "");
		return value;
	}
	
	/**
	 * �б�
	 * @param ctx
	 * @param prefName
	 * @param key
	 * @return long Ÿ���� ��
	 */
	public static long readLong(Context ctx, String prefName, String key) {
		SharedPreferences prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);
		long value = prefs.getLong(key, 0l);
		return value;
	}
	
	/**
	 * �б�
	 * @param ctx
	 * @param prefName
	 * @param key
	 * @return float Ÿ���� ��
	 */
	public static float readFloat(Context ctx, String prefName, String key) {
		SharedPreferences prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);
		float value = prefs.getFloat(key, 0f);
		return value;
	}
	
	/**
	 * �б�
	 * @param ctx
	 * @param prefName
	 * @param key
	 * @return int Ÿ���� ��
	 */
	public static int readInt(Context ctx, String prefName, String key) {
		SharedPreferences prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);
		int value = prefs.getInt(key, 0);
		return value;
	}
	
	/**
	 * �б�
	 * @param ctx
	 * @param prefName
	 * @param key
	 * @return boolean Ÿ���� ��
	 */
	public static boolean readBoolean(Context ctx, String prefName, String key) {
		SharedPreferences prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);
		boolean value = prefs.getBoolean(key, false);
		return value;
	}
}
