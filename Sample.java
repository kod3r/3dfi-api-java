/*
 * nViso 3D Facial Imaging Java Client Library Example.
 * 
 * Copyright (c) 2012 nViso SA. All Rights Reserved.
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 */
import java.io.File;

import com.nviso.nViso3DFIHttpClient;

import org.json.JSONObject;
import com.mashape.client.http.MashapeResponse;
import com.mashape.client.http.MashapeCallback;

public class Sample {

	private final static String MY_APP_ID = "__INSERT_YOUR_APP_ID__";
	private final static String MY_APP_KEY = "__INSERT_YOUR_APP_KEY__";
	private final static String MY_APP_SESSION = "__INSERT_YOUR_APP_SESSION_ID__";
	private final static String MY_APP_FORMAT = "json";
	private final static String IMAGE_URL = "https://gw1-3dfi.nviso.net/uploads/3bbda1fcc91647cc07423a9f7c2ebce0.jpg";
	private final static String IMAGE_PATH = "./example/test-small.jpg";
	
	// Call to API synchronously - suitable for server-side threaded blocking calls
	public static void sync_process_image_url( nViso3DFIHttpClient client )
	{
		// A sample method call. These parameters are not properly filled in.
		String seq_number = "0";
		MashapeResponse<JSONObject> response = client.processImageByUrl( IMAGE_URL, MY_APP_SESSION, seq_number, MY_APP_FORMAT );

		//now you can do something with the response.
		System.out.println("Sync processImageByUrl Call returned a response code of " + response.getCode());
		System.out.println("Sync processImageByUrl Call returned body of " + response.getBody());
	}
	
	// Call to API synchronously - suitable for server-side threaded blocking calls
	public static void sync_process_image_upload( nViso3DFIHttpClient client )
	{
		// A sample method call. These parameters are not properly filled in.
		String seq_number = "0";
		MashapeResponse<JSONObject> response = client.processImageByUpload( new File(IMAGE_PATH), MY_APP_SESSION, seq_number, MY_APP_FORMAT );

		//now you can do something with the response.
		System.out.println("Sync processImageByUpload Call returned a response code of " + response.getCode());
		System.out.println("Sync processImageByUpload Call returned body of " + response.getBody());
	}

	// Call to API asynchronously non-blocking - useful for mobile UI applications
	public static void async_process_image_url( nViso3DFIHttpClient client )
	{
		String seq_number = "0";
		Thread thread = client.processImageByUrl( IMAGE_URL, MY_APP_SESSION, seq_number, MY_APP_FORMAT, new MashapeCallback<JSONObject>() {
	
			public void requestCompleted(MashapeResponse<JSONObject> response) {				
				System.out.println("Async processImageByUrl returned a response code of " + response.getCode());
				System.out.println("Async processImageByUrl returned body of " + response.getBody());
			}
			
			public void errorOccurred(Exception e) {
				System.err.println("An error occurred: " + e.getMessage());
			}
		});

	}
	
	// Call to API asynchronously non-blocking - useful for mobile UI applications
	public static void async_process_image_upload( nViso3DFIHttpClient client )
	{
		String seq_number = "0";
		Thread thread = client.processImageByUpload(  new File(IMAGE_PATH), MY_APP_SESSION, seq_number, MY_APP_FORMAT, new MashapeCallback<JSONObject>() {
	
			public void requestCompleted(MashapeResponse<JSONObject> response) {				
				System.out.println("Async processImageByUpload returned a response code of " + response.getCode());
				System.out.println("Async processImageByUpload returned body of " + response.getBody());
			}
			
			public void errorOccurred(Exception e) {
				System.err.println("An error occurred: " + e.getMessage());
			}
		});

	}

	public static void main(String[] args) {
		nViso3DFIHttpClient client = new nViso3DFIHttpClient( MY_APP_ID, MY_APP_KEY );

		// Blocking processing
		sync_process_image_url(client);
		sync_process_image_upload(client);

		// Non-blocking processing
		async_process_image_url(client);
		async_process_image_upload(client);
	}
}
