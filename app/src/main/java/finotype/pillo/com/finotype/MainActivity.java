package finotype.pillo.com.finotype;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private final int SELECT_PHOTO = 1;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView= (ImageView) findViewById(R.id.imageView);

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

    public void backgroundColor(Bitmap bitmap){


        Bitmap bitmap1= Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);
        int intArray[] = new int[bitmap.getWidth()*bitmap.getHeight()];
        bitmap.getPixels(intArray, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());

        int top=0;
        int bottom=0;
        int right=0;
        int left=0;
        int count=0;
        int x=0;
        int y=0;
        int w=0;
        int k=0;
        int count2=0;

        for(int j=0; j<bitmap.getWidth(); j++){

            if (j!= 0)
                if (intArray[j]/intArray[j-1]<1.1||intArray[j-1]/intArray[j]<1.1){
                    count++;
            top+=intArray[j];
        }}

        top= (top/count);
        if (top<0)
            top= top*-1;
        count=0;
        for(int j=intArray.length-bitmap.getWidth(); j<bitmap.getWidth()*bitmap.getHeight(); j++){
            if (j!= intArray.length-bitmap.getWidth())
            if (intArray[j]/intArray[j-1]<1.1||intArray[j-1]/intArray[j]<1.1){
            count++;
            bottom+=intArray[j];}
            }

        if (count!=0)
        bottom=(bottom/count);
        if (bottom<0)
            bottom= bottom*-1;
        count=0;
        for(int j=0; j<bitmap.getWidth()*bitmap.getHeight()-bitmap.getWidth(); j+=bitmap.getWidth()){
            if (j!= 0)
                if (intArray[j]/intArray[j-1]<1.1||intArray[j-1]/intArray[j]<1.1){
            left+=intArray[j];
                count++;}}

        if (count!=0)
            left= (left/count);
        if (left<0)
        left= left*-1;
        count=0;

        for(int j=bitmap.getWidth(); j<bitmap.getWidth()*bitmap.getHeight(); j+=bitmap.getWidth()){
            if (j!= bitmap.getWidth())
                if (intArray[j]/intArray[j-1]<1.1||intArray[j-1]/intArray[j]<1.1){
            right+=intArray[j];
            count++;}}

        if (count!=0)
        right= (right/count);

        if (right<0)
            right= right*-1;

        for (int i =0; i<intArray.length; i++){
                 if (x==bitmap.getWidth()-1){
                 x=0;
                 if (y!=bitmap.getHeight()-1){
                 y++;}
                 }else{
            x++;}


            if (intArray[i]<0)
                w= intArray[i]*-1;
            else
                w=intArray[i];

            if (w/top>1.75||top/w>1.75&&w/bottom>1.75||bottom/w>1.75&&w/left>1.75||left/w>1.75&&right/w>1.75||w/right>1.75){
                bitmap1.setPixel(x, y, intArray[i]);
            }}


          y=0;
          x=0;
        count=0;



        Bitmap bitmap2= Bitmap.createBitmap(bitmap1.getWidth(), bitmap1.getHeight(), Bitmap.Config.RGB_565);
        int intArray1[] = new int[bitmap1.getWidth()*bitmap1.getHeight()];
        bitmap1.getPixels(intArray1, 0, bitmap1.getWidth(), 0, 0, bitmap1.getWidth(), bitmap1.getHeight());

        for(int j=0; j<bitmap1.getWidth()*3; j++){
            if (j!= 0)
                if(intArray1[j]!=-16777216&&intArray1[j-1]!=-16777216){
                    Log.i("here", ""+ intArray1[j]);
                    count++;
                    top+=intArray1[j];
                }}

        top = (top / count);
        if (top<0)
            top= top*-1;


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



            if (w/top>1.75||top/w>1.75){
                    bitmap2.setPixel(x, y, intArray1[i]);
                }
        imageView.setImageBitmap(bitmap2);}}





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
                         Bitmap resizedBitmap= Bitmap.createScaledBitmap(selectedImage, (int) (selectedImage.getWidth() * .1), (int) (selectedImage.getHeight() * .1), false);
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
