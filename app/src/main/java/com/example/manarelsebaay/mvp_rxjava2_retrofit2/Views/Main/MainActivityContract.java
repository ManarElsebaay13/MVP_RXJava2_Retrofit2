package com.example.manarelsebaay.mvp_rxjava2_retrofit2.Views.Main;

import com.example.manarelsebaay.mvp_rxjava2_retrofit2.Data.Movie;
import com.example.manarelsebaay.mvp_rxjava2_retrofit2.Data.TopMoviesResponse;

import java.util.List;

public interface MainActivityContract {

	interface Model {

		void getTopMovies(String apiKey, final APIListener listener);	// Retrieve list of movies
	}

	interface View {

		void setupUI();
		String getAPIKey();	// This method give out api key from string .

		void displayMovieData(List <Movie> moviesList);
		void showMessage(String msg);	// To display message as Toast messages

		// Show and hide progress dialog
		void showProgressDialog();
		void hideProgressDialog();
	}

	interface Presenter {

		void getTopMovies(String apiKey);
	}

	interface APIListener {

		void onSuccess(TopMoviesResponse response);
		void onError(Throwable response);
		void onFailure(Throwable t);

	}

}
