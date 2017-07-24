package com.bigapps.brooklyn.films.view.people.shorListPeople;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigapps.brooklyn.films.MyApplication;
import com.bigapps.brooklyn.films.R;
import com.bigapps.brooklyn.films.adapters.ShortCastListAdapter;
import com.bigapps.brooklyn.films.model.entities.Cast;
import com.bigapps.brooklyn.films.view.baseView.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShortListPeopleFragment extends BaseFragment implements ShortListPeopleView {

    @Inject ShortListPeoplePresenter presenter;
    @BindView(R.id.castRV) RecyclerView castRV;
    ShortCastListAdapter adapter;


    public static ShortListPeopleFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt("id", id);
        ShortListPeopleFragment fragment = new ShortListPeopleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ShortListPeopleFragment() {
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
        View view = inflater.inflate(R.layout.fragment_short_list_people, container, false);
        ButterKnife.bind(this, view);
        castRV.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        presenter.loadData(getArguments().getInt("id"));
        return view;
    }

    @Override
    public void showCastList(List<Cast> castList) {
        adapter = new ShortCastListAdapter(castList, getActivity());
        castRV.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }
}
