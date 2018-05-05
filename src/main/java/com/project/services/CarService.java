package com.project.services;

import com.project.models.Car;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();

    void addCar(Car car , MultipartFile file);

    void addNewCar(int car_id);

    void deleteCar(int car_id);

    List<Car> getUserCars();

    void deleteUserCar(int car_id);

    List<Car> getNotUserCars();
}
