package kr.or.jaspersoft.android.wordsympathy.act;

import kr.or.jaspersoft.android.wordsympathy.R;
import kr.or.jaspersoft.android.wordsympathy.common.Constant;
import kr.or.jaspersoft.android.wordsympathy.common.act.BaseActivity;
import kr.or.jaspersoft.android.wordsympathy.common.http.HttpPost;
import kr.or.jaspersoft.android.wordsympathy.common.ref.UserInfoManager;
import kr.or.jaspersoft.android.wordsympathy.common.util.StringUtil;
import kr.or.jaspersoft.android.wordsympathy.common.util.ToastUtil;
import kr.or.jaspersoft.android.wordsympathy.dialog.AuthorizationDialog_OAuth2_Google;
import kr.or.jaspersoft.android.wordsympathy.dialog.AuthorizationDialog_OAuth2_Twitter;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

/**
 * <pre>
 * ###############################################################################
 * ����� ���� ������
 * ###############################################################################
 * </pre>
 */
public class AuthenticationActivity extends BaseActivity {
	
	static AuthenticationActivity thisAct;
	static Button btnAuthGoogle;
	static Button btnAuthFacebook;
	static Button btnAuthTwitter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_authentication);
		
		thisAct = this;
		
		/* ���� ���� �� ����� ���� ������ */
		btnAuthGoogle = (Button) findViewById(R.id.btn_auth_google);
		btnAuthGoogle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AuthorizationDialog_OAuth2_Google googleAuthDialog = new AuthorizationDialog_OAuth2_Google(globalContext);
				googleAuthDialog.setHandler(handler);
				googleAuthDialog.show();
			}
		});
		
		/* ���̽��� ���� �� ����� ���� ������ */
		btnAuthFacebook = (Button) findViewById(R.id.btn_auth_facebook);
		btnAuthFacebook.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Session.openActiveSession(AuthenticationActivity.this, true, new Session.StatusCallback() {
					//callback when session changes state
		        	@SuppressWarnings("deprecation")
					@Override
					public void call(Session session, SessionState state, Exception exception) {
						if (session.isOpened()) {
							//make request to the /me API
							Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
								//callback after Graph API response with user object
								@Override
								public void onCompleted(GraphUser user, Response response) {
									if (user != null) {
										UserInfoManager userInfoManager = UserInfoManager.get();
										JSONObject jObj = user.getInnerJSONObject();
										String gender = "";
										try {
											gender = StringUtil.nvl(jObj.getString("gender"));
										} catch (JSONException e) {}
										String g = Constant.GENDER_UNKNOWN;
										if ("male".equalsIgnoreCase(gender)) {
											g = Constant.GENDER_MALE;
										} else if ("female".equalsIgnoreCase(gender)) {
											g = Constant.GENDER_FEMALE;
										}
										userInfoManager.setAuthType(globalContext, Constant.AUTH_TYPE_FACEBOOK);
										userInfoManager.setAuthId(globalContext, StringUtil.nvl(user.getId()));
										userInfoManager.setEmail(globalContext, "");
										userInfoManager.setName(globalContext, StringUtil.nvl(user.getName()));
										userInfoManager.setNickname(globalContext, StringUtil.nvl(user.getName()));
										userInfoManager.setGender(globalContext, g);
										userInfoManager.setImgUrl(globalContext, "");
										userInfoManager.setWebUrl(globalContext, StringUtil.nvl(user.getLink()));
										;
										goNextPage();
									}
								}
							});
						}
					}
				});
			}
		});
		
		/* Ʈ���� ���� �� ����� ���� ������ */
		btnAuthTwitter = (Button) findViewById(R.id.btn_auth_twitter);
		btnAuthTwitter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AuthorizationDialog_OAuth2_Twitter twitterAuthDialog = new AuthorizationDialog_OAuth2_Twitter(globalContext);
				twitterAuthDialog.setHandler(handler);
				twitterAuthDialog.show();
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }
	
	
	/** ���� ���� �� ���� ������ �̵� */
	private static void goNextPage() {
		Intent inspect = new Intent(thisAct, SessionInspectActivity.class);
		thisAct.startActivity(inspect);
		thisAct.finish();
	}
	
	static Handler handler = new AuthenticationHandler();
    static class AuthenticationHandler extends BaseHandler {
    	@Override
    	public void handleMessage(Message msg) {
    		super.handleMessage(msg);/* required */
    		final String authorizationCode = (String) msg.obj;
    		switch (msg.what) {
    		//
    		// ���� ���� Authorization Token ȹ�� �Ŀ� ����� ���� ������ 
    		//
    		case Constant.HANDLER_WHAT_AUTH_GOOGLE_REQUEST:
    			new Thread(new Runnable() {
					@Override
					public void run() {
						handler.sendMessage(Message.obtain(null, Constant.HANDLER_WHAT_PROGRESS_SHOW));
						;
						boolean success = true;
						Bundle bundle = HttpPost.getAccessTokenByAuthorizationToken4Google(globalContext, authorizationCode);
						if (bundle == null) {
							success = false;
						}
						;
						if (success) {
							String tokenType = bundle.getString("token_type");
							String accessToken = bundle.getString("access_token");
							bundle = HttpPost.getUserInfoByAccessToken4Google(globalContext, tokenType, accessToken);
							if (bundle == null) {
								success = false;
							}
						}
						;
						if (success) {
							UserInfoManager userInfoManager = UserInfoManager.get();
							String g = Constant.GENDER_UNKNOWN;
							String gender = StringUtil.nvl(bundle.getString("gender"));
							if ("male".equalsIgnoreCase(gender)) {
								g = Constant.GENDER_MALE;
							} else if ("female".equalsIgnoreCase(gender)) {
								g = Constant.GENDER_FEMALE;
							}
							userInfoManager.setAuthType(globalContext, Constant.AUTH_TYPE_GOOGLE);
							userInfoManager.setAuthId(globalContext, StringUtil.nvl(bundle.getString("id")));
							userInfoManager.setEmail(globalContext, StringUtil.nvl(bundle.getString("email")));
							userInfoManager.setName(globalContext, StringUtil.nvl(bundle.getString("name")));
							userInfoManager.setNickname(globalContext, StringUtil.nvl(bundle.getString("name")));
							userInfoManager.setGender(globalContext, g);
							userInfoManager.setImgUrl(globalContext, StringUtil.nvl(bundle.getString("picture")));
							userInfoManager.setWebUrl(globalContext, StringUtil.nvl(bundle.getString("link")));
							;
							goNextPage();
						} else {
							ToastUtil.show(globalContext, R.string.msg_auth_failed);
						}
						;
						handler.sendMessage(Message.obtain(null, Constant.HANDLER_WHAT_PROGRESS_HIDE));
					}
    			}).start();
				break;
			//
			// Ʈ���� ���� ��ȭâ ���û
			//
    		case Constant.HANDLER_WHAT_AUTH_TWITTER_RECALL:
    			handler.sendMessage(Message.obtain(null, Constant.HANDLER_WHAT_PROGRESS_SHOW));
    			;
    			try {
					Thread.sleep(2000l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				AuthorizationDialog_OAuth2_Twitter twitterAuthDialog = new AuthorizationDialog_OAuth2_Twitter(globalContext);
				twitterAuthDialog.setHandler(handler);
				twitterAuthDialog.show();
				;
				handler.sendMessage(Message.obtain(null, Constant.HANDLER_WHAT_PROGRESS_HIDE));
    			break;
    		//
    		// Ʈ���� ���� ���� 
    		//
    		case Constant.HANDLER_WHAT_AUTH_TWITTER_SUCCESS:
    			goNextPage();
    			break;
    		}
		}
    }
}
