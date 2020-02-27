package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final int GALERY_REQUEST_CODE = 1&2;
    public static final String TAG = MainActivity.class.getCanonicalName();

    public static final String HOMETEAM_KEY = "home";
    public static final String AWAYTEAM_KEY = "away";
//    public static final String IMAGEHOME_KEY = "imagehome";
//    public static final String IMAGEAWAY_KEY = "imageaway";


    private EditText homeTeam;
    private EditText awayTeam;
    private ImageView imageHome;
    private ImageView imageAway;
    private Bitmap bmp1;
    private Bitmap bmp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO
        homeTeam = findViewById(R.id.home_team);
        awayTeam = findViewById(R.id.away_team);
        imageHome = findViewById(R.id.home_logo);
        imageAway = findViewById(R.id.away_logo);

        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }


    public void handlerNext(View view) {
        String home = homeTeam.getText().toString();
        String away = awayTeam.getText().toString();


        if ((home).equals("") || (away).equals("") ||(imageHome).equals("") || (imageAway).equals("") || bmp1 == null || bmp2 == null ){
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else {
            imageHome.setDrawingCacheEnabled(true);
            Bitmap bmp1 = imageHome.getDrawingCache();
            imageAway.setDrawingCacheEnabled(true);
            Bitmap bmp2 = imageAway.getDrawingCache();

            Intent intent = new Intent(this, MatchActivity.class);

            intent.putExtra(HOMETEAM_KEY, home);
            intent.putExtra(AWAYTEAM_KEY, away);
            intent.putExtra("Bitmap", bmp1);
            intent.putExtra("Bitmap", bmp2);

//            imageHome.buildDrawingCache();
//            imageAway.buildDrawingCache();
//
//            Bitmap homeImage = imageHome.getDrawingCache();
//            Bitmap awayImage = imageAway.getDrawingCache();
//
//            Bundle extra = new Bundle();
//            extra.putParcelable(IMAGEHOME_KEY,homeImage);
//            extra.putParcelable(IMAGEAWAY_KEY,awayImage);
            startActivity(intent);
        }
    }

    public void handleChangeAvatar(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleChangeAvatar2(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            return;
        }
        if (requestCode == 1) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    bmp1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageHome.setImageBitmap(bmp1);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        if (requestCode == 2) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    bmp2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageAway.setImageBitmap(bmp2);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }

    }

}


