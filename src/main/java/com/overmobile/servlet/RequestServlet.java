package com.overmobile.servlet;

import com.google.gson.Gson;
import com.overmobile.model.Payment;
import com.overmobile.service.PaymentService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RequestServlet extends HttpServlet {
    private final Gson gson;
    private PaymentService paymentService;
    private Lock lock;

    public RequestServlet(Gson gson,  PaymentService paymentService) {
        super();
        this.gson = gson;
        this.paymentService = paymentService;
        lock = new ReentrantLock();
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

        lock.lock();
        int res = paymentService.insertIfNotExist(payment);
        lock.unlock();

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(res);
        out.flush();
    }

}
