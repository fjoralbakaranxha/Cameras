package com.example.Camera.controller;

import com.example.Camera.model.ChartCell;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.example.Camera.exception.CameraCollectionException;
import com.example.Camera.model.Camera;
import com.example.Camera.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class CameraController {
    @Autowired
    private CameraService cameraService;

    @RequestMapping(method = RequestMethod.POST, value = "/cameras")
    public ResponseEntity<Camera> addCamera(@RequestBody Camera camera) {
        try{
            Camera c = cameraService.addCamera(camera);
            return new ResponseEntity(c, HttpStatus.OK);
        }catch (ConstraintViolationException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (CameraCollectionException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cameras")
    public ResponseEntity getAllCameras() {
        List<Camera> cameras = cameraService.getAllCameras();
        return new ResponseEntity(cameras, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cameras/{id}")
    public ResponseEntity deleteCamera(@PathVariable String id) {
        try {
            cameraService.deleteCameraById(id);
            return new ResponseEntity("Camera deleted successfully!", HttpStatus.OK);
        } catch (CameraCollectionException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cameras/{id}")
    public ResponseEntity updateCamera(@RequestBody Camera camera, @PathVariable String id) {
        System.out.println(id);
        try {
            cameraService.updateCamera(camera, id);
            return new ResponseEntity("Camera is updated!", HttpStatus.OK);
        } catch (CameraCollectionException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cameras/{id}")
    public ResponseEntity getCamera(@PathVariable String id) {
        Optional<Camera> cameraOptional = cameraService.getCameraById(id);
        if (cameraOptional.isPresent()) {
            return new ResponseEntity(cameraOptional, HttpStatus.OK);
        } else {
            return new ResponseEntity("Camera not found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping (method = RequestMethod.GET, value ="/chart")
    public ResponseEntity getChartData() {
        List<ChartCell> chartCells = cameraService.getChartData();
        return new ResponseEntity(chartCells, HttpStatus.OK);
    }
}

