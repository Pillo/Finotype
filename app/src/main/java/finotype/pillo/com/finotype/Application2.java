package finotype.pillo.com.finotype;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Taylor on 1/25/16.
 */
public class Application2 extends Application {

    @Override public void onCreate (){
        Parse.enableLocalDatastore(this);

        Parse.initialize(this);
        super.onCreate();
    }
}
