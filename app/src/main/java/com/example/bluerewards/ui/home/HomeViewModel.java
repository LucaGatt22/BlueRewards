package com.example.bluerewards.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bluerewards.backend.User;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private User user;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome to Blue Rewards.");
    }

    public void setUser(User user) {
        this.user = user;
        mText.setValue("Welcome " + user.getName() + ":\nYou have " + user.getPoints() + " points."); // customise greeting and show points
    }

    public void increasePoints(int amount) {
        user.setPoints(user.getPoints() + amount);
    }

    public LiveData<String> getText() {
        return mText;
    }
}