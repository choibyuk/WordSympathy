package kr.or.jaspersoft.android.wordsympathy.common.obj;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * ###############################################################################
 * 단어 조회
 * ###############################################################################
 * </pre>
 */
public class WordsResult extends BaseObject {

	public Result result = new Result();
	public List<Word> words = new ArrayList<Word>();
}
