package com.example.mangmacs;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("getPizzaProduct.php")
    Call<List<PizzaListModel>> getPizza();
    @GET("getRiceMealProduct.php")
    Call<List<RiceListModel>> getRiceMeal();
    @GET("getComboMealProduct.php")
    Call<List<ComboMealListModel>> getComboMeal();
    @GET("getMealsGoodProduct.php")
    Call<List<MealsGoodListModel>> getMealsGood();
    @GET("getSeafoodsProduct.php")
    Call<List<SeafoodsListModel>> getSeafoods();
    @GET("getSoupProduct.php")
    Call<List<SoupListModel>> getSoup();
    @GET("getRiceProduct.php")
    Call<List<RicecupListModel>> getRice();
    @GET("getPancitProduct.php")
    Call<List<PancitListModel>> getPancit();
    @GET("getPancitBilaoProduct.php")
    Call<List<PancitBilaoListModel>> getPancitBilao();
    @GET("getPastaProduct.php")
    Call<List<PastaListModel>> getPasta();
    @GET("getDimsumProduct.php")
    Call<List<DimsumListModel>> getDimsum();
    @GET("getDrinksProduct.php")
    Call<List<DrinksListModel>> getDrinks();
    @GET("getNoodlesProduct.php")
    Call<List<NoodlesListModel>> getNoodles();
    @GET("getPopularProduct.php")
    Call<List<PopularListModel>> getPopular();
    @POST("customer-Login.php")
    @FormUrlEncoded
    Call<CustomerModel> userLogin(
            @Field("email_address") String email
            //@Field("user_password") String password
    );
    @POST("customer-Signup.php")
    @FormUrlEncoded
    Call<CustomerModel> registerUser(
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("email_address") String email,
            @Field("contact") String contact,
            @Field("user_password") String password
    );
    @POST("reservation.php")
    @FormUrlEncoded
    Call<ReservationModel> reservation(
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("guests") String guests,
            @Field("email") String email,
            @Field("scheduled_date") String date,
            @Field("scheduled_time") String time
    );

}
