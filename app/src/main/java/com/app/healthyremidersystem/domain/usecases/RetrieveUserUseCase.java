package com.app.healthyremidersystem.domain.usecases;

import com.app.healthyremidersystem.data.UserRepository;
import com.app.healthyremidersystem.model.User;

import androidx.lifecycle.MutableLiveData;

public class RetrieveUserUseCase {

    private final UserRepository userRepository;

    public RetrieveUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(String userId, MutableLiveData<User> userMutableLiveData) {
        userRepository.getUserDataById(userId, userMutableLiveData);
    }
}
