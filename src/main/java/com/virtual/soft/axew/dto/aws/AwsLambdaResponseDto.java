package com.virtual.soft.axew.dto.aws;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AwsLambdaResponse")
public class AwsLambdaResponseDto {

    private String response;

    private int status;

    public AwsLambdaResponseDto() {
    }

    public AwsLambdaResponseDto(String response, int status) {
        this.response = response;
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public int getStatus() {
        return status;
    }
}
