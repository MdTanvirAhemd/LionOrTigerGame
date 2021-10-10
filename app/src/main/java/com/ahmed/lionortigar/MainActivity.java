package com.ahmed.lionortigar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.gridlayout.widget.GridLayout;
public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE, TWO, No
    }

    Player currentPlayer = Player.ONE;
    
    Player[] playerChoice = new Player[9];

    int [] [] winnerRowsColumns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    private boolean gameOver = false;
    private Button btnReset;
    private GridLayout gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nullValueSet();

        btnReset = findViewById(R.id.btnReset);
        gridView = findViewById(R.id.gridLayout);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTheGame();
            }
        });

    }
    public void imageViewIsTapped(View imageView) {
        ImageView tappedImageView = (ImageView) imageView;
        int titag = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerChoice[titag] == Player.No && gameOver == false) {

            tappedImageView.setTranslationX(-2000);
            playerChoice[titag] = currentPlayer;

            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }
            tappedImageView.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(1000);
            //Toast.makeText(this, tappedImageView.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winnerColumns : winnerRowsColumns) {
                if (playerChoice[winnerColumns[0]] == playerChoice[winnerColumns[1]]
                        && playerChoice[winnerColumns[1]] == playerChoice[winnerColumns[2]] && playerChoice[winnerColumns[0]] != Player.No) {
                    gameOver = true;

                    if (currentPlayer == Player.ONE) {
                        Toast.makeText(this, "player TWO is the winner", Toast.LENGTH_LONG).show();
                    } else if (currentPlayer == Player.TWO) {
                        Toast.makeText(this, "player ONE is the winner", Toast.LENGTH_LONG).show();

                    }
                    Toast.makeText(this, "We have a winner", Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    private void resetTheGame() {

        for (int index = 0; index < gridView.getChildCount(); index++) {
            ImageView imageView = (ImageView) gridView.getChildAt(index);
            imageView.setImageDrawable(null);
        }
       currentPlayer = Player.ONE;
        nullValueSet();
        gameOver = false;

    }

    private void nullValueSet() {
        for (int i= 0; i< playerChoice.length; i++) {
            playerChoice[i] = Player.No;
        }
    }

}