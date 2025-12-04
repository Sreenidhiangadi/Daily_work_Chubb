package com.cachedmo.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Todo implements Serializable {
	@Id
    private Long id;
    private String title;
}

