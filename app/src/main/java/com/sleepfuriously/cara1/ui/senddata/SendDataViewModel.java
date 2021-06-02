package com.sleepfuriously.cara1.ui.senddata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SendDataViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SendDataViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}