package com.auchanclub.model.lists;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class List {

    @Id
    Long id;

    @Field(value = "Creator_Id")
    private Long creatorId;

}
