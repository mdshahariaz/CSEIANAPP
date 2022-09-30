package cseian.com;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class shortNote extends AppCompatActivity {

    private TextView text;
    private EditText editText;
    private Button button;
    private String dataEntered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_note);

        text = findViewById(R.id.textView);
        editText = findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.button);


    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("CSEIANAPP", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("dataEntered", dataEntered);
        editor.putInt("intKey", 100);

        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences("CSEIANAPP", MODE_PRIVATE);

        String savedData = sharedPreferences.getString("dataEntered", "Demo TextView");
        text.setText(savedData);
    }
}