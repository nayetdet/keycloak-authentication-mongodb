package io.github.nayetdet.keycloak.authentication.mongodb.persistence.mapper;

import io.github.nayetdet.keycloak.authentication.mongodb.persistence.model.User;

import java.util.UUID;

public class UserMapper {

    private UserMapper() {
    }

    public static User from(UUID keycloakId, String username) {
        return User
                .builder()
                .keycloakId(keycloakId)
                .username(username)
                .build();
    }

}
