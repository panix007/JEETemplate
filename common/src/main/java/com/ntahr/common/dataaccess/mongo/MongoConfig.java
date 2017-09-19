package com.ntahr.common.dataaccess.mongo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    String host;
    Integer port;
    String databaseName;

    public MongoConfig() {
        host = "localhost";
        port = 27017;
        databaseName = "default";
    }

    public MongoConfig(String host, Integer port, String databaseName) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
    }


    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(host, port);
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.ntahr.common.dataaccess.mongo";
    }

}
