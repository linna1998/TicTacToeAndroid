package com.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.tictactoe.core.GameChangeListener;
import com.tictactoe.core.TicTacToe;
import com.tictactoe.core.TicTacToeImpl;

public class MainActivity extends AppCompatActivity implements GameChangeListener {

  TicTacToe ticTacToe = new TicTacToeImpl();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ticTacToe.addGameChangeListener(this);

    GridLayout gridLayout = findViewById(R.id.gridView);

    System.out.println("child count: " +  gridLayout.getChildCount());

    for (int i = 0; i < gridLayout.getRowCount(); i++) {

      for (int j = 0; j < gridLayout.getColumnCount(); j++) {
        final Button button = (Button) gridLayout.getChildAt(i * gridLayout.getColumnCount() + j);

        final int finalI = i;
        final int finalJ = j;
        button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View l) {
            button.setText(ticTacToe.getCurrentPlayer());
            System.out.println("Place: " + finalI + " " + finalJ);
            ticTacToe.putPiece(finalI, finalJ);
          }
        });
      }
    }
  }

  @Override
  public void gameEnd(String winner) {
    GridLayout gridLayout = findViewById(R.id.gridView);
    String notation = ticTacToe.getCurrentPlayer() + " WINS!";

    for (int i = 0; i < gridLayout.getRowCount(); i++) {
      for (int j = 0; j < gridLayout.getColumnCount(); j++) {
        Button button = (Button) gridLayout.getChildAt(i * gridLayout.getColumnCount() + j);
        button.setText(notation);
      }
    }

  }
}
