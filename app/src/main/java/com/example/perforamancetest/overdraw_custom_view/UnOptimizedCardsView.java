package com.example.perforamancetest.overdraw_custom_view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * A custom view that presents a stacked overlapping collection of  cards. OnDraw method draws the all cards
 * completely irrespective of whether the entire card is visible or not
 */
class UnOptimizedCardsView extends View {
    /**
     * Array of cards to be displayed in the view.
     */
    private CardModel[] mCardModels;

    /**
     * The width of the card that needs to be shown
     */
    float mImageWidth;

    /**
     * THe difference in left edge positions of two consecutive image cards.
     */
    protected float mCardSpacing;

    /**
     * The position of the left edge of each card in the view
     */
    private float mCardLeft;

    /**
     * List of cards which needs to be shown in the view
     */
    private ArrayList<Card> mCards = new ArrayList<Card>();

    /**
     *
     * @param context           application context .
     * @param cardModels        card models for each card to be displayed.
     * @param imageWidth   The width of each card which is shown
     * @param cardSpacing       The difference in left edge positions of two consecutive image cards.
     */
    public UnOptimizedCardsView(Context context, CardModel[] cardModels, float imageWidth,
                                float cardSpacing) {
        super(context);

        mCardModels = cardModels;
        mImageWidth = imageWidth;
        mCardSpacing = cardSpacing;

        // Fire AsyncTasks to fetch and scale the bitmaps.
        for (int i = 0; i < mCardModels.length; i++) {
            new CardWorkerTask().execute(mCardModels[i]);
        }
    }

    /**
     * Drawing implementation to draw all the cards completely .
     */
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Don't draw anything until all the Asynctasks are done and all the Cards are ready.
        if (mCardModels.length > 0 && mCards.size() == mCardModels.length) {
            // Loop over all the cards, except the last one.
            for (int i = 0; i < mCards.size(); i++) {
                // Each card is laid out a little to the right of the previous one.
                mCardLeft = i * mCardSpacing;
                drawCard(canvas, mCards.get(i), mCardLeft, 0);
            }
        }

        // Invalidate the whole view. Doing this calls onDraw() if the view is visible.
        invalidate();
    }

    /**
     * Drawing implementation to draw one card
     */
    protected void drawCard(Canvas canvas, Card card, float left, float top) {
        Paint paint = new Paint();
        Bitmap bm = card.getBitmap();
        CardModel cardModel = card.getCard();

        // Draw outer rectangle.
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        Rect cardRect = new Rect(
                (int)left,
                (int)top,
                (int)left + (int) card.getWidth(),
                (int)top + (int) card.getHeight()
        );
        canvas.drawRect(cardRect, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.DKGRAY);
        canvas.drawRect(cardRect, paint);

        canvas.drawBitmap(
                bm,
                (cardRect.left + (card.getWidth() / 2)) - (bm.getWidth() / 2),
                (cardRect.top + (int) card.getHeaderHeight() + (card.getBodyHeight() / 2)
                        - (bm.getHeight() / 2)),
                null
        );

        paint.setTextSize(card.getTitleSize());
        paint.setColor(getResources().getColor(cardModel.getColor()));
        paint.setStyle(Paint.Style.STROKE);

        int titleLeftOffset = cardRect.left + (int) card.getTitleXOffset();
        int titleTopOffset = cardRect.top + (int) card.getTitleYOffset() +
                (int) card.getTitleSize();

        canvas.drawText(cardModel.getName(), titleLeftOffset, titleTopOffset, paint);
    }

    /**
     * converts drawable resource to bitmap
     */
    public Bitmap makeBitmap(Resources res, int resId, int reqWidth) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize.
        options.inSampleSize = calculateInSampleSize(options, reqWidth);

        // Decode bitmap with inSampleSize set.
        options.inJustDecodeBounds = false;

        // Decode bitmap.
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * Returns a bitmap scaled to the mentioned  width.
     */
    private Bitmap getScaledBitmap(Bitmap bitmap, float width) {
        float scale = width / bitmap.getWidth();
        return Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * scale),
                (int) (bitmap.getHeight() * scale), false);
    }

    /**
     * this methods subsamples the original size image to a smaller image as per the requirement. This saves memory.
     */
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth) {
        // Get the raw width of image.
        final int width = options.outWidth;
        int inSampleSize = 1;

        // Calculate the best inSampleSize.
        if (width > reqWidth) {
            final int halfWidth = width / 2;
            while ((halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * Async task which downloads bitmap on another thread and create cards out of it
     */
    class CardWorkerTask extends AsyncTask<CardModel, Void, Bitmap> {
        CardModel cardModel;
        private final WeakReference<ArrayList<Card>> mCardsReference;

        public CardWorkerTask() {
            mCardsReference = new WeakReference<ArrayList<Card>>(mCards);
        }

        @Override
        protected Bitmap doInBackground(CardModel... params) {
            cardModel = params[0];
            //
            return getScaledBitmap(
                    makeBitmap(getResources(), cardModel.getAvatarId(), (int) mImageWidth),
                    mImageWidth
            );
        }

        /**
         * Adds cards created using bitmap fetched
         */
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            // Check that the list and bitmap are not null.
            if (mCardsReference != null && bitmap != null) {
                // Create a new Card.
                mCards.add(new Card(cardModel, bitmap));
            }
        }
    }
}