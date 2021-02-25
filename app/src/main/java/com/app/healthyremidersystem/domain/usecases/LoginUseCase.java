package com.app.healthyremidersystem.domain.usecases;

import com.app.healthyremidersystem.data.UserRepository;

import androidx.lifecycle.MutableLiveData;

public class LoginUseCase {

    private UserRepository userRepository;

    public LoginUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(String email, String password, MutableLiveData<String> userId) {
        userRepository.login(email, password, userId);
    }
}
