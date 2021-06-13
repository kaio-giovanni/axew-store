package com.virtual.soft.axew.utils;

import io.sentry.Sentry;

public class SentryUtils {
    private final String sentryDsn;
    private final String stage;

    public SentryUtils () {
        sentryDsn = DotEnvUtils.getSentryDsn();
        stage = DotEnvUtils.getStage();
        Sentry.init(options -> {
            options.setDsn(sentryDsn);
            options.setEnvironment(stage);
        });
    }

    public void sendException (Throwable throwable, String tag) {
        if (throwable == null) {
            return;
        }

        Sentry.configureScope(scope -> {
            scope.setTag("tag", tag);
            scope.setExtra("error", throwable.toString());
        });

        Sentry.captureException(throwable);
        throwable.printStackTrace();
    }

}
