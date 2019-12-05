package br.senac.sp.amicao.ui;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import br.senac.sp.amicao.R;
import br.senac.sp.amicao.model.Order;
import br.senac.sp.amicao.service.OrderManager;
import br.senac.sp.amicao.util.Util;

public class OrderListFragment extends Fragment {
    private ViewGroup mainLayout;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order_list, container, false);

        mainLayout = view.findViewById(R.id.orderListMainLayout);

        refreshList();
        return view;
    }

    private void refreshList(){
        mainLayout.removeAllViews();

        for (Order order : OrderManager.getInstance().getItems()) {
            addCard(order);
        }
    }
    private void addCard(Order order) {

        try {
            final CardView cardview = (CardView) LayoutInflater.from(getContext()).inflate(R.layout.cardview_order, mainLayout, false);

            TextView tvId = cardview.findViewById(R.id.etName);
            TextView tvTotalPrice = cardview.findViewById(R.id.tvTitle);
            TextView tvCreatedAt = cardview.findViewById(R.id.tvCreatedAt);

            tvId.setText("Código: " + order.getId().toString());
            tvTotalPrice.setText("Total: " + Util.formatBRLValue(order.getTotalPrice()));
            tvCreatedAt.setText("Emissão: " + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(order.getCreatedAt()));

            mainLayout.addView(cardview);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
