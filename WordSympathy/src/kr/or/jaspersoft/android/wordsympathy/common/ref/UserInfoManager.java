package kr.or.jaspersoft.android.wordsympathy.common.ref;

import kr.or.jaspersoft.android.wordsympathy.common.util.PreferenceUtil;
import android.content.Context;

/**
 * <pre>
 * ###############################################################################
 * ���� ����� ���� ���� Ŭ����
 * ###############################################################################
 * </pre>
 */
public final class UserInfoManager {
	
	/** ���� ��� ���� [001;����, 002;���̽���, 003;Ʈ����] */
	public void setAuthType(Context ctx, String authType) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_AUTH_TYPE, authType);
	}
	
	/** ���� ��� ��ȸ [001;����, 002;���̽���, 003;Ʈ����] */
	public String getAuthType(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_AUTH_TYPE);
	}
	
	/** ���� ���̵� ���� */
	public void setAuthId(Context ctx, String authId) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_AUTH_ID, authId);
	}
	
	/** ���� ���̵� ��ȸ */
	public String getAuthId(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_AUTH_ID);
	}
	
	/** �̸��� ���� */
	public void setEmail(Context ctx, String email) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_EMAIL, email);
	}
	
	/** �̸��� ��ȸ */
	public String getEmail(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_EMAIL);
	}
	
	/** �̸� ���� */
	public void setName(Context ctx, String name) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_NAME, name);
	}
	
	/** �̸� ��ȸ */
	public String getName(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_NAME);
	}
	
	/** ��Ī ���� */
	public void setNickname(Context ctx, String nickname) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_NICKNAME, nickname);
	}
	
	/** ��Ī ��ȸ */
	public String getNickname(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_NICKNAME);
	}
	
	/** ���� ���� */
	public void setGender(Context ctx, String gender) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_GENDER, gender);
	}
	
	/** ���� ��ȸ */
	public String getGender(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_GENDER);
	}
	
	/** �̹��� ��� ���� */
	public void setImgUrl(Context ctx, String imgUrl) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_IMG_URL, imgUrl);
	}
	
	/** �̹��� ��� ��ȸ */
	public String getImgUrl(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_IMG_URL);
	}
	
	/** �� ��� ���� */
	public void setWebUrl(Context ctx, String webUrl) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_WEB_URL, webUrl);
	}
	
	/** �� ��� ��ȸ */
	public String getWebUrl(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_WEB_URL);
	}
	
	private final String USER_INFO_REF = "__USER_INFO_REF__";
	private final String ITEM_AUTH_TYPE = "__ITEM_AUTH_TYPE__";
	private final String ITEM_AUTH_ID = "__ITEM_AUTH_ID__";
	private final String ITEM_EMAIL = "__ITEM_EMAIL__";
	private final String ITEM_NAME = "__ITEM_NAME__";
	private final String ITEM_NICKNAME = "__ITEM_NICKNAME__";
	private final String ITEM_GENDER = "__ITEM_GENDER__";
	private final String ITEM_IMG_URL = "__ITEM_IMG_URL__";
	private final String ITEM_WEB_URL = "__ITEM_WEB_URL__";

	private static UserInfoManager _instance = new UserInfoManager();
	private UserInfoManager() {}
	/**
	 * UserInfoManager Singleton Instance
	 * @return
	 */
	public static synchronized UserInfoManager get() {
		if (_instance == null) {
			_instance = new UserInfoManager();
		}
		return _instance;
	}
}
