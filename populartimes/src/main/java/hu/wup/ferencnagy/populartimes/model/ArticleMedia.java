package hu.wup.ferencnagy.populartimes.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Parcelable POJO class for representing an media object related to the article.
 * @author ferencnagy
 */
public class ArticleMedia implements Parcelable {

    /**
     * Member for the type of the media object.
     */
    @SerializedName("type")
    public String type;

    /**
     * Member for the sub-type of the media object.
     */
    @SerializedName("subtype")
    public String subtype;

    /**
     * Member for the caption of the media object.
     */
    @SerializedName("caption")
    public String caption;

    /**
     * Member for the copyright of the media object.
     */
    @SerializedName("copyright")
    public String copyright;

    /**
     * Member for the list of metadata elements related to the media object.
     */
    @SerializedName("media-metadata")
    public List<ArticleMediaMetadata> mediaMetadata;

    /**
     * Creator instance for the media object parcelable.
     */
    public final static Parcelable.Creator<ArticleMedia> CREATOR = new Creator<ArticleMedia>() {

        /**
         * Method to create the media object from a Parcel object.
         * @param in Parcel object.
         * @return Media object.
         */
        @SuppressWarnings({"unchecked"})
        public ArticleMedia createFromParcel(Parcel in) {
            ArticleMedia instance = new ArticleMedia();
            instance.type = ((String) in.readValue((String.class.getClassLoader())));
            instance.subtype = ((String) in.readValue((String.class.getClassLoader())));
            instance.caption = ((String) in.readValue((String.class.getClassLoader())));
            instance.copyright = ((String) in.readValue((String.class.getClassLoader())));
            instance.mediaMetadata = new ArrayList<>();
            in.readList(instance.mediaMetadata, (ArticleMediaMetadata.class.getClassLoader()));
            return instance;
        }

        /**
         * Method to instantiate a new array of media objects.
         * @param size Size of the desired array.
         * @return Array of media objects.
         */
        public ArticleMedia[] newArray(int size) {
            return (new ArticleMedia[size]);
        }
    };

    /**
     * Method to write the media object to a Parcel object.
     * @param dest Destination Parcel object.
     * @param flags Parcel flags.
     */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(subtype);
        dest.writeValue(caption);
        dest.writeValue(copyright);
        dest.writeList(mediaMetadata);
    }

    /**
     * Method to create bitmask return value for the parcelable object.
     * @return Bitmask value.
     */
    public int describeContents() {
        return 0;
    }
}
