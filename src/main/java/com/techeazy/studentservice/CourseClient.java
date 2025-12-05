package com.techeazy.studentservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CourseClient {

    @Value("${app.course-host-name}")
    private String hostName;

    @Value("${app.course-server.port}")
    private String port;

    private final RestTemplate restTemplate;

    public CourseClient(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<Object> getCourses(Long studentId) {
        String url = "http://" + hostName + ":" + port + "/courses/student/" + studentId;
        return restTemplate.getForObject(url, List.class);
    }
}