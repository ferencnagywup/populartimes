package hu.wup.ferencnagy.populartimes.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Parcelable POJO class for representing a response object for getting articles.
 * @author ferencnagy
 */
public class GetArticlesResponse implements Parcelable {

    /**
     * Member for the list of article objects.
     */
    @SerializedName("results")
    public List<Article> articles;

    /**
     * Creator instance for the response object parcelable.
     */
    public final static Parcelable.Creator<GetArticlesResponse> CREATOR = new Creator<GetArticlesResponse>() {

        /**
         * Method to create the response object from a Parcel object.
         * @param in Parcel object.
         * @return Response object.
         */
        @SuppressWarnings({"unchecked"})
        public GetArticlesResponse createFromParcel(Parcel in) {
            GetArticlesResponse instance = new GetArticlesResponse();
            in.readList(instance.articles, (Article.class.getClassLoader()));
            return instance;
        }

        /**
         * Method to instantiate a new array of response objects.
         * @param size Size of the desired array.
         * @return Array of response objects.
         */
        public GetArticlesResponse[] newArray(int size) {
            return (new GetArticlesResponse[size]);
        }
    };

    /**
     * Method to write the response object to a Parcel object.
     * @param dest Destination Parcel object.
     * @param flags Parcel flags.
     */
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(articles);
    }

    /**
     * Method to create bitmask return value for the parcelable object.
     * @return Bitmask value.
     */
    public int describeContents() {
        return 0;
    }
}
