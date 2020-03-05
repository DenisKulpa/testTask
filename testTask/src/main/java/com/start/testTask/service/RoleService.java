package com.start.testTask.service;

import com.start.testTask.entity.Person;
import com.start.testTask.entity.Role;

import java.util.List;

public interface RoleService  {

    public Role saveRole(Role role);

    public List<Role> getAllRoles();

    public Role getRoleById(Long id);

    public void deleteRole(Long id);
}
