package hu.wup.ferencnagy.populartimes.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Parcelable POJO class for representing an media metadata object related to the article's media object.
 * @author ferencnagy
 */
public class ArticleMediaMetadata implements Parcelable {

    /**
     * Member for the URL of the media object.
     */
    @SerializedName("url")
    public String url;

    /**
     * Member for the format of the media object.
     */
    @SerializedName("format")
    public String format;

    /**
     * Creator instance for the media metadata parcelable.
     */
    public final static Parcelable.Creator<ArticleMediaMetadata> CREATOR = new Creator<ArticleMediaMetadata>() {

        /**
         * Method to create the media metadata object from a Parcel object.
         * @param in Parcel object.
         * @return Media metadata object.
         */
        @SuppressWarnings({"unchecked"})
        public ArticleMediaMetadata createFromParcel(Parcel in) {
            ArticleMediaMetadata instance = new ArticleMediaMetadata();
            instance.url = ((String) in.readValue((String.class.getClassLoader())));
            instance.format = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        /**
         * Method to instantiate a new array of media metadata objects.
         * @param size Size of the desired array.
         * @return Array of media metadata objects.
         */
        public ArticleMediaMetadata[] newArray(int size) {
            return (new ArticleMediaMetadata[size]);
        }
    };

    /**
     * Method to write the media metadata object to a Parcel object.
     * @param dest Destination Parcel object.
     * @param flags Parcel flags.
     */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(url);
        dest.writeValue(format);
    }

    /**
     * Method to create bitmask return value for the parcelable object.
     * @return Bitmask value.
     */
    public int describeContents() {
        return 0;
    }
}
