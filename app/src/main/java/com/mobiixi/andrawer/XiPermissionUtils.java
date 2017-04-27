package com.mobiixi.andrawer;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.mobiixi.andrawer.view.XiAlertDialog;

/**
 * Created by xichen on 07/01/17.
 */

public class XiPermissionUtils {
    protected static final int XI_REQUEST_WRITE_EXTERNAL_STORAGE = 0;

    protected static void requestPermissionIfNecessary(final int requestCode,
                                                       final Activity activity)
                                                                throws IllegalArgumentException {
        String requestStr = getRequestString(requestCode);

        if (ContextCompat.checkSelfPermission(activity, requestStr)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                                                                    requestStr)) {

                // show explanation
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestPermissionIfNecessary(requestCode, activity);
                    }
                };

                XiAlertDialog dialog = new XiAlertDialog(activity,
                                                         R.string.need_write_permission,
                                                         listener);
                dialog.show();

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity,
                                                  new String[]{requestStr},
                                                  requestCode);

            }
        }
    }

    protected static void onRequestPermissionsResult(int requestCode,
                                                     String permissions[],
                                                     int[] grantResults) {
        switch (requestCode) {
            case XI_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Andrawer", "****** WRITE permission granted!");

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    private static String getRequestString(int requestCode) throws IllegalArgumentException {
        String requestStr = null;
        switch (requestCode) {
            case XI_REQUEST_WRITE_EXTERNAL_STORAGE:
                requestStr = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        }

        if (requestStr == null) {
            throw new IllegalArgumentException("Can't match request code with any request string!");
        }

        return requestStr;
    }
}
