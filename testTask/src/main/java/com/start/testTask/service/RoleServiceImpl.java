package com.start.testTask.service;

import com.start.testTask.dao.PersonDAO;
import com.start.testTask.dao.RoleDAO;
import com.start.testTask.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDAO roleDAO;
    @Override
    public Role saveRole(Role role) {
        return roleDAO.saveAndFlush(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.findAll();
    }

    @Override
    public Role getPersonById(Long id) {
        return roleDAO.getOne(id);
    }

    @Override
    public void deleteRole(Long id) {
        if (roleDAO.existsById(id)) {
            roleDAO.deleteById(id);
        }
    }
}
