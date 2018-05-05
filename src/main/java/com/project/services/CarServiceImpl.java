package com.project.services;

import com.project.dao.CarDAO;
import com.project.models.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service()
public class CarServiceImpl implements CarService {


    @Autowired
    CarDAO carDAO;

    @Autowired
    UserService userService;

    @Override
    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
    @Override
    public void addCar(Car car , MultipartFile file) {

        String filename = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                filename = file.getOriginalFilename();
                String rootPath = "C:\\Users\\User\\IdeaProjects\\springMVC-(carDao)";
                File dir = new File(rootPath + File.separator + "loadFiles");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + filename);
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("uploaded-"+filename )));
                stream.write(bytes);
                stream.flush();
                stream.close();
                car.setPath(uploadedFile.getAbsolutePath());

                logger.info("uploaded: " + uploadedFile.getAbsolutePath());
                System.out.println("You are downloaded " + filename + " in " + filename + "-uploaded !");

            } catch (Exception e) {
                System.out.println("Вам не удалось загрузить " + filename + " => " + e.getMessage());
            }
        }
        car.getBrand();
        carDAO.addCar(car);
    }

    @Override
    public void addNewCar(int car_id) {
        carDAO.setNotMyCars(userService.findByLogin(userService.getUsername()).getUser_id(), car_id);
        carDAO.getMyCars(userService.findByLogin(userService.getUsername()).getUser_id());
    }


    @Override
    public void deleteCar(int car_id) {
        carDAO.deleteCar(car_id);
    }

    @Override
    public List<Car> getUserCars() {
        return carDAO.getMyCars(userService.findByLogin(userService.getUsername()).getUser_id());
    }

    @Override
    public List<Car> getNotUserCars() {
        return carDAO.getNotMyCars(userService.findByLogin(userService.getUsername()).getUser_id());
    }

    @Override
    public void deleteUserCar(int car_id) {
        System.out.println(userService.findByLogin(userService.getUsername()).getUser_id());
        System.out.println(car_id);
        carDAO.deleteUserCar(userService.findByLogin(userService.getUsername()).getUser_id(),car_id);
    }
}
