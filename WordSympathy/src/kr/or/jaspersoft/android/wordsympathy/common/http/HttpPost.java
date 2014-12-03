package kr.or.jaspersoft.android.wordsympathy.common.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import kr.or.jaspersoft.android.wordsympathy.R;
import kr.or.jaspersoft.android.wordsympathy.common.util.LogUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

public final class HttpPost {
	
	/**
	 * Authorization Token 사용하여 Access Token 얻어오기 (for Google)
	 * @param ctx
	 * @param authorizationToken
	 * @return
	 */
	public static Bundle getAccessTokenByAuthorizationToken4Google(Context ctx, String authorizationCode) {
		LogUtil.log("HttpPost.getAccessTokenByAuthorizationToken4Google() Start");
		Bundle result = new Bundle();
		try {
			Resources res = ctx.getResources();
			
			String url = "https://accounts.google.com/o/oauth2/token";
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("code", authorizationCode));
			params.add(new BasicNameValuePair("client_id", res.getString(R.string.google_oauth_client_id)));
			params.add(new BasicNameValuePair("client_secret", res.getString(R.string.google_oauth_client_secret)));
			params.add(new BasicNameValuePair("grant_type", "authorization_code"));
			params.add(new BasicNameValuePair("scope", ""));
			params.add(new BasicNameValuePair("redirect_uri", res.getString(R.string.google_oauth_reditect_uri)));
		
			String json = JSONClient.request(url, params);
			if (json != null)
				LogUtil.log(json);
			
			JSONObject jo = new JSONObject(json);
			result.putString("access_token", jo.optString("access_token", ""));
			result.putString("token_type", jo.optString("token_type", ""));
			result.putLong("expires_in", jo.optLong("expires_in", 0l));
			result.putString("refresh_token", jo.optString("refresh_token", ""));
			
		} catch (Exception e) {
			result = null;
			LogUtil.log("HttpPost.getAccessTokenByAuthorizationToken4Google() occured error: " + e.getMessage());
		}
		return result;
	}
	
	/**
	 * Access Token 사용하여 사용자 정보 얻어오기 (for google)
	 * @param ctx
	 * @param tokenType
	 * @param accessToken
	 * @return
	 */
	public static Bundle getUserInfoByAccessToken4Google(Context ctx, String tokenType, String accessToken) {
		LogUtil.log("HttpPost.getUserInfoByAccessToken4Google() Start");
		Bundle result = new Bundle();
		
		HttpURLConnection conn = null;
		BufferedReader br = null;
		try {
			URL url = new URL("https://www.googleapis.com/oauth2/v2/userinfo");
			conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty("Accept", "application/json");
		    conn.setRequestProperty("Authorization", tokenType + " " + accessToken);
		    
		    if (conn.getResponseCode() != 200) {
		    	LogUtil.log("Failed : HTTP error code : " + conn.getResponseCode());
		    	return null;
		    }
			
		    br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		    String line = null;
	        String json = "";
	        while ((line = br.readLine()) != null) {
	        	json += line;
	        }

			if (json != null)
				LogUtil.log(json);
			
			/*
				{ 
					"id": "102902274290697903190", 
					"email": "choibyuk@gmail.com", 
					"verified_email": true, 
					"name": "Byuk-Hyun CHOI (Jasper's LAB)", 
					"given_name": "Byuk-Hyun", 
					"family_name": "CHOI", 
					"link": "https://plus.google.com/102902274290697903190", 
					"picture": "https://lh3.googleusercontent.com/-eiT1RWSNDfc/AAAAAAAAAAI/AAAAAAAAAMU/5ccsBuLTfTA/photo.jpg", 
					"gender": "male", 
					"locale": "ko"
				}
			 */
			JSONObject jo = new JSONObject(json);
			result.putString("id", jo.optString("id", ""));
			result.putString("email", jo.optString("email", ""));
			result.putString("verified_email", jo.optString("verified_email", ""));
			result.putString("name", jo.optString("name", ""));
			result.putString("given_name", jo.optString("given_name", ""));
			result.putString("family_name", jo.optString("family_name", ""));
			result.putString("link", jo.optString("link", ""));
			result.putString("picture", jo.optString("picture", ""));
			result.putString("gender", jo.optString("gender", ""));
			result.putString("locale", jo.optString("locale", ""));
			
		} catch (Exception e) {
			result = null;
			LogUtil.log("HttpPost.getUserInfoByAccessToken4Google() occured error: " + e.getMessage());
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
		return result;
	}
	
	/**
	 * Refresh Token 사용하여 Access Token 얻어오기
	 * @param ctx
	 * @param refreshToken
	 * @return
	 */
	public static Bundle getAccessTokenByRefreshToken(Context ctx, String refreshToken) {
		Bundle result = new Bundle();
		try {
			Resources res = ctx.getResources();
			
			String url = "https://accounts.google.com/o/oauth2/token";
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("refresh_token", refreshToken));
			params.add(new BasicNameValuePair("client_id", res.getString(R.string.google_oauth_client_id)));
			params.add(new BasicNameValuePair("client_secret", res.getString(R.string.google_oauth_client_secret)));
			params.add(new BasicNameValuePair("grant_type", "refresh_token"));
		
			String json = JSONClient.request(url, params);
			if (json != null)
				LogUtil.log(json);
			
			JSONObject jo = new JSONObject(json);
			result.putString("access_token", jo.optString("access_token", ""));
			result.putString("token_type", jo.optString("token_type", ""));
			result.putLong("expires_in", jo.optLong("expires_in", 0l));
			
		} catch (Exception e) {
			result = null;
			LogUtil.log("HttpPost.getAccessTokenByRefreshToken() occured error: " + e.getMessage());
		}
		return result;
	}
	
	private HttpPost() {}
}
