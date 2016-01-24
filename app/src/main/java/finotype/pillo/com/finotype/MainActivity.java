package finotype.pillo.com.finotype;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private final int SELECT_PHOTO = 1;
    ImageView imageView;
    int yvalue = 0;
    int yvalue2 = 0;

    int xvalue = 0;
    int xvalue2 = 0;
    int lowk = 0;
    int twolowk = 0;
    Bitmap resizedBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = (ImageView) findViewById(R.id.imageView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
    }

    //hello my name is taylor
//thank collin
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void backgroundColor(Bitmap bitmap) {


        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        int intArray[] = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(intArray, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        double top = 0;
        double bottom = 0;
        double right = 0;
        double left = 0;
        double count = 0;
        double jj = 0;
        double kk = 0;
        int x = 0;
        int y = 0;
        double w = 0;

        for (int j = 0; j < bitmap.getWidth()/2; j++) {

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
        for (int j = intArray.length - bitmap.getWidth(); j < bitmap.getWidth() * bitmap.getHeight()-bitmap.getWidth()/2; j++) {
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


        Bitmap bitmap2 = Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), Bitmap.Config.ARGB_8888);
        int intArray1[] = new int[bitmap1.getWidth() * bitmap1.getHeight()];
        bitmap1.getPixels(intArray1, 0, bitmap1.getWidth(), 0, 0, bitmap1.getWidth(), bitmap1.getHeight());

        int hi = 0;
        int twohi = 0;
        int density[] = new int[10];

        for (int j = bitmap1.getHeight() * bitmap1.getWidth() - 1; j > bitmap1.getHeight() * bitmap.getWidth() - (bitmap1.getHeight() * bitmap1.getWidth() / 10); j--) {
            if (intArray1[j] != 0)
                density[0]++;
        }
        for (int j = bitmap1.getHeight() * bitmap1.getWidth() - 1 - (bitmap1.getHeight() * bitmap1.getWidth() / 10); j > bitmap1.getHeight() * bitmap.getWidth() - (2 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j--) {
            if (intArray1[j] != 0)
                density[1]++;
        }

        for (int j = bitmap1.getHeight() * bitmap1.getWidth() - 1 - (2 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j > bitmap1.getHeight() * bitmap.getWidth() - (3 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j--) {
            if (intArray1[j] != 0)
                density[2]++;
        }

        for (int j = bitmap1.getHeight() * bitmap1.getWidth() - 1 - (3 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j > bitmap1.getHeight() * bitmap.getWidth() - (4 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j--) {
            if (intArray1[j] != 0)
                density[3]++;
        }
        for (int j = bitmap1.getHeight() * bitmap1.getWidth() - 1 - (4 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j > bitmap1.getHeight() * bitmap.getWidth() - (5 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j--) {
            if (intArray1[j] != 0)
                density[4]++;
        }

        for (int j = bitmap1.getHeight() * bitmap1.getWidth() - 1 - (5 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j > bitmap1.getHeight() * bitmap.getWidth() - (6 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j--) {
            if (intArray1[j] != 0)
                density[5]++;
        }

        for (int j = bitmap1.getHeight() * bitmap1.getWidth() - 1 - (6 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j > bitmap1.getHeight() * bitmap.getWidth() - (7 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j--) {
            if (intArray1[j] != 0)
                density[6]++;
        }

        for (int j = bitmap1.getHeight() * bitmap1.getWidth() - 1 - (7 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j > bitmap1.getHeight() * bitmap.getWidth() - (8 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j--) {
            if (intArray1[j] != 0)
                density[7]++;
        }

        for (int j = bitmap1.getHeight() * bitmap1.getWidth() - 1 - (8 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j > bitmap1.getHeight() * bitmap.getWidth() - (9 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j--) {
            if (intArray1[j] != 0) {
                density[8]++;
            }
        }

        for (int j = bitmap1.getHeight() * bitmap1.getWidth() - 1 - (9 * bitmap1.getHeight() * bitmap1.getWidth() / 10); j > 0; j--) {
            if (intArray1[j] != 0)
                density[9]++;
        }


        for (int k = 1; k < 10; k++) {
            if(density[k]!=0&&density[k-1]!=0)
            if (density[k] / density[k - 1] > hi || density[k - 1] / density[k] > hi) {
                if (density[k] > density[k - 1]) {
                    hi = density[k] / density[k - 1];
                    lowk = k - 1;
                } else {
                    hi = density[k - 1] / density[k];
                    lowk = k - 1;
                }
            }
        }

        for (int k = 1; k < 10; k++) {
            if (k != lowk+1) {
                if(density[k]!=0&&density[k-1]!=0)
                if (density[k] / density[k - 1] > twohi || density[k - 1] / density[k] > twohi) {
                    if (density[k] > density[k - 1]) {
                        twohi = density[k] / density[k - 1];
                        twolowk = k-1;
                    } else {
                        twohi = density[k - 1] / density[k];
                        twolowk = k-1;
                    }
                }
            }
        }

        switch (lowk) {
            case 0:
                yvalue = bitmap1.getHeight() - bitmap.getHeight() / 10;
                break;
            case 1:
                yvalue = bitmap1.getHeight() - (2 * bitmap1.getHeight() / 10);
                break;
            case 2:
                yvalue = bitmap1.getHeight() - (3 * bitmap1.getHeight() / 10);
                break;
            case 3:
                yvalue = bitmap1.getHeight() - (4 * bitmap1.getHeight() / 10);
                break;
            case 4:
                yvalue = bitmap1.getHeight() - (5 * bitmap1.getHeight() / 10);
                break;
            case 5:
                yvalue = bitmap1.getHeight() - (6 * bitmap1.getHeight() / 10);
                break;
            case 6:
                yvalue = bitmap1.getHeight() - (7 * bitmap1.getHeight() / 10);
                break;
            case 7:
                yvalue = bitmap1.getHeight() - (8 * bitmap1.getHeight() / 10);
                break;
            case 8:
                yvalue = bitmap1.getHeight() - (9 * bitmap1.getHeight() / 10);
                break;
            case 9:
                yvalue = bitmap1.getHeight() - (10 * bitmap1.getHeight() / 10);
                break;
        }

        switch (twolowk) {
            case 0:
                yvalue2 = bitmap1.getHeight() - bitmap.getHeight() / 10;
                break;
            case 1:
                yvalue2 = bitmap1.getHeight() - (2 * bitmap1.getHeight() / 10);
                break;
            case 2:
                yvalue2 = bitmap1.getHeight() - (3 * bitmap1.getHeight() / 10);
                break;
            case 3:
                yvalue2 = bitmap1.getHeight() - (4 * bitmap1.getHeight() / 10);
                break;
            case 4:
                yvalue2 = bitmap1.getHeight() - (5 * bitmap1.getHeight() / 10);
                break;
            case 5:
                yvalue2 = bitmap1.getHeight() - (6 * bitmap1.getHeight() / 10);
                break;
            case 6:
                yvalue2 = bitmap1.getHeight() - (7 * bitmap1.getHeight() / 10);
                break;
            case 7:
                yvalue2 = bitmap1.getHeight() - (8 * bitmap1.getHeight() / 10);
                break;
            case 8:
                yvalue2 = bitmap1.getHeight() - (9 * bitmap1.getHeight() / 10);
                break;
            case 9:
                yvalue2 = bitmap1.getHeight() - (10 * bitmap1.getHeight() / 10);
                break;
        }
        x = 0;
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

                bitmap2.setPixel(x, y, intArray1[i]);
            }
        } else {
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

                bitmap2.setPixel(x, y, intArray1[i]);
            }
        }


        /*for(int j=0; j<bitmap.getWidth(); j++){

            if (count!=0){
            jj= top/count;}
            kk=intArray1[j];

            if (j!= 0)
                if(intArray1[j]!=0){
                    if (top!=0&&count>0){
                    if(1-(jj/kk)<.05&&1-(jj/kk)>-.05){
                        Log.i("here2 ", "" + intArray1[j] + " " + count + " " + top + " " + (1 - (jj / kk)) + " " + (1 - (kk / jj)));
                    count++;
                    top+=intArray1[j];
                }}
                else{
                        Log.i("here3 ", "" + intArray1[j]+" "+ count+" "+ top + " "+ (1-(jj/kk)) + " "+ (1-(kk/jj)));
                        count++;
                        top+=intArray1[j];}
                }
        else{if (count==0)
                    top=0;}}

        if (count!=0)
        top = (top / count);

        if (top<0)
            top= top*-1;

        if (top!=0){

        for (int i =0; i<intArray1.length; i++){


            if (x==bitmap1.getWidth()-1){
                x=0;
                if (y!=bitmap1.getHeight()-1){
                    y++;}
            }else{
                x++;}


            if (intArray1[i]<0)
                w= intArray1[i]*-1;
            else
                w=intArray1[i];

            if (w/top>1.25||top/w>1.25){

                Log.i("here", "boom" + top/w+ " "+ w/top);
                    bitmap2.setPixel(x, y, intArray1[i]);
                }}

        imageView.setImageBitmap(bitmap2);}
    else{*/

       /* Bitmap bitmap3 = Bitmap.createBitmap(bitmap2.getWidth(), bitmap2.getHeight(), Bitmap.Config.ARGB_8888);
        int intArray2[] = new int[bitmap2.getWidth() * bitmap2.getHeight()];
        bitmap2.getPixels(intArray2, 0, bitmap2.getWidth(), 0, 0, bitmap2.getWidth(), bitmap2.getHeight());

        hi = 0;
        twohi = 0;
        int density1[]= new int[10];

        for (int j = bitmap2.getHeight() * bitmap2.getWidth() - 1; j > bitmap2.getHeight() * 2*bitmap2.getWidth() / 10; j= j-bitmap2.getHeight()) {
            if (intArray2[j] != 0)
                density1[0]++;
        }
        for (int j = bitmap2.getHeight() * bitmap2.getWidth() - 1 - (bitmap2.getHeight() * bitmap2.getWidth() / 10); j > bitmap2.getHeight() * 2*bitmap2.getWidth() / 10;  j= j-bitmap2.getHeight()) {
            if (intArray2[j] != 0)
                density1[1]++;
        }

        for (int j = bitmap2.getHeight() * bitmap2.getWidth() - 1 - (2 * bitmap2.getHeight() * bitmap2.getWidth() / 10); j >bitmap2.getHeight() * 3*bitmap2.getWidth() / 10;  j= j-bitmap2.getHeight()) {
            if (intArray2[j] != 0)
                density1[2]++;
        }

        for (int j = bitmap2.getHeight() * bitmap2.getWidth() - 1 - (3 * bitmap2.getHeight() * bitmap2.getWidth() / 10); j > bitmap2.getHeight() * 4*bitmap2.getWidth() / 10;  j= j-bitmap2.getHeight()) {
            if (intArray2[j] != 0)
                density1[3]++;
        }
        for (int j = bitmap2.getHeight() * bitmap2.getWidth() - 1 - (4 * bitmap2.getHeight() * bitmap2.getWidth() / 10); j > bitmap2.getHeight() * bitmap.getWidth() - (5 * bitmap2.getHeight() * bitmap2.getWidth() / 10);  j= j-bitmap2.getHeight()) {
            if (intArray2[j] != 0)
                density1[4]++;
        }

        for (int j = bitmap2.getHeight() * bitmap2.getWidth() - 1 - (5 * bitmap2.getHeight() * bitmap2.getWidth() / 10); j > bitmap2.getHeight() * bitmap.getWidth() - (6 * bitmap2.getHeight() * bitmap2.getWidth() / 10);  j= j-bitmap2.getHeight()) {
            if (intArray2[j] != 0)
                density1[5]++;
        }

        for (int j = bitmap2.getHeight() * bitmap2.getWidth() - 1 - (6 * bitmap2.getHeight() * bitmap2.getWidth() / 10); j > bitmap2.getHeight() * bitmap.getWidth() - (7 * bitmap2.getHeight() * bitmap2.getWidth() / 10);  j= j-bitmap2.getHeight()) {
            if (intArray2[j] != 0)
                density1[6]++;
        }

        for (int j = bitmap2.getHeight() * bitmap2.getWidth() - 1 - (7 * bitmap2.getHeight() * bitmap2.getWidth() / 10); j > bitmap2.getHeight() * bitmap.getWidth() - (8 * bitmap2.getHeight() * bitmap2.getWidth() / 10); j= j-bitmap2.getHeight()) {
            if (intArray2[j] != 0)
                density1[7]++;
        }

        for (int j = bitmap2.getHeight() * bitmap2.getWidth() - 1 - (8 * bitmap2.getHeight() * bitmap2.getWidth() / 10); j > bitmap2.getHeight() * bitmap.getWidth() - (9 * bitmap2.getHeight() * bitmap2.getWidth() / 10);  j= j-bitmap2.getHeight()) {
            if (intArray2[j] != 0) {
                density1[8]++;
            }
        }

        for (int j = bitmap2.getHeight() * bitmap2.getWidth() - 1 - (9 * bitmap2.getHeight() * bitmap2.getWidth() / 10); j > 0;  j= j-bitmap2.getHeight()) {
            if (intArray2[j] != 0)
                density1[9]++;
        }

       /* for (int k = 1; k < 10; k++) {
            if(density1[k]!=0&&density1[k-1]!=0)
                if (density1[k] / density1[k - 1] > hi || density1[k - 1] / density1[k] > hi) {
                    if (density1[k] > density1[k - 1]) {
                        hi = density1[k] / density1[k - 1];
                        lowk = k - 1;
                    } else {
                        hi = density1[k - 1] / density1[k];
                        lowk = k - 1;
                    }
                }
        }

        for (int k = 1; k < 10; k++) {
            Log.i("image", ""+ density1[k]);
            if (k != lowk+1) {
                if(density1[k]!=0&&density1[k-1]!=0)
                    if (density1[k] / density1[k - 1] > twohi || density1[k - 1] / density1[k] > twohi) {
                        if (density1[k] > density1[k - 1]) {
                            twohi = density1[k] / density1[k - 1];
                            twolowk = k-1;
                        } else {
                            twohi = density1[k - 1] / density1[k];
                            twolowk = k-1;
                        }
                    }
            }
        }

        switch (lowk) {
            case 0:
                xvalue = bitmap2.getWidth() - bitmap.getWidth() / 10;
                break;
            case 1:
                xvalue = bitmap2.getWidth() - (2 * bitmap2.getWidth() / 10);
                break;
            case 2:
                xvalue = bitmap2.getWidth() - (3 * bitmap2.getWidth() / 10);
                break;
            case 3:
                xvalue = bitmap2.getWidth() - (4 * bitmap2.getWidth() / 10);
                break;
            case 4:
                xvalue = bitmap2.getWidth() - (5 * bitmap2.getWidth() / 10);
                break;
            case 5:
                xvalue = bitmap2.getWidth() - (6 * bitmap2.getWidth() / 10);
                break;
            case 6:
                xvalue = bitmap2.getWidth() - (7 * bitmap2.getWidth() / 10);
                break;
            case 7:
                xvalue = bitmap2.getWidth() - (8 * bitmap2.getWidth() / 10);
                break;
            case 8:
                xvalue = bitmap2.getWidth() - (9 * bitmap2.getWidth() / 10);
                break;
            case 9:
                xvalue = bitmap2.getWidth() - (10 * bitmap2.getWidth() / 10);
                break;
        }

        switch (twolowk) {
            case 0:
                xvalue2 = bitmap2.getWidth() - bitmap.getWidth() / 10;
                break;
            case 1:
                xvalue2 = bitmap2.getWidth() - (2 * bitmap2.getWidth() / 10);
                break;
            case 2:
                xvalue2 = bitmap2.getWidth() - (3 * bitmap2.getWidth() / 10);
                break;
            case 3:
                xvalue2 = bitmap2.getWidth() - (4 * bitmap2.getWidth() / 10);
                break;
            case 4:
                xvalue2 = bitmap2.getWidth() - (5 * bitmap2.getWidth() / 10);
                break;
            case 5:
                xvalue2 = bitmap2.getWidth() - (6 * bitmap2.getWidth() / 10);
                break;
            case 6:
                xvalue2 = bitmap2.getWidth() - (7 * bitmap2.getWidth() / 10);
                break;
            case 7:
                xvalue2 = bitmap2.getWidth() - (8 * bitmap2.getWidth() / 10);
                break;
            case 8:
                xvalue2 = bitmap2.getWidth() - (9 * bitmap2.getWidth() / 10);
                break;
            case 9:
                xvalue2 = bitmap2.getWidth() - (10 * bitmap2.getWidth() / 10);
                break;
        }
        if (yvalue>yvalue2)
            y= yvalue2;
        else
        y=yvalue;

        if (xvalue > xvalue2) {
            x = xvalue2;

            for (int i = xvalue2 * bitmap2.getHeight(); i < xvalue * bitmap2.getHeight(); i++) {

                if (x == xvalue) {
                    x = 0;
                    if (y != bitmap.getHeight() - 1) {
                        y++;
                    }
                } else {
                    x++;
                }

                bitmap2.setPixel(x, y, intArray2[i]);
            }
        } else {
            x = xvalue;
            for (int i = xvalue * bitmap2.getHeight(); i < xvalue2 * bitmap2.getHeight(); i++) {

                if (x == xvalue2) {
                    x = 0;
                    if (y != bitmap.getHeight() - 1) {
                        y++;
                    }
                } else {
                    x++;
                }

                bitmap3.setPixel(x, y, intArray2[i]);
            }*/
        imageView.setImageBitmap(bitmap2);}








    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                         Bitmap resizedBitmap= Bitmap.createScaledBitmap(selectedImage, (int) (selectedImage.getWidth() ), (int) (selectedImage.getHeight() ), false);
                        backgroundColor(resizedBitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

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
}
