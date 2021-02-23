package com.app.healthyremidersystem.domain.usecases;

import com.app.healthyremidersystem.data.UserRepository;
import com.app.healthyremidersystem.model.User;

import androidx.lifecycle.MutableLiveData;

public class AddUserUseCase {

    private UserRepository userRepository;

    public AddUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(User user, MutableLiveData<User> userSuccess) {
        userRepository.addUserToDB(user, userSuccess);
    }
}
