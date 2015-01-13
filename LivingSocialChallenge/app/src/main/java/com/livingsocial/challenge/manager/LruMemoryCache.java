package com.livingsocial.challenge.manager;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.toolbox.ImageLoader.ImageCache;


public class LruMemoryCache extends LruCache<String, Bitmap> implements ImageCache {

	public LruMemoryCache(int maxSize) {
        super(maxSize);
    }
	
	@Override
	public Bitmap getBitmap(String url) {
        Log.d(LruMemoryCache.class.getSimpleName(), "Getting image to memory Cache: " + (get(url) != null));
        return get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
        Log.d(LruMemoryCache.class.getSimpleName(), "Adding image to memory Cache: " + url);
        put(url, bitmap);
	}

}
