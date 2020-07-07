package com.example.manarelsebaay.mvp_rxjava2_retrofit2.Views.Main;


import com.example.manarelsebaay.mvp_rxjava2_retrofit2.Data.TopMoviesResponse;
import com.example.manarelsebaay.mvp_rxjava2_retrofit2.models.MovieModel;


public class MainViewPresenter implements MainActivityContract.Presenter, MainActivityContract.APIListener {

	MainActivityContract.View mView;
	MainActivityContract.Model mModel;

	public MainViewPresenter(MainActivityContract.View view) {

		mView = view;
		mModel = new MovieModel();
		mView.setupUI();
		getTopMovies(mView.getAPIKey());
	}

	// ----- Presenter Contract Methods -----

	@Override
	public void getTopMovies(String apiKey) {

		mView.showProgressDialog();
		mModel.getTopMovies(apiKey, this);

	}



	// ----- Network Listener -----

	@Override
	public void onSuccess(TopMoviesResponse response) {

		mView.hideProgressDialog();
		mView.displayMovieData(response.getResults());
	}

	@Override
	public void onError(Throwable response) {

		mView.hideProgressDialog();
		mView.showMessage("Error Occured.");
	}

	@Override
	public void onFailure(Throwable t) {

		mView.hideProgressDialog();
		mView.showMessage(t.getMessage());
	}



}
