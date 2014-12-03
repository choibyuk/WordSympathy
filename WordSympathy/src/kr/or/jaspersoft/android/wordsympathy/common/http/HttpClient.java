package kr.or.jaspersoft.android.wordsympathy.common.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import kr.or.jaspersoft.android.wordsympathy.common.Constant;
import kr.or.jaspersoft.android.wordsympathy.common.obj.Feeling;
import kr.or.jaspersoft.android.wordsympathy.common.obj.FeelingResult;
import kr.or.jaspersoft.android.wordsympathy.common.obj.FeelingsResult;
import kr.or.jaspersoft.android.wordsympathy.common.obj.Paging;
import kr.or.jaspersoft.android.wordsympathy.common.obj.Result;
import kr.or.jaspersoft.android.wordsympathy.common.obj.Symp;
import kr.or.jaspersoft.android.wordsympathy.common.obj.SympsResult;
import kr.or.jaspersoft.android.wordsympathy.common.obj.VersionNoticeResult;
import kr.or.jaspersoft.android.wordsympathy.common.obj.Word;
import kr.or.jaspersoft.android.wordsympathy.common.obj.WordsResult;
import kr.or.jaspersoft.android.wordsympathy.common.ref.UserInfoManager;
import kr.or.jaspersoft.android.wordsympathy.common.util.LogUtil;
import kr.or.jaspersoft.android.wordsympathy.common.util.StringUtil;

import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

/**
 * <pre>
 * ###############################################################################
 * Http Client for jaspersoft Server
 * ###############################################################################
 * </pre>
 */
public final class HttpClient {
	
	/** 프로필 관련 연동 모음 */
	public static class Profile {
		
		/** 프로필 등록 */
		public static Result regist(Context ctx) {
			Result result = new Result();
			
			try {
				String servletPath = "/profile/regist.do";
				
				UserInfoManager manager = UserInfoManager.get();
				String authPath = manager.getAuthType(ctx);
				String email = manager.getEmail(ctx);
				String name = manager.getName(ctx);
				String nickname = manager.getNickname(ctx);
				String imgUrl = manager.getImgUrl(ctx);
				String webUrl = manager.getWebUrl(ctx);
				String gender = manager.getGender(ctx);
				
				StringBuilder params = new StringBuilder();
				params.append("auth_path=").append(authPath);
				params.append("&email=").append(email);
				params.append("&name=").append(name);
				params.append("&nickname=").append(nickname);
				params.append("&img_url=").append(imgUrl);
				params.append("&web_url=").append(webUrl);
				params.append("&gender=").append(gender);
				
				String json = request(ctx, servletPath, params.toString());
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					result.code = resultValue;
				}
				
			} catch (Exception e) {
				LogUtil.log("HttpClient.Profile.regist() occured error: " + e.getMessage());
			}
			
			return result;
		}
		
		/** 프로필 수정 */
		public static Result update(Context ctx) {
			Result result = new Result();
			
			try {
				String servletPath = "/profile/update.do";
				
				UserInfoManager manager = UserInfoManager.get();
				String authPath = manager.getAuthType(ctx);
				String email = manager.getEmail(ctx);
				String name = manager.getName(ctx);
				String nickname = manager.getNickname(ctx);
				String imgUrl = manager.getImgUrl(ctx);
				String webUrl = manager.getWebUrl(ctx);
				String gender = manager.getGender(ctx);
				
				StringBuilder params = new StringBuilder();
				params.append("auth_path=").append(authPath);
				params.append("&email=").append(email);
				params.append("&name=").append(name);
				params.append("&nickname=").append(nickname);
				params.append("&img_url=").append(imgUrl);
				params.append("&web_url=").append(webUrl);
				params.append("&gender=").append(gender);
				
				String json = request(ctx, servletPath, params.toString());
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					result.code = resultValue;
				}
				
			} catch (Exception e) {
				LogUtil.log("HttpClient.Profile.update() occured error: " + e.getMessage());
			}
			
