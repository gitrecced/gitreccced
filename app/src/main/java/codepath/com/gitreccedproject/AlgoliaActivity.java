package codepath.com.gitreccedproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.algolia.search.saas.Client;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

//this activity loads data from firebase into algolia
public class AlgoliaActivity extends AppCompatActivity {

    public Button movies_btn;
    public Button tv_btn;

    Client client = new Client("IF4OZJWJDV", "08b9cd4c085bb021ef94d0781fd000fe"); //TODO - add API key instead of ""

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algolia);

        movies_btn = findViewById(R.id.movies_btn);
        tv_btn = findViewById(R.id.tv_btn);

        movies_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client.getIndex("movies").clearIndexAsync(null,null);

                DatabaseReference moviesRef;
                moviesRef = FirebaseDatabase.getInstance().getReference("movies");
                com.google.firebase.database.Query moviesquery = null;
                moviesquery = moviesRef;

                moviesquery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            Item item = new Item();

                            item.setIid(postSnapshot.getKey());
                            item.setGenre(postSnapshot.child("genre").getValue().toString());
                            item.setDetails(postSnapshot.child("overview").getValue().toString());
                            item.setTitle(postSnapshot.child("title").getValue().toString());

                            {
                                try {
                                    client.getIndex("movies").addObjectAsync(new JSONObject()
                                            .put("Iid", postSnapshot.getKey())
                                            .put("genre", postSnapshot.child("genre").getValue().toString())
                                            .put("overview", postSnapshot.child("overview").getValue().toString())
                                            .put("title", postSnapshot.child("title").getValue().toString()), null);
                                    Log.i("algolia",postSnapshot.child("title").getValue().toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.i("snapshot", "loadPost:onCancelled");
                    }
                });
            }
        });


        tv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client.getIndex("tv").clearIndexAsync(null,null);

                DatabaseReference tvRef;
                tvRef = FirebaseDatabase.getInstance().getReference("tv");

                com.google.firebase.database.Query tvquery = null;
                tvquery = tvRef;

                tvquery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            Item item = new Item();

                            item.setIid(postSnapshot.getKey());
                            item.setGenre(postSnapshot.child("genre").getValue().toString());
                            item.setDetails(postSnapshot.child("overview").getValue().toString());
                            item.setTitle(postSnapshot.child("title").getValue().toString());

                            {
                                try {
                                    client.getIndex("tv").addObjectAsync(new JSONObject()
                                            .put("Iid", postSnapshot.getKey())
                                            .put("genre", postSnapshot.child("genre").getValue().toString())
                                            .put("overview", postSnapshot.child("overview").getValue().toString())
                                            .put("title", postSnapshot.child("title").getValue().toString()), null);
                                    Log.i("algolia",postSnapshot.child("title").getValue().toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.i("snapshot", "loadPost:onCancelled");
                    }
                });
            }
        });

    }
}