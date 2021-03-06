package com.example.ratgateentertainmentsoftware;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int[] heads = {
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6,
            R.drawable.pic7, R.drawable.pic8, R.drawable.pic9, R.drawable.pic10, R.drawable.pic11, R.drawable.pic12};
    int tail = R.drawable.pic00;
    ArrayList<ImageView> imageViews = new ArrayList<>();
    Integer[] cardsByOrder = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11};
    int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    List<Integer> assignedCard;
    Boolean shuffled;
    Boolean victorious;
    Boolean toggledCard;
    ImageView previous;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViews.add(findViewById(R.id.imageView1));
        imageViews.add(findViewById(R.id.imageView2));
        imageViews.add(findViewById(R.id.imageView3));
        imageViews.add(findViewById(R.id.imageView4));
        imageViews.add(findViewById(R.id.imageView5));
        imageViews.add(findViewById(R.id.imageView6));
        imageViews.add(findViewById(R.id.imageView7));
        imageViews.add(findViewById(R.id.imageView8));
        imageViews.add(findViewById(R.id.imageView9));
        imageViews.add(findViewById(R.id.imageView10));
        imageViews.add(findViewById(R.id.imageView11));
        imageViews.add(findViewById(R.id.imageView12));
        imageViews.add(findViewById(R.id.imageView13));
        imageViews.add(findViewById(R.id.imageView14));
        imageViews.add(findViewById(R.id.imageView15));
        imageViews.add(findViewById(R.id.imageView16));
        imageViews.add(findViewById(R.id.imageView17));
        imageViews.add(findViewById(R.id.imageView18));
        imageViews.add(findViewById(R.id.imageView19));
        imageViews.add(findViewById(R.id.imageView20));
        imageViews.add(findViewById(R.id.imageView21));
        imageViews.add(findViewById(R.id.imageView22));
        imageViews.add(findViewById(R.id.imageView23));
        imageViews.add(findViewById(R.id.imageView24));
        shuffled = false;
        previous = findViewById(R.id.imageView1);
    }


    public void shuffle(View view){
        shuffled = true;
        Log.i("Postep", "kliknieto shuffle");
        victorious = false;
        toggledCard = false;
        Log.i("Postep", "ustalil wartosc zmiennych");

        //Wyzeroj stan gry, odwroc wszestkie karty rewersami do gory, przypisz nowe awersy do kart
        assignedCard = Arrays.asList(cardsByOrder);
        Log.i("Postep", assignedCard.toString());
        Collections.shuffle(assignedCard);
        Log.i("Postep", assignedCard.toString());
        Log.i("Postep", "Pomieszal tablice kart");
        gameState = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Log.i("Postep", "wyzerowal stan gry");
        for (ImageView imageView: imageViews) {
            imageView.setImageResource(tail);
        }
        Log.i("Postep", "Odwrocil karty rewersami do gory");
    }


    public void panic(View view){
        if(shuffled){
            Log.i("PostPanika", "Panikujesz");
            Log.i("PostPanika", "Gamestate: " + Arrays.toString(gameState));
            Log.i("PostPanika", "Assigned Cards: " + assignedCard.toString());
            Log.i("PostPanika", "Victorious: " + victorious.toString());
            Log.i("PostPanika", "toggled card: " + toggledCard.toString());
            Log.i("PostPanika", "Poprzedni: " + previous.getTag().toString());
        } else {
            Log.i("PostPanika", "Niepotasowany");
        }
    }


    public void clicked(View view) {
        if(shuffled){
            ImageView clickedCard = (ImageView) view;
            int clickedTag = Integer.parseInt(clickedCard.getTag().toString());
            int previousTag = Integer.parseInt(previous.getTag().toString());
            Log.i("Postep", "Kliknieto " + clickedCard.getTag().toString());
            Log.i("Postep", "Poprzedni " + previous.getTag().toString());

            //Sprawdz, czy kliklal w awers czy rewers
            if(gameState[clickedTag-1]==0){

                //obroc karte, zmien stan gry,
                clickedCard.setImageResource(heads[assignedCard.get(clickedTag-1)]);
                clickedCard.animate().setDuration(400).rotation(360);
                gameState[clickedTag-1] = 1;
                Log.i("Postep", "Zmieniłem obrazek");

                // sprawdz czy dobrze, zostaw jak tak, schowaj jak nie, kontynuuj jak nie ma do porownania
                //Sprawdz,  czy jest to pierwsze klikniecie

                if(toggledCard){

                    //Sprawdz, czy para
                    if(assignedCard.get(previousTag - 1).equals(assignedCard.get(clickedTag - 1))){
                        toggledCard = false;

                        //Sprawdz, czy wygrałes
                        victorious = true;
                        for (int state:gameState) {
                            if(state==0){
                                victorious = false;
                                break;
                            }
                        }
                    } else {
                        gameState[previousTag - 1] = 0;
                        gameState[clickedTag - 1] = 0;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.i("Postep", "Zaczekałem chwilę");
                        clickedCard.setImageResource(tail);
                        previous.setImageResource(tail);
                    }
                    toggledCard = false;
                } else {
                    toggledCard = true;
                    previous = clickedCard;
                }
                
                //sprawdz, czy wygrałes
                if(victorious){
                    Toast.makeText(this, "Congrats, You have won the game", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Toast.makeText(this, "To play, You have to shuffle first", Toast.LENGTH_LONG).show();
        }
    }
}