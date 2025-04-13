package core;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import core.BasePage;
import helpers.LogHelper;
import helpers.PropertiesFile;
import io.qameta.allure.Step;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import java.sql.*;
import java.util.HashMap;
import java.util.Set;

import static com.mongodb.client.model.Sorts.descending;

public class DataBase extends BasePage {
    private static Logger logger = LogHelper.getLogger();
    public static  HashMap<String, String> dataMap = new HashMap<>();
    public DataBase() {
    }
    @Step("Kết nốt data base : {0}")
    public Connection setUpDB(String url, String user, String passWord) {
        Connection con;
        logger.info("Set Up DB " + url );
        try {
            if (PropertiesFile.getPropValue(url).contains("postgresql")) {
                Class.forName("org.postgresql.Driver");
            }
            else {
                Class.forName("cdata.jdbc.mongodb.MongoDBDriver");
            }
            String dbUrl = PropertiesFile.getPropValue(url);
            String dbUser = PropertiesFile.getPropValue(user);
            String dbPass = PropertiesFile.getPropValue(passWord);
            con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
    @Step("Khởi tạo Db")
    public Statement createStatement(Connection con) {
        Statement stmt ;
        try {
//            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt = con.createStatement();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stmt;
    }

    @Step("Thực hiện truy vấn dữ liệu : {0}")
    public ResultSet queryDb(Statement stmt, String query) {
        ResultSet res;
        logger.info("Query DB: " + query );
        String content= PropertiesFile.getPropValue(query);
        if (content == null) {
            content = query;
        }
        try {
            res = stmt.executeQuery(content);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    @Step("Lấy dữ liệu từ các cột db")
    public static HashMap<String, String> getResultDataBase(ResultSet res) {
        try {
            ResultSetMetaData md = res.getMetaData();
//            res.last();
//            int row = res.getRow();
//            res.first();
            while (res.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    dataMap.put(md.getColumnName(i), res.getString(i));
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        logger.info("Get result DB:");
        Set<String> set = dataMap.keySet();
        for (String key : set) {
            System.out.println("Key: " + key + "   Value: " + dataMap.get(key));
        }
        return dataMap;
    }
    @Step("Kiểm tra dữ liệu cột: {0}")
    public void checkDataBase(String actuals, String expects) {
        logger.info("Check DB: ");
        String[] actual = actuals.split(",");
        String[] expect = expects.split(",");
        for (int i = 0; i < expect.length; i++) {
            keyword.assertEqualData(dataMap.get(actual[i]), expect[i]);
        }
    }
    public MongoClient connectMongoDb(String url) {
        logger.info("Connect mongo DB: ");
        url = PropertiesFile.getPropValue(url);
        MongoClient mongoClient = new MongoClient(new MongoClientURI(url));
        return mongoClient;
    }
    public MongoDatabase getDatabase(MongoClient mongoClient , String dataBase){
        logger.info("Get db mongo: ");
        MongoDatabase database = mongoClient.getDatabase(dataBase);
        return database;
    }
    public MongoCollection getTable(MongoDatabase database, String tableName){
        logger.info("Get table db mongo: ");
        MongoCollection<Document> collection = database.getCollection(tableName);
        return collection;
    }

    public String getResultDevice(MongoCollection<Document> collection, Bson filter){
        logger.info("Get result device mongo: ");
        Document lastDocument = collection.find(filter).sort(descending("created_at")).first();
        return lastDocument.get("device_name").toString();
    }
}
