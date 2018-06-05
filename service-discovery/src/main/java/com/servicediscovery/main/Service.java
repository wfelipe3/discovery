package com.servicediscovery.main;

import lombok.Data;

@Data
public class Service {
    private String name;
    private String url;
    private String healthCheckUrl;
}
