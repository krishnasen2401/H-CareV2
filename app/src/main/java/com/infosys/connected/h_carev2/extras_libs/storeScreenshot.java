package com.infosys.connected.h_carev2.extras_libs;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class storeScreenshot {
    public static void shareScreen(Activity a, Bitmap b, String name) {
        String path = Environment.DIRECTORY_PICTURES;
        File folder = new File(a.getExternalFilesDir(null) + "Health");
        try {
            if (!folder.exists()) {
                folder.mkdirs();
                System.out.println("Making dirs");
            }
            File myFile = new File(folder.getAbsolutePath(), name+".jpg");
            myFile.createNewFile();
            FileOutputStream out = new FileOutputStream(myFile);
            b.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            Uri savedImageURI = Uri.parse(myFile.getAbsolutePath());
            Toast.makeText(a.getBaseContext(),String.valueOf(savedImageURI),Toast.LENGTH_SHORT).show();
            Log.d("Path", String.valueOf(savedImageURI));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
