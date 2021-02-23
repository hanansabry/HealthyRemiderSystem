package com.app.healthyremidersystem.domain;

import com.app.healthyremidersystem.data.UserRepository;
import com.app.healthyremidersystem.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class UserRepositoryImpl implements UserRepository {

    private final FirebaseAuth auth;

    public UserRepositoryImpl() {
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(String email, String password, MutableLiveData<Boolean> success) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    success.setValue(task.isSuccessful());
                });
    }

    @Override
    public void register(User user, MutableLiveData<User> addedUser) {
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        addedUser.setValue(user);
                    } else {
                        addedUser.setValue(null);
                    }
                });
    }

    @Override
    public void addUserToDB(User user, MutableLiveData<User> userSuccess) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference userId = databaseReference.push();
        userId.setValue(user).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                user.setId(userId.getKey());
                userSuccess.setValue(user);
            } else {
                userSuccess.setValue(null);
            }
        });
    }
}
