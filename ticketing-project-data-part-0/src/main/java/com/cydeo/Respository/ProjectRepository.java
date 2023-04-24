package com.cydeo.Respository;

import com.cydeo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Project findByProjectCode(String code);
}