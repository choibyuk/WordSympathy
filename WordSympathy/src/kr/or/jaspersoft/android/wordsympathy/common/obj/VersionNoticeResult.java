package kr.or.jaspersoft.android.wordsympathy.common.obj;

/**
 * <pre>
 * ###############################################################################
 * 앱버전, 공지사항 조회
 * ###############################################################################
 * </pre>
 */
public class VersionNoticeResult extends BaseObject {

	public Result result = new Result();
	public AppVersion appVersion = new AppVersion();
	public Notice notice = new Notice();
}
