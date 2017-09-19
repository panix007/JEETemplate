package com.ntahr.common.dataaccess.mongo.dao;

import com.ntahr.common.dataaccess.mongo.MongoConfig;
import com.ntahr.common.dataaccess.mongo.exception.MongoException;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class MongoDAO<T> {

    MongoConfig mongoConfig;

    public MongoDAO() {
        mongoConfig = new MongoConfig();
    }

    public MongoDAO(String mongoHost, Integer mongoPort, String collectionName) {
        this.mongoConfig = new MongoConfig(mongoHost, mongoPort, collectionName);
    }

    public void insert(T data, String collection) throws MongoException {
        try {
            mongoConfig.mongoTemplate().insert(data, collection);
        } catch (Exception exception) {
            throw new MongoException(exception);
        }
    }

    public void save(T data) throws MongoException {
        try {
            mongoConfig.mongoTemplate().save(data);
        } catch (Exception exception) {
            throw new MongoException(exception);
        }
    }

    public void save(T data, String collection) throws MongoException {
        try {
            mongoConfig.mongoTemplate().save(data, collection);
        } catch (Exception exception) {
            throw new MongoException(exception);
        }
    }

    public List<T> findAll(Class<T> tClass, String collection) throws MongoException {
        try {
            return mongoConfig.mongoTemplate().findAll(tClass, collection);
        } catch (Exception exception) {
            throw new MongoException(exception);
        }
    }

    public List<T> filter(Class<T> tClass, String collection, String name, String value) throws MongoException {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where(name).is(value));
            return mongoConfig.mongoTemplate().find(query, tClass, collection);
        } catch (Exception exception) {
            throw new MongoException(exception);
        }
    }

    public List<T> filter(Class<T> tClass, String collection, Map<String, String> criterias) throws MongoException {
        try {
            Query query = new Query();
            criterias.forEach((name, value) -> query.addCriteria(Criteria.where(name).is(value)));
            return mongoConfig.mongoTemplate().find(query, tClass, collection);
        } catch (Exception exception) {
            throw new MongoException(exception);
        }
    }

    public List<T> filterOnDateRange(Class<T> tClass, String collection, String dateFieldName, Long startDate, Long endDate) throws MongoException {
        return filterOnDateRange(tClass, collection, null, dateFieldName, startDate, endDate);
    }

    public List<T> filterOnDateRange(Class<T> tClass, String collection, Map<String, String> criterias, String dateFieldName, Long startDate, Long endDate) throws MongoException {
        try {
            Query query = new Query();
            if (criterias != null) {
                criterias.forEach((name, value) -> query.addCriteria(Criteria.where(name).is(value)));
            }
            query.addCriteria(Criteria.where(dateFieldName).gte(startDate).lte(endDate));
            return mongoConfig.mongoTemplate().find(query, tClass, collection);
        } catch (Exception exception) {
            throw new MongoException(exception);
        }
    }

    public void dropCollection(String collection) throws MongoException {
        try {
            mongoConfig.mongoTemplate().dropCollection(collection);
        } catch (Exception exception) {
            throw new MongoException(exception);
        }
    }

    public Set<String> getCollections() throws MongoException {
        try {
            return mongoConfig.mongoTemplate().getCollectionNames();
        } catch (Exception exception) {
            throw new MongoException(exception);
        }
    }
}
