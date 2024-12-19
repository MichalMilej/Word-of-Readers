package pl.milej.michal.worldofreaders.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRoleRequest {

    @NotNull
    private UserRole userRole;
}
