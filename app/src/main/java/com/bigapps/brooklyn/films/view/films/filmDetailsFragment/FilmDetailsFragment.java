package com.bigapps.brooklyn.films.view.films.filmDetailsFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bigapps.brooklyn.films.MyApplication;
import com.bigapps.brooklyn.films.R;
import com.bigapps.brooklyn.films.model.ModelImpl;
import com.bigapps.brooklyn.films.model.entities.FilmDetails;
import com.bigapps.brooklyn.films.view.baseView.BaseFragment;
import com.bigapps.brooklyn.films.view.films.filmsListFragment.ShortFilmListFragment;
import com.bigapps.brooklyn.films.view.people.shorListPeople.ShortListPeopleFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilmDetailsFragment extends BaseFragment implements FilmDetailsView {

    @Inject FilmDetailsPresenter presenter;
    @BindView(R.id.filmImage) ImageView filmImage;
    @BindView(R.id.filmNameTxt) TextView filmNameTxt;
    @BindView(R.id.filmRatingTxt) TextView filmRatingTxt;
    @BindView(R.id.imageLoadPb) ProgressBar imageLoadPb;
    @BindView(R.id.genreTxt) TextView genreTxt;
    @BindView(R.id.originalTitleTxt) TextView originalTitleTxt;
    @BindView(R.id.ratingBar) RatingBar ratingBar;
    @BindView(R.id.overviewTxt) TextView overviewTxt;

    public FilmDetailsFragment() {
    }

    public static FilmDetailsFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt("id", id);
        FilmDetailsFragment fragment = new FilmDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_details, container, false);
        ButterKnife.bind(this, view);
        presenter.loadData(getArguments().getInt("id"));
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getComponent().inject(this);
        presenter.subscribe(this);
    }

    @Override
    public void showFilm(FilmDetails filmDetails) {
        imageLoadPb.setVisibility(View.VISIBLE);
        Picasso.with(getActivity())
                .load("http://image.tmdb.org/t/p/w500/" + filmDetails.getPosterPath())
                .error(R.drawable.ic_no_image)
                .into(filmImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        imageLoadPb.setVisibility(GONE);
                    }

                    @Override
                    public void onError() {
                        imageLoadPb.setVisibility(GONE);
                    }
                });
        filmNameTxt.setText(filmDetails.getTitle() + " (" +
                filmDetails.getReleaseDate().substring(0, 4) + ")");
        filmRatingTxt.setText(String.valueOf(filmDetails.getVoteAverage()));
        ratingBar.setRating(filmDetails.getVoteAverage());
        if (filmDetails.getTitle().equals(filmDetails.getOriginalTitle())) {
            originalTitleTxt.setVisibility(GONE);
        } else originalTitleTxt.setText(filmDetails.getOriginalTitle());
        genreTxt.setText(filmDetails.getGenresString());
        overviewTxt.setText(filmDetails.getOverview());
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.castListContainer,
                        ShortListPeopleFragment.newInstance(getArguments().getInt("id")))
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }
}
