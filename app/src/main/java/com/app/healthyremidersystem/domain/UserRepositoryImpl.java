package com.app.healthyremidersystem.domain;

import com.app.healthyremidersystem.data.UserRepository;
import com.app.healthyremidersystem.model.User;

import androidx.lifecycle.MutableLiveData;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public void login(String email, String password, MutableLiveData<Boolean> success) {

    }

    @Override
    public void register(User user, MutableLiveData<Boolean> success) {

    }
}
