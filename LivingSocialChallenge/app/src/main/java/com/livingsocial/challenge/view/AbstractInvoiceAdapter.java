package com.livingsocial.challenge.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import com.livingsocial.challenge.model.Invoice;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ale on 1/12/15.
 */

public abstract class AbstractInvoiceAdapter extends BaseAdapter implements Filterable {
    private List<Invoice> list = new ArrayList<Invoice>();
    private List<Invoice> filteredList = new ArrayList<Invoice>();
    private InvoiceFilter filter = new InvoiceFilter();


    private Context context;

    public AbstractInvoiceAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Invoice getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = createView();
        }
        //Update view
        Invoice invoice = getItem(position);
        updateView(convertView, invoice);
        return convertView;
    }

    protected Context getContext(){
        return context;
    }

    public void setData(List<Invoice> list){
        if(list != null){
            this.list = list;
            filteredList = list;
            notifyDataSetChanged();
        }
    }
    public void resetFilter(){
        filteredList = list;
        notifyDataSetChanged();;
    }
    @Override
    public Filter getFilter() {
        return filter;
    }

    protected abstract View createView();

    protected abstract void updateView(View convertView, Invoice invoice);


    private class InvoiceFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            final List<Invoice> originalList = list;
            int count = originalList.size();

            final ArrayList<Invoice> filteredList = new ArrayList<Invoice>(count);
            Invoice filterableString ;
            for (Invoice invoice: originalList) {
                if (invoice.getTitle().toLowerCase().startsWith(filterString)) {
                    filteredList.add(invoice);
                }
            }
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (ArrayList<Invoice>) results.values;
            notifyDataSetChanged();
        }

    }

}
