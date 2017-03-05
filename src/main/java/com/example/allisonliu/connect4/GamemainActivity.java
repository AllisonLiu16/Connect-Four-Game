package com.example.allisonliu.connect4;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.AlteredCharSequence;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebHistoryItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GamemainActivity extends AppCompatActivity {

    LinearLayout linearLayout2;
    LinearLayout chesslayout;
    LinearLayout titlelayout;
    LinearLayout buttonlayout;
    LinearLayout verticallayout1;
    LinearLayout linearLayout1;
    LinearLayout emptylayout;
    LinearLayout footlayout;
    LinearLayout imagelayout;
    LinearLayout emptylayout1;
    MediaPlayer mediaPlayer,mediaPlayer1,mediaPlayer2;
    LinearLayout soundlayout;
  //  LinearLayout emptylayout3;

    ImageView imageView[][] = new ImageView[6][7];
    public ArrayList<Integer> arraylist1 = new ArrayList<Integer>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mediaPlayer = MediaPlayer.create(this,R.raw.playchess);
        mediaPlayer1=MediaPlayer.create(this,R.raw.background);
        mediaPlayer2=MediaPlayer.create(this,R.raw.win);
        mediaPlayer.start();
        mediaPlayer.setVolume(1000f, 1000f);
        mediaPlayer1.start();

        super.onCreate(savedInstanceState);
        WindowManager wm = this.getWindowManager();
        int edge = wm.getDefaultDisplay().getWidth() * 8 / 10 / 6;
        linearLayout1 = new LinearLayout(this);
        titlelayout = new LinearLayout(this);
        linearLayout2 = new LinearLayout(this);
        chesslayout = new LinearLayout(this);
        buttonlayout = new LinearLayout(this);
        emptylayout = new LinearLayout(this);
        footlayout = new LinearLayout(this);
        imagelayout = new LinearLayout(this);
        emptylayout1 = new LinearLayout(this);
        soundlayout= new LinearLayout(this);
     //   emptylayout3 = new LinearLayout(this);

        Button b1 = new Button(this);
        Button b2 = new Button(this);
        soundlayout.setBackground(getResources().getDrawable(R.drawable.sound));

        //声音控制
       soundlayout.setOnClickListener(new View.OnClickListener(){
           public void onClick(View b) {
            if(mediaPlayer1.isPlaying()) {
                mediaPlayer1.pause();
                soundlayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.unsound));
            }
               else {
                mediaPlayer1.start();
                soundlayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.sound));
            }

           }

        });
        b2.setBackgroundDrawable(getResources().getDrawable(R.drawable.retract));
        b1.setBackgroundDrawable(getResources().getDrawable(R.drawable.newgame));
        footlayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_meitu_2));
        titlelayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.title1));
        verticallayout1 = new LinearLayout(this);
        LinearLayout.LayoutParams getimageFrame = new LinearLayout.LayoutParams(wm.getDefaultDisplay().getWidth() / 5, wm.getDefaultDisplay().getHeight() / 10);
        LinearLayout.LayoutParams gettitleFrame = new LinearLayout.LayoutParams(wm.getDefaultDisplay().getWidth(), wm.getDefaultDisplay().getHeight() / 5);
        LinearLayout.LayoutParams getsoundFrame = new LinearLayout.LayoutParams(wm.getDefaultDisplay().getWidth()/6, wm.getDefaultDisplay().getHeight() / 10);
         //LinearLayout.LayoutParams getsoundFrame1= new LinearLayout.LayoutParams(wm.getDefaultDisplay().getWidth()/15,wm.getDefaultDisplay().getHeight()/10);
        buttonlayout.setOrientation(LinearLayout.HORIZONTAL);
        emptylayout.setOrientation(LinearLayout.VERTICAL);
        emptylayout1.setOrientation(LinearLayout.HORIZONTAL);
        footlayout.setOrientation(LinearLayout.HORIZONTAL);
        imagelayout.setOrientation(LinearLayout.VERTICAL);
        soundlayout.setOrientation(LinearLayout.HORIZONTAL);
       // emptylayout3.setOrientation(LinearLayout.HORIZONTAL);
        int buttWid = wm.getDefaultDisplay().getWidth() / 3;
        int buttHeight = wm.getDefaultDisplay().getHeight() / 10;
        LinearLayout.LayoutParams getWidHei1 = new LinearLayout.LayoutParams(buttWid, buttHeight);
        buttonlayout.addView(b1, getWidHei1);
        //emptylayout.addView(emptylayout3,getsoundFrame1);
        emptylayout.addView(soundlayout,getsoundFrame);
        buttonlayout.addView(emptylayout, getWidHei1);
        buttonlayout.addView(b2, getWidHei1);
        footlayout.addView(emptylayout1, getWidHei1);
        footlayout.addView(imagelayout, getimageFrame);


        //New Game Button
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View a) {
                mediaPlayer1.start();

                imagelayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.images));
                int i, j;
                for (i = 0; i <= 5; i++) {
                    for (j = 0; j <= 6; j++) {
                        imageView[i][j].setImageDrawable(getResources().getDrawable(R.drawable.emptychess));
                        im[i][j] = 0;
                        imageView[i][j].setClickable(true);
                    }
                }
            }
        });
        //Retract Button
        b2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View b) {

                if (arraylist1.size() != 0) {
                    int l = arraylist1.get(arraylist1.size() - 1);
                    imageView[l / 7][l % 7].setImageDrawable(getResources().getDrawable(R.drawable.emptychess));
                    im[l / 7][l % 7] = 0;
                    arraylist1.remove(arraylist1.size() - 1);
                } else Toast.makeText(getApplicationContext(), "Cannot retract!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        chesslayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout1.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < 6; i++) {
            LinearLayout arrayimage = new LinearLayout(this);
            arrayimage.setOrientation(LinearLayout.HORIZONTAL);

            for (int j = 0; j < 7; j++) {
                imageView[i][j] = new ImageView(this);
                imageView[i][j].setImageDrawable(getResources().getDrawable(R.drawable.emptychess));
                final int finalj = j;

                imageView[i][j].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        imgeclick(finalj);
                    }
                });


                LinearLayout.LayoutParams getWidHei = new LinearLayout.LayoutParams(edge, edge);
                arrayimage.addView(imageView[i][j], getWidHei);
            }
            chesslayout.addView(arrayimage);
        }
        linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams getverticalFrame1 = new LinearLayout.LayoutParams(wm.getDefaultDisplay().getWidth() / 25, 0);
        verticallayout1.setOrientation(LinearLayout.VERTICAL);
        linearLayout2.addView(verticallayout1, getverticalFrame1);
        linearLayout2.addView(chesslayout);
        linearLayout1.addView(titlelayout, gettitleFrame);
        linearLayout1.addView(buttonlayout);
        linearLayout1.addView(linearLayout2);
        linearLayout1.addView(footlayout);

        setContentView(linearLayout1);

    }

