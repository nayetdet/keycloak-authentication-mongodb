package io.github.nayetdet.keycloak.authentication.mongodb.persistence.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.nayetdet.keycloak.authentication.mongodb.persistence.model.User;

import static com.mongodb.client.model.Filters.eq;

public class UserDAO {

    private static final String USER_COLLECTION_NAME = "user";
    private final MongoCollection<User> userCollection;

    public UserDAO(MongoDatabase mongoDatabase) {
        userCollection = mongoDatabase.getCollection(USER_COLLECTION_NAME, User.class);
    }

    public void save(User user) {
        if (user.getId() == null) {
            userCollection.insertOne(user);
        } else {
            userCollection.replaceOne(eq(user.getId()), user);
        }
    }

}
