package com.houbenz.gameofthronesqutotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private RecyclerViewAdapter adapter;

    private Toolbar toolbar;



    private ArrayList<Quote> quotes = new ArrayList<>();

    private boolean isGrid=false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        initQuote();
        prepareRecylerView();

        QuotesViewModel quotesViewModel = new ViewModelProvider(this).get(QuotesViewModel.class);
        quotesViewModel.getSavedQuotes().observe(this,savedQuotes ->{

        });



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                for (int i = 0 ; i < 3 ; i++){
                    getQuote();
                }
            }
        });
    }

    /**
     * prepare the recyclerview for the first apprearence,
     * we put isGrid to true so the adapter
     * (thinks we pressed change to list from and show a list as default )
     */
    private void prepareRecylerView(){
        adapter = new RecyclerViewAdapter(quotes,true);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void switchBetweenGridAndListRecyclerView(boolean isGrid){
        RecyclerView.LayoutManager manager;


        if(isGrid){
             manager = new LinearLayoutManager(getApplicationContext());
             adapter = new RecyclerViewAdapter(quotes,isGrid);
        }else{
             manager=new GridLayoutManager(getApplicationContext(),2);
             adapter = new RecyclerViewAdapter(quotes,isGrid);
        }
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }


    private void initQuote(){
        for (int i = 0 ; i < 3 ; i++){
            getQuote();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.change_list_grid:
                    if (isGrid){
                        Toast.makeText(getApplicationContext(),"change to list",Toast.LENGTH_SHORT).show();
                        item.setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_baseline_format_list_bulleted_24,null));
                    }else {
                        Toast.makeText(getApplicationContext(),"change to grid",Toast.LENGTH_SHORT).show();
                        item.setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_baseline_grid_on_24,null));
                    }
                switchBetweenGridAndListRecyclerView(isGrid);
                isGrid = !isGrid;

                break;
            case R.id.bookmark:
                    Toast.makeText(getApplicationContext(),"bookmarks",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private void getQuote(){
        Retrofit retrofit = RetrofitSingleton.getInstance();

        QuoteService service = retrofit.create(QuoteService.class);

        Call<Quote> getQuote= service.getQuote();

        getQuote.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                if (response.code() == 200 ){
                    quotes.add(response.body());
                    adapter.notifyItemInserted(quotes.size() - 1 );
                }else
                    Toast.makeText(getApplicationContext(),"Some error happened",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                Log.i("okii", t.getMessage());
            }
        });
    }
}