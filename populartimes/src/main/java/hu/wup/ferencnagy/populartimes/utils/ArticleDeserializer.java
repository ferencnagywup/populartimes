package hu.wup.ferencnagy.populartimes.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import hu.wup.ferencnagy.populartimes.model.Article;
import hu.wup.ferencnagy.populartimes.model.ArticleMedia;

/**
 * Class for custom deserialization of the article object.
 * @author ferencnagy
 */
public class ArticleDeserializer implements JsonDeserializer<Article> {

    /**
     * Overridden method of deserialize for the sake of custom deserialization of an article object.
     * It might happen that the media object of an article is sometimes represented as an empty string
     * instead of an array therefore the default GSON parser will be failing, so special handling
     * is needed for that field. Other needed article element can be handled as usual.
     * @param json JSON element.
     * @param typeOfT Type of the element.
     * @param context JSON deserialization context.
     * @return Successfully deserialized article object.
     * @throws JsonParseException possible exception
     */
    @Override
    public Article deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonElement = jsonObject.get("media");
        List<ArticleMedia> articleMediaList = Collections.emptyList();

        if (jsonElement.isJsonArray()) {
            articleMediaList = context.deserialize(jsonElement.getAsJsonArray(), new TypeToken<List<ArticleMedia>>(){}.getType());
        }

        String url;
        String author;
        String title;
        String summary;
        String publishedDate;

        jsonElement = jsonObject.get("url");
        url = context.deserialize(jsonElement, new TypeToken<String>(){}.getType());

        jsonElement = jsonObject.get("byline");
        author = context.deserialize(jsonElement, new TypeToken<String>(){}.getType());

        jsonElement = jsonObject.get("title");
        title = context.deserialize(jsonElement, new TypeToken<String>(){}.getType());

        jsonElement = jsonObject.get("abstract");
        summary = context.deserialize(jsonElement, new TypeToken<String>(){}.getType());

        jsonElement = jsonObject.get("published_date");
        publishedDate = context.deserialize(jsonElement, new TypeToken<String>(){}.getType());

        return new Article(url, author, title, summary, publishedDate, articleMediaList);
    }
}
