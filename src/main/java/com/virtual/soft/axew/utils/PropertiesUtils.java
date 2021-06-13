package com.virtual.soft.axew.utils;

public class PropertiesUtils {

    private PropertiesUtils () {
        // Do nothing
    }

    public static void loadByDotEnv () {
        System.setProperty("spring.datasource.url", DotEnvUtils.getDatabaseUrl());
        System.setProperty("spring.datasource.username", DotEnvUtils.getDatabaseUserName());
        System.setProperty("spring.datasource.password", DotEnvUtils.getDatabasePassword());
        System.setProperty("jwt.secret", DotEnvUtils.getJwtSecret());
        System.setProperty("jwt.expiration", DotEnvUtils.getJwtExpiration());
    }
}
