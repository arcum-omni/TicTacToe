package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Fields
    private Button[][] buttons;
    private TextView gameMessages;
    private Player x;
    private Player o;
    public Player currPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(" TicTacToe 3 in a Row!");
        getSupportActionBar().setIcon(getDrawable(R.drawable.ic_tictactoe));

        createButtons();
        gameMessages = findViewById(R.id.game_messages);

        startGame();
    }

    private void createButtons() {
        buttons = new Button[][]
                {
                        {findViewById(R.id.button1), findViewById(R.id.button2), findViewById(R.id.button3)},
                        {findViewById(R.id.button4), findViewById(R.id.button5), findViewById(R.id.button6)},
                        {findViewById(R.id.button7), findViewById(R.id.button8), findViewById(R.id.button9)}
                };
    }

    private void startGame() {
        x = new Player("X");
        o = new Player("O");

        currPlayer = x;

        displayCurrentPlayer();
    }

    public void onClick(View v) {
        for (int rows = 0; rows < buttons.length; rows++) {
            for (int cols = 0; cols < buttons[0].length; cols++) {
                if (v == buttons[rows][cols] && buttons[rows][cols].getText() == "") {
                    buttons[rows][cols].setText(currPlayer.playerName);
                }
                else if(v == buttons[rows][cols] && buttons[rows][cols].getText() != "") {
                    swapPlayerTurn();
                }
            }
        }

        if(isThereWinner("X") || isThereWinner("O") || isThereTieGame()){
            gameOver();
            if (isThereWinner("X")) {
                displayGameMessages("Player X Won!");
            }
            else if (isThereWinner("O")) {
                displayGameMessages("Player O Won!");
            }
            else {
                displayGameMessages("Tie Game!");
            }
        }
        else {
            // button logic here
            swapPlayerTurn();
        }
    }

    private boolean isThereWinner(String player) {
        if (// in rows
            (buttons[0][0].getText() == player && buttons[0][1].getText() == player && buttons[0][2].getText() == player ||
             buttons[1][0].getText() == player && buttons[1][1].getText() == player && buttons[1][2].getText() == player ||
             buttons[2][0].getText() == player && buttons[2][1].getText() == player && buttons[2][2].getText() == player) ||
            // or in columns
            (buttons[0][0].getText() == player && buttons[1][0].getText() == player && buttons[2][0].getText() == player ||
             buttons[0][1].getText() == player && buttons[1][1].getText() == player && buttons[2][1].getText() == player ||
             buttons[0][2].getText() == player && buttons[1][2].getText() == player && buttons[2][2].getText() == player) ||
            // or diagonals
            (buttons[0][0].getText() == player && buttons[1][1].getText() == player && buttons[2][2].getText() == player ||
             buttons[0][2].getText() == player && buttons[1][1].getText() == player && buttons[2][0].getText() == player))
        {
            return true;
        }
        return false;
    }

    private boolean isThereTieGame() {
        for (int rows = 0; rows < buttons.length; rows++) {
            for (int cols = 0; cols < buttons[0].length; cols++) {
                if (buttons[rows][cols].getText() == "") {
                    return false;
                }
            }
        }
        return true;
    }

    private void gameOver() {
        for (int rows = 0; rows < buttons.length; rows++) {
            for (int cols = 0; cols < buttons[0].length; cols++) {
                buttons[rows][cols].setEnabled(false);
            }
        }
    }

    public void startButton(View v) {
        for (int rows = 0; rows < buttons.length; rows++) {
            for (int cols = 0; cols < buttons[0].length; cols++) {
                buttons[rows][cols].setText("");
                buttons[rows][cols].setEnabled(true);
            }
        }

        startGame();
    }

    private void swapPlayerTurn() {
        if (currPlayer == x) {
            currPlayer = o;
            displayCurrentPlayer();
        }
        else {
            currPlayer = x;
            displayCurrentPlayer();
        }
    }

    private void displayCurrentPlayer() {
        displayGameMessages("Player " + currPlayer.playerName + "'s Turn");
    }

    private void displayGameMessages(String s) {
        gameMessages.setText(s);
    }
}

