package com.overmobile.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.overmobile.dao.PaymentDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletRegistrator implements ServletContextListener {
    private GsonBuilder gsonBuilder;
    private PaymentDao paymentDao;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        gsonBuilder = new GsonBuilder();
        paymentDao = new PaymentDao();

        context.addServlet("requestServlet", new RequestServlet(gsonBuilder.setLenient().create(), paymentDao)).addMapping("/request");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
