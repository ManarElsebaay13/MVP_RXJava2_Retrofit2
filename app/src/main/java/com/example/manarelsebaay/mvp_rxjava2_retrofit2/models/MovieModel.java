package com.example.manarelsebaay.mvp_rxjava2_retrofit2.models;


import com.example.manarelsebaay.mvp_rxjava2_retrofit2.Data.TopMoviesResponse;
import com.example.manarelsebaay.mvp_rxjava2_retrofit2.Network.TmdbApiInterface;
import com.example.manarelsebaay.mvp_rxjava2_retrofit2.Views.Main.MainActivityContract;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MovieModel implements MainActivityContract.Model {


	@Override
	public void getTopMovies(String apiKey, final MainActivityContract.APIListener listener) {

		try {

			//  Create a Retrofit client and using the interface

			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl("https://api.themoviedb.org")
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
					.build();

			TmdbApiInterface service = retrofit.create(TmdbApiInterface.class);


			HashMap<String, String> options = new HashMap<>();
			options.put("api_key", apiKey);
			options.put("language", "en-US");
			options.put("sort_by", "popularity.desc");
			options.put("include_adult", "false");
			options.put("include_video", "false");
			options.put("page", "1");


			Observable<TopMoviesResponse> observable=service.getTopMovies(options).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

			observable.subscribe(new Observer <TopMoviesResponse>() {
				@Override
				public void onSubscribe(Disposable d) {

				}

				@Override
				public void onNext(TopMoviesResponse topMoviesResponse) {
					listener.onSuccess(topMoviesResponse);

				}

				@Override
				public void onError(Throwable e) {

					listener.onError(e);
				}

				@Override
				public void onComplete() {

				}
			});




		} catch (Exception e) {
			listener.onFailure(e);
			e.printStackTrace();
		}


	}

}



/**
 *
 *
 Observable<TopMoviesResponse> observable=service.getTopMovies(options);
 observable.subscribeOn(Schedulers.io())
 .observeOn(AndroidSchedulers.mainThread());

 Observer <TopMoviesResponse> observer = new Observer <TopMoviesResponse>() {
@Override
public void onSubscribe(Disposable d) {

}

@Override
public void onNext(TopMoviesResponse topMoviesResponse) {
listener.onSuccess();

}

@Override
public void onError(Throwable e) {

}

@Override
public void onComplete() {

}
};





 */


/**	Call<TopMoviesResponse> call = service.getTopMovies(options);

 call.enqueue(new Callback<TopMoviesResponse>() {
@Override
public void onResponse(Call<TopMoviesResponse> call, Response <TopMoviesResponse> response) {

if (response.isSuccessful()) {

listener.onSuccess(response);
} else {

listener.onError(response);
}
}

@Override
public void onFailure(Call<TopMoviesResponse> call, Throwable t) {

listener.onFailure(t);
}
});

 **/