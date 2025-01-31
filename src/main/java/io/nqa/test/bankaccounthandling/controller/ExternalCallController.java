package io.nqa.test.bankaccounthandling.controller;

import io.nqa.commons.CustomResponse;
import io.nqa.test.bankaccounthandling.service.IExternalCallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external")
@RequiredArgsConstructor
public class ExternalCallController {
    private final IExternalCallService service;

    @GetMapping()
    public CustomResponse getRandom() {
        return service.getRandom();
    }

    @GetMapping("{code}")
    public CustomResponse get(@PathVariable String code) {
        return service.makeRequest(code);
    }

    @GetMapping("all")
    public CustomResponse getAll() {
        return service.makeAllRequests();
    }
}
