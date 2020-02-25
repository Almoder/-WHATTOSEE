package com.RaProject.whattosee;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopMoney extends AppCompatActivity {
    private int coinCount;
    private AdView adView1;
    private Button mBuyButton;
    private List<Items> items = new ArrayList();
    ListView countriesList;
    private BillingClient mBillingClient;
    private Map<String, SkuDetails> mSkuDetailsMap = new HashMap<>();
    private TextView coinCountText;
    private String mSkuId = "500";
    private String mSkuId1 = "1000coins";
    private String mSkuId2 = "5000";
    private String mSkuId3 = "10000";
    int p500 = 0;
    int p1000 = 0;
    int p5000 = 0;
    int p10000 = 0;
    private String RES;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_money);
        coinCountText = findViewById(R.id.coin_count_text1);
        coinCount = 0;
        initBilling();

        setInitialData();
        // получаем элемент ListView
        countriesList = (ListView) findViewById(R.id.countriesList);
        // создаем адаптер
        StateAdapter stateAdapter = new StateAdapter(this, R.layout.list_what, items);
        // устанавливаем адаптер
        countriesList.setAdapter(stateAdapter);

        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                switch (position) {
                    case 0:
                        launchBilling(mSkuId);
                        break;
                    case 1:
                        launchBilling(mSkuId1);
                        break;
                    case 2:
                        launchBilling(mSkuId2);
                        break;
                    case 3:
                        launchBilling(mSkuId3);
                        break;
                    default: break;
                }
            }
        };
        countriesList.setOnItemClickListener(itemListener);
    }



    private void initBilling() {
        mBillingClient = BillingClient.newBuilder(this).setListener(new PurchasesUpdatedListener() {
            @Override
            public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
                if (responseCode == BillingClient.BillingResponse.OK && purchases != null) {
                    //here when purchase completed
                    payComplete();
                }
            }
        }).build();
        mBillingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
                if (billingResponseCode == BillingClient.BillingResponse.OK) {
                    //below you can query information about products and purchase
                    querySkuDetails(); //query for products
                    List<Purchase> purchasesList = queryPurchases(); //query for purchases

                    //if the purchase has already been made to give the goods
                    for (int i = 0; i < purchasesList.size(); i++) {
                        String purchaseId = purchasesList.get(i).getSku();
                        if(TextUtils.equals(mSkuId, purchaseId)) {
                            payComplete();
                            p500++;
                        }
                        if(TextUtils.equals(mSkuId1, purchaseId)) {
                            payComplete();
                            p1000++;
                        }
                        if(TextUtils.equals(mSkuId2, purchaseId)) {
                            payComplete();
                            p5000++;
                        }
                        if(TextUtils.equals(mSkuId3, purchaseId)) {
                            payComplete();
                            p10000++;
                        }
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                //here when something went wrong, e.g. no internet connection
            }
        });
    }

    private void querySkuDetails() {
        SkuDetailsParams.Builder skuDetailsParamsBuilder = SkuDetailsParams.newBuilder();
        List<String> skuList = new ArrayList<>();
        skuList.add(mSkuId);
        skuList.add(mSkuId1);
        skuList.add(mSkuId2);
        skuList.add(mSkuId3);
        skuDetailsParamsBuilder.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        mBillingClient.querySkuDetailsAsync(skuDetailsParamsBuilder.build(), new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(int responseCode, List<SkuDetails> skuDetailsList) {
                if (responseCode == 0) {
                    for (SkuDetails skuDetails : skuDetailsList) {
                        mSkuDetailsMap.put(skuDetails.getSku(), skuDetails);

                    }
                }
            }
        });
    }

    private List<Purchase> queryPurchases() {
        Purchase.PurchasesResult purchasesResult = mBillingClient.queryPurchases(BillingClient.SkuType.INAPP);
        return purchasesResult.getPurchasesList();
    }

    public void launchBilling(String skuId) {
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(mSkuDetailsMap.get(skuId))
                .build();
        mBillingClient.launchBillingFlow(this, billingFlowParams);
    }

    private void payComplete() {

    }
    private void addCoins(int coins) {
        coinCount += coins;
        coinCountText.setText("" + coinCount);
    }
    private void setInitialData(){

        //items.add(new Items ("Стальной гигант", "-", R.mipmap.ic_launcher2));
        items.add(new Items(getString(R.string.Buy) + "500 r.a" , "0,99$", R.drawable.coin));
        items.add(new Items(getString(R.string.Buy) + "1000 r.a", "1,39$", R.drawable.coin));
        items.add(new Items(getString(R.string.Buy) + "5000 r.a", "4,95$", R.drawable.coin));
        items.add(new Items(getString(R.string.Buy) + "10000 r.a", "5,94$", R.drawable.coin));
        //items.add(new Items ("Корпорация монстров", "-", R.mipmap.ic_launcher2));
        //items.add(new Items ("Труп Невесты", "-", R.mipmap.ic_launcher2));

    }
}