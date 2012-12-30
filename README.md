# nViso 3D Facial Imaging Java / Android SDK (v1)

The nViso Developer Platform is a set of APIs that make your application more engaging through capturing real-time emotional feedback and enabling next generation real-time interactivity. For a full list of capabilities please consult the [nViso Developer Platform Portal](https://developer.nviso.net) for more information.

This repository contains the open source Java SDK that allows you to access [nViso Developer Platform](https://developer.nviso.net) from your Java or Android application. In order to use this SDK you will need to have an registered and authorized account from the [nViso Developer Platform Portal](https://developer.nviso.net) and a valid application ID and Key. Except as otherwise noted, the nViso 3D Facial Imaging Java SDK is licensed under the MIT Licence (http://opensource.org/licenses/MIT).

## Installation & Dependencies

The Java SDK requires the Json, Gson, Apache HttpComponents and the HttpMime libraries to be included in the build path. For convience all JAR files are included inside the [lib folder](https://github.com/nViso/3dfi-api-java/blob/master/lib/).

For Android Users remember to set the `android.permission.INTERNET` permission in the manifest XML file. The JSON and HttpClient libraries should already be included in the SDK, so importing only HttpMime should be enough.

This is how the project looks like when using Android 4.1:

![Project in Android 4.1](https://s3.amazonaws.com/mashape-static-production/web/assets/android.png)

## Getting Started

Getting started with the nViso 3D Facial Imaging Java SDK couldn't be easier. Create a `nViso3DFIHttpClient` and you're ready to go. You will find a [sample file](https://github.com/nViso/3dfi-api-java/blob/master/Sample.java) that allows you to get started immediately in the [java root folder](https://github.com/nViso/3dfi-api-java/blob/master/).

### API Credentials

The `nViso3DFIHttpClient` needs your application ID and key found by logging into the [nViso Developer Platform](https://developer.nviso.net). You can pass these directly to the constructor. Your keys should be kept secret and never shared with anyone!

```java
public class Sample {
	private final static String MY_APP_ID = "__INSERT_YOUR_APP_ID__";
	private final static String MY_APP_KEY = "__INSERT_YOUR_APP_KEY__";
	
	public static void create_client()
	{	
		nViso3DFIHttpClient client = new nViso3DFIHttpClient( MY_APP_ID, MY_APP_KEY );
	}
}
```

### Processing an Image by URL

The `nViso3DFIHttpClient` can process a URL locating an image in any standard image format (JPEG, PNG, BMP, etc). It optionally accepts a session id and sequence number used for reporting and ordering results.

```java
public class Sample {

	private final static String MY_APP_ID = "__INSERT_YOUR_APP_ID__";
	private final static String MY_APP_KEY = "__INSERT_YOUR_APP_KEY__";
	private final static String MY_APP_SESSION = "__INSERT_YOUR_APP_SESSION_ID__";
	private final static String MY_APP_SEQ_NUMBER = "0";
	private final static String MY_APP_FORMAT = "json";
	private final static String IMAGE_URL = "https://gw1-3dfi.nviso.net/uploads/3bbda1fcc91647cc07423a9f7c2ebce0.jpg";
	
	// Call to API synchronously - suitable for server-side threaded blocking calls
	public static void sync_process_image_url(  )
	{
		nViso3DFIHttpClient client = new nViso3DFIHttpClient( MY_APP_ID, MY_APP_KEY );
		MashapeResponse<JSONObject> response = client.processImageByUrl( IMAGE_URL, MY_APP_SESSION, MY_APP_SEQ_NUMBER, MY_APP_FORMAT );

		//now you can do something with the response.
		System.out.println("Sync processImageByUrl Call returned a response code of " + response.getCode());
		System.out.println("Sync processImageByUrl Call returned body of " + response.getBody());
	}
}
```

### Processing an Image by Upload

The `nViso3DFIHttpClient` can process an image stored locally on the computer. The selected file will be uploaded as part of the request. It 
optionally accepts a session id and sequence number used for reporting and ordering results.

```java
public class Sample {

	private final static String MY_APP_ID = "__INSERT_YOUR_APP_ID__";
	private final static String MY_APP_KEY = "__INSERT_YOUR_APP_KEY__";
	private final static String MY_APP_SESSION = "__INSERT_YOUR_APP_SESSION_ID__";
	private final static String MY_APP_SEQ_NUMBER = "0";
	private final static String MY_APP_FORMAT = "json";
	private final static String IMAGE_PATH = "__INSERT_YOUR_IMAGE_PATH__";
	
	// Call to API synchronously - suitable for server-side threaded blocking calls
	public static void sync_process_image_upload(  )
	{
		nViso3DFIHttpClient client = new nViso3DFIHttpClient( MY_APP_ID, MY_APP_KEY );
		MashapeResponse<JSONObject> response = client.processImageByUrl( new File(IMAGE_PATH), MY_APP_SESSION, MY_APP_SEQ_NUMBER, MY_APP_FORMAT );

		//now you can do something with the response.
		System.out.println("Sync processImageByUrl Call returned a response code of " + response.getCode());
		System.out.println("Sync processImageByUrl Call returned body of " + response.getBody());
	}
}
```

### Non-blocking Asychronous Calls for Mobile UI

The `nViso3DFIHttpClient` supports building a mobile application, you often want to consume a remote endpoint asynchronously without blocking the UI. If this is the case, then the client library expects one of your objects to implement the `requestCompleted` method of the `MashapeCallback` interface, for example:

```java
	nViso3DFIHttpClient client = new nViso3DFIHttpClient( MY_APP_ID, MY_APP_KEY );
	Thread thread = client.processImageByUpload( new File(IMAGE_URL), MY_APP_SESSION, MY_APP_SEQ_NUMBER, MY_APP_FORMAT, new MashapeCallback<JSONObject>() {

		public void requestCompleted(MashapeResponse<JSONObject> response) {				
			System.out.println("Async processImageByUrl returned a response code of " + response.getCode());
			System.out.println("Async processImageByUrl returned body of " + response.getBody());
		}
		
		public void errorOccurred(Exception e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	});
```

An asynchronous call returns a Thread object.

### Response Format

The client library returns a `MashapeResponse` object that includes the response of the endpoint. It has the following properties:

- int code: the HTTP status code.
- Map<String, String> headers: the response headers.
- InputStream rawBody: the non-parsed raw body.
- (JSONObject, String) body: the parsed body.
-- It's String when the API returns a plain string response.
-- It's JSONObject when the API returns a JSON object.

The parsed body containing the data returned by the endpoint can be accessed simply by :

```java
JSONObject res = response.getBody();
```

Data returned from the API is output either as a JSON object or XML (depending on the format you choose). 

- `json`: the output will be valid JSON with the mimetype of `application/json`. 
- `eml`: the output will be valid EmotionML with the mimetype of `text/xml`.

For further documentation on the response data model please consult the [nViso Developer Platform Portal](https://developer.nviso.net) for more information.

## Support & Feedback

Please shoot us an email if you have questions or feedback (info@nviso.ch) or open a GitHub issue for bugs and feature requests.

## Credits

This repository incorporates :

- The [Mashape Library](https://github.com/Mashape/mashape-java-client-library) which is distributed under the [GPL v3 license](https://github.com/Mashape/mashape-java-client-library/blob/master/LICENSE).
- The [JSON Library](http://www.json.org/java/index.html) which is distributed under its own [free and no waranty license](http://www.json.org/license.html).
- The [Google GSON Library](https://github.com/Mashape/mashape-java-client-library) which is distributed under the [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0).
- The [Apache HTTP Components Library](http://hc.apache.org/) which is distributed under the [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0).