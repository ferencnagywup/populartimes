package hu.wup.ferencnagy.populartimes.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hu.wup.ferencnagy.populartimes.BuildConfig;
import hu.wup.ferencnagy.populartimes.R;
import hu.wup.ferencnagy.populartimes.api.ArticleApi;
import hu.wup.ferencnagy.populartimes.model.Article;
import hu.wup.ferencnagy.populartimes.model.GetArticlesResponse;
import hu.wup.ferencnagy.populartimes.utils.ArticleDeserializer;
import hu.wup.ferencnagy.populartimes.utils.ArticlesRecyclerViewAdapter;
import hu.wup.ferencnagy.populartimes.utils.RecyclerViewDividerItemDecoration;
import hu.wup.ferencnagy.populartimes.utils.RecyclerViewOnItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Class for the launcher activity which is the article overview screen.
 * @author ferencnagy
 */
public class ArticleOverviewActivity extends AppCompatActivity {

    private ArticleApi articleApi;
    private RecyclerView recyclerView;
    private ArticlesRecyclerViewAdapter articlesRecyclerViewAdapter;

    /**
     * Overridden method of onCreate for the sake of initializing the
     * screen, the calligraphy framework and the Retrofit API for getting
     * the articles.
     * @param savedInstanceState State of the saved instance bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_overview);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Imperial.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        recyclerView = (RecyclerView) findViewById(R.id.article_recycler_view);

        setupArticleApi();
        articleApi.getArticles(BuildConfig.API_KEY).enqueue(getArticlesResponseCallback);
    }

    /**
     * Overridden method of attachBaseContext for the sake
     * of calligraphy framework wrapping around the current context.
     * @param context Current context.
     */
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context));
    }

    /**
     * Method to setup the Retrofit API for getting the articles.
     */
    private void setupArticleApi() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapter(Article.class, new ArticleDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        articleApi = retrofit.create(ArticleApi.class);
    }

    /**
     * Callback for handling the response coming from the article querying API.
     */
    private Callback<GetArticlesResponse> getArticlesResponseCallback = new Callback<GetArticlesResponse>() {

        /**
         * Overridden method of onResponse for the sake of initializing the
         * recycler view component and filling it with the contents of the response.
         * @param call Calling method.
         * @param response Response from the server.
         */
        @Override
        public void onResponse(Call<GetArticlesResponse> call, Response<GetArticlesResponse> response) {
            if(response.isSuccessful()) {
                GetArticlesResponse getArticlesResponse = response.body();
                articlesRecyclerViewAdapter = new ArticlesRecyclerViewAdapter(ArticleOverviewActivity.this, getArticlesResponse.articles);
                recyclerView.setAdapter(articlesRecyclerViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                recyclerView.addItemDecoration(new RecyclerViewDividerItemDecoration(getBaseContext()));
                recyclerView.addOnItemTouchListener(onItemClickListener);
            } else {
                Snackbar.make(recyclerView, R.string.error_query_could_not_be_performed, Snackbar.LENGTH_INDEFINITE).show();
            }
        }

        /**
         * Overridden method of onFailure for the sake of handling communication failiure
         * @param call Calling method.
         * @param t Throwable object.
         */
        @Override
        public void onFailure(Call<GetArticlesResponse> call, Throwable t) {
            Snackbar.make(recyclerView, R.string.error_query_could_not_be_performed, Snackbar.LENGTH_INDEFINITE).show();
        }
    };

    /**
     * Listener of clicking on a recycler view item for the sake of navigation to the article
     * detail screen with wrapping of the selected article as transfer object.
     */
    private RecyclerViewOnItemClickListener onItemClickListener = new RecyclerViewOnItemClickListener(getBaseContext(), new RecyclerViewOnItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Article selectedArticle = articlesRecyclerViewAdapter.getArticle(position);
            Intent intent = new Intent(ArticleOverviewActivity.this, ArticleDetailActivity.class);
            intent.putExtra(BuildConfig.SELECTED_ARTICLE_KEY, selectedArticle);
            startActivity(intent);
        }
    });
}
