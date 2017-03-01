package com.el.unity.log.log4j;

import org.apache.log4j.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xvshu on 2016/6/17.
 */

@Component
public class LoggerSearch {
    private AppenderParser appenderParser;
    private LogFileParser logParser;
    private Map<String, Appender> appenders;
    private String configFilePath = "log4j.xml";
    List<LogEntry> logEntries;
    Map<String, List<LogEntry>> allLogEntries;

    @PostConstruct
    public void init() throws Exception {
        appenderParser = new AppenderParser();
        logParser = new LogFileParser();
        appenderParser.parse(getConfigFilePath());
        appenders = appenderParser.getAppenderBag();
        allLogEntries = new HashMap<String, List<LogEntry>>();
    }

    public Map<String, List<LogEntry>> searchAll(String content, Integer upperLogNum, Integer lowerLogNum) throws Exception {
        for (Appender appender : appenders.values()) {
            if (appender instanceof FileAppender) {
                FileAppender fileAppender = (FileAppender) appender;
                if (appender instanceof DailyRollingFileAppender) {
                    Layout layout = fileAppender.getLayout();
                    if (layout instanceof PatternLayout) {
                        PatternLayout patternLayout = (PatternLayout) layout;
//                        String conversionPattern = patternLayout.getConversionPattern();
//                        String fileName = fileAppender.getFile();
//                        logEntries = logParser.parse(fileName, conversionPattern, content, upperLogNum, lowerLogNum);
//                        allLogEntries.put(new File(fileName).getName(), logEntries);
                    }
                }
            }
        }
        return allLogEntries;
    }

    public void setConfigFilePath(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    public String getConfigFilePath() {
        return LoggerSearch.class.getClassLoader().getResource(configFilePath).getFile();
    }
}
