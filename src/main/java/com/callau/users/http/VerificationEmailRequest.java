package com.callau.users.http;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class VerificationEmailRequest {

    @NotBlank
    private String ownerRef;

    @NotBlank
    @Email
    private String recipient;

    @NotBlank
    private final String subject = "Confirm your registration";

    @NotBlank
    private String verificationCode;

    private String body = """
                <body>
                    <div style="width: 50%%; margin: auto; background-color: #F6F6F6;">
                        <div style="padding-top: 40px;;">
                            <h1 style="text-align: center; vertical-align: middle; margin-top: 0px">Thank you for registering in Laurelin</h1>
                        </div>
                        <div style="margin-top: 60px;">
                            <h2 style="text-align: center;">Verify your email address clicking the button below</h2>
                        </div>
                        <div style="text-align: center; padding-bottom: 40px;">
                            <a href="http://localhost:8071/users-ms/users/verify?verifyCode=%s">
                                <button style="width: 120px; height: 40px; background-color: #4CAE53; color: white; border: none;">Verify</button>
                            </a>
                        </div>
                    </div>
                </body>""";

    public VerificationEmailRequest(String verificationCode) {
        this.verificationCode = verificationCode;
        this.body = this.body.formatted(this.getVerificationCode());
    }

}
