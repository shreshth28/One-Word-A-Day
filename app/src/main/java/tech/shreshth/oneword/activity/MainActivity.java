package tech.shreshth.oneword.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.shreshth.oneword.R;
import tech.shreshth.oneword.RetrofitClient;
import tech.shreshth.oneword.adaper.NewsAdapter;
import tech.shreshth.oneword.model.Articles;
import tech.shreshth.oneword.model.News;

public class MainActivity extends AppCompatActivity {

    private String query;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private NewsAdapter newsAdapter;
    private List<Articles> articlesList=new ArrayList<>();
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent()!=null)
            query=getIntent().getStringExtra("query");
        recyclerView=findViewById(R.id.article_recycler_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("LATEST NEWS ON "+query.toUpperCase());
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        newsAdapter=new NewsAdapter(articlesList);
        recyclerView.setAdapter(newsAdapter);
        getData();
    }

    private void getData() {
      RetrofitClient.getRetrofitClient().getNews(query,getResources().getString(R.string.api_key)).enqueue(new Callback<News>() {
          @Override
          public void onResponse(Call<News> call, Response<News> response) {
              Log.d("Response Body",response.toString());
              if(response.isSuccessful() && response.body()!=null)
              {
                  articlesList.addAll(response.body().getArticles());
                  newsAdapter.notifyDataSetChanged();
                  recyclerView.scheduleLayoutAnimation();
              }
          }

          @Override
          public void onFailure(Call<News> call, Throwable t) {
              Toast.makeText(MainActivity.this, "Error while fetching data", Toast.LENGTH_SHORT).show();
          }
      });
    }
}