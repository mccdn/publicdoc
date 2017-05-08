import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Sample {

    public static void main(String[] args) throws Exception {
    	String reuqestUrl = "-- your request api url --";
    	String keyID = "-- your request api key id --";
    	String keyValue = "-- your request api key value";
    	String requestMethod = "-- your request method --".toUpperCase();
    	
    	ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
    	String requestTime = utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    	
    	URL url = new URL(reuqestUrl);    	  	
    	HttpURLConnection httpUrlConnection = (HttpURLConnection)url.openConnection();    	
    	httpUrlConnection.setRequestMethod(requestMethod);
    	httpUrlConnection.setRequestProperty("Content-type", "application/json");
    	httpUrlConnection.setRequestProperty("Authorization", calculateAuthorizationHeader(reuqestUrl, requestTime, keyID, keyValue, requestMethod));
    	httpUrlConnection.setRequestProperty("x-azurecdn-request-date", requestTime);
    	
    	InputStream _is;
    	if (httpUrlConnection.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
    	    _is = httpUrlConnection.getInputStream();
    	} else {
    	     /* error from server */
    	    _is = httpUrlConnection.getErrorStream();
    	}
           	
    	BufferedReader bufferReader = new BufferedReader(new InputStreamReader(_is, "utf-8"));   	
    	String str = "";
    	String line = "";
    	while((line = bufferReader.readLine()) != null) {
    		str += line;
    	}
    	
    	System.out.println(str);
    }
    
    private static String calculateAuthorizationHeader(String requestURL, String requestTimestamp, String keyID, String keyValue, String httpMethod) throws Exception {
    	URL url = new URL(requestURL);
    	String path = url.getPath();
    	
    	// Get query parameters
    	String query = url.getQuery();    	
    	String[] params = query.split("&");
    	Map<String, String> paramMap = new TreeMap<String, String>();
    	for(String param: params) {
    		String[] paramterParts = param.split("=");
    		if(paramterParts.length != 2) {
    			continue;
    		}
    		
    		paramMap.put(paramterParts[0], paramterParts[1]);
    	}
    	
    	String orderedQueries = paramMap.entrySet()
						    	        .stream()
						    	        .map(entry -> entry.getKey() + ":" + entry.getValue())
						    	        .collect(Collectors.joining(", "));
    	
    	String content = String.format("%s\r\n%s\r\n%s\r\n%s", path, orderedQueries, requestTimestamp, httpMethod);    	
    	Mac sha256HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(keyValue.getBytes(), "HmacSHA256");
        sha256HMAC.init(secret_key);
        byte[] bytes = sha256HMAC.doFinal(content.getBytes());
        StringBuffer hash = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
          String hex = Integer.toHexString(0xFF & bytes[i]);
          if (hex.length() == 1) {
            hash.append('0');
          }
          hash.append(hex);
        }

    	return String.format("AzureCDN %s:%s", keyID, hash.toString().toUpperCase());
    }
}

