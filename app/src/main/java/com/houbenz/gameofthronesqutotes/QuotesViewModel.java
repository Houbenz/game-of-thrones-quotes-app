package com.houbenz.gameofthronesqutotes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class QuotesViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Quote>> savedQuotes;

    public LiveData<ArrayList<Quote>> getSavedQuotes(){
        if (savedQuotes==null){
            savedQuotes=new MutableLiveData<ArrayList<Quote>>();
            loadSavedQuotes();
        }

        return savedQuotes;
    }

    public void loadSavedQuotes(){

    }
}
