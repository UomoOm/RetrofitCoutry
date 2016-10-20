package om.studies.om.a1810;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.activity_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(countries, this);
        adapter.setListener(new RecyclerViewAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClickListener(Country item, int position) {
                Toast.makeText(MainActivity.this, (CharSequence) item, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Retrofit.getCountries(new Callback<List<Country>>() {
            @Override
            public void success(List<Country> countries, Response response) {

                recyclerView.setAdapter(new RecyclerViewAdapter(countries, MainActivity.this));

                Toast.makeText(MainActivity.this, countries.get(0).name, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
