package com.rkubyshkin;

import com.rkubyshkin.model.Structure;

import java.io.*;
import java.util.Properties;

public class Config {
    protected static final File PROPERTIES = new File(".\\storage\\persons.properties");
    private File storageDir;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    private static final Config INSTANCE = new Config();

    public static Config get() {
        return INSTANCE;
    }

    public Config() {
        try (InputStream is = new FileInputStream("./config/persons.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            storageDir = new File(properties.getProperty("storage.dir"));
            dbUrl = properties.getProperty("db.url");
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");

        } catch (IOException e) {
            new IllegalStateException("Invalid config file" + PROPERTIES.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
