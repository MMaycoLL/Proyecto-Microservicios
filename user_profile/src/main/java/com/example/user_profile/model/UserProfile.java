package com.example.user_profile.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "userProfiles")
public class UserProfile {

    @Id
    private String id;

    private String username;
    private String personalUrl;
    private String nickname;
    private Boolean isContactInfoPublic;
    private String address;
    private String biography;
    private String organization;
    private String country;
    private String socialLinks;
}
