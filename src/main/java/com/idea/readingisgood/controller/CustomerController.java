package com.idea.readingisgood.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.idea.readingisgood.dto.CustomerDTO;
import com.idea.readingisgood.entity.response.BaseResponse;
import com.idea.readingisgood.service.CustomerService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "customer")
@Validated
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getCustomer(@RequestParam String userId) {
        return customerService.fetchOneById(userId);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<BaseResponse> getListCustomer(
        @RequestParam(name = "startIndex", required = false) Integer startIndex,
        @RequestParam(name = "size", required = false) Integer size) {
        return customerService.fetchAll(startIndex,size);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        return customerService.save(customerDTO);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        return customerService.update(customerDTO);
    }
}
