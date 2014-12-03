package kr.or.jaspersoft.android.wordsympathy.common.util;

public class KoreanUtil {
	private static final char[] CHOSEONG = {
		'��','��','��','��','��','��','��','��','��','��',
		'��','��','��','��','��','��','��','��','��'
	};
	private static final char[] JUNGSEONG = {
		'��','��','��','��','��','��','��','��','��','��',
		'��','��','��','��','��','��','��','��','��','��',
		'��'
	};
	private static final char[] JONGSEONG = {
		'\0','��','��','��','��','��','��','��','��','��',
		'��','��','��','��','��','��','��','��','��','��',
		'��','��','��','��','��','��','��','��'
	};
	private static final int JUNG_JONG = JUNGSEONG.length * JONGSEONG.length;
	
	public static char[] decompose(String word) {
		char[] result = new char[0];
		for (int i=0; i<word.length(); i++) {
			char c = word.charAt(i);
			char[] chars = decompose(c);
			;
			int resultSize = result.length;
			int charsSize = chars.length;
			;
			char[] temp = new char[resultSize + charsSize];
			if (resultSize > 0) {
				System.arraycopy(result, 0, temp, 0, resultSize);
				System.arraycopy(chars, 0, temp, resultSize, chars.length);	
			} else {
				System.arraycopy(chars, 0, temp, 0, chars.length);
			}
			;
			result = new char[temp.length];
			System.arraycopy(temp, 0, result, 0, temp.length);
		}
		return result;
	}
	
	/**
	 * �ѱ� �ѱ��ڸ� �ʼ�/�߼�/������ �迭�� ����� ��ȯ�Ѵ�.
	 * @param c
	 * @return
	 */
	public static char[] decompose(char c) {
		char[] result = null;
		if (c > 0xD7A3 || c < 0xAC00) {
			return new char[]{c};
		}
		c -= 0xAC00;
		char choseong = CHOSEONG[c / JUNG_JONG];
		c = (char) (c % JUNG_JONG);
		char jungseong = JUNGSEONG[c / JONGSEONG.length];
		char jongseong = JONGSEONG[c % JONGSEONG.length];
		
		if (jongseong != 0) {
			result = new char[] {choseong, jungseong, jongseong};
		} else {
			result = new char[] {choseong, jungseong};			
		}
		return result;
	}	
	
	public static char compound(int first, int middle, int last) {		
		return (char)(0xAC00 + first* JUNG_JONG + middle * JONGSEONG.length + last);
	}
	

	public static char makeChar(char ch, int mdl, int last) {		
		ch -= 0xAC00;		
		int first = ch/JUNG_JONG;		 
		return compound(first,mdl,last);
	}
	
	public static char makeChar(char ch, int last) {
		ch -= 0xAC00;		
		int first = ch/JUNG_JONG;	
		ch = (char)(ch % JUNG_JONG);
		int middle = ch/JONGSEONG.length;
		return compound(first,middle,last);		
	}
	
	public static char replaceJongsung(char dest, char source) {
		source -= 0xAC00;		
		int last = source % JONGSEONG.length;
		return makeChar(dest,last);	
	}
}
