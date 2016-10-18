package om.studies.om.a1810;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (RecyclerView) findViewById(R.id.activity_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Retrofit.getCountries(new Callback<List<Country>>() {
            @Override
            public void success(List<Country> countries, Response response) {

                list.setAdapter(new RecyclerViewAdapter(countries, MainActivity.this));

                Toast.makeText(MainActivity.this, countries.get(0).name, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
