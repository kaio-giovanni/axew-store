package com.virtual.soft.axew.log;

import org.slf4j.LoggerFactory;

public class Log {

    private final org.slf4j.Logger logger;

    public Log(Class<? extends Object> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void warning(String msg) {
        logger.warn(msg);
    }

    public void error(String error) {
        logger.error(error);
    }
}