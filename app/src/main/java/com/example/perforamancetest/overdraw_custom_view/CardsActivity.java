package com.example.perforamancetest.overdraw_custom_view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.performancetest.R;


/**
 * Demonstration Activity to present aview showing overlapping stack of cards. This will demonstrate difference in UI rendering performance
 * for both View with extra drawing and another with clipping to avoid drawing areas of the screen which are not visible
 */
public class CardsActivity extends Activity {
    public static final String TAG = "droid-cards-activity";

    protected static final float DROID_IMAGE_WIDTH = 420f;
    protected static final float CARD_SPACING = DROID_IMAGE_WIDTH / 20;

    private FrameLayout containerView;


    private ConstraintLayout mdroidCardsContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);

        mdroidCardsContainer = findViewById(R.id.activity_droid_cards_container);
        containerView = findViewById(R.id.container_cards_view);

        CardModel[] cardModels = new CardModel[30];

        for(int index =0; index<30; index++){
            if(index%3 ==0){
                cardModels[index] = new CardModel("Joanna", R.color.joanna_color, R.drawable.joanna);
            }
            else if(index%3 ==1){
                cardModels[index] = new CardModel("Shailen", R.color.shailen_color, R.drawable.shailen);
            }
            else {
                cardModels[index] = new CardModel("Chris", R.color.chris_color, R.drawable.chris);

            }
        }

        final UnOptimizedCardsView droidCardView = new UnOptimizedCardsView(
                this,
                cardModels,
                DROID_IMAGE_WIDTH,
                CARD_SPACING
        );

        droidCardView.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));

        findViewById(R.id.btn_add_cards_View).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                containerView.addView(droidCardView);
            }
        });


    }
}