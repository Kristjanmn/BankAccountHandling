package io.nqa.test.bankaccounthandling.service;

import io.nqa.commons.CustomResponse;

public interface IExternalCallService {
    CustomResponse makeRequest(String uri);
    CustomResponse makeRequest(int statusCode);
    CustomResponse getRandom();
    CustomResponse makeAllRequests();
}
