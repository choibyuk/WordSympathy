package kr.or.jaspersoft.android.wordsympathy.common.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * <pre>
 * ###############################################################################
 * Toast ��ƿ Ŭ����
 * ###############################################################################
 * </pre>
 */
public final class ToastUtil {

	/**
	 * �佺Ʈ �޽��� ���̱�
	 * @param ctx
	 * @param resId
	 */
	public static void show(Context ctx, int resId) {
		String text = ctx.getResources().getString(resId);
		show(ctx, text);
	}
	
	/**
	 * �佺Ʈ �޽��� ���̱�
	 * @param ctx
	 * @param resId
	 * @param lengthType
	 */
	public static void show(Context ctx, int resId, int lengthType) {
		String text = ctx.getResources().getString(resId);
		show(ctx, text, lengthType);
	}
	
	/**
	 * �佺Ʈ �޽��� ���̱�
	 * @param ctx
	 * @param text
	 */
	public static void show(Context ctx, CharSequence text) {
		Toast toast = Toast.makeText(ctx, text, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	
	/**
	 * �佺Ʈ �޽��� ���̱�
	 * @param ctx
	 * @param text
	 * @param lengthType
	 */
	public static void show(Context ctx, CharSequence text, int lengthType) {
		Toast toast = Toast.makeText(ctx, text, lengthType);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
