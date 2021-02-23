package com.app.healthyremidersystem.data;

import com.app.healthyremidersystem.model.User;

import androidx.lifecycle.MutableLiveData;

public interface UserRepository {

    void login(String email, String password, MutableLiveData<Boolean> success);
    void register(User user, MutableLiveData<User> addedUser);
    void addUserToDB(User user, MutableLiveData<User> userSuccess);
}
