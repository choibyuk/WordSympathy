package kr.or.jaspersoft.android.wordsympathy.common.dialog;

import kr.or.jaspersoft.android.wordsympathy.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;

/**
 * <pre>
 * ###############################################################################
 * �۾� ���� ���� ���α׷����� ��ȭâ
 * ����: http://stackoverflow.com/questions/3225889/how-to-center-progress-indicator-in-progressdialog-easily-when-no-title-text-pa
 * ###############################################################################
 * </pre>
 */
public final class TaskDoingProgress {
	
	private static MafiaProgressDialog __progress__;
	
	// prevent to construct
	private TaskDoingProgress() {}
	
	/**
	 * �۾� ���� ���� ���α׷����� ���̱�
	 * @param context
	 * @return
	 */
	public static Dialog show(Context context) {
		
		if (__progress__ == null) {
			__progress__ = new MafiaProgressDialog(context);
			ProgressBar progressBar = new ProgressBar(context);
			ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			__progress__.addContentView(progressBar, lp);
			__progress__.setCancelable(true);
			__progress__.setOnCancelListener(new DialogInterface.OnCancelListener() {
				@Override
				public void onCancel(DialogInterface dialog) {
					__progress__.dismiss();
					__progress__ = null;
				}
			});
		}
		__progress__.show();
		return __progress__;
	}
	
	/**
	 * �۾� ���� ���� ���α׷����� �����
	 */
	public static void hide() {
		
		if (__progress__ != null) {
			__progress__.dismiss();
			__progress__ = null;
		}
	}

	/**
	 * <pre>
	 * ###########################################################################
	 * ���α׷��� ��ȭâ ���� ���� Ŭ����
	 * ###########################################################################
	 * </pre>
	 */
	private static class MafiaProgressDialog extends Dialog {
		
		public MafiaProgressDialog(Context context) {
			super(context, R.style.Task_Doing_Dialog);
		}
	}
}
