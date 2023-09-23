package daniil.ardiukov;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Hello world!
 */
public class App {
    public static final String CONF_FILE_NAME = "conf.properties";

    public static final String PROFILES_FILE_PROP_NAME = "confFile";

    public static final String FILE_DB_PROP_NAME = "fileDB";

    public static void main(String[] args) throws URISyntaxException, IOException {
        String path = getDataBasePath();
        System.out.println(path);
        Jakarta jakarta_1 = createJakarta();
        jakarta_1.writeToJson(path);

        Jakarta jakarta_2 = Jakarta.readFromJson(path);

        System.out.println(jakarta_1.equals(jakarta_2));

    }

    public static Jakarta createJakarta() {
        Jakarta result = new Jakarta();
        result.setVersion("1.0");
        result.setDescription("a test one");
        Technology maven = new Technology();
        maven.setName("maven");
        maven.setDescription("Something");
        result.updateTechnology(maven);
        result.updateTechnology(maven);

        Technology jackson = new Technology();
        jackson.setName("jackson");
        jackson.setDescription("json mater");
        result.updateTechnology(jackson);
        return result;
    }

    public static String getDataBasePath() throws URISyntaxException, IOException {
        String profilesConf = getProperty(CONF_FILE_NAME, PROFILES_FILE_PROP_NAME);
        String fileDB = getProperty(profilesConf, FILE_DB_PROP_NAME);
        File file = new File(fileDB);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file.getAbsolutePath();
    }

    public static String getProperty(String resource, String propertyName) {
        try (InputStream is = App.class.getResourceAsStream("/" + resource)) {
            Properties prop = new Properties();
            prop.load(is);
            return prop.getProperty(propertyName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
