package com.smartlogix.gateway;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Enumeration;

@RestController
public class BffProxyController {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${bff.service.url:http://bff-service:8084}")
    private String bffServiceUrl;

    @RequestMapping("/api/bff/**")
    public ResponseEntity<String> proxyToBff(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String targetUrl = bffServiceUrl + requestURI;
        if (request.getQueryString() != null) {
            targetUrl += "?" + request.getQueryString();
        }

        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            // Evitar duplicar headers que causan problemas como host y content-length
            if (!headerName.equalsIgnoreCase("host") && !headerName.equalsIgnoreCase("content-length")) {
                headers.add(headerName, request.getHeader(headerName));
            }
        }

        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);

        return restTemplate.exchange(
                targetUrl,
                HttpMethod.valueOf(request.getMethod()),
                httpEntity,
                String.class
        );
    }
}
