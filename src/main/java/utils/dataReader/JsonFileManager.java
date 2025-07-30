package utils.dataReader;


import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;


import java.io.FileReader;
import java.io.IOException;


public class JsonFileManager {
    private String jsonReader;
    private final String jsonFilePath;

    public JsonFileManager(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        try {
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(new FileReader(jsonFilePath));
            jsonReader = data.toJSONString();
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTestData(String jsonPath) {
        String testData = "";
        try {
            testData = JsonPath.read(jsonReader, jsonPath);
        } catch (PathNotFoundException e) {
            e.printStackTrace();
            Assert.fail("No results for json path: '" + jsonPath + "' in the json file: '" + this.jsonFilePath + "'");
        }
        return testData;
    }

}