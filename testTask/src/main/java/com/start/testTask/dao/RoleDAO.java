package com.start.testTask.dao;

import com.start.testTask.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long>{
}
