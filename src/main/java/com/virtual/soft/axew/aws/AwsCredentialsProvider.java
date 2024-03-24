package com.virtual.soft.axew.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.virtual.soft.axew.utils.DotEnvUtils;

public class AwsCredentialsProvider implements AWSCredentialsProvider {

    @Override
    public AWSCredentials getCredentials() {
        return new Credentials();
    }

    @Override
    public void refresh() {
        // Do nothing because it's not needed yet
    }

    private static class Credentials implements AWSCredentials {

        @Override
        public String getAWSAccessKeyId() {
            return DotEnvUtils.getAwsAccessKeyId();
        }

        @Override
        public String getAWSSecretKey() {
            return DotEnvUtils.getAwsSecretAccessKey();
        }
    }
}