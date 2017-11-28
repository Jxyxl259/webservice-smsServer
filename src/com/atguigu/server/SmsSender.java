package com.atguigu.server;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SmsSender {

	public static String URL ="http://gw.api.taobao.com/router/rest";
	
	public static String APPKEY="23605043";
	
	public static String SECRET="d87fcc937064a905";
	
	public static String TEMPLATE_CODE="SMS_62075068";
	
    private static HashMap<String ,Integer> ipcountMap=new HashMap<String ,Integer>();
	
	
	public static void main(String[] args) throws ApiException {
		 //sendSms("15311869949", "��ʲô�أ�" );

	}
	
	@Path("/sms")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public static String sendSms(@FormParam("phonenum") String phonenum,@FormParam("msg")String  msg ,@Context HttpServletRequest request) throws ApiException{
	    String ip =request.getRemoteHost();
 
	    System.out.println(ip+":"+phonenum+":"+msg+":");
	    
 
		TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend( "" );
		req.setSmsType( "normal" );
		req.setSmsFreeSignName( "张晨老师短信服务" );
		req.setSmsParamString( "{content:'"+msg+"'}" );
		req.setRecNum( phonenum );
		req.setSmsTemplateCode( TEMPLATE_CODE );
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		 System.out.println(ip+":"+phonenum+":"+msg+":"+rsp.getBody());
		return rsp.getBody() ;
	}
	
	private static boolean checkip(String ip){
		Integer ipcount=  ipcountMap.get(ip);
		if(ipcount!=null&&ipcount>=3){
			return false;
		}
		return true;
		 
	}
	
	private static void countip(String ip){
		Integer ipcount=  ipcountMap.get(ip);
		if(ipcount!=null ){
			ipcountMap.put(ip, ++ipcount)  ;
		}
 
	}
	
	
	
}
