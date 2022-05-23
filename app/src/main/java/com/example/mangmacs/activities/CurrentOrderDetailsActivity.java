package com.example.mangmacs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangmacs.R;
import com.example.mangmacs.SharedPreference;
import com.example.mangmacs.adapter.NewOrdersDetailAdapter;
import com.example.mangmacs.api.ApiInterface;
import com.example.mangmacs.api.OrdersListener;
import com.example.mangmacs.api.RetrofitInstance;
import com.example.mangmacs.model.CurrentOrdersModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentOrderDetailsActivity extends AppCompatActivity implements OrdersListener {
    private TextView orderStatus,orderNumber,orderType,totalAmount,arrowBack;
    private TextView dineInName,dineInEmail,pickUpName,pickUpEmail,deliveryName,deliveryPhoneNum,devAddress,devLabelAddress,deliveryTime,paymentMethod;
    private CardView deliveryDetails,pickUpDetails,dineInDetails,devTimeDetails,paymentMethodDetails;
    private RecyclerView newOrderDetailLists;
    private String newAccountName,newEmail,newRecipientName,newPhoneNumber,newLabelAddress,newAddress,newOrderType,newOrderStatus,newOrderNumber,newDeliveryTime,newPaymentMethod;
    private List<CurrentOrdersModel> currentOrdersModels;
    private NewOrdersDetailAdapter newOrdersDetailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order_details);
        arrowBack = findViewById(R.id.arrow_back);
        orderNumber = findViewById(R.id.orderNumber);
        orderStatus = findViewById(R.id.orderStatus);
        orderType = findViewById(R.id.orderType);
        totalAmount = findViewById(R.id.totalAmount);
        dineInDetails = findViewById(R.id.dineInDetails);
        dineInName = findViewById(R.id.dineInName);
        dineInEmail = findViewById(R.id.dineInEmail);
        pickUpDetails = findViewById(R.id.pickUpDetails);
        pickUpName = findViewById(R.id.pickUpName);
        pickUpEmail = findViewById(R.id.pickUpEmail);
        deliveryName = findViewById(R.id.deliveryName);
        deliveryPhoneNum = findViewById(R.id.deliveryPhoneNum);
        devAddress = findViewById(R.id.devAddress);
        devLabelAddress = findViewById(R.id.labelAddress);
        deliveryDetails = findViewById(R.id.deliveryDetails);
        devTimeDetails = findViewById(R.id.devTimeDetails);
        deliveryTime = findViewById(R.id.deliveryTime);
        paymentMethodDetails = findViewById(R.id.paymentMethodDetails);
        paymentMethod = findViewById(R.id.paymentMethod);
        deliveryDetails.setVisibility(View.VISIBLE);
        pickUpDetails.setVisibility(View.VISIBLE);
        dineInDetails.setVisibility(View.VISIBLE);
        devTimeDetails.setVisibility(View.GONE);
        paymentMethodDetails.setVisibility(View.GONE);
        newOrderDetailLists = findViewById(R.id.newOrderDetailLists);
        newOrderDetailLists.setHasFixedSize(true);
        newOrderDetailLists.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dismissOrder();
        showOrders();
        Back();
    }
    private void dismissOrder() {
        Intent intent = getIntent();
        newDeliveryTime = intent.getStringExtra("deliveryTime");
        newAccountName = intent.getStringExtra("customerName");
        newEmail = intent.getStringExtra("email");
        newRecipientName = intent.getStringExtra("recipientName");
        newPhoneNumber = intent.getStringExtra("phoneNumber");
        newLabelAddress = intent.getStringExtra("labelAddress");
        newAddress = intent.getStringExtra("address");
        newOrderType = intent.getStringExtra("orderType");
        newOrderStatus = intent.getStringExtra("orderStatus");
        newOrderNumber = intent.getStringExtra("orderNumber");
        newPaymentMethod = intent.getStringExtra("paymentMethod");
        orderStatus.setText(newOrderStatus);
        orderType.setText(newOrderType);
        orderNumber.setText(newOrderNumber);
        deliveryTime.setText(newDeliveryTime);
        paymentMethod.setText(newPaymentMethod);
        String OrderType = orderType.getText().toString();
        if (OrderType.equals("Pick Up")){
            pickUpName.setText(newAccountName);
            pickUpEmail.setText(newEmail);
            deliveryDetails.setVisibility(View.GONE);
            dineInDetails.setVisibility(View.GONE);
            pickUpDetails.setVisibility(View.VISIBLE);
            paymentMethodDetails.setVisibility(View.VISIBLE);
        }
        else if (OrderType.equals("Deliver")){
            deliveryName.setText(newRecipientName);
            deliveryPhoneNum.setText(newPhoneNumber);
            devAddress.setText(newAddress);
            devLabelAddress.setText(newLabelAddress);
            deliveryDetails.setVisibility(View.VISIBLE);
            devTimeDetails.setVisibility(View.VISIBLE);
            dineInDetails.setVisibility(View.GONE);
            pickUpDetails.setVisibility(View.GONE);
            paymentMethodDetails.setVisibility(View.VISIBLE);
        }
        else{
            dineInName.setText(newAccountName);
            dineInEmail.setText(newEmail);
            dineInDetails.setVisibility(View.VISIBLE);
            pickUpDetails.setVisibility(View.GONE);
            deliveryDetails.setVisibility(View.GONE);
        }
    }
    private void showOrders(){
        String email = SharedPreference.getSharedPreference(this).setEmail();
        String number = orderNumber.getText().toString();
        ApiInterface apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        Call<List<CurrentOrdersModel>> call = apiInterface.getNewOrderDetails(email,number);
        call.enqueue(new Callback<List<CurrentOrdersModel>>() {
            @Override
            public void onResponse(Call<List<CurrentOrdersModel>> call, Response<List<CurrentOrdersModel>> response) {
                currentOrdersModels = response.body();
                newOrdersDetailAdapter = new NewOrdersDetailAdapter(CurrentOrderDetailsActivity.this,currentOrdersModels,CurrentOrderDetailsActivity.this);
                newOrderDetailLists.setAdapter(newOrdersDetailAdapter);
                newOrdersDetailAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<CurrentOrdersModel>> call, Throwable t) {

            }
        });
    }
    private void Back() {
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyOrdersActivity.class));
            }
        });
    }

    @Override
    public void onProductsChange(ArrayList<String> products) {

    }

    @Override
    public void onProductCodeChange(ArrayList<String> productCode) {

    }

    @Override
    public void onVariationChange(ArrayList<String> variations) {

    }

    @Override
    public void onQuantityChange(ArrayList<String> quantity) {

    }

    @Override
    public void onAddOnsChange(ArrayList<String> addOns) {

    }

    @Override
    public void onSubTotalChange(ArrayList<String> subTotal) {

    }

    @Override
    public void onPriceChange(ArrayList<String> price) {

    }

    @Override
    public void onImgProductChange(ArrayList<String> imgProduct) {

    }

    @Override
    public void onCustomerIdChange(String customerId) {

    }

    @Override
    public void onTotalAmountChange(String amount) {
        totalAmount.setText("₱ ".concat(amount).concat(".00"));
    }


}