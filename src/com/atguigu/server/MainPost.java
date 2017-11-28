package com.atguigu.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
 
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
 
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

 

public class MainPost {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		
	     HttpPost httpPost=new HttpPost("http://192.168.10.252:9999/sms_test/sms");
	 
	    List<NameValuePair> list =new ArrayList<NameValuePair>();
	    
	    list.add(new BasicNameValuePair("phonenum", "13810168266")) ;
	    
	    list.add( new BasicNameValuePair("msg", "您好1111fasdfasdfasdf11111111111111111111111111111111111!"));
	    
	    
	    HttpEntity httpEntity=new UrlEncodedFormEntity(list,"utf-8");
		
	    httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
	    
	    httpPost.setEntity(httpEntity);
		
	    HttpResponse httpResponse= httpClient.execute(httpPost);
	    
	    if(httpResponse.getStatusLine()!=null &&httpResponse.getStatusLine().equals(HttpStatus.SC_OK)){
	    	String result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");
	    	System.out.println(result);
	    }else{
	    	String result= EntityUtils.toString(httpResponse.getEntity(), "utf-8");

	    	System.out.println("err:"+result);
	    }

	}

}
