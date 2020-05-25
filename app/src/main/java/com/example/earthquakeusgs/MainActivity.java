package com.example.earthquakeusgs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<word>> {

    private String REQUEST_URL="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=1&limit=100";
    private wordadapter mAdapter;
    ListView listView;
    TextView mEmptyStateTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * mAdapter to be populated by onLoadFinished
         */
        mAdapter = new wordadapter(getApplicationContext(), new ArrayList<word>());

        listView =(ListView) findViewById(R.id.list);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyStateTextView);


        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override                                                                                                // parent= wordadapter
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {                       // view represents current view clicked// position of view in adapter
                String url = mAdapter.getItem(position).getLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        LoaderManager.getInstance(this).initLoader(0,null,this);
//        fetchDataAsyncTask a=new fetchDataAsyncTask();
//        a.execute(REQUEST_URL);
    }

    @NonNull
    @Override
    public Loader<ArrayList<word>> onCreateLoader(int id, @Nullable Bundle args) {
        return new wordListLoader(getApplicationContext(),REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<word>> loader, ArrayList<word> data) {
        //Set empty state text to display "No earthquakes found."
        mEmptyStateTextView.setText("no_earthquakes");
        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<word>> loader) {
        mAdapter.clear();
    }

    /*
    //----------------------------------------------------------------------------------AsyncTask
    private class fetchDataAsyncTask extends AsyncTask<String,Void, ArrayList<word>>{
        @Override
        protected ArrayList<word> doInBackground(String... strings) {
            if(strings.length<1||strings[0]==null){
                return null;
            }
            return QueryUtils.fetchData(strings[0]);
        }
        @Override
        protected void onPostExecute(ArrayList<word> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
    */

}
