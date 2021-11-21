package com.example.gisteekassignment2.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.Map;

@Configuration
public class MongoConfig {
    @Bean
    @ConditionalOnMissingBean(MongoConverter.class)
    public MappingMongoConverter mappingMongoConverter(MongoDbFactory factory,
    MongoMappingContext context, MongoCustomConversions conversions){
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingMongoConverter = new MappingMongoConverter(dbRefResolver, context);
        mappingMongoConverter.setCustomConversions(conversions);
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return mappingMongoConverter;
    }


}
