package com.ntahr.common.dataaccess.mongo.repository;

import com.ntahr.common.dataaccess.mongo.objects.LocationPoint;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LocationRepository extends MongoRepository<LocationPoint, String> {

    List<LocationPoint> findByDeviceId(String deviceId);

    List<LocationPoint> findByDeviceName(String deviceName);

}
