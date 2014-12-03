package kr.or.jaspersoft.android.wordsympathy.common.obj;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * ###############################################################################
 * 공감글 목록 조회
 * ###############################################################################
 * </pre>
 */
public class SympsResult extends BaseObject {

	public Result result = new Result();
	public List<Symp> symps = new ArrayList<Symp>();
}
