package com.nviso;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.File;
import org.json.JSONObject;
import org.json.JSONArray;

import com.mashape.client.authentication.Authentication;
import com.mashape.client.authentication.AuthenticationParameter;
import com.mashape.client.authentication.MashapeAuthentication;
import com.mashape.client.authentication.QueryAuthentication;
import com.mashape.client.authentication.HeaderAuthentication;
import com.mashape.client.authentication.BasicAuthentication;
import com.mashape.client.authentication.OAuthAuthentication;
import com.mashape.client.authentication.OAuth10aAuthentication;
import com.mashape.client.authentication.OAuth2Authentication;
import com.mashape.client.http.ContentType;
import com.mashape.client.http.HttpClient;
import com.mashape.client.http.HttpMethod;
import com.mashape.client.http.MashapeCallback;
import com.mashape.client.http.MashapeResponse;
import com.mashape.client.http.ResponseType;
import com.mashape.client.http.utils.HttpUtils;

public class nViso3DFIHttpClient {

	private final static String PUBLIC_DNS = "3dfi.nviso.net/api/v1/";
    private List<Authentication> authenticationHandlers;

    public nViso3DFIHttpClient (String app_id, String app_key) {
		authenticationHandlers = new LinkedList<Authentication>();
        authenticationHandlers.add(new QueryAuthentication(new AuthenticationParameter("app_id", app_id), new AuthenticationParameter("app_key", app_key)));
        
    }
    
    /**
     * Synchronous call with optional parameters.
     */
    public MashapeResponse<JSONObject> processImageByUrl(String url, String app_session, String seq_number, String format) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        
        if (app_session != null && !app_session.equals("")) {
	parameters.put("app_session", app_session);
        }
        
        
        
        if (seq_number != null && !seq_number.equals("")) {
	parameters.put("seq_number", seq_number);
        }
        
        
        
        if (url != null && !url.equals("")) {
	parameters.put("url", url);
        }
        
        
        
        if (format != null && !format.equals("")) {
	parameters.put("format", format);
        }
        
        
        
        return (MashapeResponse<JSONObject>) HttpClient.doRequest(JSONObject.class,
                    HttpMethod.GET,
                    "https://" + PUBLIC_DNS + "/process/image/url/",
                    parameters,
                    ContentType.FORM,
                    ResponseType.JSON,
                    authenticationHandlers);
    }

    /**
     * Synchronous call without optional parameters.
     */
    public MashapeResponse<JSONObject> processImageByUrl(String url) {
        return processImageByUrl(url, "", "", "");
    }


    /**
     * Asynchronous call with optional parameters.
     */
    public Thread processImageByUrl(String url, String app_session, String seq_number, String format, MashapeCallback<JSONObject> callback) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        
        
        if (app_session != null && !app_session.equals("")) {
        
            parameters.put("app_session", app_session);
        }
        
        
        
        if (seq_number != null && !seq_number.equals("")) {
        
            parameters.put("seq_number", seq_number);
        }
        
        
        
        if (url != null && !url.equals("")) {
        
            parameters.put("url", url);
        }
        
        
        
        if (format != null && !format.equals("")) {
        
            parameters.put("format", format);
        }
        
        
        return HttpClient.doRequest(JSONObject.class,
                    HttpMethod.GET,
                    "https://" + PUBLIC_DNS + "/process/image/url/",
                    parameters,
                    ContentType.FORM,
                    ResponseType.JSON,
                    authenticationHandlers,
                    callback);
    }

    /**
     * Asynchronous call without optional parameters.
     */
    public Thread processImageByUrl(String url, MashapeCallback<JSONObject> callback) {
        return processImageByUrl(url, "", "", "", callback);
    }

    /**
     * Synchronous call with optional parameters.
     */
    public MashapeResponse<JSONObject> processImageByUpload(File image, String app_session, String seq_number, String format) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        
        if (image != null && !image.equals("")) {
	parameters.put("image", image);
        }
        
        
        
        if (app_session != null && !app_session.equals("")) {
	parameters.put("app_session", app_session);
        }
        
        
        
        if (seq_number != null && !seq_number.equals("")) {
	parameters.put("seq_number", seq_number);
        }
        
        
        
        if (format != null && !format.equals("")) {
	parameters.put("format", format);
        }
        
        
        
        return (MashapeResponse<JSONObject>) HttpClient.doRequest(JSONObject.class,
                    HttpMethod.POST,
                    "https://" + PUBLIC_DNS + "/process/image/upload/",
                    parameters,
                    ContentType.BINARY,
                    ResponseType.JSON,
                    authenticationHandlers);
    }

    /**
     * Synchronous call without optional parameters.
     */
    public MashapeResponse<JSONObject> processImageByUpload(File image) {
        return processImageByUpload(image, "", "", "");
    }


    /**
     * Asynchronous call with optional parameters.
     */
    public Thread processImageByUpload(File image, String app_session, String seq_number, String format, MashapeCallback<JSONObject> callback) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        
        
        if (image != null && !image.equals("")) {
        
            parameters.put("image", image);
        }
        
        
        
        if (app_session != null && !app_session.equals("")) {
        
            parameters.put("app_session", app_session);
        }
        
        
        
        if (seq_number != null && !seq_number.equals("")) {
        
            parameters.put("seq_number", seq_number);
        }
        
        
        
        if (format != null && !format.equals("")) {
        
            parameters.put("format", format);
        }
        
        
        return HttpClient.doRequest(JSONObject.class,
                    HttpMethod.POST,
                    "https://" + PUBLIC_DNS + "/process/image/upload/",
                    parameters,
                    ContentType.BINARY,
                    ResponseType.JSON,
                    authenticationHandlers,
                    callback);
    }

    /**
     * Asynchronous call without optional parameters.
     */
    public Thread processImageByUpload(File image, MashapeCallback<JSONObject> callback) {
        return processImageByUpload(image, "", "", "", callback);
    }

}