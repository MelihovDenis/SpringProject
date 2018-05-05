package com.project.dao;

import com.project.models.Manager;
import com.project.models.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDAO {


    @Select("SELECT * FROM users LEFT JOIN user_roles using (user_id) WHERE role_id = (1) or role_id = (3)")
    List<User> getAllUsers();

    @Delete("delete from users where user_id=#{id}")
    void delete(int id);

    @Select("SELECT * FROM users WHERE login=#{login}")
    User findByLogin(String login);

    @Insert("insert into users(name, surname, login, password) values (#{name},#{surname},#{login},#{password})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "user_id", before = false, resultType=Integer.class)
    void addUser(User user);

    @Insert("insert into users(name, surname, login, password) values (#{name},#{surname},#{login},#{password})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "user_id", before = false, resultType=Integer.class)
    void addManager(Manager manager);

    @Insert("INSERT into user_roles(user_id, role_id) values (#{id},1)")
    void setRoleById(int id);

    @Insert("INSERT into user_roles(user_id, role_id) values (#{id},3)")
    void setMRoleById(int id);

    @Select("SELECT name FROM user_roles LEFT JOIN roles USING(role_id) WHERE user_id=#{id}")
    String getRoleById(int id);


}
