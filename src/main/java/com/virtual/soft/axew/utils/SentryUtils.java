package com.virtual.soft.axew.utils;

import io.sentry.Sentry;

public class SentryUtils {
    private String sentryDsn;
    private String stage;
    private static final String PROJECT_NAME = "axew-api";

    public SentryUtils () {
        sentryDsn = DotEnvUtils.getSentryDsn();
        stage = DotEnvUtils.getStage();
        Sentry.init(options -> {
            options.setDsn(sentryDsn);
            options.setEnvironment(stage);
        });
    }

    public void sendException (Throwable throwable) {
        if (throwable == null) {
            return;
        }

        Sentry.configureScope(scope -> {
            scope.setTag("projectName", PROJECT_NAME);
            scope.setExtra("error", throwable.toString());
        });

        Sentry.captureException(throwable);
    }

}
