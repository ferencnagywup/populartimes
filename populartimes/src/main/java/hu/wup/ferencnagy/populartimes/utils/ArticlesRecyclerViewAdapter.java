package hu.wup.ferencnagy.populartimes.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hu.wup.ferencnagy.populartimes.R;
import hu.wup.ferencnagy.populartimes.model.Article;
import hu.wup.ferencnagy.populartimes.model.ArticleMedia;
import hu.wup.ferencnagy.populartimes.model.ArticleMediaMetadata;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Class for the adapter of the recycler view use in the article overview screen.
 * @author ferencnagy
 */
public class ArticlesRecyclerViewAdapter extends RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Article> articles;

    /**
     * Constructor.
     * @param context Context.
     * @param articles List of articles to display.
     */
    public ArticlesRecyclerViewAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    /**
     * Overridden method of onCreateViewHolder for the sake of inflating the layout of an article item.
     * @param parent Parent ViewGroup.
     * @param viewType Type of the view.
     * @return Valid ViewHolder object containing the layout of an article item.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_article_overview, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Overridden method of onBindViewHolder for the sake of binding the values of displayable
     * article attributes to the members of the view holder.
     * @param holder View holder.
     * @param position Position index of the current item.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Article currentArticle = articles.get(position);
        holder.title.setText(currentArticle.title);
        holder.author.setText(currentArticle.author);
        holder.publishedDate.setText(currentArticle.publishedDate);

        if (currentArticle.media.size() > 0) {
            ArticleMedia articleMedia = currentArticle.media.get(0);
            if (articleMedia.type.equals("image") && articleMedia.subtype.equals("photo")) {
                for (ArticleMediaMetadata articleMediaMetadata : articleMedia.mediaMetadata) {
                    if (articleMediaMetadata.format.equals("Normal")) {
                        Picasso.with(context)
                                .load(articleMediaMetadata.url)
                                .transform(new CropCircleTransformation())
                                .into(holder.thumbnail);
                        break;
                    }
                }
            } else {
                holder.thumbnail.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_article_thumbnail_placeholder));
            }
        } else {
            holder.thumbnail.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_article_thumbnail_placeholder));
        }
    }

    /**
     * Overridden method of getItemCount to get the number of listed articles.
     * @return Number of listed articles.
     */
    @Override
    public int getItemCount() {
        return articles.size();
    }

    /**
     * Method to get an article object based on an item position.
     * @param position Position index of a given item.
     * @return Currently selected article object.
     */
    public Article getArticle(int position) {
        return articles.get(position);
    }

    /**
     * Inner class for holding the view components for a given item of the recycler view.
     * @author ferencnagy
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;
        TextView publishedDate;
        ImageView thumbnail;

        /**
         * Constructor.
         * @param itemView View of the current item.
         */
        ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.article_overview_item_title);
            author = (TextView) itemView.findViewById(R.id.article_overview_item_author);
            publishedDate = (TextView) itemView.findViewById(R.id.article_overview_item_publication_date);
            thumbnail = (ImageView) itemView.findViewById(R.id.article_overview_item_thumbnail);
        }
    }
}
