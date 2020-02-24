package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {

    private static final String RESULT_KEY = "result";
    private TextView homeText;
    private TextView awayText;
    private ImageView imageHome;
    private ImageView imageAway;
    int skorHome = 0;
    int skorAway = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        imageHome = findViewById(R.id.home_logo);
        imageAway = findViewById(R.id.away_logo);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            Bundle extra = getIntent().getExtras();
            Bitmap bmp = extra.getParcelable("imageHome");
            Bitmap bmp2 = extra.getParcelable("imageAway");


            homeText.setText(extras.getString(MainActivity.HOMETEAM_KEY));
            awayText.setText(extras.getString(MainActivity.AWAYTEAM_KEY));
            imageHome.setImageBitmap(bmp);
            imageAway.setImageBitmap(bmp2);


            //TODO
            //1.Menampilkan detail match sesuai data dari main activity
            //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
            //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
        }
    }

    public void addscorehome(int scoreHome){
        TextView scoreView= findViewById(R.id.score_home);
        scoreView.setText(String.valueOf(scoreHome));
    }
    public void handleaddhome(View view) {
        skorHome++;
        addscorehome(skorHome);

    }
    public void addscoreaway(int scoreaway){
        TextView scoreView= findViewById(R.id.score_away);
        scoreView.setText(String.valueOf(scoreaway));
    }

    public void handleaddaway(View view) {
        skorAway++;
        addscoreaway(skorAway);
    }

    public void handlecek(View view) {
        String result=null;
        if (skorHome==skorAway){
            result ="Draw";
        }else if(skorHome>skorAway){
            result = homeText.getText().toString();
        }else if (skorAway>skorHome){
            result = awayText.getText().toString();
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(RESULT_KEY, result);
        startActivity(intent);
    }
}