			return result;
		}
	}
	
	
	/** 단어 관련 연동 모음 */
	public static class Words {
		
		/** 제시 단어 조회 */
		public static WordsResult present(Context ctx) {
			WordsResult wordsResult = new WordsResult();
			
			try {
				String servletPath = "/words/present.do";
				
				String json = request(ctx, servletPath, "");
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					wordsResult.result.code = resultValue;
				}
				if (Constant.SUCCESS.equals(wordsResult.result.code)) {
					JSONArray jaWords = jo.getJSONArray("words");
					for (int i=0; i<jaWords.length(); i++) {
						JSONObject joWord = jaWords.getJSONObject(i);
						Word word = new Word();
						word.wordCategory = joWord.getString("category_code");
						word.wordId = joWord.getLong("word_id");
						word.word = joWord.getString("word");
						wordsResult.words.add(word);
					}
				}
				
			} catch (Exception e) {
				wordsResult.result.code = Constant.FAIL;
				LogUtil.log("HttpClient.Words.present() occured error: " + e.getMessage());
			}
			
			return wordsResult;
		}
		
		/** 단어 찾기 */
		public static WordsResult find(Context ctx, String findWord) {
			WordsResult wordsResult = new WordsResult();
			
			try {
				String servletPath = "/words/find.do";
				
				StringBuilder params = new StringBuilder();
				params.append("find_word=").append(findWord);
				
				String json = request(ctx, servletPath, params.toString());
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					wordsResult.result.code = resultValue;
				}
				if (Constant.SUCCESS.equals(wordsResult.result.code)) {
					JSONArray jaWords = jo.getJSONArray("words");
					for (int i=0; i<jaWords.length(); i++) {
						JSONObject joWord = jaWords.getJSONObject(i);
						Word word = new Word();
						word.wordCategory = joWord.getString("category_code");
						word.wordId = joWord.getLong("word_id");
						word.word = joWord.getString("word");
						wordsResult.words.add(word);
					}
				}
				
			} catch (Exception e) {
				wordsResult.result.code = Constant.FAIL;
				LogUtil.log("HttpClient.Words.find() occured error: " + e.getMessage());
			}
			
			return wordsResult;
		}
	}
	
	/** 느낌글 관련 연동 모음 */
	public static class Feels {
		
		/** 단어-느낌글 목록 조회 */
		public static FeelingsResult list(Context ctx, String wordId, Paging paging) {
			FeelingsResult feelingsResult = new FeelingsResult();
			
			try {
				String servletPath = "/feels/" + wordId + "/list.do";
				
				StringBuilder params = new StringBuilder();
				buildPagingParams(params, paging);
				
				String json = request(ctx, servletPath, params.toString());
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					feelingsResult.result.code = resultValue;
				}
				if (Constant.SUCCESS.equals(feelingsResult.result.code)) {
					JSONObject joWord = jo.getJSONObject("word");
					feelingsResult.word.wordId = joWord.getLong("word_id");
					feelingsResult.word.word = joWord.getString("word");
					feelingsResult.word.wordCategory = joWord.getString("category_code");
					;
					JSONArray jaFeels = jo.getJSONArray("feels");
					for (int i=0; i<jaFeels.length(); i++) {
						JSONObject joFeel = jaFeels.getJSONObject(i);
						Feeling feeling = new Feeling();
						feeling.feelId = joFeel.getLong("feel_id");
						feeling.content = joFeel.getString("content");
						feeling.topEmoId = joFeel.getLong("top_emo_id");
						feeling.emoCount = joFeel.getInt("emo_count");
						feeling.sympCount = joFeel.getInt("symp_count");
						feeling.createAuthId = joFeel.getLong("create_auth_id");
						feeling.createUserName = joFeel.getString("create_user_name");
						feeling.createDt = joFeel.getString("create_dt");
						feelingsResult.feelings.add(feeling);
					}
				}
				
			} catch (Exception e) {
				feelingsResult.result.code = Constant.FAIL;
				LogUtil.log("HttpClient.Feels.list() occured error: " + e.getMessage());
			}
			
			return feelingsResult;
		}
		
		/** 단어-느낌글 상세 조회 */
		public static FeelingResult detail(Context ctx, String feelId) {
			FeelingResult feelingResult = new FeelingResult();
			
			try {
				String servletPath = "/feels/" + feelId + "/detail.do";
				
				StringBuilder params = new StringBuilder("");
				
				String json = request(ctx, servletPath, params.toString());
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					feelingResult.result.code = resultValue;
				}
				if (Constant.SUCCESS.equals(feelingResult.result.code)) {
					JSONObject joWord = jo.getJSONObject("word");
					feelingResult.word.wordId = joWord.getLong("word_id");
					feelingResult.word.word = joWord.getString("word");
					feelingResult.word.wordCategory = joWord.getString("category_code");
					;
					JSONObject joFeel = jo.getJSONObject("feel");
					feelingResult.feeling.feelId = joFeel.getLong("feel_id");
					feelingResult.feeling.content = joFeel.getString("content");
					feelingResult.feeling.topEmoId = joFeel.getLong("top_emo_id");
					feelingResult.feeling.emoCount = joFeel.getInt("emo_count");
					feelingResult.feeling.sympCount = joFeel.getInt("symp_count");
					feelingResult.feeling.createAuthId = joFeel.getLong("create_auth_id");
					feelingResult.feeling.createUserName = joFeel.getString("create_user_name");
					feelingResult.feeling.createDt = joFeel.getString("create_dt");
				}
				
			} catch (Exception e) {
				feelingResult.result.code = Constant.FAIL;
				LogUtil.log("HttpClient.Feels.detail() occured error: " + e.getMessage());
			}
			
			return feelingResult;
		}
		
		/** 단어-느낌글 등록 */
		public static Result regist(Context ctx, String wordId, String content) {
			Result result = new Result();
			
			try {
				String servletPath = "/feels/" + wordId + "/regist.do";
				
				StringBuilder params = new StringBuilder();
				params.append("content=").append(content);
				
				String json = request(ctx, servletPath, params.toString());
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					result.code = resultValue;
				}
				
			} catch (Exception e) {
				LogUtil.log("HttpClient.Feels.regist() occured error: " + e.getMessage());
			}
			
			return result;
		}
		
		/** 단어-느낌글 수정 */
		public static Result update(Context ctx, String feelId, String content) {
			Result result = new Result();
			
			try {
				String servletPath = "/feels/" + feelId + "/update.do";
				
				StringBuilder params = new StringBuilder();
				params.append("content=").append(content);
				
				String json = request(ctx, servletPath, params.toString());
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					result.code = resultValue;
				}
				
			} catch (Exception e) {
				LogUtil.log("HttpClient.Feels.update() occured error: " + e.getMessage());
			}
			
			return result;
		}
		
		/** 단어-느낌글 삭제 */
		public static Result delete(Context ctx, String feelId) {
			Result result = new Result();
			
			try {
				String servletPath = "/feels/" + feelId + "/delete.do";
				
				StringBuilder params = new StringBuilder("");
				
				String json = request(ctx, servletPath, params.toString());
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					result.code = resultValue;
				}
				
			} catch (Exception e) {
				LogUtil.log("HttpClient.Feels.delete() occured error: " + e.getMessage());
			}
			
			return result;
		}
	}
	
	/** 공감글 관련 연동 모음 */
	public static class Symps {
		
		/** 공감글 목록 조회 */
		public static SympsResult list(Context ctx, String feelId, Paging paging) {
			SympsResult sympsResult = new SympsResult();
			
			try {
				String servletPath = "/symps/" + feelId + "/list.do";
				
				StringBuilder params = new StringBuilder();
				buildPagingParams(params, paging);
				
				String json = request(ctx, servletPath, params.toString());
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					sympsResult.result.code = resultValue;
				}
				if (Constant.SUCCESS.equals(sympsResult.result.code)) {
					JSONArray jaSymps = jo.getJSONArray("symps");
					for (int i=0; i<jaSymps.length(); i++) {
						JSONObject joSymp = jaSymps.getJSONObject(i);
						Symp symp = new Symp();
						symp.sympId = joSymp.getLong("symp_id");
						symp.content = joSymp.getString("content");
						symp.createAuthId = joSymp.getLong("create_auth_id");
						symp.createUserName = joSymp.getString("create_user_name");
						symp.createDt = joSymp.getString("create_dt");
						symp.updateDt = joSymp.getString("update_dt");
						sympsResult.symps.add(symp);
					}
				}
				
			} catch (Exception e) {
				sympsResult.result.code = Constant.FAIL;
				LogUtil.log("HttpClient.Symps.list() occured error: " + e.getMessage());
			}
			
			return sympsResult;
		}
		
		/** 공감글 등록 */
		public static Result regist(Context ctx, String feelId, String content) {
			Result result = new Result();
			
			try {
				String servletPath = "/symps/" + feelId + "/regist.do";
				
				StringBuilder params = new StringBuilder();
				params.append("content=").append(content);
				
				String json = request(ctx, servletPath, params.toString());
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					result.code = resultValue;
				}
				
			} catch (Exception e) {
				LogUtil.log("HttpClient.Symps.regist() occured error: " + e.getMessage());
			}
			
			return result;
		}
		
		/** 공감글 수정 */
		public static Result update(Context ctx, String sympId, String content) {
			Result result = new Result();
			
			try {
				String servletPath = "/symps/" + sympId + "/update.do";
				
				StringBuilder params = new StringBuilder();
				params.append("content=").append(content);
				
				String json = request(ctx, servletPath, params.toString());
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					result.code = resultValue;
				}
				
			} catch (Exception e) {
				LogUtil.log("HttpClient.Symps.update() occured error: " + e.getMessage());
			}
			
			return result;
		}
		
		/** 공감글 삭제 */
		public static Result delete(Context ctx, String sympId) {
			Result result = new Result();
			
			try {
				String servletPath = "/symps/" + sympId + "/delete.do";
				
				StringBuilder params = new StringBuilder("");
				
				String json = request(ctx, servletPath, params.toString());
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					result.code = resultValue;
				}
				
			} catch (Exception e) {
				LogUtil.log("HttpClient.Symps.delete() occured error: " + e.getMessage());
			}
			
			return result;
		}
	}
	
	/** 기타 연동 모음 */
	public static class Etc {
		
		/** 앱버전-공지사항 조회 */
		public static VersionNoticeResult notice(Context ctx) {
			VersionNoticeResult versionNoticeResult = new VersionNoticeResult();
			
			try {
				String servletPath = "/etc/notice.do";
				
				StringBuilder params = new StringBuilder("");
				
				String json = request(ctx, servletPath, params.toString());
				
				JSONObject jo = new JSONObject(json);
				String resultValue = jo.getString("result");
				if (!StringUtil.isBlank(resultValue)) {
					versionNoticeResult.result.code = resultValue;
				}
				if (Constant.SUCCESS.equals(versionNoticeResult.result.code)) {
					JSONObject joVersion = jo.getJSONObject("version");
					versionNoticeResult.appVersion.verId = joVersion.getInt("ver_id");
					versionNoticeResult.appVersion.verName = joVersion.getString("ver_name");
					;
					JSONObject joNotice = jo.getJSONObject("notice");
					versionNoticeResult.notice.noticeId = joNotice.getLong("notice_id");
					versionNoticeResult.notice.title = joNotice.getString("title");
					versionNoticeResult.notice.content = joNotice.getString("content");
				}
				
			} catch (Exception e) {
				versionNoticeResult.result.code = Constant.FAIL;
				LogUtil.log("HttpClient.Etc.notice() occured error: " + e.getMessage());
			}
			
			return versionNoticeResult;
		}
	}
	
	private static StringBuilder buildPagingParams(StringBuilder builder, Paging paging) {
		if (builder.length() > 0) {
			builder.append("&");
		}
		builder.append("directive=").append(paging.directive);
		builder.append("&standard_id=").append(paging.standardId);
		builder.append("&count=").append(paging.count);
		return builder;
	}
	
	private static String request(Context ctx, String servletPath, String params) {
		HttpURLConnection conn = null;
		BufferedReader br = null;
		String json = "";
		
		try {
			String fullUrl = MY_SERVER_URL + servletPath;
			URL url = new URL(fullUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
			;
		    conn.setRequestMethod("POST");
		    conn.setRequestProperty("Accept", "application/json");
			String authId = UserInfoManager.get().getAuthId(ctx);
		    if (!StringUtil.isBlank(authId)) {
		    	conn.setRequestProperty("auth_id", authId);
		    }
		    
		    OutputStream os = conn.getOutputStream();
		    os.write(params.getBytes(HTTP.UTF_8));
		    os.flush();
		    os.close();
		    
		    if (conn.getResponseCode() != 200) {
		    	LogUtil.log("Failed : HTTP error code : " + conn.getResponseCode());
		    	return json;
		    }
			
		    br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		    String line = null;
	        while ((line = br.readLine()) != null) {
	        	json += line;
	        }
			if (json != null)
				LogUtil.log(json);
			
		} catch (Exception e) {
			json = "{\"result\":\"" + Constant.FAIL + "\"}";
			LogUtil.log("HttpClient.request() occured error: " + e.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {}
			if (conn != null) {
				conn.disconnect();
			}
		}
		
		return json;
	}
	
	/** 서버 URL */
	private static final String MY_SERVER_URL = "http://api.jaspersoft.or.kr/api/sympathy";
	private HttpClient() {}
}
