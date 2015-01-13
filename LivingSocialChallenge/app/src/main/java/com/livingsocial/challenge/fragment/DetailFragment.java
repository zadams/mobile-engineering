package com.livingsocial.challenge.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.livingsocial.challenge.R;
import com.livingsocial.challenge.manager.ImageManager;
import com.livingsocial.challenge.model.Invoice;
import java.text.NumberFormat;

/**
 * Created by Ale on 1/12/15.
 */

public class DetailFragment extends Fragment {
    public static final String BUNDLE_KEY_INVOICE = "BUNDLE_KEY_INVOICE";

    private NetworkImageView imageview;
    private TextView textviewMerchantName;
    private TextView textviewTitle;
    private TextView textviewDescription;
    private TextView textviewPrice;
    private Invoice invoice;

    public DetailFragment(){
        super();
    }

    public static DetailFragment newInstance(Invoice invoice) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_KEY_INVOICE, invoice);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ls_fragment_detail, container, false);
        imageview = (NetworkImageView)view.findViewById(R.id.ls_imageview);
        textviewMerchantName = (TextView)view.findViewById(R.id.ls_textview_merchant_name);
        textviewTitle = (TextView)view.findViewById(R.id.ls_textview_title);
        textviewDescription = (TextView)view.findViewById(R.id.ls_textview_description);
        textviewPrice = (TextView)view.findViewById(R.id.ls_textview_price);
        invoice = (Invoice)getArguments().getSerializable(BUNDLE_KEY_INVOICE);
        updateView();
        return view;
    }

    public void updateView(){
        imageview.setDefaultImageResId(R.drawable.ls_red_cross);
        ImageManager.getInstance(getActivity()).requestImage(invoice.getImageUrl(),imageview);
        textviewMerchantName.setText(invoice.getMerchantName());
        textviewTitle.setText(invoice.getTitle());
        textviewDescription.setText(invoice.getDescription());
        textviewPrice.setText(NumberFormat.getCurrencyInstance().format(invoice.getPrice()));
    }

}
