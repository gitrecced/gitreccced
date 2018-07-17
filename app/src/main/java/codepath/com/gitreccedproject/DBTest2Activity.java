package codepath.com.gitreccedproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBTest2Activity extends AppCompatActivity {

    DatabaseReference dbItems;
    EditText etGenre;
    EditText etTitle;
    Button btnSubmitItem;

    String uid = "user id not set yet"; //user id (initialized to dummy string for testing)
    String iid = "item id not set yet"; //item id (initialized to dummy string for testing)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest2);

        //reference to items field of json array in database
        dbItems = FirebaseDatabase.getInstance().getReference("items").child(uid);
        etGenre = findViewById(R.id.etGenre);
        etTitle = findViewById(R.id.etTitle);
        btnSubmitItem = findViewById(R.id.btnSubmitItem);

        //set click listener to submit item info to realtime db
        btnSubmitItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add new item to items field
                addItem();
            }
        });
    }

    private void addItem(){
        String genre = etGenre.getText().toString();
        String title = etTitle.getText().toString();

        if(!TextUtils.isEmpty(title)){
            iid = dbItems.push().getKey();

            Intent intent = getIntent();
            String uid = intent.getStringExtra("uid");

            //new item to add
            Item newItem = new Item(iid, genre, title, "dummy details string", uid);

            dbItems.child(iid).setValue(newItem);

        }
    }
}
