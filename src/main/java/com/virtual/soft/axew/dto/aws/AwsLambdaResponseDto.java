package com.virtual.soft.axew.dto.aws;

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
