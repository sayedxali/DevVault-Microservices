package com.dev.vault.ProjectService.feign.client;

import com.dev.vault.shared.lib.exceptions.DevVaultException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@Component
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();

        log.error("❌ {{}} ❌", response.request().url());

        try {

            DevVaultException devVaultException = objectMapper.readValue(
                    response.body().asInputStream(),
                    DevVaultException.class
            );
            return new DevVaultException(devVaultException.getMessage(), response.status());

        } catch (Exception ex) {
            throw new DevVaultException("⚠️ INTERNAL_SERVER_ERROR ⚠️", INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.value());
        }
    }

}