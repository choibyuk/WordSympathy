package kr.or.jaspersoft.android.wordsympathy.common.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

public final class JSONClient {
	
	private JSONClient() {}
	
	public static String request(String url, List<NameValuePair> params) throws Exception {
        HttpClient client = new DefaultHttpClient();
        
        HttpPost post = new HttpPost(url);
        post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        
        /** 아래 코드에서  java.lang.OutOfMemoryError 발생하므로 코드 대체 */
        //return parseResponse(client.execute(post));
        HttpResponse response = client.execute(post);
        BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), HTTP.UTF_8));
        String line = null;
        String result = "";
        while ((line = bufreader.readLine()) != null) {
        	result += line;
        }
        return result;
	}
	
	@SuppressWarnings("unused")
	private static String parseResponse(HttpResponse response) throws Exception {
		BufferedInputStream stream = new BufferedInputStream(response.getEntity().getContent());
        
        int bracket_stack = -99; // ascii: '{'-123, '}'-125
        int read_count = 0;
        boolean continued = true;
        while (continued) {
        	int byt = stream.read();
        	read_count++;
        	if (byt == 123) {
        		if (bracket_stack == -99) {
        			bracket_stack = 0;
        			stream.mark(Integer.MAX_VALUE);
        		}
        		++bracket_stack;
        	}
        	if (byt == 125) {
        		--bracket_stack;
        	}
        	if (bracket_stack == 0) {
        		continued = false;
        		stream.reset();
        	}
        }
        byte[] bJSON = new byte[read_count];
        stream.read(bJSON, 0, read_count);
        stream.close();
        String tJSON = new String(bJSON);

        return "{" + tJSON.substring(0, tJSON.length()-1);
	}
}
