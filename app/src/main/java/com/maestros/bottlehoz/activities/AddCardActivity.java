package com.maestros.bottlehoz.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.parser.IntegerParser;
import com.denizsubasi.creditcardview.AddCreditCardActivity;
import com.denizsubasi.creditcardview.CreditCardItem;
import com.maestros.bottlehoz.R;
import com.maestros.bottlehoz.adapter.AllListingAdapter;
import com.maestros.bottlehoz.adapter.ShowCreateCartAdapter;
import com.maestros.bottlehoz.databinding.ActivityAddCardBinding;
import com.maestros.bottlehoz.databinding.ActivityCartBinding;
import com.maestros.bottlehoz.model.AllListingModel;
import com.maestros.bottlehoz.model.ShowCreditCartModel;

import java.util.ArrayList;
import java.util.List;

public class AddCardActivity extends AppCompatActivity {
     ActivityAddCardBinding binding;
    private Object CreditCardItem;
    private ArrayList<ShowCreditCartModel> cardList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(AddCreditCardActivity.newIntent(AddCardActivity.this, (com.denizsubasi.creditcardview.CreditCardItem) CreditCardItem), 200);





            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CreditCardItem creditCardItem = data.getExtras().getParcelable(AddCreditCardActivity.KEY_CREDIT_CARD);
        Log.e("AddCardActivity", "onActivityResult: " +creditCardItem);

        showCard(creditCardItem.getHolderName(),creditCardItem.getCardNumber(),creditCardItem.getExpiryDate());
    }

    private void showCard( String holderName,String cardNumber, String expiryDate){
        ShowCreditCartModel model=new ShowCreditCartModel(holderName,cardNumber,expiryDate);
        for (int i = 0; i <1 ; i++) {
           cardList.add(model);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AddCardActivity.this,RecyclerView.VERTICAL,false);
        binding.constraintLayout3.setLayoutManager(layoutManager);
        ShowCreateCartAdapter adapter = new ShowCreateCartAdapter(AddCardActivity.this, cardList);
        binding.constraintLayout3.setAdapter(adapter);
    }

}