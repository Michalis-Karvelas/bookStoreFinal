package com.groupproject.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groupproject.entities.Role;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResponse extends Response{
    private List<Role> roles;
    private Role role;

    public RoleResponse(String msg, List<Role> roles){

        super(msg);
        this.roles=roles;
    }

    public RoleResponse(String msg, Role role){

        super(msg);
        this.role=role;
    }
}
