package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;

    //0 - 0 , 1 - X

    int activePlayer = 0;

    int[]GameState = {-1 , -1 , -1 , -1 , -1 , -1 , -1 , -1 , -1};

    int[][]WinState = { {0,1,2} , {3,4,5} , {6,7,8},
                        {0,3,6} , {1,4,7} , {2,5,8},
                        {0,4,8},{2,4,6}};

    public void playerTap(View view){
        boolean check = true;
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());



        if(GameState[tappedImage] == -1 && gameActive == true){
            GameState[tappedImage] = activePlayer;

            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.zerops);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn");

            }
            else {
                img.setImageResource(R.drawable.crossps);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("0's Turn");

            }
            img.animate().translationYBy(1000f).setDuration(200);
        }

        for(int[] winPosition: WinState) {
            if (GameState[winPosition[0]] == GameState[winPosition[1]] &&
                    GameState[winPosition[1]] == GameState[winPosition[2]] &&
                    GameState[winPosition[0]] != -1) {
                gameActive = false;
                String winnerStr;

                if (GameState[winPosition[0]] == 0) {
                    winnerStr = "0 has won";
                } else {
                    winnerStr = "X has won";
                }

                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }
        }
        if(gameActive == true){
            for(int i =0 ; i<GameState.length ; i++){
                if(GameState[i] == -1){
                    check = false;
                    break;
                }
            }
            if(check == true){
                gameActive = false;
                TextView status = findViewById(R.id.status);
                status.setText("Draw!");
            }

        }




    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<GameState.length; i++){
            GameState[i] = -1;
        }
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("0's Turn");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}