package hu.wup.ferencnagy.populartimes.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Parcelable POJO class for representing an article object.
 * @author ferencnagy
 */
public class Article implements Parcelable {

    /**
     * Member for the URL of the full article.
     */
    @SerializedName("url")
    public String url;

    /**
     * Member for the author(s) of the article.
     */
    @SerializedName("byline")
    public String author;

    /**
     * Member for the title of the article.
     */
    @SerializedName("title")
    public String title;

    /**
     * Member for the short summary of the article.
     */
    @SerializedName("abstract")
    public String summary;

    /**
     * Member for the published date of the article.
     */
    @SerializedName("published_date")
    public String publishedDate;

    /**
     * Member for the list of media objects related to the article.
     */
    @SerializedName("media")
    public List<ArticleMedia> media;

    /**
     * Default constructor.
     */
    public Article() {
    }

    /**
     * Constructor for custom deserialization usage purposes.
     * @param url URL of the article.
     * @param author Author of the article.
     * @param title Title of the article.
     * @param summary Summary of the article.
     * @param publishedDate Published date of the article.
     * @param mediaList List of media objects related to the article.
     */
    public Article(String url, String author, String title, String summary, String publishedDate, List<ArticleMedia> mediaList) {
        this.url = url;
        this.author = author;
        this.title = title;
        this.summary = summary;
        this.publishedDate = publishedDate;
        this.media = mediaList;
    }

    /**
     * Creator instance for the article parcelable.
     */
    public final static Parcelable.Creator<Article> CREATOR = new Creator<Article>() {

        /**
         * Method to create the article object from a Parcel object.
         * @param in Parcel object.
         * @return Article object.
         */
        @SuppressWarnings({"unchecked"})
        public Article createFromParcel(Parcel in) {
            Article instance = new Article();
            instance.url = ((String) in.readValue((String.class.getClassLoader())));
            instance.author = ((String) in.readValue((String.class.getClassLoader())));
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            instance.summary = ((String) in.readValue((String.class.getClassLoader())));
            instance.publishedDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.media = new ArrayList<>();
            in.readList(instance.media, (ArticleMedia.class.getClassLoader()));
            return instance;
        }

        /**
         * Method to instantiate a new array of article objects.
         * @param size Size of the desired array.
         * @return Array of article objects.
         */
        public Article[] newArray(int size) {
            return (new Article[size]);
        }
    };

    /**
     * Method to write the article object to a Parcel object.
     * @param dest Destination Parcel object.
     * @param flags Parcel flags.
     */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(url);
        dest.writeValue(author);
        dest.writeValue(title);
        dest.writeValue(summary);
        dest.writeValue(publishedDate);
        dest.writeList(media);
    }

    /**
     * Method to create bitmask return value for the parcelable object.
     * @return Bitmask value.
     */
    public int describeContents() {
        return 0;
    }
}
