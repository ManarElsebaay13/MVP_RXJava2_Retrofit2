package com.example.manarelsebaay.mvp_rxjava2_retrofit2.Views.Main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.manarelsebaay.mvp_rxjava2_retrofit2.R;
import com.example.manarelsebaay.mvp_rxjava2_retrofit2.Data.Movie;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

	MainActivityContract.Presenter mPresenter;
	ProgressDialog progressDialog;
	RecyclerView recyclerView;
	List <Movie> movieList;
	MoviesAdapter moviesAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mPresenter = new MainViewPresenter(this);
	}

	//region ----- View Contract Methods -----

	@Override
	public void setupUI() {

		progressDialog = new ProgressDialog(this);
		recyclerView = findViewById(R.id.recycler_view);

		movieList = new ArrayList <>();
		moviesAdapter = new MoviesAdapter(movieList);

		recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
		recyclerView.setAdapter(moviesAdapter);

	}

	@Override
	public String getAPIKey() {
		return getString(R.string.api_key);
	}

	@Override
	public void displayMovieData(List <Movie> list) {

		Log.d("mvp", list.size() + "");

		movieList.clear();
		movieList.addAll(list);
		moviesAdapter.notifyDataSetChanged();

	}

	@Override
	public void showMessage(String msg) {

		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showProgressDialog() {

		if (progressDialog != null && progressDialog.isShowing()) {

			progressDialog.setMessage("Loading...");

		} else {
			progressDialog.setIndeterminate(true);
			progressDialog.setMessage("Loading...");
			progressDialog.setCancelable(false);

			try {
				progressDialog.show();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	@Override
	public void hideProgressDialog() {

		try {

			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();
				progressDialog.hide();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
