package com.ravi.camerausingsurfacecamera;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by LENOVO on 11-11-2017.
 */

public class SurfaceCamera extends Activity implements SurfaceHolder.Callback {
    SurfaceView surfacecamera;
    SurfaceHolder surfaceHolder;
  //  Camera camera;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surfaceview);

      //  surfacecamera=(SurfaceView)findViewById(R.id.camerapreview);
        this.surfaceHolder = surfacecamera.getHolder();
        this.surfaceHolder.addCallback(this);

        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
       // View view1=inflater.inflate(R.layout.traveltracker_title, null);
        View view2=inflater.inflate(R.layout.traveltracker_camera_control, null);
        ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
       // addContentView(view1, localLayoutParams);
        addContentView(view2, localLayoutParams);




        Button capture=(Button)view2.findViewById(R.id.capture_image_button);

        Button skip=(Button)view2.findViewById(R.id.skipp_button);

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(SurfaceCamera.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    requeststorage();

                }
                else {
                 //   SurfaceCamera.this.camera.takePicture(SurfaceCamera.this.myShutterCallback, SurfaceCamera.this.myPictureCallback_RAW, SurfaceCamera.this.myPictureCallback_JPG);
                }
            }
        });

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void  requeststorage() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            Log.i("PERM_TAG",
                    "Displaying location permission rationale to provide additional context.");

//            Snackbar.make(mLayout, "Request permission to get Contacts",
//                    Snackbar.LENGTH_INDEFINITE)
//                    .setAction("Ok", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//
//                            ActivityCompat.requestPermissions(BenefactorInfo.this,
//                                    new String[]{Manifest.permission.READ_CONTACTS},
//                                    1);
//
//
//                                                   }
//                    })
//                    .show();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Permission");
            alertDialog.setMessage("Request permission for external storage");
            alertDialog.setCancelable(false);
            alertDialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(SurfaceCamera.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);

                    dialog.cancel();
                }
            });

            alertDialog.show();


        } else {


            ActivityCompat.requestPermissions(SurfaceCamera.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);


        }
    }


    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    //  startActivityForResult(new Intent("android.intent.action.PICK", ContactsContract.Contacts.CONTENT_URI), BenefactorInfo.PICK_CONTACT);
                //    SurfaceCamera.this.camera.takePicture(SurfaceCamera.this.myShutterCallback, SurfaceCamera.this.myPictureCallback_RAW, SurfaceCamera.this.myPictureCallback_JPG);


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.



                    Toast.makeText(this, "Unable to storage the camera file", Toast.LENGTH_LONG).show();

//                    Intent localIntent = new Intent(SurfaceCamera.this, TravelTrackerRecord.class);
//                    localIntent.putExtra("Photo", "");
//                    SurfaceCamera.this.startActivity(localIntent);
//                    SurfaceCamera.this.finish();
                }
                return;
            }



            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
