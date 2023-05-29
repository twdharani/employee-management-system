package com.dharani.employeemanagementsystem.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> generateSuccessResponse(String message, HttpStatus status) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Status", "Success");
        map.put("Message", message);

        return new ResponseEntity<Object>(map, status);
    }
}
