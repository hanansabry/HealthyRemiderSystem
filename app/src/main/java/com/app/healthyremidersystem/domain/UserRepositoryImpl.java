package com.app.healthyremidersystem.domain;

import com.app.healthyremidersystem.data.UserRepository;
import com.app.healthyremidersystem.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class UserRepositoryImpl implements UserRepository {

    private final FirebaseAuth auth;

    public UserRepositoryImpl() {
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(String email, String password, MutableLiveData<String> userId) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        userId.setValue(task.getResult().getUser().getUid());
                    } else {
                        userId.setValue(null);
                    }
                });
    }

    @Override
    public void register(User user, MutableLiveData<User> addedUser) {
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        user.setId(task.getResult().getUser().getUid());
                        addedUser.setValue(user);
                    } else {
                        addedUser.setValue(null);
                    }
                });
    }

    @Override
    public void addUserToDB(User user, MutableLiveData<User> userSuccess) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(user.getId()).setValue(user).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                userSuccess.setValue(user);
            } else {
                userSuccess.setValue(null);
            }
        });
    }

    @Override
    public void getUserDataById(String userId, MutableLiveData<User> returnedUser) {
        FirebaseDatabase.getInstance()
                .getReference("users")
                .child(userId)
        .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                user.setId(userId);
                returnedUser.setValue(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                returnedUser.setValue(null);
            }
        });
    }
}
