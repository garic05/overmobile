package com.overmobile.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.overmobile.model.Payment;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class RequestServletTest {
    private Gson gson;
    private GsonBuilder gsonBuilder;
    private Payment payment;

    @Test
    public void testRequest() throws IOException {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.setLenient().create();
        payment = new Payment(null, "paymentId", 123L, 1000L);

        URL url = new URL ("http://localhost:8080/overmobile-1.0-SNAPSHOT/request");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        String jsonString = gson.toJson(payment, Payment.class);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }

    @Test
    public void testJson() {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        payment = new Payment(null, "paymentId", 123L, 1000L);
        System.out.println(gson.toJson(payment, Payment.class));
    }
}