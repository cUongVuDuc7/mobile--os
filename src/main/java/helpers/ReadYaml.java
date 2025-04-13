package helpers;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

public class ReadYaml {

    public static String[] loadConfig(String path){
        String config= "";
        Yaml yaml = new Yaml();
        Map<String, Object> yamlMap;
        try (InputStream inputStream = new FileInputStream(path)) {
            yamlMap = yaml.load(inputStream);
            Set<String> set = yamlMap.keySet();
            for (String key : set) {
                config = yamlMap.get(key).toString().replace("[", "").replace("]","").replace("{","").replace("}","");
                System.out.println(key + "    " + yamlMap.get(key));
            }
            return config.split(", ");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config from: " + path);
        }
    }
}

