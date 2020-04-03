package com.project.chatapp.Fragments;

import com.project.chatapp.Notifications.MyResponse;
import com.project.chatapp.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAxNPVxXA:APA91bFr3wqYq92UpgmxzYQsFnrZPbnBu625VTWNOJ9JgiHnzTNjExA0yT2AZ-MYZaM_0l9_osXYjL981K_ZxPP0lUS9Trg_K_0yTFJaRfY_UFGKewMpPRr-7e5qZffyQRh0pr9Bhc9e"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
