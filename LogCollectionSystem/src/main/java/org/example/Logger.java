package org.example;

public class Logger {
    private int id;
    private String level;
    private String logMsg;

    @Override
    public String toString() {
        return "Logger{" +
                "id=" + id +
                ", level='" + level + '\'' +
                ", logMsg='" + logMsg + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

}
