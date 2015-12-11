package com.todor.diabetes.ui.product_list;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.todor.diabetes.R;
import com.todor.diabetes.db.ProductFunctionality;
import com.todor.diabetes.models.Product;
import com.todor.diabetes.ui.BaseFragment;
import com.todor.diabetes.ui.CursorAdapter;
import com.todor.diabetes.ui.CursorLoader;

import java.util.ArrayList;

public class ProductListFragment extends BaseFragment implements
        LoaderManager.LoaderCallbacks<ArrayList<Product>> {

    private ProductFunctionality dbManager;
    private String fragmentName = "Product list";
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.product_layout, container, false);
        dbManager = new ProductFunctionality(getActivity());

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        LinearLayoutManager       layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.ItemAnimator itemAnimator  = new DefaultItemAnimator();

        getActivity().getLoaderManager().initLoader(0, null, this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);

        return v;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public String getFragmentTitle() {
        return fragmentName;
    }

    @Override
    public Loader<ArrayList<Product>> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Product>> loader, ArrayList<Product> data) {
        recyclerView.setAdapter(new CursorAdapter(data));
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Product>> loader) {
        recyclerView.setAdapter(null);
    }
}
