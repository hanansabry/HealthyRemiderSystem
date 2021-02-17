package com.app.healthyremidersystem.domain.usecases;

import com.app.healthyremidersystem.data.UserRepository;
import com.app.healthyremidersystem.model.User;

import androidx.lifecycle.MutableLiveData;

public class RegisterUseCase {

    private UserRepository userRepository;

    public RegisterUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user, MutableLiveData<Boolean> success) {
        userRepository.register(user, success);
    }
}
