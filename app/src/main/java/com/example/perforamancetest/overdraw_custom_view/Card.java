
package com.example.perforamancetest.overdraw_custom_view;

import android.graphics.Bitmap;


class Card {

    protected static final float SPACE_AROUND_IMAGE = 20f;

    protected static final float BITMAP_HEIGHT_HEADER_HEIGHT_RATIO = .25f;

    private CardModel mCardModel;
    private Bitmap mBitmap;

    private float mHeaderHeight;
    private float mBodyHeight;
    private float mTitleSize;

    Card(CardModel cardModel, Bitmap bitmap) {
        mCardModel = cardModel;
        mBitmap = bitmap;

        mBodyHeight = mBitmap.getHeight() + SPACE_AROUND_IMAGE;
        mHeaderHeight = mBitmap.getHeight() * BITMAP_HEIGHT_HEADER_HEIGHT_RATIO;
        mTitleSize = mHeaderHeight / 2;
    }

    private String logDimensions() {
        return "mBodyHeight = " + mBodyHeight +
                ", mHeaderHeight = " + mHeaderHeight +
                ", mTitleSize = " + mTitleSize +
                ", getWidth() = " + String.valueOf(getWidth());
    }

    protected float getWidth() {
        return mBitmap.getWidth() + (2 * SPACE_AROUND_IMAGE);
    }

    protected float getBodyHeight() {
        return mBodyHeight;
    }

    protected float getHeaderHeight() {
        return mHeaderHeight;
    }

    protected float getHeight() {
        return getBodyHeight() + getHeaderHeight();
    }

    protected float getTitleSize() {
        return mTitleSize;
    }

    protected Bitmap getBitmap() {
        return mBitmap;
    }

    protected CardModel getCard() {
        return mCardModel;
    }

    protected float getTitleXOffset() {
        return SPACE_AROUND_IMAGE;
    }

    protected float getTitleYOffset() {
        return SPACE_AROUND_IMAGE;
    }
}