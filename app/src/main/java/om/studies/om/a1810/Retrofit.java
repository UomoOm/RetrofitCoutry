package om.studies.om.a1810;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Om on 18.10.2016.
 */

public class Retrofit {

    private static final String ENDPOINT = "http://restcountries.eu/rest";
    private static ApiReferences apiReferences;

    static {
        initialize();
    }

    private static void initialize() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        apiReferences = restAdapter.create(ApiReferences.class);
    }

    interface ApiReferences {
        @GET("/v1/all")
        void getCountries(Callback<List<Country>> callback);

        @GET("/v1/capital/{capital}")
        void getCountry(@Path("capital") String capital, Callback<Country> callback);
    }

    public static void getCountries(Callback<List<Country>> callback) {
        apiReferences.getCountries(callback);

    }

    public static void getCountry(String capital, Callback<Country> callback) {
        apiReferences.getCountry(capital, callback);

    }
}
