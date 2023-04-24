package com.cydeo.service.impl;

import com.cydeo.Respository.UserRepository;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImp(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public List<UserDTO> listAllUsers() {

        List<User> userList = userRepository.findAll(Sort.by("firstName"));

        return userList.stream().map(userMapper::convertToDto).collect(Collectors.toList());

    }

    @Override
    public UserDTO findByUserName(String username) {
        User user = userRepository.findByUserName(username);
        return userMapper.convertToDto(user);
    }

    @Override
    public void save(UserDTO user) {

        userRepository.save(userMapper.convetToEntity(user));

    }

    @Override
    public void deleteByUserName(String username) {

        userRepository.deleteByUserName(username);

    }

    @Override
    public UserDTO update(UserDTO user) {
        //Find the current user
        User user1 = userRepository.findByUserName(user.getUserName());  //has id
        //Map update user dto to entity object
        User convertedUser = userMapper.convetToEntity(user);  // has id?
        //set id to the converted object
        convertedUser.setId(user1.getId());
        //save updated user in the db
        userRepository.save((convertedUser));

        return findByUserName(user.getUserName());
    }

    @Override
    public void delete(String username) {
        //Go to db and get that user with user name.
        User user = userRepository.findByUserName(username);
        //Change the is deleted filed to true
        user.setIsDeleted(true);
        //save the object in the db
        userRepository.save(user);



    }

    @Override
    public List<UserDTO> listAllByRole(String role) {

        List<User> users = userRepository.findByRoleDescriptionIgnoreCase(role);

        return users.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }


}
