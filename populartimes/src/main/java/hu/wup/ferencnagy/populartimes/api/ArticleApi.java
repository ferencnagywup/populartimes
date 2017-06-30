package hu.wup.ferencnagy.populartimes.api;

import hu.wup.ferencnagy.populartimes.model.GetArticlesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface for the Api endpoints of the article query.
 * @author ferencnagy
 */
public interface ArticleApi {

    /**
     * Method to get the New York Times Most Popular articles from the last week.
     * @param apiKey API key for the New York Times API.
     * @return Formalized response object which contains the queried articles.
     */
    @GET("all-sections/7.json")
    Call<GetArticlesResponse> getArticles(@Query("api-key") String apiKey);
}
