package kr.or.jaspersoft.android.wordsympathy.dialog;

import java.util.List;
import java.util.Map;

import kr.or.jaspersoft.android.wordsympathy.R;
import kr.or.jaspersoft.android.wordsympathy.common.Constant;
import kr.or.jaspersoft.android.wordsympathy.common.dialog.BaseDialog;
import kr.or.jaspersoft.android.wordsympathy.common.ref.UserInfoManager;
import kr.or.jaspersoft.android.wordsympathy.common.util.LogUtil;
import kr.or.jaspersoft.android.wordsympathy.common.util.StringUtil;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * <pre>
 * ###############################################################################
 * 트위터 인증 대화창
 * ###############################################################################
 * </pre>
 */
public class AuthorizationDialog_OAuth2_Twitter extends BaseDialog {
	
	static WebView wbOauth;
	static Twitter twitter;
	static RequestToken requestToken;
	final static String CALLBACK_URL = "http://localhost";
	final static String CALLBACK_ACCEPT = "/?oauth_token";
	final static String CALLBACK_DENIED = "/?denied";
	
	public AuthorizationDialog_OAuth2_Twitter(Context context) {
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
		String twitterKey = res.getString(R.string.twitter_api_key);
        String twitterSecret = res.getString(R.string.twitter_api_secret);
        twitter = new TwitterFactory().getInstance();
    	twitter.setOAuthConsumer(twitterKey, twitterSecret);
		
		wbOauth = (WebView) findViewById(R.id.authWebView);
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
			public void onPageStarted(final WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				LogUtil.log("onPageStarted(): " + url);
				
				if ("https://twitter.com/intent/sessions".equals(url)) {
					if (openerHandler != null) {
						openerHandler.postDelayed(new Runnable() {
							@Override
							public void run() {
								openerHandler.sendMessage(Message.obtain(null, Constant.HANDLER_WHAT_AUTH_TWITTER_RECALL));
								AuthorizationDialog_OAuth2_Twitter.this.dismiss();
							}
						}, 1000l);
					}
				}
				
				if (url.startsWith("https://twitter.com/login/error")) {
					AuthorizationDialog_OAuth2_Twitter.this.dismiss();
				}
			}
			
			@Override
    		public boolean shouldOverrideUrlLoading(WebView view, String url) {
				LogUtil.log("shouldOverrideUrlLoading(): " + url);
    			view.loadUrl(url);
    			return true;
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				LogUtil.log("onPageFinished(): " + url);
				
				if (!StringUtil.isNull(url) && url.startsWith(CALLBACK_URL+CALLBACK_ACCEPT)) {
					Map<String, List<String>> params = StringUtil.getQueryParams(url);
					if (params != null) {
						List<String> oauthVerifiers = params.get("oauth_verifier");
						String oauthVerifier = oauthVerifiers.get(0);
						try {
							AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, oauthVerifier);
							String token = accessToken.getToken();
							String tokenSecret = accessToken.getTokenSecret();
							long userId = accessToken.getUserId();
							LogUtil.log("token: "+token+", tokenSecret: "+tokenSecret+", userId: "+userId);
						} catch (TwitterException e1) {
							e1.printStackTrace();
						}
						;
			            User user = null;
						try {
							user = twitter.verifyCredentials();
						} catch (TwitterException e) {
							e.printStackTrace();
						}
						if (user == null) {
							LogUtil.log("User is null.");
							return;
						}
						;
			            UserInfoManager userInfoManager = UserInfoManager.get();
						userInfoManager.setAuthType(globalContext, Constant.AUTH_TYPE_TWITTER);
						userInfoManager.setAuthId(globalContext, String.valueOf(user.getId()));
						userInfoManager.setEmail(globalContext, "");
						userInfoManager.setName(globalContext, StringUtil.nvl(user.getName()));
						userInfoManager.setNickname(globalContext, StringUtil.nvl(user.getName()));
						userInfoManager.setGender(globalContext, Constant.GENDER_UNKNOWN);
						userInfoManager.setImgUrl(globalContext, StringUtil.nvl(user.getProfileImageURL()));
						userInfoManager.setWebUrl(globalContext, StringUtil.nvl(user.getURL()));
						
						// 종료
						if (openerHandler != null) {
							openerHandler.sendMessage(Message.obtain(null, Constant.HANDLER_WHAT_AUTH_TWITTER_SUCCESS));
						}
						AuthorizationDialog_OAuth2_Twitter.this.dismiss();
					}
				} else if (!StringUtil.isNull(url) && url.startsWith(CALLBACK_URL+CALLBACK_DENIED)) {
					//말뭉치 권한 승인 거절
					AuthorizationDialog_OAuth2_Twitter.this.dismiss();
				}
				
			}
		});
		
		// load oauth page
		loadTwitterOauthUrl();
	}
	
	private void loadTwitterOauthUrl() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if (openerHandler != null) {
					openerHandler.sendMessage(Message.obtain(null, Constant.HANDLER_WHAT_PROGRESS_SHOW));
				}
				try {
					requestToken = twitter.getOAuthRequestToken(CALLBACK_URL);
					String authorizationUrl = requestToken.getAuthorizationURL();
					LogUtil.log("authorizationUrl: "+authorizationUrl);
					wbOauth.loadUrl(authorizationUrl);
				} catch (TwitterException e) {
					e.printStackTrace();
				}		
				if (openerHandler != null) {
					openerHandler.sendMessage(Message.obtain(null, Constant.HANDLER_WHAT_PROGRESS_HIDE));
				}
			}
		}).start();
	}
	
	@Override
	protected String whatDialogTitle() {
		return getContext().getResources().getString(R.string.title_authorization);
	}
	
	@Override
	protected int whatDialogContentViewResId() {
		return R.layout.dialog_auth_twitter_oauth;
	}
	
	@Override
	protected boolean onPreCloseDialog() {
		return super.onPreCloseDialog();
	}
}
