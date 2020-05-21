package com.infosys.connected.h_carev2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class X_ray_Diagonis extends AppCompatActivity {
    private static final int gallerycode = 100;
    private static final int permissioncode = 101;
    String activity;
    Uri fileuri;
    File file;
    TextView x_ray,x_ray_message;
    TableLayout tl;
    ImageView x_ray_imageview;
    Bitmap bitmap;

    public void imagePickers(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select A image"), gallerycode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_ray__diagonis);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.customtoolbar);

        x_ray = findViewById(R.id.x_ray_upload_name);
        tl=findViewById(R.id.x_ray_tl);
        x_ray_message=findViewById(R.id.x_ray_message);
        tl.setVisibility(View.INVISIBLE);
        x_ray_message.setVisibility(View.GONE);
        x_ray_imageview=findViewById(R.id.x_ray_upload_image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                //permission is not granted ,request it
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions, permissioncode);
            } else {
                //permission already granted

            }
        } else {
            //system is less than marshmello
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == gallerycode) {
            fileuri = data.getData();
            file = new File("" + fileuri);
            Cursor cursor = null;
            try {
                cursor = getContentResolver().query(fileuri, new String[]{
                        MediaStore.Images.ImageColumns.DISPLAY_NAME
                }, null, null, null);

                if (cursor != null && cursor.moveToFirst()) {
                    String filenaem=cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME));
                    x_ray.setText(filenaem);
                    if(filenaem.equals("pneumonia.png")||filenaem.equals("normal.png")) {
                     if(filenaem.equals("pneumonia.png")){
                        tl.setVisibility(View.VISIBLE);
                         TextView pneuomiarisk=findViewById(R.id.pneumoniarisk);
                         pneuomiarisk.setText("High Risk");
                         ImageView error=findViewById(R.id.pnerrorimage);
                         error.setVisibility(View.VISIBLE);
                         x_ray_message.setVisibility(View.VISIBLE);
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), fileuri);
                        x_ray_imageview.setImageBitmap(bitmap);
                    }
                    else {
                        TextView pneuomiarisk=findViewById(R.id.pneumoniarisk);
                        pneuomiarisk.setText("Low Risk");
                        ImageView error=findViewById(R.id.pnerrorimage);
                        error.setVisibility(View.INVISIBLE);
                        tl.setVisibility(View.VISIBLE);

                        x_ray_message.setVisibility(View.VISIBLE);
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), fileuri);
                        x_ray_imageview.setImageBitmap(bitmap);
                    }}else {
                        tl.setVisibility(View.INVISIBLE);
                        x_ray_message.setVisibility(View.GONE);
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), fileuri);
                        x_ray_imageview.setImageBitmap(bitmap);
                        Toast.makeText(this,"Upload Correct Image",Toast.LENGTH_LONG).show();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }

    }
