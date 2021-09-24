package com.groupproject.services.interfaces;

import com.groupproject.entities.Role;
import com.groupproject.requests.AccountRequest;
import com.groupproject.requests.RoleRequest;

import java.util.List;

public interface IRoleService {

    // list of all roles
        List<Role> getAll();

        // get role by id
        Role getById(Long id);

        // new role
        void newRole(RoleRequest request);

        //update role(by id)
        Role updateRole(Long id, RoleRequest request);

        // delete role(by id)
        boolean deleteRole(Long id);
}
