package com.livingsocial.challenge.manager;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class ImageManager {
	private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private static Context context;
    private static ImageManager imageManager;
    private LruMemoryCache lruMemoryCache;

    private ImageManager(Context context) {
    	this.context = context;
        requestQueue = getRequestQueue();
        lruMemoryCache = new LruMemoryCache( getDefaultLruCacheSize());
        imageLoader = new ImageLoader(requestQueue, lruMemoryCache);
        requestQueue.start();
    }

    private static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        return cacheSize;
    }

    public static synchronized ImageManager getInstance(Context context) {
        if (imageManager == null) {
            imageManager = new ImageManager(context);
        }
        return imageManager;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
    
    public void requestImage(String url, NetworkImageView view) {
        view.setImageUrl(url, imageLoader);
    }

}
