package br.senac.sp.amicao.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
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

import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.senac.sp.amicao.R;
import br.senac.sp.amicao.model.Cart;
import br.senac.sp.amicao.model.ItemCart;
import br.senac.sp.amicao.model.Product;
import br.senac.sp.amicao.util.Util;

public class CartActivity extends AppCompatActivity {
    private ViewGroup mainLayout;
    private TextView tvTotalPrice;
    private Button btnFinalize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mainLayout = findViewById(R.id.productListMainLayout);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnFinalize = findViewById(R.id.btnFinalize);

        refreshList();
    }
    private void setTvTotalPrice(){
        String formatValue = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(Cart.getInstance().getTotalPrice());
        tvTotalPrice.setText("Total: " + formatValue);
    }

    private void refreshList(){
        mainLayout.removeAllViews();

        setTvTotalPrice();

        if(Cart.getInstance().getItems().size() == 0)
            btnFinalize.setVisibility(View.GONE);
        else btnFinalize.setVisibility(View.VISIBLE);

        for (ItemCart item : Cart.getInstance().getItems()) {
            addCard(item);
        }
    }
    private void addCard(final ItemCart itemCart) {

        try {
            final CardView cardview = (CardView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.cardview_item_cart, mainLayout, false);

            TextView txtTitle = cardview.findViewById(R.id.txtNome);
            TextView tvPriceUnit = cardview.findViewById(R.id.txtPreco);
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
                    Cart.getInstance().delete(itemCart);
                    refreshList();
                }
            });

            etQty.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                    int qty = Util.toInteger(s.toString());
                    itemCart.setQty(qty);
                    try {
                        Cart.getInstance().updateQuantity(itemCart);
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
