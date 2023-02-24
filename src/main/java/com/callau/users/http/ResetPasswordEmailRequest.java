package com.callau.users.http;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ResetPasswordEmailRequest {

    @NotBlank
    private String ownerRef;

    @NotBlank
    @Email
    private String recipient;

    @NotBlank
    private final String subject = "Reset your password";

    @NotBlank
    private String verificationCode;

    private String body = """
                <body>
                    <div style="width: 50%%; margin: auto; background-color: #F6F6F6;">
                        <div style="padding-top: 40px;;">
                            <h1 style="text-align: center; vertical-align: middle; margin-top: 0px">Password Reset</h1>
                        </div>
                        <div style="margin-top: 60px;">
                            <h2 style="text-align: center;">To reset your password, click the button below</h2>
                        </div>
                        <div style="text-align: center; padding-bottom: 40px;">
                            <a href="http://localhost:8071/users-ms/users/password/new?verifyCode=%s">
                                <button style="width: 120px; height: 40px; background-color: #4CAE53; color: white; border: none;">Reset Password</button>
                            </a>
                        </div>
                    </div>
                </body>""";

    public ResetPasswordEmailRequest(String verificationCode) {
        this.verificationCode = verificationCode;
        this.body = this.body.formatted(this.getVerificationCode());
    }


}