boolean win;
            int counter = 0;
    int im[][] = new int[6][7];
    static ArrayList<Integer> arraylist = new ArrayList<Integer>();
            public void imgeclick ( int j){

                arraylist = new ArrayList<Integer>();
                int temp = -1;
                for (int imgi = 5; imgi >= 0; imgi--) {
                    if (im[imgi][j] == 0) {
                        arraylist1.add(imgi * 7 + j);
                        temp = imgi;
                        if (counter % 2 == 0) {
                            imageView[imgi][j].setImageDrawable(getResources().getDrawable(R.drawable.chess1));
                            im[imgi][j] = 1;
                            imagelayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.chess2));
                        } else {
                            imageView[imgi][j].setImageDrawable(getResources().getDrawable(R.drawable.chess2));
                            im[imgi][j] = 2;
                            imagelayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.chess1));
                        }
                        counter++;
                        break;
                    }
                }


                if (temp != -1) {

                    int sign = (counter + 1) % 2 + 1;
                    if (lineLeft(temp, j, sign, im) + lineRight(temp, j, sign, im) - 1 >= 4) {

                        if (sign == 2) {
                            for (int l = 0; l < arraylist.size(); l++) {
                                imageView[(int) arraylist.get(l) / 7][(int) arraylist.get(l) % 7].setImageDrawable(getResources().getDrawable(R.drawable.chesswin2));
                            }
                        } else {
                            for (int l = 0; l < arraylist.size(); l++) {
                                imageView[(int) arraylist.get(l) / 7][(int) arraylist.get(l) % 7].setImageDrawable(getResources().getDrawable(R.drawable.chesswin1));
                            }

                        }
                        mediaPlayer2.start();
                        mediaPlayer1.pause();
                        Toast.makeText(getApplicationContext(), "Congradulation! You Win!",
                                Toast.LENGTH_SHORT).show();
                        for(int i=0; i<=5;i++)
                        {
                            for(int i1=0; i1<=6; i1++){
                                imageView[i][i1].setClickable(false);
                                Toast.makeText(getApplicationContext(), "Please start new game!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                    if (lineDown(temp, j, sign, im) >= 4) {
                        if (sign == 2) {
                            for (int l = 0; l < arraylist.size(); l++) {
                                imageView[(int) arraylist.get(l) / 7][(int) arraylist.get(l) % 7].setImageDrawable(getResources().getDrawable(R.drawable.chesswin2));
                            }
                        } else {
                            for (int l = 0; l < arraylist.size(); l++) {
                                imageView[(int) arraylist.get(l) / 7][(int) arraylist.get(l) % 7].setImageDrawable(getResources().getDrawable(R.drawable.chesswin1));
                            }
                        }
                        mediaPlayer2.start();
                        mediaPlayer1.pause();
                        Toast.makeText(getApplicationContext(), "Congradulation! You Win!",
                                Toast.LENGTH_SHORT).show();
                        for(int i=0; i<=5;i++)
                        {
                            for(int i1=0; i1<=6; i1++){
                                imageView[i][i1].setClickable(false);
                                Toast.makeText(getApplicationContext(), "Please start new game!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    if (ZhengDiagUp(temp, j, sign, im) + ZhengDiagDown(temp, j, sign, im) - 1 >= 4) {
                        if (sign == 2) {
                            for (int l = 0; l < arraylist.size(); l++) {
                                imageView[(int) arraylist.get(l) / 7][(int) arraylist.get(l) % 7].setImageDrawable(getResources().getDrawable(R.drawable.chesswin2));
                            }
                        } else {
                            for (int l = 0; l < arraylist.size(); l++) {
                                imageView[(int) arraylist.get(l) / 7][(int) arraylist.get(l) % 7].setImageDrawable(getResources().getDrawable(R.drawable.chesswin1));
                            }
                        }
                        mediaPlayer2.start();
                        mediaPlayer1.pause();
                        Toast.makeText(getApplicationContext(), "Congradulation! You Win!",
                                Toast.LENGTH_SHORT).show();
                        for(int i=0; i<=5;i++)
                        {
                            for(int i1=0; i1<=6; i1++){
                                imageView[i][i1].setClickable(false);
                                Toast.makeText(getApplicationContext(), "Please start new game!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    if (fanDiagUp(temp, j, sign, im) + fanDiagDown(temp, j, sign, im) - 1 >= 4) {
                        if (sign == 2) {
                            for (int l = 0; l < arraylist.size(); l++) {
                                imageView[(int) arraylist.get(l) / 7][(int) arraylist.get(l) % 7].setImageDrawable(getResources().getDrawable(R.drawable.chesswin2));
                            }
                        } else {
                            for (int l = 0; l < arraylist.size(); l++) {
                                imageView[(int) arraylist.get(l) / 7][(int) arraylist.get(l) % 7].setImageDrawable(getResources().getDrawable(R.drawable.chesswin1));
                            }

                        }
                        mediaPlayer2.start();
                        mediaPlayer1.pause();
                        Toast.makeText(getApplicationContext(), "Congradulation! You Win!",
                                Toast.LENGTH_SHORT).show();
                        for(int i=0; i<=5;i++)
                        {
                            for(int i1=0; i1<=6; i1++){
                                imageView[i][i1].setClickable(false);
                                Toast.makeText(getApplicationContext(), "Please start new game!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }


            }

        //横向遍历

        public static  int lineLeft(int x, int y, int sign, int im[][]) {
            if (y == 0) {
                if (im[x][y] == sign) {
                    arraylist.add(x * 7 + y);
                    return 1;
                } else return 0;
            } else {
                if (im[x][y] == sign) {
                    arraylist.add(x * 7 + y);

                    return lineLeft(x, y - 1, sign, im) + 1;
                } else return 0;
            }
        }

        //横向遍历
        public static  int lineRight(int a, int b, int sign, int im[][]) {
            if (b == 6) {
                if (im[a][b] == sign) {
                    arraylist.add(a * 7 + b);
                    return 1;
                } else return 0;
            } else {
                if (im[a][b] == sign) {
                    arraylist.add(a * 7 + b);
                    return lineRight(a, b + 1, sign, im) + 1;
                } else return 0;
            }
        }


        //竖向遍历
        public static int lineDown(int a, int b, int sign, int im[][]) {
            if (a == 5) {
                if (im[a][b] == sign) {
                    arraylist.add(a * 7 + b);
                    return 1;
                } else return 0;
            } else {
                if (im[a][b] == sign) {
                    arraylist.add(a * 7 + b);
                    return lineDown(a + 1, b, sign, im) + 1;
                } else return 0;
            }
        }

        //正对角线遍历
        public static  int ZhengDiagUp(int i, int j, int sign, int im[][]) {

            if (i == 0 || j == 6) {
                if (im[i][j] == sign) {
                    arraylist.add(i * 7 + j);
                    return 1;
                } else return 0;
            } else {
                if (im[i][j] == sign) {
                    arraylist.add(i * 7 + j);
                    return ZhengDiagUp(i - 1, j + 1, sign, im) + 1;
                } else return 0;
            }
        }

        public static int ZhengDiagDown(int x, int y, int sign, int im[][]) {
            if (x == 5 || y == 0) {
                if (im[x][y] == sign) {
                    arraylist.add(x * 7 + y);
                    return 1;
                } else return 0;
            } else {
                if (im[x][y] == sign) {
                    arraylist.add(x * 7 + y);
                    return ZhengDiagDown(x + 1, y - 1, sign, im) + 1;
                } else return 0;
            }
        }

        //反对角遍历
        public static int fanDiagUp(int i, int j, int sign, int im[][]) {
            if (i == 0 || j == 0) {
                if (im[i][j] == sign) {
                    arraylist.add(i * 7 + j);
                    return 1;
                } else return 0;
            } else {
                if (im[i][j] == sign) {
                    arraylist.add(i * 7 + j);
                    return fanDiagUp(i - 1, j - 1, sign, im) + 1;
                } else return 0;
            }
        }

        public static int fanDiagDown(int i, int j, int sign, int im[][]) {
            if (i == 5 || j == 6) {
                if (im[i][j] == sign) {
                    arraylist.add(i * 7 + j);
                    return 1;
                } else return 0;
            } else {
                if (im[i][j] == sign) {
                    arraylist.add(i * 7 + j);
                    return fanDiagDown(i + 1, j + 1, sign, im) + 1;
                } else return 0;
            }
        }


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */





