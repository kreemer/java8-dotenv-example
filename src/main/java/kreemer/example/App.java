package kreemer.example;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

import java.io.IOException;
import java.util.Properties;

/**
 * Hello world!
 */
public class App 
{
    public static void main(String[] args) throws IOException {
        System.out.println("Reading properties");
        Properties properties = loadProperties();

        System.out.println("a: " + properties.getProperty("a"));
        System.out.println("b: " + properties.getProperty("b"));
        System.out.println("c: " + properties.getProperty("c"));
    }

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(App.class.getResourceAsStream("/app.properties"));

        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        for (DotenvEntry e : dotenv.entries()) {
            properties.setProperty(e.getKey(), e.getValue());
        }

        return properties;
    }
}
