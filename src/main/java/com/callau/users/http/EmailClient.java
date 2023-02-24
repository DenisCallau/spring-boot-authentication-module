package com.callau.users.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

// Remove the url parameter when eureka is configured
@FeignClient(value = "email-ms", url = "email")
public interface EmailClient {

    @RequestMapping(method = RequestMethod.POST, value = "/email")
    void sendVerificationEmail(@RequestBody @Valid VerificationEmailRequest dto);

    @RequestMapping(method = RequestMethod.POST, value = "/email")
    void sendResetPasswordEmail(@RequestBody @Valid ResetPasswordEmailRequest dto);

}
