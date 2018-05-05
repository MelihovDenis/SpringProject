package com.project.controllers;

import com.project.models.Car;
import com.project.services.CarService;
import com.project.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class CarController {

    @Autowired
    UserService userService;

    @Autowired
    CarService carService;


    @GetMapping("/carsView")
    public String cars(Model model){
        model.addAttribute("userCars",carService.getUserCars());
        model.addAttribute("cars",carService.getAllCars());
        return "cars";
    }

    @GetMapping("/control_of_cars")
    public String control_of_cars(Model model){
        model.addAttribute("cars",carService.getAllCars());
        return "control_of_cars";
    }

    @GetMapping("/deleteUserCars/{car_id}")
    public String deleteUserCars(@PathVariable("car_id") int car_id){
        carService.deleteUserCar(car_id);
        return "redirect:/carsView";
    }

    @GetMapping("/deleteCar/{car_id}")
    public String deleteCar(@PathVariable("car_id") int car_id){
        carService.deleteCar(car_id);
        return "redirect:/control_of_cars";
    }


    //by admin
    @PostMapping("/addCar")
    public String addCar(@ModelAttribute("carForm")Car carForm ,@RequestParam("file") MultipartFile file , ModelMap modelMap) {
        carService.addCar(carForm , file);
        modelMap.addAttribute("file" ,file );
        return "redirect:/control_of_cars";
    }


    //by user
    @RequestMapping(value = "/addNewCar/{car_id}", method = RequestMethod.GET)
    public String addNewCar( Model model, @PathVariable("car_id")int car_id) {
        carService.addNewCar(car_id);
        model.addAttribute("userCars",carService.getUserCars());
        model.addAttribute("cars",carService.getAllCars());
        return "redirect:/carsView";
    }

}
