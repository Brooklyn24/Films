package com.bigapps.brooklyn.films.view.container.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bigapps.brooklyn.films.MyApplication;
import com.bigapps.brooklyn.films.R;
import com.bigapps.brooklyn.films.model.ModelImpl;
import com.bigapps.brooklyn.films.view.MainContainerCallbacks;
import com.bigapps.brooklyn.films.view.baseView.BaseFragment;
import com.bigapps.brooklyn.films.view.films.filmsListFragment.ShortFilmListFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainContainerFragment extends BaseFragment implements MainContainerView {

    @Inject MainContainerPresenter presenter;

    public MainContainerFragment() {
        // Required empty public constructor
    }

    public static MainContainerFragment newInstance() {

        Bundle args = new Bundle();

        MainContainerFragment fragment = new MainContainerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getComponent().inject(this);
        presenter.subscribe(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_container, container, false);
        ButterKnife.bind(this, view);
        presenter.loadData();
        return view;
    }

    @Override
    public void showInfo() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.nowPlayingContainer,
                        ShortFilmListFragment.newInstance(ModelImpl.NOW_PLAYING))
                .commit();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.topRatedContainer,
                        ShortFilmListFragment.newInstance(ModelImpl.TOP_RATED))
                .commit();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.popularDContainer,
                        ShortFilmListFragment.newInstance(ModelImpl.POPULAR))
                .commit();
    }

    public void showNowPlayingFullList(){
        ((MainContainerCallbacks.ShowFilmList)containerActivity).showNowPlaying();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }
}
