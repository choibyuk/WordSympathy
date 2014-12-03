package kr.or.jaspersoft.android.wordsympathy.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class StringUtil {
	
	public static boolean isBlank(String src) {
		return src == null || "".equals(src);
	}
	
	public static boolean isNull(Object src) {
		return src == null;
	}
	
	public static boolean isEmpty(String src) {
		return src != null && "".equals(src);
	}
	
	public static String nvl(String src) {
		return isNull(src) ? "" : src;
	}
	
	public static String nvl(String src, String replace) {
		return isNull(src) ? replace : src;
	}

	/**
	 * <pre>
	 * URL 중에 쿼리스트링 부분을 분석하여 파라미터를 리턴 
	 * </pre>
	 * @param url
	 * @return 오류 발생 시에 null을 리턴
	 */
	public static Map<String, List<String>> getQueryParams(String url) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		try {
			String[] urlParts = url.split("\\?");
			if (urlParts.length > 1) {
				String query = urlParts[1];
				for (String param : query.split("&")) {
					String[] pair = param.split("=");
					String key = URLDecoder.decode(pair[0], "UTF-8");
					String value = "";
					if (pair.length > 1) {
						value = URLDecoder.decode(pair[1], "UTF-8");
					}

					List<String> values = params.get(key);
					if (values == null) {
						values = new ArrayList<String>();
						params.put(key, values);
					}
					values.add(value);
				}
			}

		} catch (UnsupportedEncodingException ex) {
			params = null;
		}
		
		return params;
	}
}
