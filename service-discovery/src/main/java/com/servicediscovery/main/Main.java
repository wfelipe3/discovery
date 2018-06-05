package com.servicediscovery.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        post("/service-discovery/register", (req, res) -> {
            val service = toService(req.bodyAsBytes());
            res.status(201);
            res.type("application/json");
            return service.toString();
        });
    }

    private static Service toService(byte[] body) throws java.io.IOException {
        val mapper = new ObjectMapper();
        return mapper.readValue(body, Service.class);
    }
}
