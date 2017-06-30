package hu.wup.ferencnagy.populartimes.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;

import hu.wup.ferencnagy.populartimes.BuildConfig;
import hu.wup.ferencnagy.populartimes.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Class for the container activity of the article detail screen.
 * @author ferencnagy
 */
public class ArticleDetailActivity extends AppCompatActivity {

    /**
     * Overridden method of onCreate for the sake of screen initialization
     * and fragment creation with the selected article object taken into consideration.
     * @param savedInstanceState State of the saved instance bundle.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.article_detail_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(BuildConfig.SELECTED_ARTICLE_KEY, getIntent().getParcelableExtra(BuildConfig.SELECTED_ARTICLE_KEY));
            ArticleDetailFragment fragment = new ArticleDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.article_detail_container, fragment)
                    .commit();
        }
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
}
