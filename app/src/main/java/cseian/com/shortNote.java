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

                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                        NotificationChannel channel=new NotificationChannel("Notification","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
                        NotificationManager manager=getSystemService(NotificationManager.class);
                        manager.createNotificationChannel(channel);
                }


                button.setOnClickListener(new View.OnClickListener(){
                                                  @Override
                                                  public void onClick(View v) {
                                                          NotificationCompat.Builder builder=new NotificationCompat.Builder(shortNote.this,"Notification");
                                                          builder.setContentTitle("Title");
                                                          builder.setContentText("Simple Notification");
                                                          builder.setSmallIcon(R.drawable.img);
                                                          builder.setAutoCancel(true);

                                                          NotificationManagerCompat managerCompat=NotificationManagerCompat.from(shortNote.this);
                                                          managerCompat.notify(1, builder.build());
                                                          dataEntered = editText.getText().toString();
                                                          text.setText(dataEntered);
                                                  }
                                          }
                );
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