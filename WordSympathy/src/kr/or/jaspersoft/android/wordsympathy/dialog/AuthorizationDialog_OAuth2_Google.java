package kr.or.jaspersoft.android.wordsympathy.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.jaspersoft.android.wordsympathy.R;
import kr.or.jaspersoft.android.wordsympathy.common.Constant;
import kr.or.jaspersoft.android.wordsympathy.common.dialog.BaseDialog;
import kr.or.jaspersoft.android.wordsympathy.common.util.LogUtil;
import kr.or.jaspersoft.android.wordsympathy.common.util.StringUtil;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;

/**
 * <pre>
 * ###############################################################################
 * 구글 인증 대화창
 * ###############################################################################
 * </pre>
 */
public class AuthorizationDialog_OAuth2_Google extends BaseDialog {
	
	public AuthorizationDialog_OAuth2_Google(Context context) {
		super(context);
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		if (openerHandler != null)
			openerHandler.sendMessage(Message.obtain(null, Constant.HANDLER_WHAT_PROGRESS_SHOW));
		
		Resources res = getContext().getResources();
		final String CLIENT_ID = res.getString(R.string.google_oauth_client_id);
		final String REDIRECT_URI = res.getString(R.string.google_oauth_reditect_uri);
		final List<String> scopes = new ArrayList<String>();
		scopes.add(res.getString(R.string.google_oauth_scope_1));
		scopes.add(res.getString(R.string.google_oauth_scope_2));
		
		final WebView wbOauth = (WebView) findViewById(R.id.authWebView);
		wbOauth.getSettings().setSavePassword(false);
		wbOauth.getSettings().setSaveFormData(false);
		wbOauth.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
		wbOauth.getSettings().setJavaScriptEnabled(true);
		// important: call input method(keyboard)
		wbOauth.setOnTouchListener(new View.OnTouchListener() {
			@SuppressLint("ClickableViewAccessibility") 
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_UP:
					if (!v.hasFocus()) {
						v.requestFocus();
					}
					break;
				}
				return false;
			}
		});
		// important: response oauth 2.0
		wbOauth.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				if (url != null && url.startsWith(REDIRECT_URI)) {
					LogUtil.log("oauth response url: " + url);
					Map<String, List<String>> params = StringUtil.getQueryParams(url);
					final String CODE_PARAM_NAME = "code";
					if (params != null && params.containsKey(CODE_PARAM_NAME)) {
						List<String> authorizationCodes = params.get(CODE_PARAM_NAME);
						if (authorizationCodes != null && authorizationCodes.size() > 0) {
							// important: 인증 결과로 얻은 authorization code
							String authorizationCode = authorizationCodes.get(0);
							if (openerHandler != null)
								openerHandler.sendMessage(Message.obtain(null, Constant.HANDLER_WHAT_AUTH_GOOGLE_REQUEST, authorizationCode));
							// close dialog
							AuthorizationDialog_OAuth2_Google.this.dismiss();
						}
					}
				}
				
				if (openerHandler != null)
					openerHandler.sendMessage(Message.obtain(null, Constant.HANDLER_WHAT_PROGRESS_HIDE));
			}
		});
		
		// load oauth page
		GoogleAuthorizationCodeRequestUrl oauthBuilder = new GoogleAuthorizationCodeRequestUrl(CLIENT_ID, REDIRECT_URI, scopes);
		String oauthUrl = oauthBuilder.build();
		wbOauth.loadUrl(oauthUrl);
	}
	
	@Override
	protected String whatDialogTitle() {
		return getContext().getResources().getString(R.string.title_authorization);
	}
	
	@Override
	protected int whatDialogContentViewResId() {
		return R.layout.dialog_auth_google_oauth;
	}
	
	@Override
	protected boolean onPreCloseDialog() {
		return super.onPreCloseDialog();
	}
}
