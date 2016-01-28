package finotype.pillo.com.finotype;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final int SELECT_PHOTO = 1;
    ImageView imageView;
    ImageView imageView2;

    ImageView imageView3;
    int yvalue = 0;
    int yvalue2 = 0;
    int xvalue = 0;
    int xvalue2 = 0;
    int lowk = 0;
    int twolowk = 0;
    int lowx = 0;
    int twolowx = 0;
    Bitmap bitmap1;
    Bitmap bitmap2;
    int ix=0;
    int fx=0;
    int iy=0;
    double hix = 0;
    double twohix = 0;
    int fy=0;
    double hi = 0;
    double twohi = 0;
    double ratio=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = (ImageView) findViewById(R.id.imageView);

        imageView2 = (ImageView) findViewById(R.id.imageView2);

        imageView3= (ImageView) findViewById(R.id.imageView3);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        final EditText editText = (EditText) findViewById(R.id.textView2);
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject gameScore = new ParseObject("FishObject");
                gameScore.put("FishName", editText.getText().toString());
                gameScore.saveInBackground();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void backgroundColor(Bitmap bitmap) {


        hi = 0;
        twohi = 0;
        hix = 0;
        twohix = 0;
        xvalue2=0;
        xvalue=0;
        yvalue=0;
        lowk=0;
        twolowk=0;
        lowx=0;
        twolowx=0;
        yvalue2=0;
        bitmap1 = null;
        bitmap2 = null;
        bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        int intArray[] = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(intArray, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());


        imageView3.setImageBitmap(bitmap);

        double top = 0;
        double bottom = 0;
        double right = 0;
        double left = 0;
        double count = 0;
        int x = 0;
        int y = 0;
        double w = 0;

        for (int j = 0; j < bitmap.getWidth() / 2; j++) {

            if (j != 0)
                if (1 - (intArray[j] / intArray[j - 1]) < .1 && 1 - (intArray[j] / intArray[j - 1]) > -.1) {
                    count++;
                    top += intArray[j];
                }
        }

        top = (top / count);
        if (top < 0)
            top = top * -1;

        count = 0;
        for (int j = intArray.length - bitmap.getWidth(); j < bitmap.getWidth() * bitmap.getHeight() - bitmap.getWidth() / 2; j++) {
            if (j != intArray.length - bitmap.getWidth())
                if (1 - (intArray[j] / intArray[j - 1]) < .1 && 1 - (intArray[j] / intArray[j - 1]) > -.1) {
                    count++;
                    bottom += intArray[j];
                }
        }

        if (count != 0)
            bottom = (bottom / count);
        if (bottom < 0)
            bottom = bottom * -1;
        count = 0;
        for (int j = 0; j < bitmap.getWidth() * bitmap.getHeight() - bitmap.getWidth(); j += bitmap.getWidth()) {
            if (j != 0)
                if (1 - (intArray[j] / intArray[j - 1]) < .1 && 1 - (intArray[j] / intArray[j - 1]) > -.1) {
                    left += intArray[j];
                    count++;
                }
        }

        if (count != 0)
            left = (left / count);
        if (left < 0)
            left = left * -1;
        count = 0;

        for (int j = bitmap.getWidth(); j < bitmap.getWidth() * bitmap.getHeight(); j += bitmap.getWidth()) {
            if (j != bitmap.getWidth())
                if (1 - (intArray[j] / intArray[j - 1]) < .1 && 1 - (intArray[j] / intArray[j - 1]) > -.1) {
                    right += intArray[j];
                    count++;
                }
        }

        if (count != 0)
            right = (right / count);

        if (right < 0)
            right = right * -1;
        int zero = 4;

        if (top == 0)
            zero--;
        if (bottom == 0)
            zero--;
        if (right == 0)
            zero--;
        if (left == 0)
            zero--;

        if (zero != 0)
            top = (top + bottom + right + left) / zero;

        for (int i = 0; i < intArray.length; i++) {
            if (x == bitmap.getWidth() - 1) {
                x = 0;
                if (y != bitmap.getHeight() - 1) {
                    y++;
                }
            } else {
                x++;
            }

            if (intArray[i] < 0)
                w = intArray[i] * -1;
            else
                w = intArray[i];

            if (w / top > 1.65 || top / w > 1.65) {
                bitmap1.setPixel(x, y, intArray[i]);
            }

        }


        bitmap2 = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), Bitmap.Config.ARGB_8888);
        int intArray1[] = new int[bitmap1.getWidth() * bitmap1.getHeight()];
        bitmap1.getPixels(intArray1, 0, bitmap1.getWidth(), 0, 0, bitmap1.getWidth(), bitmap1.getHeight());

        int density[] = new int[60];
        int density2[] = new int[20];

       for (int jj = 0; jj < 60; jj++) {
            for (int j = bitmap1.getHeight() * bitmap1.getWidth() - 1 - (jj * bitmap1.getHeight() * bitmap1.getWidth() / 60); j > bitmap1.getHeight() * bitmap.getWidth() - 1 - (jj * bitmap1.getHeight() * bitmap1.getWidth() / 60) - (bitmap1.getWidth()); j--) {
                if (j > 0)
                    if (intArray1[j] != 0) {
                        density[jj]++;
                    }
            }}

       for (int ll = 1; ll < 10; ll++) {
            for (int j = bitmap1.getHeight() * bitmap1.getWidth() - 1 - (ll * bitmap1.getWidth() / 40); j > ll * bitmap1.getWidth() / 40; j=j-bitmap1.getWidth()) {
                if (j > 0)
                    if (ll!=1){
                    if (intArray1[j] != 0) {
                        density2[ll]++;
                    }}
                else if (intArray1[j] != 0){
                        density2[ll-1]++;
                    }
            }}

          int ll=9;

        for (int kk = 39; kk > 30;kk--) {
            ll++;
            for (int j = bitmap1.getHeight() * bitmap1.getWidth() - 1 - (kk * bitmap1.getWidth() / 40); j > kk * bitmap1.getWidth() / 40; j=j-bitmap1.getWidth()) {
                if (j > 0)
                    if (intArray1[j] != 0) {
                        density2[ll]++;
                    }
            }}


       for (int k = 1; k < 30; k++) {

            double first = density[k - 1];
            double second = density[k];

            if (first != 0) {
                if (density[k] > density[k - 1] && second / first > 2) {
                    hi = second / first;
                    lowk = k - 1;
                }
            }
            if (second != 0) {
                if (density[k - 1] > density[k] && first / second > 2) {
                    hi = first / second;
                    lowk = k - 1;
                }
            }
        }

        for (int k = 59; k > 30; k--) {

            double first = density[k - 1];
            double second = density[k];

            if (first != 0) {
                if (density[k] > density[k - 1] && second / first > 2) {
                    twohi = second / first;
                    twolowk = k - 1;
                }}
                if (second != 0) {
                    if (density[k - 1] > density[k] && first / second > 2) {
                        twohi = first / second;
                        twolowk = k - 1;
                    }
                }}


       for (int k =1; k <8; k++) {

            double first = density2[k - 1];
            double second = density2[k];


           Log.i("xvalue", ""+ second/first+" "+first+ " "+second+ " "+ hix);
            if (first != 0) {
                if (density2[k] > density2[k - 1] &&second/first>hix|| second/first==0) {
                    hix = second / first;
                    lowx = k - 1;
                }
            }

            if (second != 0) {
                if (density2[k - 1] > density2[k] &&first/second>hix|| first / second ==0) {
                    hix = first / second;
                    lowx = k - 1;
                }
            }
        }

        for (int k = 8; k<18; k++) {

            double first = density2[k - 1];
            double second = density2[k];


            Log.i("xvalue1", ""+ second/first+" "+first+ " "+second+ " "+ twohix);
            if (first != 0) {
                if (density2[k] > density2[k - 1] && second/first>twohix||second/first ==0) {
                    twohix = second / first;
                    twolowx = k - 1;
                }}
                if (second != 0) {
                    if (density2[k - 1] > density2[k] && first/second>twohix||first / second ==0) {
                        twohix = first / second;
                        twolowx = k - 1;
                    }
                }

        }
        //Setup initial variables

        Log.i("xvalue2", ""+ twohix+ " "+hix);
       switch (lowx) {
            case 0:
                xvalue = bitmap1.getWidth() - (bitmap1.getWidth() / 40);
                break;
            case 1:
                xvalue = bitmap1.getWidth() - (2 * bitmap1.getWidth() / 40);
                break;
            case 2:
                xvalue = bitmap1.getWidth() - (3 * bitmap1.getWidth() / 40);
                break;
            case 3:
                xvalue = bitmap1.getWidth() - (4 * bitmap1.getWidth() / 40);
                break;
            case 4:
                xvalue = bitmap1.getWidth() - (5 * bitmap1.getWidth() / 40);
                break;
           case 5:
               xvalue = bitmap1.getWidth() - (6*bitmap1.getWidth() / 40);
               break;
           case 6:
               xvalue = bitmap1.getWidth() - (7 * bitmap1.getWidth() / 40);
               break;
           case 7:
               xvalue = bitmap1.getWidth() - (8 * bitmap1.getWidth() / 40);
               break;
           case 8:
               xvalue = bitmap1.getWidth() - (9 * bitmap1.getWidth() / 40);
               break;
           case 9:
               xvalue = bitmap1.getWidth() - (10 * bitmap1.getWidth() / 40);
               break;

       }

        switch (twolowx) {
            case 10:
                xvalue2 = bitmap1.getWidth() - (40*bitmap1.getWidth() / 40);
                break;
            case 11:
                xvalue2 = bitmap1.getWidth() - (39 * bitmap1.getWidth() / 40);
                break;
            case 12:
                xvalue2 = bitmap1.getWidth() - (38 * bitmap1.getWidth() / 40);
                break;
            case 13:
                xvalue2= bitmap1.getWidth() - (37* bitmap1.getWidth() / 40);
                break;
            case 14:
                xvalue2 = bitmap1.getWidth() - (36 * bitmap1.getWidth() / 40);
                break;
            case 15:
                xvalue2 = bitmap1.getWidth() - (35*bitmap1.getWidth() / 40);
                break;
            case 16:
                xvalue2 = bitmap1.getWidth() - (34 * bitmap1.getWidth() / 40);
                break;
            case 17:
                xvalue2 = bitmap1.getWidth() - (33 * bitmap1.getWidth() / 40);
                break;
            case 18:
                xvalue2 = bitmap1.getWidth() - (32 * bitmap1.getWidth() / 40);
                break;
            case 19:
                xvalue2 = bitmap1.getWidth() - (31 * bitmap1.getWidth() / 40);
                break;
        }


        switch (lowk) {
            case 0:
                yvalue = bitmap1.getHeight() - (bitmap1.getHeight() / 60);
                break;
            case 1:
                yvalue = bitmap1.getHeight() - (2 * bitmap1.getHeight() / 60);
                break;
            case 2:
                yvalue = bitmap1.getHeight() - (3 * bitmap1.getHeight() / 60);
                break;
            case 3:
                yvalue = bitmap1.getHeight() - (4 * bitmap1.getHeight() / 60);
                break;
            case 4:
                yvalue = bitmap1.getHeight() - (5 * bitmap1.getHeight() / 60);
                break;
            case 5:
                yvalue = bitmap1.getHeight() - (6 * bitmap1.getHeight() / 60);
                break;
            case 6:
                yvalue = bitmap1.getHeight() - (7 * bitmap1.getHeight() / 60);
                break;
            case 7:
                yvalue = bitmap1.getHeight() - (8 * bitmap1.getHeight() / 60);
                break;
            case 8:
                yvalue = bitmap1.getHeight() - (9 * bitmap1.getHeight() / 60);
                break;
            case 9:
                yvalue = bitmap1.getHeight() - (10 * bitmap1.getHeight() / 60);
                break;
            case 10:
                yvalue = bitmap1.getHeight() - (11 * bitmap.getHeight() / 60);
                break;
            case 11:
                yvalue = bitmap1.getHeight() - (12 * bitmap1.getHeight() / 60);
                break;
            case 12:
                yvalue = bitmap1.getHeight() - (13 * bitmap1.getHeight() / 60);
                break;
            case 13:
                yvalue = bitmap1.getHeight() - (14 * bitmap1.getHeight() / 60);
                break;
            case 14:
                yvalue = bitmap1.getHeight() - (15 * bitmap1.getHeight() / 60);
                break;
            case 15:
                yvalue = bitmap1.getHeight() - (16 * bitmap1.getHeight() / 60);
                break;
            case 16:
                yvalue = bitmap1.getHeight() - (17 * bitmap1.getHeight() / 60);
                break;
            case 17:
                yvalue = bitmap1.getHeight() - (18 * bitmap1.getHeight() / 60);
                break;
            case 18:
                yvalue = bitmap1.getHeight() - (19 * bitmap1.getHeight() / 60);
                break;
            case 19:
                yvalue = bitmap1.getHeight() - (20 * bitmap1.getHeight() / 60);
                break;
            case 20:
                yvalue = bitmap1.getHeight() - (21 * bitmap1.getHeight() / 60);
                break;
            case 21:
                yvalue = bitmap1.getHeight() - (22 * bitmap1.getHeight() / 60);
                break;
            case 22:
                yvalue = bitmap1.getHeight() - (23 * bitmap1.getHeight() / 60);
                break;
            case 23:
                yvalue = bitmap1.getHeight() - (24 * bitmap1.getHeight() / 60);
                break;
            case 24:
                yvalue = bitmap1.getHeight() - (25 * bitmap1.getHeight() / 60);
                break;
            case 25:
                yvalue = bitmap1.getHeight() - (26 * bitmap1.getHeight() / 60);
                break;
            case 26:
                yvalue = bitmap1.getHeight() - (27 * bitmap1.getHeight() / 60);
                break;
            case 27:
                yvalue = bitmap1.getHeight() - (28 * bitmap1.getHeight() / 60);
                break;
            case 28:
                yvalue = bitmap1.getHeight() - (29 * bitmap1.getHeight() / 60);
                break;
            case 29:
                yvalue = bitmap1.getHeight() - (120 * bitmap1.getHeight() / 60);
                break;
        }


        switch (twolowk) {
            case 30:
                yvalue2 = bitmap1.getHeight() - (31 * bitmap1.getHeight() / 60);
                break;
            case 31:
                yvalue2 = bitmap1.getHeight() - (32 * bitmap1.getHeight() / 60);
                break;
            case 32:
                yvalue2 = bitmap1.getHeight() - (33 * bitmap1.getHeight() / 60);
                break;
            case 33:
                yvalue2 = bitmap1.getHeight() - (34 * bitmap1.getHeight() / 60);
                break;
            case 34:
                yvalue2 = bitmap1.getHeight() - (35 * bitmap1.getHeight() / 60);
                break;
            case 35:
                yvalue2 = bitmap1.getHeight() - (36 * bitmap1.getHeight() / 60);
                break;
            case 36:
                yvalue2 = bitmap1.getHeight() - (37 * bitmap1.getHeight() / 60);
                break;
            case 37:
                yvalue2 = bitmap1.getHeight() - (38 * bitmap1.getHeight() / 60);
                break;
            case 38:
                yvalue2 = bitmap1.getHeight() - (39 * bitmap1.getHeight() / 60);
                break;
            case 39:
                yvalue2 = bitmap1.getHeight() - (40 * bitmap1.getHeight() / 60);
                break;
            case 40:
                yvalue2 = bitmap1.getHeight() - (41 * bitmap.getHeight() / 60);
                break;
            case 41:
                yvalue2 = bitmap1.getHeight() - (42 * bitmap1.getHeight() / 60);
                break;
            case 42:
                yvalue2 = bitmap1.getHeight() - (43 * bitmap1.getHeight() / 60);
                break;
            case 43:
                yvalue2 = bitmap1.getHeight() - (44 * bitmap1.getHeight() / 60);
                break;
            case 44:
                yvalue2 = bitmap1.getHeight() - (45 * bitmap1.getHeight() / 60);
                break;
            case 45:
                yvalue2 = bitmap1.getHeight() - (46 * bitmap1.getHeight() / 60);
                break;
            case 46:
                yvalue2 = bitmap1.getHeight() - (47 * bitmap1.getHeight() / 60);
                break;
            case 47:
                yvalue2 = bitmap1.getHeight() - (48 * bitmap1.getHeight() / 60);
                break;
            case 48:
                yvalue2 = bitmap1.getHeight() - (49 * bitmap1.getHeight() / 60);
                break;
            case 49:
                yvalue2 = bitmap1.getHeight() - (50 * bitmap1.getHeight() / 60);
                break;
            case 50:
                yvalue2 = bitmap1.getHeight() - (51 * bitmap1.getHeight() / 60);
                break;
            case 51:
                yvalue2 = bitmap1.getHeight() - (52 * bitmap1.getHeight() / 60);
                break;
            case 52:
                yvalue2 = bitmap1.getHeight() - (53 * bitmap1.getHeight() / 60);
                break;
            case 53:
                yvalue2 = bitmap1.getHeight() - (54 * bitmap1.getHeight() / 60);
                break;
            case 54:
                yvalue2 = bitmap1.getHeight() - (55 * bitmap1.getHeight() / 60);
                break;
            case 55:
                yvalue2 = bitmap1.getHeight() - (56 * bitmap1.getHeight() / 60);
                break;
            case 56:
                yvalue2 = bitmap1.getHeight() - (57 * bitmap1.getHeight() / 60);
                break;
            case 57:
                yvalue2 = bitmap1.getHeight() - (58 * bitmap1.getHeight() / 60);
                break;
            case 58:
                yvalue2 = bitmap1.getHeight() - (59 * bitmap1.getHeight() / 60);
                break;
            case 59:
                yvalue2 = bitmap1.getHeight() - (60 * bitmap1.getHeight() / 60);
                break;
        }

        x = 0;
        ix = 0;
        fx = 0;
        iy = 0;
        fy = 0;


        if (yvalue > yvalue2) {
            y = yvalue2;
            for (int i = yvalue2 * bitmap1.getWidth(); i < yvalue * bitmap1.getWidth(); i++) {

                if (x == bitmap.getWidth() - 1) {
                    x = 0;
                    if (y != bitmap.getHeight() - 1) {
                        y++;
                    }
                } else {
                    x++;
                }
                if (intArray1[i] != -1&&x>xvalue2&&x<xvalue){
                    if(x>fx)
                        fx=x;
                    if(y>fy)
                        fy=y;
                    if (ix==0||x<ix)
                        ix=x;
                    if (iy==0||y<iy)
                        iy=y;
                    bitmap2.setPixel(x, y, intArray1[i]);}
            }}else {
            y = yvalue;
            for (int i = yvalue * bitmap1.getWidth(); i < yvalue2 * bitmap1.getWidth(); i++) {


                if (x == bitmap.getWidth() - 1) {
                    x = 0;
                    if (y != bitmap.getHeight() - 1) {
                        y++;
                    }
                } else {
                    x++;
                }

                if (intArray1[i] != -1&&x>xvalue2&&x<xvalue){
                    if(x>fx)
                    fx=x;
                    if(y>fy)
                    fy=y;
                    if (ix==0||x<ix)
                        ix=x;
                    if (iy==0||y<iy)
                        iy=y;
                    bitmap2.setPixel(x, y, intArray1[i]);}}}


        fx= fx-ix;
        fy= fy-iy;
        Log.i("fx", "fx "+ fx+ "fy "+ fy);

        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        int pixelCount=0;

        int pixelColor;
        int r, g, b;
        r = g = b = 0;

        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                pixelColor = bitmap2.getPixel(xx, yy);
                if (pixelColor!=0){
                pixelCount++;
                    r += Color.red(pixelColor);
                    g += Color.green(pixelColor);
                b+= Color.blue(pixelColor);}
            }
        }

        r /= pixelCount;
        g /= pixelCount;
        b /= pixelCount;


        ratio= (double)(fx)/(double)(fy);
        Log.i("ratio", ""+ ratio+ " "+Color.rgb(r,g,b)+ " "+ r+ " "+ g + " "+ b);
        imageView2.setBackgroundColor(Color.rgb(r, g , b));
        imageView.setImageBitmap(bitmap2);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);


        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = imageReturnedIntent.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                double targeth = imageView.getWidth();
                double h = selectedImage.getWidth();
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(selectedImage, (int) ((double)(selectedImage.getWidth()) * (targeth / h)), (int) ((double)(selectedImage.getHeight() * (targeth / h))), false);
                backgroundColor(resizedBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File fos = getDir(imageFileName, Context.MODE_APPEND);

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = fos.getAbsolutePath();
        return fos;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
}


