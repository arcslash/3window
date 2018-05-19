package com.masholdings.pixel.ishara.modelloader.model.entities;

import android.util.Log;

import java.util.Arrays;

/**
 * MAS PIXEL
 * Developed by Ishara Abeykoon.
 * Contact isharaAb@masholdings.com / isharaux@gmail.com
 */
public class Camera {
    String TAG = "CAMERA";
    String cameraName;
    /*Float values for the perspective
    * xfov, aspect_ratio, znear, zfar
    * */
    private Float[] optics;

    public Camera(){
        optics = new Float[]{0f,0f,0f,0f};
    }
    public Camera(String name){
        this.cameraName = name;
        optics = new Float[]{0f,0f,0f,0f};
    }

    public void setOptics(float xfov, float aspect_ratio, float znear, float zfar){

        optics[0] = xfov;
        optics[1] = aspect_ratio;
        optics[2] = znear;
        optics[3] = zfar;
        Log.i(TAG, "Values set:" + Arrays.toString(optics));

    }
    public Float[] getOptics(){

        return optics;
    }
}
