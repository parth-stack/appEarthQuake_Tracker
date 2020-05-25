package com.example.earthquakeusgs;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.security.AccessController.getContext;

public class wordadapter extends ArrayAdapter<word> {

    public wordadapter(@NonNull Context context, ArrayList<word> words) {
        super(context,0,words);
        ////resource id=0 because we don't need android to implement any resource file we will do it manually by override getview method
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //----------------------------
        View listitemview=convertView;
        if(listitemview==null){
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        word curentword=getItem(position);
        //----------------------------

        ////////////////////////////////////////////////////////////////////////////////////////////
        TextView magnitudeView = listitemview.findViewById(R.id.magnitude);
        String formattedMagnitude = formatMagnitude(curentword.getMagnitude());
        magnitudeView.setText(formattedMagnitude);
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(curentword.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        ////////////////////////////////////////////////////////////////////////////////////////////
        String originalLocation=curentword.getLocation();
        String primaryLocation,locationOffset;

        if (originalLocation.contains("of")) {
            String[] parts = originalLocation.split("of");
            locationOffset = parts[0] + "of";
            primaryLocation = parts[1];
        } else {
            locationOffset = "Near_the";
            primaryLocation = originalLocation;
        }

        TextView location_offsetView = listitemview.findViewById(R.id.location_offset);
        location_offsetView.setText(locationOffset);

        TextView primary_locationView = listitemview.findViewById(R.id.primary_location);
        primary_locationView.setText(primaryLocation);

        ////////////////////////////////////////////////////////////////////////////////////////////
        Long mTimeInMilliseconds=curentword.getmTimeInMilliseconds();
        Date dateObj=new Date(mTimeInMilliseconds);

        TextView dateView=listitemview.findViewById(R.id.date);
        dateView.setText(formatDate(dateObj));

        TextView timeView=listitemview.findViewById(R.id.time);
        timeView.setText(formatTime(dateObj));


        return listitemview;

    }





    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        //CONVERTING COLOR RESOURCE ID TO COLOR INTEGER
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
