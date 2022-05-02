package com.example.mangmacs.api;

import com.example.mangmacs.model.AddressListModel;
import com.example.mangmacs.model.CartModel;
import com.example.mangmacs.model.ComboMealListModel;
import com.example.mangmacs.model.CurrentOrdersModel;
import com.example.mangmacs.model.CustomerLoginModel;
import com.example.mangmacs.model.CustomerModel;
import com.example.mangmacs.model.DimsumListModel;
import com.example.mangmacs.model.DrinksListModel;
import com.example.mangmacs.model.MealsGoodListModel;
import com.example.mangmacs.model.NoodlesListModel;
import com.example.mangmacs.model.PancitBilaoListModel;
import com.example.mangmacs.model.PancitListModel;
import com.example.mangmacs.model.PastaListModel;
import com.example.mangmacs.model.PizzaListModel;
import com.example.mangmacs.model.PopularListModel;
import com.example.mangmacs.model.PromoListModel;
import com.example.mangmacs.model.ReservationModel;
import com.example.mangmacs.model.RiceListModel;
import com.example.mangmacs.model.RicecupListModel;
import com.example.mangmacs.model.SeafoodsListModel;
import com.example.mangmacs.model.SoupListModel;
import com.example.mangmacs.model.UpdateAccountModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
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
    @GET("getPromoProduct.php")
    Call<List<PromoListModel>> getPromos();
    @GET("customerLogin.php")
    Call<CustomerLoginModel> userLogin(
            @Query("email_address") String email_address,
            @Query("user_password") String password
    );
    @POST("customer-Signup.php")
    @FormUrlEncoded
    Call<CustomerModel> registerUser(
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("email_address") String email,
            @Field("user_password") String password,
            @Field("code") int code
    );
    @GET("selectCustomer.php")
    Call<UpdateAccountModel> selectAccount(
            @Query("email_address") String email
    );
    @POST("customerUpdate.php")
    @FormUrlEncoded
    Call<UpdateAccountModel> updateAccount(
            @Field("email_address") String email,
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("gender") String gender,
            @Field("birthdate") String birthdate
    );
    @Multipart
    @POST("customerUpdatePword.php")
    Call<UpdateAccountModel> updatePassword(
            @Field("email") String email,
            @Field("currentPassword") String oldPword,
            @Field("newPassword") String newPword
    );
    @POST("recoverPassword.php")
    @FormUrlEncoded
    Call<UpdateAccountModel> getEmail(
            @Field("email") String email,
            @Field("code") int code
    );
    @GET("verifyCode.php")
    Call<UpdateAccountModel> getCode(
      @Query("email") String email,
      @Query("code") String code
    );
    @POST("resetPassword.php")
    @FormUrlEncoded
    Call<UpdateAccountModel> resetPassword(
            @Field("email") String email,
            @Field("newPassword") String newPassword,
            @Field("confirmPassword") String confirmPassword
    );
    @GET("verifyEmail.php")
    Call<UpdateAccountModel> getEmail(
            @Query("email") String email,
            @Query("code") String code
    );
    @POST("customerAddress.php")
    @FormUrlEncoded
    Call<UpdateAccountModel> createAddress(
            @Field("customerID") String customerId,
            @Field("fullname") String fullname,
            @Field("email") String email,
            @Field("street") String street,
            @Field("barangay") String barangay,
            @Field("city") String city,
            @Field("province") String province,
            @Field("labelAddress") String labeladdress,
            @Field("phoneNo") String phoneNumber
    );
    @GET("selectAddress.php")
    Call<List<AddressListModel>> getAddress(
            @Query("emailaddress") String email
    );
    @POST("updateCustomerAddress.php")
    @FormUrlEncoded
    Call<UpdateAccountModel> updateCustomerAddress(
            @Field("id") int id,
            @Field("fullname") String fullname,
            @Field("street") String street,
            @Field("barangay") String barangay,
            @Field("labelAddress") String labelAddress,
            @Field("phoneNumber") String phoneNumber
    );
    @POST("deleteAddress.php")
    @FormUrlEncoded
    Call<UpdateAccountModel> deleteAddress(
            @Field("id") String id
    );
    @POST("reservation.php")
    @FormUrlEncoded
    Call<ReservationModel> reservation(
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("guests") String guests,
            @Field("email") String email,
            @Field("phoneNumber") String phoneNumber,
            @Field("scheduled_date") String date,
            @Field("scheduled_time") String time
    );
    @POST("customerCartProduct.php")
    @FormUrlEncoded
    Call<CartModel> addcart(
            @Field("email") String email,
            @Field("code") String productCode,
            @Field("product") String productName,
            @Field("variation") String variation,
            @Field("fname") String firstname,
            @Field("lname") String lastname,
            @Field("price") int amount,
            @Field("quantity") int quantity,
            @Field("add_ons") String addOns,
            @Field("imageProduct") String image
    );
    @GET("selectCart.php")
    Call<List<CartModel>> getCart(
            @Query("emailaddress") String email
    );
    @GET("deleteCart.php")
    Call<CartModel> deleteCart(
            @Query("id") int id
    );
    @POST("updateQuantity.php")
    @FormUrlEncoded
    Call<CartModel> updateQuantity(
            @Field("id") int id,
            @Field("quantity") int quantity
    );
    @POST("customerOrders.php")
    @FormUrlEncoded
    Call<CartModel> insertOrder(
            @Field("productCode[]") ArrayList<String> productCode,
            @Field("accountName") String accountName,
            @Field("recipientName") String recipientName,
            @Field("customer_address") String address,
            @Field("labelAddress") String labelAddress,
            @Field("email") String email,
            @Field("phoneNumber") String phoneNumber,
            @Field("product[]") ArrayList<String> product,
            @Field("variation[]") ArrayList<String> variation,
            @Field("quantity[]") ArrayList<String> quantity,
            @Field("addOns[]") ArrayList<String> addOns,
            @Field("price[]") ArrayList<String> price,
            @Field("subTotal[]") ArrayList<String> subTotal,
            @Field("totalAmount") String totalAmount,
            @Field("paymentPhoto") String paymentPhoto,
            @Field("paymentType") String paymentType,
            @Field("imgProduct[]") ArrayList<String> imgProduct,
            @Field("orderType") String orderType,
            @Field("orderStatus") String orderStatus,
            @Field("requiredDate") String requiredDate,
            @Field("requiredTime") String requiredTime
    );
    @GET("selectNewOrders.php")
    Call<List<CurrentOrdersModel>> getCurrentOrders(
            @Query("emailAddress") String emailAddress
    );
    @GET("selectDeliveredOrders.php")
    Call<List<CurrentOrdersModel>> getDeliveredOrders(
            @Query("emailAddress") String emailAddress
    );
    @GET("selectNewReservation.php")
    Call<List<ReservationModel>> getCurrentBookings(
            @Query("emailAddress") String emailAddress
    );
    @GET("selectProcessedReservation.php")
    Call<List<ReservationModel>> getPreviousBookings(
            @Query("emailAddress") String emailAddress
    );
    @GET("selectNewOrderDetails.php")
    Call<List<CurrentOrdersModel>> getNewOrderDetails(
            @Query("emailAddress") String emailAddress,
            @Query("orderNumber") String orderNumber
    );
    @GET("selectDeliveredOrderDetails.php")
    Call<List<CurrentOrdersModel>> getPreviousOrderDetails(
            @Query("emailAddress") String emailAddress,
            @Query("orderNumber") String orderNumber
    );
    @POST("cancelOrders.php")
    @FormUrlEncoded
    Call<CurrentOrdersModel> cancelOrder(
        @Field("id") String id
    );
    @POST("cancelBookings.php")
    @FormUrlEncoded
    Call<CartModel> cancelBookings(
            @Field("id") String id
    );
}
