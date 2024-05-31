package com.example.user_profile.service;

import com.example.user_profile.repository.UserProfileRepository;
import com.example.user_profile.model.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public Optional<UserProfile> getUserProfile(String username) {
        return userProfileRepository.findByUsername(username);
    }

    public UserProfile updateUserProfile(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }
}
