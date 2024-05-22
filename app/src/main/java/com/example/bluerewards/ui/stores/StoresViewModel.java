package com.example.bluerewards.ui.stores;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class StoresViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public StoresViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is stores fragment");
        mText.setValue("Stores:\n\n"+toString(getStores()));
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }


    ArrayList<String> getStores(){
        ArrayList<String> storesList = new ArrayList<>();
        return storesList;
    }
    private static String toString(@NonNull ArrayList<String> arrayStr){
        String result = "";
        for (int i=0; i<arrayStr.size(); i++){
            result += arrayStr.get(i) + "\n";
        }
        return result;
    }
}