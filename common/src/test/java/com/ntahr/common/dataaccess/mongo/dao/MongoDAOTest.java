package com.ntahr.common.dataaccess.mongo.dao;

import com.ntahr.common.dataaccess.mongo.exception.MongoException;
import com.ntahr.common.dataaccess.mongo.objects.LocationPoint;
import com.ntahr.common.properties.PropertiesUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/app-context.xml")
public class MongoDAOTest {

    static String MONGO_HOST = PropertiesUtil.getInstance().getProperty("mongo.host");
    static Integer MONGO_PORT = Integer.parseInt(PropertiesUtil.getInstance().getProperty("mongo.port"));
    static String MONGO_COLLECTION = PropertiesUtil.getInstance().getProperty("mongo.db.name");

    static MongoDAO<LocationPoint> mongoDAO = new MongoDAO<>(MONGO_HOST, MONGO_PORT, MONGO_COLLECTION);

    List<LocationPoint> locationPointList = Arrays.asList(
            new LocationPoint("77.12345", "18.234988", "1", "AXA0001", System.currentTimeMillis() - 1000L),
            new LocationPoint("77.11145", "18.232322", "1", "AXA0001", System.currentTimeMillis() - 2000L),
            new LocationPoint("78.12345", "18.234988", "2", "AXA0002", System.currentTimeMillis() - 3000L),
            new LocationPoint("79.12345", "19.234988", "2", "AXA0002", System.currentTimeMillis() - 4000L),
            new LocationPoint("22.12345", "13.234988", "3", "AXA0003", System.currentTimeMillis() - 5000L),
            new LocationPoint("22.12345", "13.234655", "3", "AXA0003", System.currentTimeMillis() - 6000L),
            new LocationPoint("22.12345", "18.234099", "3", "AXA0003", System.currentTimeMillis() - 7000L),
            new LocationPoint("41.12345", "31.234155", "4", "AXA0004", System.currentTimeMillis() - 8000L),
            new LocationPoint("41.12345", "31.234166", "4", "AXA0004", System.currentTimeMillis() - 9000L),
            new LocationPoint("41.12345", "31.234177", "4", "AXA0004", System.currentTimeMillis() - 10000L)
    );

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println(" >>> " + PropertiesUtil.getInstance().getProperty("mongo.host"));
        System.out.println(" >>> " + PropertiesUtil.getInstance().getProperty("mongo.port"));
        System.out.println(" >>> " + PropertiesUtil.getInstance().getProperty("mongo.db.name"));
    }

    @AfterClass
    public static void tearDown() throws Exception {
        mongoDAO.dropCollection("locations");
    }

    @Test
    public void insert() throws Exception {
        locationPointList.forEach(locationPoint -> {
            try {
                mongoDAO.insert(locationPoint, "locations");
            } catch (MongoException e) {
                assert false;
            }
        });
    }

    @Test
    public void save() throws Exception {
    }

    @Test
    public void save1() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
        List<LocationPoint> locations = mongoDAO.findAll(LocationPoint.class, "locations");
        assert locations != null && locations.size() != 0;
        locations.forEach(System.out::println);
    }

    @Test
    public void filter() throws Exception {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("deviceId", "4");
        List<LocationPoint> locations = mongoDAO.filter(LocationPoint.class, "locations", queryMap);
        assert locations != null && locations.size() != 0;
        locations.forEach(System.out::println);
    }

    @Test
    public void filterOnDateRange() throws Exception {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("deviceId", "3");
        List<LocationPoint> locations = mongoDAO.filterOnDateRange(LocationPoint.class, "locations", queryMap, "recordedTime", System.currentTimeMillis() - 10000L, System.currentTimeMillis() - 1000);
        assert locations != null && locations.size() != 0;
        locations.forEach(System.out::println);
    }

    @Test
    public void filter1() throws Exception {
    }

}