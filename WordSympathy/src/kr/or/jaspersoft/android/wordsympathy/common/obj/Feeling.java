package kr.or.jaspersoft.android.wordsympathy.common.obj;

import java.util.ArrayList;
import java.util.List;

import kr.or.jaspersoft.android.wordsympathy.common.Constant;

/**
 * <pre>
 * ###############################################################################
 * ´À³¦±Û
 * ###############################################################################
 * </pre>
 */
public class Feeling extends BaseObject {

	public long feelId = 0l;
	public String content = "";
	public long topEmoId = 0l;
	public int emoCount = 0;
	public int sympCount = 0;
	public char delYn = Constant.NO;
	public char publicYn = Constant.YES;
	public long createAuthId = 0l;
	public String createUserName = "";
	public String createDt = "";
	public String updateDt = "";
	public List<Emotion> emotions = new ArrayList<Emotion>();
}
