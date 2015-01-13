package com.livingsocial.challenge.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.livingsocial.challenge.R;
import com.livingsocial.challenge.manager.ImageManager;
import com.livingsocial.challenge.model.Invoice;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ale on 1/12/15.
 */

public class InvoiceGridAdapter extends AbstractInvoiceAdapter {

    public InvoiceGridAdapter(Context context) {
        super(context);
    }

    @Override
    protected View createView(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ls_gridview_item, null);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.imageview = (NetworkImageView)view.findViewById(R.id.ls_imageview);
        viewHolder.textviewDescription = (TextView)view.findViewById(R.id.ls_textview_description);
        viewHolder.textviewMerchantName = (TextView)view.findViewById(R.id.ls_textview_merchant_name);
        viewHolder.textviewPrice = (TextView)view.findViewById(R.id.ls_textview_price);
        viewHolder.textviewTitle = (TextView)view.findViewById(R.id.ls_textview_title);
        viewHolder.imageview.setDefaultImageResId(R.drawable.ls_red_cross);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    protected void updateView(View convertView, Invoice invoice){
        ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.imageview.setDefaultImageResId(R.drawable.ls_red_cross);
        ImageManager.getInstance(getContext()).requestImage(invoice.getImageUrl(),viewHolder.imageview);
        viewHolder.textviewDescription.setText(invoice.getDescription());
        viewHolder.textviewMerchantName.setText(invoice.getMerchantName());
        viewHolder.textviewPrice.setText(NumberFormat.getCurrencyInstance().format(invoice.getPrice()));
        viewHolder.textviewTitle.setText(invoice.getTitle());
    }

    private static class ViewHolder{
        NetworkImageView imageview;
        TextView textviewMerchantName;
        TextView textviewTitle;
        TextView textviewDescription;
        TextView textviewPrice;
    }


}
