package hu.wup.ferencnagy.populartimes.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import hu.wup.ferencnagy.populartimes.BuildConfig;
import hu.wup.ferencnagy.populartimes.R;
import hu.wup.ferencnagy.populartimes.model.Article;
import hu.wup.ferencnagy.populartimes.model.ArticleMedia;
import hu.wup.ferencnagy.populartimes.model.ArticleMediaMetadata;

/**
 * Class for the fragment portion of the article detail screen.
 * @author ferencnagy
 */
public class ArticleDetailFragment extends Fragment {

    private Article selectedArticle;

    private TextView title;
    private TextView author;
    private TextView publishedDate;
    private ImageView photo;
    private TextView photoCaption;
    private TextView photoCopyright;
    private TextView summary;
    private Button readFullArticleButton;

    /**
     * Overridden method of onCreate for the sake of getting the selected article object.
     * @param savedInstanceState State of the saved instance bundle.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedArticle = getArguments().getParcelable(BuildConfig.SELECTED_ARTICLE_KEY);
    }

    /**
     * Overridden method of onCreate for the sake of inflating the layout of the fragment
     * and setting up the screen components.
     * @param inflater Layout inflater object.
     * @param container ViewGroup container.
     * @param savedInstanceState State of the saved instance bundle.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article_detail, container, false);
        setupUiComponents(rootView);
        return rootView;
    }

    /**
     * Method to setup the screen components in the root view (the layout of the fragment).
     * @param rootView Root view.
     */
    private void setupUiComponents(View rootView) {
        title = (TextView) rootView.findViewById(R.id.article_detail_title);
        author = (TextView) rootView.findViewById(R.id.article_detail_author);
        publishedDate = (TextView) rootView.findViewById(R.id.article_detail_published_date);
        photo = (ImageView) rootView.findViewById(R.id.article_detail_photo);
        photoCaption = (TextView) rootView.findViewById(R.id.article_detail_photo_caption);
        photoCopyright = (TextView) rootView.findViewById(R.id.article_detail_photo_copyright);
        summary = (TextView) rootView.findViewById(R.id.article_detail_summary);
        readFullArticleButton = (Button) rootView.findViewById(R.id.article_detail_read_full_article_button);

        title.setText(selectedArticle.title);
        author.setText(selectedArticle.author);
        publishedDate.setText(selectedArticle.publishedDate);
        setupArticlePhoto();
        summary.setText(selectedArticle.summary);
        readFullArticleButton.setOnClickListener(readFullArticleButtonOnClickListener);
    }

    /**
     * Method to setup the photo portion of the article detail screen.
     * The photo portion is made from the photo URL, the caption and the copyright information.
     */
    private void setupArticlePhoto() {
        if (selectedArticle.media.size() > 0) {
            ArticleMedia articleMedia = selectedArticle.media.get(0);
            if (articleMedia.type.equals("image") && articleMedia.subtype.equals("photo")) {
                for (ArticleMediaMetadata articleMediaMetadata : articleMedia.mediaMetadata) {
                    if (articleMediaMetadata.format.equals("superJumbo")) {
                        Picasso.with(getContext())
                                .load(articleMediaMetadata.url)
                                .into(photo);
                        break;
                    }
                }
            } else {
                photo.setVisibility(View.GONE);
            }
            photoCaption.setText(articleMedia.caption);
            photoCaption.setVisibility(articleMedia.caption.length() == 0 ? View.GONE : View.VISIBLE);
            photoCopyright.setText(articleMedia.copyright);
            photoCopyright.setVisibility(articleMedia.copyright.length() == 0 ? View.GONE : View.VISIBLE);
        } else {
            photo.setVisibility(View.GONE);
        }
    }

    /**
     * Listener for the clicking of the read full article button.
     * It opens the full content of the article in a separate browser application
     * based on the URL of the article.
     */
    private View.OnClickListener readFullArticleButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(selectedArticle.url));
            startActivity(intent);
        }
    };
}
