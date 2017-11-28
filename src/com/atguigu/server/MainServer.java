package com.atguigu.server;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

 

public class MainServer {

	public static void main(String[] args) {
		JAXRSServerFactoryBean jaxrsServerFactoryBean=new JAXRSServerFactoryBean();
		
		jaxrsServerFactoryBean.setAddress("http://192.168.10.252:9999/sms_test");
		
		jaxrsServerFactoryBean.setResourceClasses(SmsSender.class);
		
		jaxrsServerFactoryBean.create().start();
		
		System.out.println("服务再启动");

	}

}
