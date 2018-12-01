//package com.ddlab.rnd.spring.boot.app;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Scanner;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpServletResponseWrapper;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
////@Component
//public class SimpleLoggingFilter implements Filter {
//	
//    private final Logger logger = LoggerFactory.getLogger(SimpleLoggingFilter .class);
//    
////    public static String convertStreamToString(java.io.InputStream is) {
////        Scanner scanner = new Scanner(is).useDelimiter("\\A");
////        String str = scanner.hasNext() ? scanner.next() : "";
//////        scanner.close();
////        return str ;
////    }
//    
//    public static String getBody(HttpServletRequest request) throws IOException {
//
//        String body = null;
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader bufferedReader = null;
//
//        try {
//            InputStream inputStream = request.getInputStream();
//            System.out.println("Got :::"+inputStream);
//            if (inputStream != null) {
//                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                char[] charBuffer = new char[128];
//                int bytesRead = -1;
//                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
//                    stringBuilder.append(charBuffer, 0, bytesRead);
//                }
//            } else {
//                stringBuilder.append("");
//            }
//            System.out.println("============================");
//        } catch (IOException ex) {
//        	ex.printStackTrace();
////            throw ex;
//        } finally {
////            if (bufferedReader != null) {
////                try {
////                	System.out.println("------ Going to close ---------");
//////                    bufferedReader.close();
////                    System.out.println("Closed completely ...");
////                } catch (IOException ex) {
////                	System.err.println("-------------------");
////                	ex.printStackTrace();
//////                    throw ex;
////                }
////            }
//        }
//
//        body = stringBuilder.toString();
//        return body;
//    }
//    
//    private void extractRequestBody(HttpServletRequest request) throws IOException {
//    	if ("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod()) ) {
////    	   String test = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//    	   logger.info(getBody(request));
////    	   logger.info( convertStreamToString(request.getInputStream()));
//    	}
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) res;
//        HttpServletRequest request = (HttpServletRequest) req;
//        
//        HttpServletRequestWrapper req1 = new HttpServletRequestWrapper(request);
//        HttpServletResponseWrapper res1 = new HttpServletResponseWrapper(response);
//        
//        request = req1;
//        
//        String url = request.getServletPath();
//        logger.info(url + " - " + request.getMethod());
//        
////        InputStream is = request.getInputStream();
////        System.out.println(is);
//        
//        extractRequestBody(req1);
//        
//        
//        chain.doFilter(req, res);
//        logger.info(url + " - " + response.getStatus());
//    }
//
//    public void init(FilterConfig filterConfig) {
//    	
//    }
//
//    public void destroy() {
//    	
//    }
//
//}