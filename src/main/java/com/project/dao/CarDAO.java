package com.project.dao;

import com.project.models.Car;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface CarDAO {
    @Select("SELECT * FROM cars")
    List<Car> getAllCars();

    @Insert("insert into cars(brand,path) values (#{brand},#{path})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "car_id", before = false, resultType = Integer.class)
    void addCar(Car car);


    @Delete("delete from cars where car_id=#{id}")
    void deleteCar(int id);

    @Select("SELECT  car_id, brand, path FROM cars LEFT JOIN user_cars using(car_id) where user_id=#{user_id};")
    List<Car> getMyCars(int user_id);

    @Insert("INSERT into user_cars(user_id, car_id) values (#{user_id},#{car_id})")
    void setNotMyCars(@Param("user_id") int user_id ,@Param("car_id") int car_id);

    @Select("SELECT  car_id, brand, path FROM cars LEFT JOIN user_cars using(car_id) where user_id NOT LIKE #{user_id};")
    List<Car> getNotMyCars(int user_id);

    @Delete("delete from user_cars where user_id= #{user_id} and car_id= #{car_id}")
    void deleteUserCar(@Param("user_id") int user_id, @Param("car_id") int car_id);

}
