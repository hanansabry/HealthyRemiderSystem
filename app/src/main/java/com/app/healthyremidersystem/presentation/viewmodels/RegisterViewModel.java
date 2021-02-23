package com.app.healthyremidersystem.presentation.viewmodels;

import com.app.healthyremidersystem.Injection;
import com.app.healthyremidersystem.domain.usecases.AddUserUseCase;
import com.app.healthyremidersystem.domain.usecases.RegisterUseCase;
import com.app.healthyremidersystem.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {

    private static final String EG_REGEX = "(^01\\d{9})";

    private RegisterUseCase registerUseCase;
    private AddUserUseCase addUserUseCase;
    private MutableLiveData<User> addedUser = new MutableLiveData<>();
    private MutableLiveData<User> returnedUser = new MutableLiveData<>();
    private MutableLiveData<String> fullNameError = new MutableLiveData<>();
    private MutableLiveData<String> phoneError = new MutableLiveData<>();
    private MutableLiveData<String> emailError = new MutableLiveData<>();
    private MutableLiveData<String> passwordError = new MutableLiveData<>();
    private MutableLiveData<String> confirmPasswordError = new MutableLiveData<>();
    private MutableLiveData<String> weightError = new MutableLiveData<>();
    private MutableLiveData<String> lengthError = new MutableLiveData<>();
    private MutableLiveData<String> genderError = new MutableLiveData<>();

    public RegisterViewModel() {
        this.registerUseCase = Injection.getRegisterUseCase();
        this.addUserUseCase = Injection.getAddUserUserCase();
    }

    public MutableLiveData<User> getUser() {
        return addedUser;
    }

    public MutableLiveData<User> getReturnedUser() {
        return returnedUser;
    }

    public MutableLiveData<String> getFullNameError() {
        return fullNameError;
    }

    public MutableLiveData<String> getPhoneError() {
        return phoneError;
    }

    public MutableLiveData<String> getEmailError() {
        return emailError;
    }

    public MutableLiveData<String> getPasswordError() {
        return passwordError;
    }

    public MutableLiveData<String> getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public MutableLiveData<String> getWeightError() {
        return weightError;
    }

    public MutableLiveData<String> getLengthError() {
        return lengthError;
    }

    public MutableLiveData<String> getGenderError() {
        return genderError;
    }

    public void register(String fullName, String phone, String email,
                         String password, String confirmPassword, double weight,
                         double length, String gender) {
        if (validate(fullName, phone, email, password, confirmPassword, weight, length, gender)) {
            User user = new User(fullName, phone, email, password, weight, length, gender);
            registerUseCase.execute(user, addedUser);
        }
    }

    public void addUser(User user) {
        addUserUseCase.execute(user, returnedUser);
    }

    private boolean validate(String fullName, String phone, String email,
                             String password, String confirmPassword, double weight,
                             double length, String gender) {
        boolean isValid = true;
        if (fullName == null || fullName.isEmpty()) {
            fullNameError.setValue("Required");
            isValid = false;
        }
        if (phone == null || phone.isEmpty()) {
            phoneError.setValue("Required");
            isValid = false;
        }
        if (gender == null || gender.isEmpty()) {
            genderError.setValue("Required");
            isValid = false;
        }
        if (weight == 0) {
            weightError.setValue("Required");
            isValid = false;
        }
        if (length == 0) {
            lengthError.setValue("Required");
            isValid = false;
        }
        if (!matchesRegex(EG_REGEX, phone)) {
            phoneError.setValue("Incorrect phone number");
            isValid = false;
        }
        if (email == null || email.isEmpty()) {
            emailError.setValue("Required");
            isValid = false;
        }
        if (!isEmailValid(email)) {
            emailError.setValue("Incorrect email format");
            isValid = false;
        }
        if (password == null || password.isEmpty()) {
            passwordError.setValue("Required");
            isValid = false;
        } else if (password.length() < 8) {
            passwordError.setValue("Password must be 8 characters or more");
            isValid = false;
        } else if (confirmPassword == null || confirmPassword.isEmpty()) {
            confirmPasswordError.setValue("Required");
            isValid = false;
        } else if (!password.equals(confirmPassword)) {
            confirmPasswordError.setValue("doesn't match password");
            isValid = false;
        }
        return isValid;
    }

    public boolean matchesRegex(String regex, String textToCompare) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(textToCompare).matches();
    }

    public boolean isEmailValid(String email) {
        boolean isValid = false;

        // String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        String expression = "(?!^[.+&'_-]*@.*$)(^[_\\w\\d+&'-]+(\\.[_\\w\\d+&'-]*)*@[\\w\\d-]+(\\.[\\w\\d-]+)*\\.(([\\d]{1,3})|([\\w]{2,}))$)";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
