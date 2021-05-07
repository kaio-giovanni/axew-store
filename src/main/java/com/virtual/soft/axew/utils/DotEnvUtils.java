package com.virtual.soft.axew.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class DotEnvUtils {

    private static final String DEFAULT_AWS_REGION = "us-west-2";

    private static Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    private DotEnvUtils () {
    }

    public static String getStage () {
        return dotenv.get("STAGE", "dev");
    }

    public static String getSentryDsn () {
        return dotenv.get("SENTRY_DSN");
    }
}
