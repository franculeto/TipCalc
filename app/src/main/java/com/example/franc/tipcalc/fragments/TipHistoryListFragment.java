package com.example.franc.tipcalc.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.franc.tipcalc.R;
import com.example.franc.tipcalc.adapters.OnItemClickListener;
import com.example.franc.tipcalc.adapters.TipAdapter;
import com.example.franc.tipcalc.models.TipRecord;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener, OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private TipAdapter adapter;

    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();
        return view;
    }

    private void initAdapter()
    {
        if(adapter == null)
        {
            adapter = new TipAdapter(getActivity().getApplicationContext(), this );
        }
    }

    @Override
    public void addToList(TipRecord record) {

        adapter.add(record);

    }

    private void initRecyclerView()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void clearList() {
    adapter.clear();
    }

    @Override
    public void onItemClick(TipRecord tipRecord) {

        Toast.makeText(getActivity(), tipRecord.getDateFormatedd(), Toast.LENGTH_SHORT).show();
    }
}
