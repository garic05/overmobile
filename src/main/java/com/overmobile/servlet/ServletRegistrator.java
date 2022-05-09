package com.overmobile.servlet;

import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.overmobile.dao.DaoUtils;
import com.overmobile.dao.PaymentDao;
import com.overmobile.model.Payment;
import com.overmobile.service.PaymentService;
import com.overmobile.util.PropertyUtil;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletRegistrator implements ServletContextListener {
    private static final String PROP_DB_NAME = "mongodb.database";
    private static final String PROP_PAYMENT_COLLECTION_NAME = "mongodb.paymentCollection";

    private GsonBuilder gsonBuilder;
    private MongoDatabase paymentDatabase;
    private MongoCollection<Payment> paymentMongoCollection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        gsonBuilder = new GsonBuilder().setLenient();

        paymentDatabase = DaoUtils.getMongoClient().getDatabase(PropertyUtil.getProperty(PROP_DB_NAME));

        paymentMongoCollection = paymentDatabase.getCollection(PropertyUtil.getProperty(PROP_PAYMENT_COLLECTION_NAME), Payment.class);

        context.addServlet("requestServlet", new RequestServlet(gsonBuilder.create(), new PaymentService(new PaymentDao(paymentMongoCollection))))
                .addMapping("/request");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }



}
