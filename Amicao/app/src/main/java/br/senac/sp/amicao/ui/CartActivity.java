package br.senac.sp.amicao.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import br.senac.sp.amicao.R;
import br.senac.sp.amicao.model.Order;
import br.senac.sp.amicao.service.CartManager;
import br.senac.sp.amicao.model.ItemCart;
import br.senac.sp.amicao.service.OrderManager;
import br.senac.sp.amicao.util.Util;

public class CartActivity extends AppCompatActivity {
    private ViewGroup mainLayout;
    private TextView tvTotalPrice;
    private Button btnFinalize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mainLayout = findViewById(R.id.cartListMainLayout);
        tvTotalPrice = findViewById(R.id.tvTitle);
        btnFinalize = findViewById(R.id.btnFinalize);

        btnFinalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Util.getPreference(getApplicationContext()).getBoolean("isLoggedIn", false)){ // nao esta logado
                    Toast toast= Toast.makeText(getApplicationContext(),"Você precisa realizar o login antes de efetuar a compra.",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    i.putExtra("classBack", "cart");
                    startActivityForResult(i, 1);
                    return;
                }



                new AlertDialog.Builder(CartActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Finalizar compra")
                        .setMessage("Tem certeza que deseja finalizar sua compra?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Integer customerId = Util.getPreference(getApplicationContext()).getInt("currentCustomerId", 0);
                                Order order = new Order();
                                order.setCustomerId(customerId);
                                order.setCreatedAt(new Date());
                                order.setItems(CartManager.getInstance().getItems());

                                OrderManager.getInstance().add(order);

                                CartManager.getInstance().clearItems();
                                Toast toast= Toast.makeText(getApplicationContext(),"Compra realizada com sucesso!",Toast.LENGTH_SHORT);
                                toast.setMargin(50,50);
                                toast.show();
                                //Util.showDialog("Compra realizada com sucesso!", "Compra", this);
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                i.putExtra("classBack", "main");
                                startActivityForResult(i, 1);
                                finish();
                            }
                        })
                        .setNegativeButton("Não", null)
                        .show();
            }
        });

        refreshList();
    }

    private void setTvTotalPrice() {
        String formatValue = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(CartManager.getInstance().getTotalPrice());
        tvTotalPrice.setText("Total: " + formatValue);
    }

    private void refreshList() {
        mainLayout.removeAllViews();

        setTvTotalPrice();

        if (CartManager.getInstance().getItems().size() == 0)
            btnFinalize.setVisibility(View.GONE);
        else btnFinalize.setVisibility(View.VISIBLE);

        for (ItemCart item : CartManager.getInstance().getItems()) {
            addCard(item);
        }
    }

    private void addCard(final ItemCart itemCart) {

        try {
            final CardView cardview = (CardView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.cardview_item_cart, mainLayout, false);

            TextView txtTitle = cardview.findViewById(R.id.etName);
            TextView tvPriceUnit = cardview.findViewById(R.id.tvTitle);
            Button btnDelCart = cardview.findViewById(R.id.btnDelCart);
            EditText etQty = cardview.findViewById(R.id.etQty);
            final TextView tvItemTotalPrice = cardview.findViewById(R.id.tvItemTotalPrice);

            txtTitle.setText(itemCart.getProduct().getNomeProduto() == null ? "" : itemCart.getProduct().getNomeProduto());

            String priceUnitFmt = Util.formatBRLValue(itemCart.getProduct().getPrecProduto());
            tvPriceUnit.setText(priceUnitFmt == null ? "" : priceUnitFmt);

            String priceTotalItemFmt = Util.formatBRLValue(itemCart.getProduct().getPrecProduto() * itemCart.getQty());
            tvItemTotalPrice.setText(priceTotalItemFmt == null ? "" : priceTotalItemFmt);

            etQty.setText(itemCart.getQty().toString());


            String url = Util.URL_API + "android/rest/produto/image/" + itemCart.getProduct().getIdProduto();
            ImageView imageView = cardview.findViewById(R.id.ivImage);
            ImageLoader imagemLoader = ImageLoader.getInstance();
            imagemLoader.init(Util.getImageLoaderConfig(getApplicationContext()));
            imagemLoader.displayImage(url, imageView);
/*
            cardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), ProductDetailActivity.class);
                    i.putExtra("id", itemCart.getProduct().getIdProduto());
                    startActivityForResult(i, 1);
                }
            });
*/
            btnDelCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CartManager.getInstance().delete(itemCart);
                    refreshList();
                }
            });

            etQty.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                    int qty = Util.toInteger(s.toString());
                    itemCart.setQty(qty);
                    try {
                        CartManager.getInstance().updateQuantity(itemCart);
                        setTvTotalPrice();
                        String priceTotalItemFmt = Util.formatBRLValue(itemCart.getProduct().getPrecProduto() * itemCart.getQty());
                        tvItemTotalPrice.setText(priceTotalItemFmt == null ? "" : priceTotalItemFmt);
                    } catch (Exception e) {
                        Util.showDialog(e.getMessage(), "Ops!", getApplicationContext());
                        itemCart.setQty(1);
                        setTvTotalPrice();
                        String priceTotalItemFmt = Util.formatBRLValue(itemCart.getProduct().getPrecProduto() * itemCart.getQty());
                        tvItemTotalPrice.setText(priceTotalItemFmt == null ? "" : priceTotalItemFmt);
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });


            mainLayout.addView(cardview);
        } catch (Exception e) {
            //e.printStackTrace();
        }

    }

}
