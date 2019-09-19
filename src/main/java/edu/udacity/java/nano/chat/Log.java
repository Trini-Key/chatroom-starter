package edu.udacity.java.nano.chat;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    public Logger keysLogger;
    FileHandler handleFiles;

    public Log(String fileName) throws SecurityException, IOException{

        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }

        handleFiles = new FileHandler(fileName, true);
        keysLogger = Logger.getLogger("test");
        keysLogger.addHandler(handleFiles);
        SimpleFormatter format = new SimpleFormatter();
        handleFiles.setFormatter(format);
    }
}
