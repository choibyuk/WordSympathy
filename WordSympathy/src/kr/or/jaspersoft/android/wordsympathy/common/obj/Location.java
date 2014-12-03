package kr.or.jaspersoft.android.wordsympathy.common.obj;

/**
 * <pre>
 * ###############################################################################
 * ÁÂÇ¥
 * ###############################################################################
 * </pre>
 */
public class Location extends BaseObject {

	public int x = 0;
	public int y = 0;
	
	public Location() {
		new Location(0, 0);
	}
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
