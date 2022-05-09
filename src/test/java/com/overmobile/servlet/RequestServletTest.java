package com.overmobile.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.overmobile.model.Payment;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RequestServletTest {

    @Test
    public void testRequest() throws IOException {
        //a bit hardcode but this is test so...
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setLenient().create();
        Payment payment = new Payment(null, "paymentId3", 123L, 1000L);
        URL url = new URL ("http://localhost:8080/overmobile-1.0-SNAPSHOT/request");


        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        String jsonString = gson.toJson(payment, Payment.class);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response);
        }


    }
}