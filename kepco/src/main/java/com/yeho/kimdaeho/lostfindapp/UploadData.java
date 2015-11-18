package com.yeho.kimdaeho.lostfindapp;

import android.app.ProgressDialog;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KimDaeho on 15. 11. 18..
 */

//업로드 시켜주는 Class
public class UploadData {

    //DB insert
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    BufferedReader bufferedReader;
    String tmp_response;

    private int serverResponseCode = 0;
    private ProgressDialog progdialog = null;
    private String upLoadServerUri = null;

    //파일 정보를 Upload 시키는 URI
    static final String UPLOAD_URI = "http://58.229.240.79/kepco/fileupload.php";
    //저장된 목록을 Upload 시키는 URi
    static final String CONTENT_INPUT_URI = "http://58.229.240.79/kepco/contentinput.php";

    static final String NAMEVALUE_NUM = "user_num";
    static final String NAMEVALUE_CONTENT = "content";
    static final String NAMEVALUE_FILENAME = "fileName";
    static final String NAMEVALUE_FILENAME2 = "fileName2";
    static final String NAMEVALUE_ADDRESS = "address";
    static final String NAMEVALUE_LATITUDE = "latitude";
    static final String NAMEVALUE_LONGITUDE = "longitude";
    static final String NAMEVALUE_CATEGORY = "category";

    
}
