package com.example.nearbyrecyclestationmap.Constant;

import com.example.nearbyrecyclestationmap.PlaceModel;
import com.example.nearbyrecyclestationmap.R;

import java.util.ArrayList;
import java.util.Arrays;

public interface AllConstant {

    int STORAGE_REQUEST_CODE = 1000;
    int LOCATION_REQUEST_CODE = 2000;
    String IMAGE_PATH = "/Profile/image_profile.jpg";

    ArrayList<PlaceModel> placesName = new ArrayList<>(
            Arrays.asList(
                    new PlaceModel(1, "Recycling Centre", "recycling")
            )
    );
}
