package com.app.healthyremidersystem.presentation.viewmodels;

import com.app.healthyremidersystem.Injection;
import com.app.healthyremidersystem.domain.usecases.RetrieveUserUseCase;
import com.app.healthyremidersystem.model.User;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {

    private final RetrieveUserUseCase retrieveUserUseCase;
    private MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    public UserViewModel() {
        retrieveUserUseCase = Injection.getRetrieveUserUseCase();
    }

    public void getUser(String userId) {
        retrieveUserUseCase.execute(userId, userMutableLiveData);
    }

    public MutableLiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
