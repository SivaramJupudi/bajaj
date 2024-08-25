package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
@RequestMapping("/bfhl")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Response> getOperationCode() {
        return ResponseEntity.ok(new Response(1));
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<ApiResponse> processData(@RequestBody RequestData requestData) {
        String userId = "john_doe_17091999";  // Replace with dynamic data if needed
        String email = "john@xyz.com";
        String rollNumber = "ABCD123";

        if (requestData == null || requestData.getData() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> lowercaseAlphabets = new ArrayList<>();

        for (String item : requestData.getData()) {
            if (item.matches("\\d+")) {
                numbers.add(item);
            } else if (item.matches("[a-zA-Z]")) {
                alphabets.add(item);
                if (item.matches("[a-z]")) {
                    lowercaseAlphabets.add(item);
                }
            }
        }

        String highestLowercase = lowercaseAlphabets.stream()
                .max(String::compareTo)
                .orElse("");

        ApiResponse response = new ApiResponse(true, userId, email, rollNumber, numbers, alphabets, List.of(highestLowercase));
        return ResponseEntity.ok(response);
    }
}

class RequestData {
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}

class Response {
    private int operation_code;

    public Response(int operation_code) {
        this.operation_code = operation_code;
    }

    public int getOperation_code() {
        return operation_code;
    }

    public void setOperation_code(int operation_code) {
        this.operation_code = operation_code;
    }
}

class ApiResponse {
    private boolean is_success;
    private String user_id;
    private String email;
    private String roll_number;
    private List<String> numbers;
    private List<String> alphabets;
    private List<String> highest_lowercase_alphabet;

    public ApiResponse(boolean is_success, String user_id, String email, String roll_number,
                       List<String> numbers, List<String> alphabets, List<String> highest_lowercase_alphabet) {
        this.is_success = is_success;
        this.user_id = user_id;
        this.email = email;
        this.roll_number = roll_number;
        this.numbers = numbers;
        this.alphabets = alphabets;
        this.highest_lowercase_alphabet = highest_lowercase_alphabet;
    }

    public boolean isIs_success() {
        return is_success;
    }

    public void setIs_success(boolean is_success) {
        this.is_success = is_success;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(String roll_number) {
        this.roll_number = roll_number;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    public List<String> getAlphabets() {
        return alphabets;
    }

    public void setAlphabets(List<String> alphabets) {
        this.alphabets = alphabets;
    }

    public List<String> getHighest_lowercase_alphabet() {
        return highest_lowercase_alphabet;
    }

    public void setHighest_lowercase_alphabet(List<String> highest_lowercase_alphabet) {
        this.highest_lowercase_alphabet = highest_lowercase_alphabet;
    }
}
