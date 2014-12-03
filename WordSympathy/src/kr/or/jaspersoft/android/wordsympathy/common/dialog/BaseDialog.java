package kr.or.jaspersoft.android.wordsympathy.common.dialog;

import kr.or.jaspersoft.android.wordsympathy.R;
import kr.or.jaspersoft.android.wordsympathy.common.util.LogUtil;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * <pre>
 * ###############################################################################
 * Dialog
 * ###############################################################################
 * </pre>
 */
public abstract class BaseDialog extends Dialog {
	
	protected Context globalContext;
	
	/** ��ȭâ ���� */
	private String dialogTitle;
	
	/**Content View Resource ID for this Dialog */
	private Integer dialogContentViewResId;
	
	/** Activity handler that open this dialog */
	protected Handler openerHandler;
	
	/** Dialog Title */
	protected TextView txtTitle;
	
	@SuppressLint("InlinedApi")
	public BaseDialog(Context context) {
		super(context, R.style.Theme_Dialog);
	}
	
	public BaseDialog(Context context, int theme) {
		super(context, theme);
	}
	
	public void setHandler(Handler handler) {
		this.openerHandler = handler;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		globalContext = getContext();
		dialogTitle = whatDialogTitle();
		dialogContentViewResId = Integer.valueOf(whatDialogContentViewResId());
		setCancelable(false);
		inflateDialog();
		LogUtil.log(this.getClass().getName() + ".onCreate()");
	}
	
	/**
	 * ��ȭâ �����
	 */
	private void inflateDialog() {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View dialogWrapper = inflater.inflate(R.layout.dialog_common_wrapper, null);

		// ��ȭâ ����
		txtTitle = (TextView) dialogWrapper.findViewById(R.id.txt_dialog_common_title);
		txtTitle.setText(dialogTitle);
		
		// �̺�Ʈ ���̱�
		ImageButton btnClose = (ImageButton) dialogWrapper.findViewById(R.id.btn_dialog_common_close_xxx);
		btnClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// �ݱ� ��ó�� ����
				boolean canClose = onPreCloseDialog();
				// ��ȭâ �ݱ�
				if (canClose)
					BaseDialog.this.dismiss();
			}
		});
		
		LinearLayout.LayoutParams contentLayout = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		contentLayout.setMargins(10, 14, 28, 14);
		LinearLayout contentArea = (LinearLayout) dialogWrapper.findViewById(R.id.dialog_content_area_aaa);
		View dialogContent = inflater.inflate(dialogContentViewResId, null);
		contentArea.addView(dialogContent, contentLayout);
		
		RelativeLayout.LayoutParams wrapperLayout = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		setContentView(dialogWrapper, wrapperLayout);
	}
	
	/**
	 * ��ȭâ ���� ����
	 * @return
	 */
	protected abstract String whatDialogTitle();
	
	/**
	 * ��ȭâ�� UI�� �����ϴ� ���̾ƿ� ID ����
	 * @return
	 */
	protected abstract int whatDialogContentViewResId();

	/**
	 * <pre>
	 * dialogWrapper�� �޸� �ݱ� ��ư�� Ŭ������ ��
	 * ��ȭâ�� �����鼭 ������ ��ó���� ������ �� �ִ�.
	 * </pre>
	 * @return ��ȭâ�� �ݾƵ� �Ǹ� true, �ݱ� ���� false
	 */
	protected boolean onPreCloseDialog() {
		return true;
	}
}
