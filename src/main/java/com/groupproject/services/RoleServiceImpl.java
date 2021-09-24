package com.groupproject.services;

import com.groupproject.entities.Role;
import com.groupproject.repository.RoleRepository;
import com.groupproject.requests.RoleRequest;
import com.groupproject.services.interfaces.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void newRole(RoleRequest request) {
        log.info("Ready to create and save a new role");
        Role role=new Role(request.getType());
        roleRepository.save(role);
        log.info("The role has been successfully inserted to the DB");
    }

    @Override
    public Role updateRole(Long id, RoleRequest request) {
        log.info("Ready to update an existing role");
        Role existingRole = roleRepository.findById(id).orElse(null);
        if (isNull(existingRole)) {
            log.info("The role does not exists");
            return null;
        }
            existingRole.setType(request.getType());

        Role updatedRole = roleRepository.save(existingRole);
        log.info("The updated role is {}", updatedRole);
        log.info("The updated role has been inserted to the DB");
        return updatedRole;
    }

    @Override
    public boolean deleteRole(Long id) {
        log.info("Ready to delete a role");
        if (isNull(id)){
            log.info("There is no match for this id in the Database");
            return false;
        }
        log.info("Role with the id {} was deleted successfully",id);
        roleRepository.deleteById(id);
        return true;
    }
}
