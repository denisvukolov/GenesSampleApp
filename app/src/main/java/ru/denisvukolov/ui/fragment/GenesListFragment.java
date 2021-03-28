package ru.denisvukolov.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import ru.denisvukolov.OnListItemClickListener;
import ru.denisvukolov.di.features.geneslist.GenesListModule;
import ru.denisvukolov.domain.entity.GeneItem;
import ru.denisvukolov.genesapp.R;
import ru.denisvukolov.presentation.presenter.GenesListPresenter;
import ru.denisvukolov.presentation.view.GenesListView;
import ru.denisvukolov.ui.adapter.GenesAdapter;
import ru.denisvukolov.ui.base.BaseMvpAppCompatFragment;

public class GenesListFragment extends BaseMvpAppCompatFragment implements GenesListView {

    private ProgressBar progressBar;
    private RecyclerView rvGenes;
    private SwipeRefreshLayout refreshLayout;

    @Inject
    public GenesAdapter adapter;

    @InjectPresenter
    public GenesListPresenter presenter;

    @Inject
    Provider<GenesListPresenter> presenterProvider;

    @ProvidePresenter
    GenesListPresenter providePresenter() {
        return presenterProvider.get();
    }

    public static GenesListFragment newInstance() {
        return new GenesListFragment();
    }

    private OnListItemClickListener onListItemClickListener = (object, position) -> presenter.onGeneItemClicked((Integer) object);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initDI();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_genes_list, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initDI() {
        getApplicationComponent().plus(new GenesListModule(
                onListItemClickListener)).inject(this);
    }

    private void initUI(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        rvGenes = view.findViewById(R.id.rv_genes);
        rvGenes.setLayoutManager(new LinearLayoutManager(getContext()));
        rvGenes.setAdapter(adapter);
        progressBar = view.findViewById(R.id.progress_bar);
        refreshLayout = view.findViewById(R.id.swipe_to_refresh);
        refreshLayout.setOnRefreshListener(() -> presenter.onRefreshClicked());
    }

    @Override
    public void showGenesList(List<GeneItem> genes) {
        adapter.swapItems(genes);
        rvGenes.setVisibility(View.VISIBLE);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void openGeneDetailsScreen(int geneId) {
        Toast.makeText(getContext(), "Show gene details for id=" + geneId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
        rvGenes.setVisibility(View.GONE);
        refreshLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
        rvGenes.setVisibility(View.VISIBLE);
        refreshLayout.setVisibility(View.VISIBLE);
    }
}
