package me.oneorg.app;


import me.oneorg.app.config.WebConfig;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class AppApplication {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();
        String contextPath = "";
        File docBaseFile = new File("src/main/webapp");
        if (!docBaseFile.exists()) {
            docBaseFile.mkdirs();
        }
        String docBase = docBaseFile.getAbsolutePath();
        Context context = tomcat.addContext(contextPath, docBase);

        File additionWebInfClasses = new File("target/classes");
        if (additionWebInfClasses.exists()) {
            WebResourceRoot resources = new StandardRoot(context);
            resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
            context.setResources(resources);
        }
        
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();
        springContext.setClassLoader(AppApplication.class.getClassLoader());
        springContext.register(WebConfig.class);
        
        DispatcherServlet dispatcherServlet = new DispatcherServlet(springContext);
        Wrapper wrapper = Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet);
        wrapper.setLoadOnStartup(1);
        context.addServletMappingDecoded("/", "dispatcherServlet");
        
        tomcat.start();
        tomcat.getServer().await();
    }
}
