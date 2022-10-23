package log;

import java.io.IOException;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    public static Logger logger = Logger.getLogger("MyLog");
    private static FileHandler fileHandler;
    
    public static void iniciarLog() {
    	try {
            fileHandler = new FileHandler("loggerFile.txt");
            logger.addHandler(fileHandler);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.log(Level.INFO, "Iniciando Logger");
        } catch (SecurityException e) {
        	logger.log(Level.INFO, "Exception:" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
        	logger.log(Level.INFO, "IO Exception:" + e.getMessage());
            e.printStackTrace();
        }
    }
    
//    public static void cerrarLog() {
//    	logger.log(Level.INFO, "Cerrando Logger");
//    	fileHandler.close();
//    }
}
