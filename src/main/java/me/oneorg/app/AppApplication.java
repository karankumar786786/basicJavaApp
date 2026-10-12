package me.oneorg.app;


import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class AppApplication {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();
        String contextPath = "";
        String docBase = new File("src/main/webapp").getAbsolutePath();
        Context context = tomcat.addContext(contextPath,docBase);
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        DispatcherServlet dispatcherServlet = new DispatcherServlet(springContext);
        Tomcat.addServlet(context,"dispatcherServlet",dispatcherServlet);
        context.addServletMapping("/","dispatcherServlet");
        tomcat.start();
        tomcat.getServer().await();
    }

}
