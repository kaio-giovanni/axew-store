package com.virtual.soft.axew.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class DotEnvUtils {

    private DotEnvUtils() {
    }

    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    public static String getStage() {
        return dotenv.get("STAGE", "dev");
    }

    public static String getSentryDsn() {
        return dotenv.get("SENTRY_DSN", "");
    }

    public static String getDatabaseUrl() {
        return dotenv.get("DB_URL");
    }

    public static String getDatabaseUserName() {
        return dotenv.get("DB_USERNAME");
    }

    public static String getDatabasePassword() {
        return dotenv.get("DB_PASSWORD");
    }

    public static String getJwtSecret() {
        return dotenv.get("JWT_SECRET", "AbCdEfGhIYxZ");
    }

    public static String getJwtExpiration() {
        return dotenv.get("JWT_EXPIRATION");
    }

    public static String getAwsAccessKeyId() {
        return dotenv.get("AWS_ACCESS_KEY_ID");
    }

    public static String getAwsSecretAccessKey() {
        return dotenv.get("AWS_SECRET_ACCESS_KEY");
    }
}
