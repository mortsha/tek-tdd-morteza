package tek.tdd.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
Read config File
Open Browser
quit browser
Encapsulate instance of WebDriver
*/
public abstract class BaseSetup {

    private static final Logger LOGGER = LogManager.getLogger(BaseSetup.class);
    private Properties properties;
    private static WebDriver driver;

    public BaseSetup(){
        // Reading Config file and Loading to properties
        String configFile =  System.getProperty("user.dir") + "/src/test/resources/configs/dev-config.properties";
        try {
            LOGGER.debug("Reading config file from path {}" , configFile);
            InputStream inputStream = new FileInputStream(new File(configFile));
            properties = new Properties();
            properties.load(inputStream);
        }catch (IOException ioException){
            LOGGER.error("Config file error with message {}" ,ioException.getMessage());
            throw new RuntimeException("Config file error with message {} "+ ioException.getMessage());
        }

    }


}
