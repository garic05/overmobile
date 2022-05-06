package com.overmobile.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.overmobile.dao.PaymentDao;
import com.overmobile.model.Payment;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.CharBuffer;

//@WebServlet("/hello")
public class RequestServlet extends HttpServlet {
    private Gson gson;
    private PaymentDao paymentDao;

    public RequestServlet(Gson gson,  PaymentDao paymentDao) {
        super();
        this.gson = gson;
        this.paymentDao = paymentDao;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        char []buff = new char[1<<20];
        reader.read(buff);
        String body = new String(buff).trim();
        Payment payment = gson.fromJson(body, Payment.class);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print("");
        out.flush();
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        response.setContentType("application/json");
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.getWriter().println("{ \"status\": \"ok\"}");
//    }
}
