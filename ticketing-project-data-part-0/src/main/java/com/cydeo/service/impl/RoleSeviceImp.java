package com.cydeo.service.impl;

import com.cydeo.config.Respository.RoleRepository;
import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import com.cydeo.mapper.RoleMapper;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleSeviceImp implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    public RoleSeviceImp(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }


    @Override
    public List<RoleDTO> listAllRoles() {

        //Controller called me and requesting all RoleDTOs so it can show in the drop-down in the ui
        //I need to make a call to db and get all the roles from table
        //Go to repository and find a service (method) which gives me the roles form db
        //how I will call any service here?

//        List<Role> roleList = roleRepository.findAll();  see below OR

        // I have Role entities from DB
        //I need to convert those Role entities to DTOs
        //I need to use Modelmapper.
        //I already crated a class called RoleMapper and there are methods for me that makes this conversion


        return roleRepository.findAll().stream().map(roleMapper::convertToDto).collect(Collectors.toList());

        //OR

//        roleList.stream().map(roleMapper::convertToDto);
//        return roleList.stream().map(roleMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}
