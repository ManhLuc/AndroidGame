package com.websarva.wings.android.kanjibuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ToggleButton;

import java.util.*;

public class MainActivity<LoginActivity> extends AppCompatActivity {

    int[] pieceId =
            {R.id.piece_9, R.id.piece_8, R.id.piece_7,
                    R.id.piece_6, R.id.piece_5, R.id.piece_4,
                    R.id.piece_3, R.id.piece_2, R.id.piece_1};
    int[] answer =
            {R.drawable.yuki_9, R.drawable.yuki_8, R.drawable.yuki_7,
                    R.drawable.yuki_6, R.drawable.yuki_5, R.drawable.yuki_4,
                    R.drawable.yuki_3, R.drawable.yuki_2, R.drawable.yuki_1};


    boolean clicked = false;
    ImageView clickedView;
    int clickedTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //make shuffled pieces layout to start a game using answer pieces and show them
        shufflePieces(answer, pieceId);
        //set an event listener on the pieces
        ListenerOnPiece lp = new ListenerOnPiece();
        for (int i = 0; i < pieceId.length; i++) {
            ImageView iv = findViewById(pieceId[i]);
            iv.setOnClickListener(lp);
        }
    }

        private void shufflePieces ( int[] ans, int[] pi){
            //shuffle pieces

            Integer[] intArrObj = new Integer[ans.length];
            for (int i = 0; i < intArrObj.length; i++)
                intArrObj[i] = new Integer(ans[i]);
            List<Integer> intListObj = Arrays.asList(intArrObj);
            Collections.shuffle(intListObj);
            intListObj.toArray(intArrObj);
            for (int i = 0; i < intArrObj.length; i++) {
                ImageView iv = findViewById(pi[i]);
                int n = intArrObj[i].intValue();
                //set the name as same as drawable to tag
                iv.setTag(n);
                //show pieces after shuffle
                iv.setImageResource(n);
                //set margins to 4
                LayoutParams lpms = iv.getLayoutParams();
                MarginLayoutParams mlpms = (MarginLayoutParams) lpms;
                mlpms.setMargins(4, 4, 4, 4);
                iv.setLayoutParams(mlpms);
            }
        }


        private class ListenerOnPiece implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                ImageView iv = findViewById(view.getId());
                iv.setColorFilter(0x11ff0000, PorterDuff.Mode.SRC_ATOP);
                int ctg = Integer.parseInt(iv.getTag().toString());
                if (clicked) {
                    //work for second selecting
                    clicked = false;
                    //clear color filters
                    clickedView.clearColorFilter();
                    iv.clearColorFilter();
                    //swap clickedTag and ctg
                    iv.setImageResource(clickedTag);
                    clickedView.setImageResource(ctg);
                    iv.setTag(Integer.toString(clickedTag));
                    clickedView.setTag(Integer.toString(ctg));
                    //check answer
                    boolean collect = true;
                    for (int i = 0; i < answer.length; i++) {
                        ImageView tmp = findViewById(pieceId[i]);
                        if (answer[i] != Integer.parseInt(tmp.getTag().toString())) {
                            collect = false;
                        }
                    }
                    // in case of correct
                    if (collect) {
                        //set margins to 0
                        for (int i = 0; i < pieceId.length; i++) {
                            ImageView tmp = findViewById(pieceId[i]);
                            LayoutParams lpms = tmp.getLayoutParams();
                            MarginLayoutParams mlpms = (MarginLayoutParams) lpms;
                            mlpms.setMargins(0, 0, 0, 0);
                            tmp.setLayoutParams(mlpms);
                        }
                        //show a message of "Perfect"
                        Toast t = Toast.makeText(MainActivity.this, "Perfect!", Toast.LENGTH_LONG);
                        t.setGravity(Gravity.TOP, 0, 300);
                        t.show();
                    }
                } else {
                    //work for first selecting
                    clicked = true;
                    clickedView = iv;
                    clickedTag = ctg;
                }

            }
        }

        public void btFinish (View v){
            this.finish();

        }
        public void shuffle (View v){

            shufflePieces(answer, pieceId);


                /*Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);*/



            }



        public void cotae (View v){
            setContentView(R.layout.activity_main);
            ListenerOnPiece lp = new ListenerOnPiece();
            for (int i = 0; i > pieceId.length; i++) {
                ImageView iv = findViewById(pieceId[i]);
                iv.setOnClickListener(lp);
            }
        }

    }

    









