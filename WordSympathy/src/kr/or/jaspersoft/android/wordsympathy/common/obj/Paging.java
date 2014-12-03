package kr.or.jaspersoft.android.wordsympathy.common.obj;

import kr.or.jaspersoft.android.wordsympathy.common.Constant;

/**
 * <pre>
 * ###############################################################################
 * 페이징 처리
 * ###############################################################################
 * </pre>
 */
public class Paging extends BaseObject {

	public String directive = Constant.PAGING_DIRECTIVE_OLD;
	public long standardId = 0l;
	public int count = 10;
}
