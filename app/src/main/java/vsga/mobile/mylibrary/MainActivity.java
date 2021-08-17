package vsga.mobile.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etname;
    private DatabaseHelper databaseHelper;
    private TextView tvnames;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        tvnames = findViewById(R.id.txtTVNames);

        Button btnStore = findViewById(R.id.btnStore);
        Button btnGetall = findViewById(R.id.btnGet);
        etname = findViewById(R.id.editName);

        btnStore.setOnClickListener(v -> {
            databaseHelper.addStudentDetail(etname.getText().toString());
            etname.setText("");
            Toast.makeText(MainActivity.this, "Stored Succesfully!", Toast.LENGTH_SHORT).show();
        });

        btnGetall.setOnClickListener(v -> {
            arrayList = databaseHelper.getALLStudentList();
            tvnames.setText("");
            for (int i=0;i<arrayList.size();i++){
                tvnames.setText(tvnames.getText().toString()+","+arrayList.get(i));
            }
        });
    }
}