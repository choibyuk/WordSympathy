package kr.or.jaspersoft.android.wordsympathy.common.ref;

import kr.or.jaspersoft.android.wordsympathy.common.util.PreferenceUtil;
import android.content.Context;

/**
 * <pre>
 * ###############################################################################
 * 로컬 사용자 정보 관리 클래스
 * ###############################################################################
 * </pre>
 */
public final class UserInfoManager {
	
	/** 인증 경로 저장 [001;구글, 002;페이스북, 003;트위터] */
	public void setAuthType(Context ctx, String authType) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_AUTH_TYPE, authType);
	}
	
	/** 인증 경로 조회 [001;구글, 002;페이스북, 003;트위터] */
	public String getAuthType(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_AUTH_TYPE);
	}
	
	/** 인증 아이디 저장 */
	public void setAuthId(Context ctx, String authId) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_AUTH_ID, authId);
	}
	
	/** 인증 아이디 조회 */
	public String getAuthId(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_AUTH_ID);
	}
	
	/** 이메일 저장 */
	public void setEmail(Context ctx, String email) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_EMAIL, email);
	}
	
	/** 이메일 조회 */
	public String getEmail(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_EMAIL);
	}
	
	/** 이름 저장 */
	public void setName(Context ctx, String name) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_NAME, name);
	}
	
	/** 이름 조회 */
	public String getName(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_NAME);
	}
	
	/** 별칭 저장 */
	public void setNickname(Context ctx, String nickname) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_NICKNAME, nickname);
	}
	
	/** 별칭 조회 */
	public String getNickname(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_NICKNAME);
	}
	
	/** 성별 저장 */
	public void setGender(Context ctx, String gender) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_GENDER, gender);
	}
	
	/** 성별 조회 */
	public String getGender(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_GENDER);
	}
	
	/** 이미지 경로 저장 */
	public void setImgUrl(Context ctx, String imgUrl) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_IMG_URL, imgUrl);
	}
	
	/** 이미지 경로 조회 */
	public String getImgUrl(Context ctx) {
		return PreferenceUtil.readString(ctx, USER_INFO_REF, ITEM_IMG_URL);
	}
	
	/** 웹 경로 저장 */
	public void setWebUrl(Context ctx, String webUrl) {
		PreferenceUtil.save(ctx, USER_INFO_REF, ITEM_WEB_URL, webUrl);
	}
	
	/** 웹 경로 조회 */
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
