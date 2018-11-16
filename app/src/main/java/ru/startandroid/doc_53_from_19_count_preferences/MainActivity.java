package ru.startandroid.doc_53_from_19_count_preferences;


import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.os.Bundle;

import org.w3c.dom.TypeInfo;

import java.sql.Types;

public class MainActivity extends AppCompatActivity {
    private TextView mHelloTextView, mСountTextView;
    private int mCntСrow = 0, mCntСat = 0;

    // 53
    // это будет именем файла настроек
    public static final String APP_PREFERENCES = "settings_cnt";
    // Создадим параметр, который мы хотим сохранять в настройках. Нас интересуют показания счётчика.
    //public static final String APP_COUNTER_Сrow = "counter_Сrow";
    //public static final String APP_PREFERENCES_COUNTER = "counter";
    // Создаём переменную, представляющую экземпляр класса SharedPreferences, который отвечает за работу с настройками:
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelloTextView = (TextView) findViewById(R.id.text_KtoTi);
        mСountTextView = (TextView) findViewById(R.id.text_rez);

    }

    @Override
    protected void onPause() {
        super.onPause();
        // Запоминаем данные
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt("counter_Сrow" , mCntСrow);
        editor.putInt("counter_Сat" , mCntСat);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mSettings.contains("counter_Сrow")) {
            // Получаем число из настроек
            mCntСrow = mSettings.getInt("counter_Сrow", 0);
            mCntСat = mSettings.getInt("counter_Сat" , 0);


            rez_Say();  // Выводим на экран данные из настроек
        }
    }

    public void Click_new_crow(View view) {
        // mСountTextView.setText("Я насчитал " + ++mCntСrow + " ворон"+"["+mCntСat );
        mCntСrow++;

        LayoutInflater inflater  = getLayoutInflater() ;
        View layout = inflater.inflate(R.layout.custom_layout ,
                (ViewGroup) findViewById(R.id.toast_layout) );
        Toast ts_crow = Toast.makeText( getApplicationContext() , "ворон" ,  Toast.LENGTH_SHORT);
        ts_crow.setGravity(Gravity.LEFT , 0, 0);
        ts_crow.setView(layout);

        ts_crow.show();
        rez_Say();
    }

    public void Click_new_cat(View view) {
        mCntСat++;
        Toast ts_cat = Toast.makeText( getApplicationContext() , "котиков" ,  Toast.LENGTH_LONG);
        ts_cat.setGravity(Gravity.RIGHT , 0, 0);
        LinearLayout toastContainer = (LinearLayout) ts_cat.getView();
        ImageView catImg =new ImageView( getApplicationContext());
        catImg.setImageResource(R.drawable.cat) ;
        toastContainer.addView( catImg , 0);
        ts_cat.show();
        rez_Say();
        // mСountTextView.setText("Я насчитал " + ++mCntСat + "котиков "+"["+mCntСrow );
    }

    public void rez_Say() {
        mСountTextView.setText("Я насчитал " + mCntСrow + " ворон и " + mCntСat + " котиков ");
    }

    public void Click_Say(View view) {
        mHelloTextView.setText("Hello User!");
    }
}

