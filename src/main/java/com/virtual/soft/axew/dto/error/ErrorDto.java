package com.virtual.soft.axew.dto.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

public class ErrorDto {
    @Schema(example = "2021-01-01 12:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private final Instant timestamp;

    @Schema(example = "404")
    private final Integer status;

    @Schema(example = "Resource Exception")
    private final String error;

    @Schema(example = "Resource not found.")
    private String message;

    @Schema(example = "/url")
    private String path;

    public ErrorDto (Instant timestamp, Integer status, String error) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
    }

    public ErrorDto setMessage (String message) {
        this.message = message;
        return this;
    }

    public ErrorDto setPath (String path) {
        this.path = path;
        return this;
    }

    public Instant getTimestamp () {
        return timestamp;
    }

    public Integer getStatus () {
        return status;
    }

    public String getError () {
        return error;
    }

    public String getMessage () {
        return message;
    }

    public String getPath () {
        return path;
    }
}
