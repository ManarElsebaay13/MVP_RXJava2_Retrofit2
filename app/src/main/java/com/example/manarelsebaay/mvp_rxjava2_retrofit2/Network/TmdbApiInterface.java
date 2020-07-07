package com.example.manarelsebaay.mvp_rxjava2_retrofit2.Network;


import com.example.manarelsebaay.mvp_rxjava2_retrofit2.Data.TopMoviesResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


public interface TmdbApiInterface {

	@GET("/3/discover/movie")
    Call <TopMoviesResponse> getTopMovies(@QueryMap Map <String, String> options);
}