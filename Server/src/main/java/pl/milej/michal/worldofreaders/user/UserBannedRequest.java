package pl.milej.michal.worldofreaders.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserBannedRequest {

    @NotNull
    private Boolean banned;
}
