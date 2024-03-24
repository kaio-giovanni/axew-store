package com.virtual.soft.axew.aws.lambda;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.virtual.soft.axew.aws.AwsCredentialsProvider;
import com.virtual.soft.axew.dto.aws.AwsLambdaResponseDto;

import java.nio.charset.StandardCharsets;

public class AwsLambdaRequest {

    public static final int DEFAULT_SOCKET_TIMEOUT = 30000;
    public static final int DEFAULT_CONNECTION_TIMEOUT = 5000;
    public static final int DEFAULT_MAX_ERROR_RETRY = 0;

    private final ClientConfiguration clientConfig;

    public AwsLambdaRequest() {
        this.clientConfig = makeDefaultClientConfig();
    }

    private ClientConfiguration makeDefaultClientConfig() {
        ClientConfiguration defaultClientConfig = new ClientConfiguration();
        defaultClientConfig.setSocketTimeout(DEFAULT_SOCKET_TIMEOUT);
        defaultClientConfig.setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT);
        defaultClientConfig.setMaxErrorRetry(DEFAULT_MAX_ERROR_RETRY);
        return defaultClientConfig;
    }

    private AwsLambdaResponseDto sendRequest(String lambdaName, String awsRegion, String payloadJson) {
        Regions region = Regions.fromName(awsRegion);

        AWSLambdaClientBuilder builder = AWSLambdaClientBuilder.standard()
                .withRegion(region)
                .withClientConfiguration(clientConfig);

        AWSLambda client = builder.build();

        InvokeRequest req = new InvokeRequest()
                .withFunctionName(lambdaName)
                .withPayload(payloadJson);

        req.setRequestCredentialsProvider(new AwsCredentialsProvider());
        InvokeResult invokeResult = client.invoke(req);
        byte[] buffer = invokeResult.getPayload().array();
        String resp = new String(buffer, StandardCharsets.UTF_8);
        int status = invokeResult.getStatusCode();

        return new AwsLambdaResponseDto(resp, status);
    }
}
