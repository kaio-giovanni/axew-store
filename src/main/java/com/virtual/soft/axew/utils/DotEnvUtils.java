package com.virtual.soft.axew.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class DotEnvUtils {

    private DotEnvUtils () {
    }

    private static Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    public static String getStage () {
        return dotenv.get("STAGE", "dev");
    }

    public static String getSentryDsn () {
        return dotenv.get("SENTRY_DSN");
    }

    public static String getAwsAccessKeyId () {
        return dotenv.get("AWS_ACCESS_KEY_ID");
    }

    public static String getAwsSecretAccessKey () {
        return dotenv.get("AWS_SECRET_ACCESS_KEY");
    }
}
