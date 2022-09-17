package com.iiht.security.dto;

import lombok.*;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Document("apikey")
public class ApiKey {

	@Id
    private String key;
    private List<String> services;
}
