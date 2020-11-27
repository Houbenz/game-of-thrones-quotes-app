package com.houbenz.gameofthronesqutotes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteService {



    @GET("quotes")
    Call<Quote> getQuote();
}
