package fr.dawan.demoapirest.dtos;

import java.time.LocalDateTime;

public class LogDto {

    private int code;
    private String message;
    private LocalDateTime timeStamp;
    private String path;

    public enum LogLevel{
        DEBUG, INFO, WARN, ERROR
    }

    private LogLevel level;

    public enum LogType{
        ACCESS, EXCEPTION, CLIENT_APP
    }

    private LogType logType;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public LogType getLogType() {
        return logType;
    }

    public void setLogType(LogType logType) {
        this.logType = logType;
    }
}
