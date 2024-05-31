package com.example.user_profile.controller;
import com.example.user_profile.model.UserProfile;
import com.example.user_profile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<UserProfile> getUserProfile(@RequestBody String username) {
        return userProfileService.getUserProfile(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<UserProfile> updateUserProfile(@RequestBody UserProfile userProfile) {
        UserProfile updatedProfile = userProfileService.updateUserProfile(userProfile);
        return ResponseEntity.ok(updatedProfile);
    }
}
