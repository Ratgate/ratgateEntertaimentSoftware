package com.example.ratgateentertainmentsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] heads = {
            "R.drawable.pic01", "R.drawable.pic02", "R.drawable.pic03", "R.drawable.pic04", "R.drawable.pic05", "R.drawable.pic06", 
            "R.drawable.pic07", "R.drawable.pic08", "R.drawable.pic09", "R.drawable.pic10", "R.drawable.pic11", "R.drawable.pic12", 
            "R.drawable.pic13", "R.drawable.pic14", "R.drawable.pic15", "R.drawable.pic16", "R.drawable.pic17", "R.drawable.pic18", 
            "R.drawable.pic19", "R.drawable.pic20", "R.drawable.pic21", "R.drawable.pic22", "R.drawable.pic23", "R.drawable.pic24"};
    String tail = "R.drawable.pic00";
    ArrayList<ImageView> imageIds = new ArrayList<ImageView>();
  //  GridLayout gridLayout = findViewById(R.id.gridLayout);
    Integer[] cardsByOrder = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12};

    Boolean victorious;
    List<Integer> assignedCard;
    int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    Boolean toggledCard;
    ImageView previous;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageIds.add(findViewById(R.id.imageView1));
        imageIds.add(findViewById(R.id.imageView2));
        imageIds.add(findViewById(R.id.imageView3));
        imageIds.add(findViewById(R.id.imageView4));
        imageIds.add(findViewById(R.id.imageView5));
        imageIds.add(findViewById(R.id.imageView6));
        imageIds.add(findViewById(R.id.imageView7));
        imageIds.add(findViewById(R.id.imageView8));
        imageIds.add(findViewById(R.id.imageView9));
        imageIds.add(findViewById(R.id.imageView10));
        imageIds.add(findViewById(R.id.imageView11));
        imageIds.add(findViewById(R.id.imageView12));
        imageIds.add(findViewById(R.id.imageView13));
        imageIds.add(findViewById(R.id.imageView14));
        imageIds.add(findViewById(R.id.imageView15));
        imageIds.add(findViewById(R.id.imageView16));
        imageIds.add(findViewById(R.id.imageView17));
        imageIds.add(findViewById(R.id.imageView18));
        imageIds.add(findViewById(R.id.imageView19));
        imageIds.add(findViewById(R.id.imageView20));
        imageIds.add(findViewById(R.id.imageView21));
        imageIds.add(findViewById(R.id.imageView22));
        imageIds.add(findViewById(R.id.imageView23));
        imageIds.add(findViewById(R.id.imageView24));

   //     play();
    }

    public void play(View view){
 //       gridLayout.setVisibility(View.VISIBLE);
        Log.i("Postep", "Play w ogule odpalil");
        victorious = false;
        toggledCard = false;
        toggledCard = false;
        Log.i("Postep", "ustalil wartosc zmiennych");
        //Wyzeroj stan gry, odwroc wszestkie karty rewersami do gory, przypisz nowe awersy do kart
        assignedCard = Arrays.asList(cardsByOrder);
        Collections.shuffle(assignedCard);
        Log.i("Postep", "Pomieszal tablice kart");

        for (int state:gameState) {
            state = 0;
        }
        Log.i("Postep", "wyzerowal stan gry");


        for (ImageView imageView:imageIds) {
            imageView.setImageResource(R.drawable.pic00);
        }
        Log.i("Postep", "Odwrocil karty rewersami do gory");
    }

    public void clicked(View view){
        ImageView clickedCard = (ImageView) view;
        Log.i("Postep", "Kliknieto" + clickedCard.getTag().toString());

        String tempString;
        int clickedTag = Integer.parseInt(clickedCard.getTag().toString());
        //Sprawdz, czy kliklal w awers czy rewers
        if(gameState[clickedTag]==0){
            //obroc karte, zmien stan gry,

            tempString = "pic" + clickedTag;
            int resID = getResources().getIdentifier(tempString , "drawable", getPackageName());
            clickedCard.setImageResource(resID);
            gameState[clickedTag-1] = 1;

            // sprawdz czy dobrze, zostaw jak tak, schowaj jak nie, kontynuuj jak nie ma do porownania
            //Sprawdz,  czy jest to pierwsze klikniecie

            if(toggledCard){
                //Sprawdz, czy para
                if(assignedCard.get(Integer.parseInt(previous.getTag().toString()) - 1) == assignedCard.get(clickedTag)){
                    //Sprawdz, czy wygrałes
                    victorious = true;
                    for (int state:gameState) {
                        if(state==0){
                            victorious = false;
                            break;
                        }
                    }
                } else {
                    gameState[Integer.parseInt(previous.getTag().toString()) - 1] = 0;
                    gameState[clickedTag-1] = 0;
                    clickedCard.setImageResource(R.drawable.pic00);
                    previous.setImageResource(R.drawable.pic00);
                    }
                toggledCard = false;
            } else {
                toggledCard = true;
                previous = clickedCard;
            }
        }
        //sprawdz, czy wygrałes
        if(victorious){
            Toast.makeText(this, "Congrats, You have won the game", Toast.LENGTH_LONG).show();
        }
    }
}