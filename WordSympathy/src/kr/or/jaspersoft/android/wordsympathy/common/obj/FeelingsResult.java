package kr.or.jaspersoft.android.wordsympathy.common.obj;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * ###############################################################################
 * 느낌글 목록 조회
 * ###############################################################################
 * </pre>
 */
public class FeelingsResult extends BaseObject {

	public Result result = new Result();
	public Word word = new Word();
	public List<Feeling> feelings = new ArrayList<Feeling>();
}
