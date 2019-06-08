
package com.movtrack.RestClient.TV;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "imdb_id",
    "freebase_mid",
    "freebase_id",
    "tvdb_id",
    "tvrage_id",
    "facebook_id",
    "instagram_id",
    "twitter_id"
})
public class ExternalIds {

    @JsonProperty("imdb_id")
    private String imdbId;
    @JsonProperty("freebase_mid")
    private Object freebaseMid;
    @JsonProperty("freebase_id")
    private Object freebaseId;
    @JsonProperty("tvdb_id")
    private Integer tvdbId;
    @JsonProperty("tvrage_id")
    private Object tvrageId;
    @JsonProperty("facebook_id")
    private Object facebookId;
    @JsonProperty("instagram_id")
    private String instagramId;
    @JsonProperty("twitter_id")
    private String twitterId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("imdb_id")
    public String getImdbId() {
        return imdbId;
    }

    @JsonProperty("imdb_id")
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @JsonProperty("freebase_mid")
    public Object getFreebaseMid() {
        return freebaseMid;
    }

    @JsonProperty("freebase_mid")
    public void setFreebaseMid(Object freebaseMid) {
        this.freebaseMid = freebaseMid;
    }

    @JsonProperty("freebase_id")
    public Object getFreebaseId() {
        return freebaseId;
    }

    @JsonProperty("freebase_id")
    public void setFreebaseId(Object freebaseId) {
        this.freebaseId = freebaseId;
    }

    @JsonProperty("tvdb_id")
    public Integer getTvdbId() {
        return tvdbId;
    }

    @JsonProperty("tvdb_id")
    public void setTvdbId(Integer tvdbId) {
        this.tvdbId = tvdbId;
    }

    @JsonProperty("tvrage_id")
    public Object getTvrageId() {
        return tvrageId;
    }

    @JsonProperty("tvrage_id")
    public void setTvrageId(Object tvrageId) {
        this.tvrageId = tvrageId;
    }

    @JsonProperty("facebook_id")
    public Object getFacebookId() {
        return facebookId;
    }

    @JsonProperty("facebook_id")
    public void setFacebookId(Object facebookId) {
        this.facebookId = facebookId;
    }

    @JsonProperty("instagram_id")
    public String getInstagramId() {
        return instagramId;
    }

    @JsonProperty("instagram_id")
    public void setInstagramId(String instagramId) {
        this.instagramId = instagramId;
    }

    @JsonProperty("twitter_id")
    public String getTwitterId() {
        return twitterId;
    }

    @JsonProperty("twitter_id")
    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
