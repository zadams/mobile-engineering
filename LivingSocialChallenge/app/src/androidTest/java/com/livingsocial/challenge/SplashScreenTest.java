package com.livingsocial.challenge;

import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.livingsocial.challenge.R;
import com.livingsocial.challenge.activity.SplashActivity;
import com.livingsocial.challenge.manager.DataManager;
import com.livingsocial.challenge.manager.ImageManager;
import com.livingsocial.challenge.model.Invoice;

import java.util.List;

/**
 * Created by Ale on 1/13/15.
 */
public class SplashScreenTest extends ActivityInstrumentationTestCase2 {

    public SplashScreenTest() {
        super(SplashActivity.class);
    }
    public void testUIState() throws Exception {
        Button button = (Button)getActivity().findViewById(R.id.ls_button);
        assertNotNull(button);
        ProgressBar progressBar = (ProgressBar)getActivity().findViewById(R.id.ls_progressbar);
        assertNotNull(progressBar);
    }

    public void testReadingInvoiceFile() throws Exception {
        DataManager.getInstance(getActivity()).loadInvoice(SplashActivity.INVOICE_FILE, null);
        List<Invoice> list = DataManager.getInstance(getActivity()).getInvoices();
        assertNotNull(list);
        // Number of of item is 100
        assertEquals(list.size(), 100);
    }

    public void testInvoiceFileContent() throws Exception {
        DataManager.getInstance(getActivity()).loadInvoice(SplashActivity.INVOICE_FILE, null);
        List<Invoice> list = DataManager.getInstance(getActivity()).getInvoices();
        for (Invoice invoice : list) {
            assertFalse(TextUtils.isEmpty(invoice.getDescription()));
            assertFalse(TextUtils.isEmpty(invoice.getImageUrl()));
            assertFalse(TextUtils.isEmpty(invoice.getMerchantName()));
            assertTrue(invoice.getPrice() >= 0);
            assertFalse(TextUtils.isEmpty(invoice.getTitle()));

        }
    }

}
