package com.sjapp.orderservice.external.client.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjapp.orderservice.error.OrderServiceCustomException;
import com.sjapp.orderservice.external.client.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream() , ErrorResponse.class);
            return new OrderServiceCustomException(errorResponse.getErrorMessage() , errorResponse.getErrorCode());
        } catch (IOException e) {
            throw new OrderServiceCustomException("Internal Server Error" , HttpStatus.INTERNAL_SERVER_ERROR.toString());
        }
    }
}
