package kr.or.jaspersoft.android.wordsympathy.common.obj;

import kr.or.jaspersoft.android.wordsympathy.common.Constant;

/**
 * <pre>
 * ###############################################################################
 * 사용자 프로필
 * ###############################################################################
 * </pre>
 */
public class Profile extends BaseObject {

	public String authPath = Constant.AUTH_TYPE_GOOGLE;
	public long authId = 0l;
	public String name = "";
	public String nickname = "";
	public String email = "";
	public String imgUrl = "";
	public String webUrl = "";
	public String gender = Constant.GENDER_UNKNOWN;
	public String createDt = "";
	public String updateDt = "";
}
