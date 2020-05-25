package com.example.earthquakeusgs;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

public class wordListLoader extends AsyncTaskLoader<ArrayList<word>> {
    private String mUrl;
    public wordListLoader(@NonNull Context context,String url) {
        super(context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<word> loadInBackground() {
        if(mUrl==null){
            return null;
        }
        return QueryUtils.fetchData(mUrl);
    }
}
