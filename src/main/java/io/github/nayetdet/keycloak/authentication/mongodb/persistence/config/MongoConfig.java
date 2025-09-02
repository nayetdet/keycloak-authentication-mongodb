package io.github.nayetdet.keycloak.authentication.mongodb.persistence.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.UuidRepresentation;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.eclipse.microprofile.config.ConfigProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoConfig {

    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;

    private MongoConfig() {
    }

    public static synchronized MongoClient getMongoClient() {
        if (mongoClient == null) {
            var settings = MongoClientSettings
                    .builder()
                    .applyConnectionString(new ConnectionString(ConfigProvider.getConfig().getValue("quarkus.mongodb.connection-string", String.class)))
                    .uuidRepresentation(UuidRepresentation.STANDARD)
                    .build();

            mongoClient = MongoClients.create(settings);
        }

        return mongoClient;
    }

    public static synchronized MongoDatabase getMongoDatabase() {
        if (mongoDatabase == null) {
            var pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
            var pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
            mongoDatabase = getMongoClient().getDatabase(ConfigProvider.getConfig().getValue("quarkus.mongodb.database", String.class)).withCodecRegistry(pojoCodecRegistry);
        }

        return mongoDatabase;
    }

}
