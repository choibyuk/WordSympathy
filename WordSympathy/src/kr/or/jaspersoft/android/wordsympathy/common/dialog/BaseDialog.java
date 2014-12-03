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
	
	/** 대화창 제목 */
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
	 * 대화창 만들기
	 */
	private void inflateDialog() {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View dialogWrapper = inflater.inflate(R.layout.dialog_common_wrapper, null);

		// 대화창 제목
		txtTitle = (TextView) dialogWrapper.findViewById(R.id.txt_dialog_common_title);
		txtTitle.setText(dialogTitle);
		
		// 이벤트 붙이기
		ImageButton btnClose = (ImageButton) dialogWrapper.findViewById(R.id.btn_dialog_common_close_xxx);
		btnClose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 닫기 전처리 수행
				boolean canClose = onPreCloseDialog();
				// 대화창 닫기
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
	 * 대화창 제목 설정
	 * @return
	 */
	protected abstract String whatDialogTitle();
	
	/**
	 * 대화창의 UI를 결정하는 레이아웃 ID 설정
	 * @return
	 */
	protected abstract int whatDialogContentViewResId();

	/**
	 * <pre>
	 * dialogWrapper에 달린 닫기 버튼을 클릭했을 때
	 * 대화창을 닫으면서 실행할 전처리를 구현할 수 있다.
	 * </pre>
	 * @return 대화창을 닫아도 되면 true, 닫기 금지 false
	 */
	protected boolean onPreCloseDialog() {
		return true;
	}
}
