package com.example.nearbyrecyclestationmap.Fragments.InformationFolderFragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.nearbyrecyclestationmap.R;
import com.example.nearbyrecyclestationmap.ml.MobilenetV110224Quant;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class captureImage extends AppCompatActivity {

    Bitmap bitmap;
    ImageView imgView;
    Button captureButton;
    Button selectButton;
    Button predictButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_fragment_capture_image);

        //Read label.txt file and arrange into TownList List.
        String fileName = "label.txt";
        List<String> TownList = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(fileName)));
            String mLine;

            while((mLine = reader.readLine()) != null){
                TownList.add(mLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                try {
                    reader.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        //Initialization
        imgView = findViewById(R.id.CenterImage);
        captureButton = findViewById(R.id.camerabtn);
        selectButton = findViewById(R.id.selectbtn);
        predictButton = findViewById(R.id.predictbtn);
        textView = findViewById(R.id.TextView);

        if(ContextCompat.checkSelfPermission(captureImage.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(captureImage.this,new String[]{Manifest.permission.CAMERA},101);
        }

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                SetImage.launch(intent);
            }
        });

        //Select Button Action
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                //startActivityForResult(intent, code)
                OutputMap.launch(intent);

            }
        });

        //Predict Button Action
        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bitmap == null){
                    Toast.makeText(getBaseContext(),"Please select an image before predict.",Toast.LENGTH_SHORT).show();
                    return;
                }

                Bitmap resized = Bitmap.createScaledBitmap(bitmap,224,224,true);

                try {
                    MobilenetV110224Quant model = MobilenetV110224Quant.newInstance(getBaseContext());

                    // Creates inputs for reference.
                    TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);

                    TensorImage tbuffer = TensorImage.fromBitmap(resized);
                    ByteBuffer byteBuffer = tbuffer.getBuffer();

                    inputFeature0.loadBuffer(byteBuffer);

                    // Runs model inference and gets result.
                    MobilenetV110224Quant.Outputs outputs = model.process(inputFeature0);
                    TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                    int max = getMax(outputFeature0.getFloatArray());

                    textView.setText(TownList.get(max));

                    // Releases model resources if no longer used.
                    model.close();
                } catch (IOException e) {
                    // TODO Handle the exception
                }
            }
        });
    }

    public int getMax(float[] arr){
        int index = 0;
        float min = 0f;

        for(int i=0;i<1000;i++){
            if(arr[i] > min){
                index = i;
                min = arr[i];
            }
        }

        return index;
    }

    //onActivityResult
    ActivityResultLauncher<Intent> OutputMap = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();

                    Uri uri = intent.getData();

                    imgView.setImageURI(uri);

                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
    );

    ActivityResultLauncher<Intent> SetImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();

                    Bitmap bm = (Bitmap) intent.getExtras().get("data");
                    bitmap = bm;
                    imgView.setImageBitmap(bitmap);

                }
            }
    );
}