package com.example.Camera.service;
import com.example.Camera.exception.CameraCollectionException;
import com.example.Camera.model.Camera;
import com.example.Camera.model.ChartCell;
import com.example.Camera.repository.CameraRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    public Camera addCamera(Camera camera) throws ConstraintViolationException, CameraCollectionException {
        Optional<Camera> cameraOptional = cameraRepository.findByIp(camera.getIp());
        if(cameraOptional.isPresent()) {
            System.out.println(cameraOptional.get());
            throw new CameraCollectionException(CameraCollectionException.IpAlreadyExists());
        } else {
           return cameraRepository.save(camera);
        }
    }

    public List<Camera> getAllCameras() {
        List<Camera> cameras = cameraRepository.findAll();
        if(cameras.size() > 0) {
            return cameras;
        } else {
            return new ArrayList<>();
        }
    }

    public void deleteCameraById(String id) throws CameraCollectionException {
        Optional<Camera> cameraOptional = cameraRepository.findById(id);
        if (!cameraOptional.isPresent()) {
            throw new CameraCollectionException(CameraCollectionException.NotFoundException(id));
        } else {
            cameraRepository.deleteById(id);
        }
    }
    public void updateCamera(Camera camera, String id) throws CameraCollectionException {
        Optional<Camera> cameraOptional = cameraRepository.findById(id);
        if (!cameraOptional.isPresent()) {
            throw new CameraCollectionException(CameraCollectionException.NotFoundException(camera.getId()));
        } else {
            Camera cameraToUpdate = cameraOptional.get();
            BeanUtils.copyProperties(camera, cameraToUpdate);
            cameraRepository.save(cameraToUpdate);
        }
    }
    public Optional<Camera> getCameraById(String id) {
        return cameraRepository.findById(id);
    }

    public List<ChartCell> getChartData() {
        return cameraRepository.groupByPixel();
    }

}
