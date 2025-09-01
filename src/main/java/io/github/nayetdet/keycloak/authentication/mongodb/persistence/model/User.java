package io.github.nayetdet.keycloak.authentication.mongodb.persistence.model;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @BsonId
    private ObjectId id;
    private UUID keycloakId;
    private String username;

}
