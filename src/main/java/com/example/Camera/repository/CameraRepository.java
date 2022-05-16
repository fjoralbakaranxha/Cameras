package com.example.Camera.repository;

import com.example.Camera.model.Camera;
import com.example.Camera.model.ChartCell;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CameraRepository extends MongoRepository<Camera, String> {
   @Query("{'ip':?0}")
   Optional<Camera> findByIp(String ip);

   @Aggregation(pipeline = {
           "{$group: {" +
                   "_id: $resolution, " +
                   "pixel: {$first : $resolution}, " +
                   "total: { $sum: 1 }}}"
   })
   public List<ChartCell> groupByPixel();
}

