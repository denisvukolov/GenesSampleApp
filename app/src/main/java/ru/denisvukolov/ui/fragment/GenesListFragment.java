package ru.denisvukolov.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import ru.denisvukolov.genesapp.databinding.FragmentGenesListBinding;
import ru.denisvukolov.presentation.presenter.GenesListPresenter;
import ru.denisvukolov.presentation.view.GenesListView;
import ru.denisvukolov.ui.adapter.GenesAdapter;
import ru.denisvukolov.ui.base.BaseMvpAppCompatFragment;
import ru.denisvukolov.ui.views.RequestErrorView;

public class GenesListFragment extends BaseMvpAppCompatFragment implements GenesListView {

    @InjectPresenter
    public GenesListPresenter presenter;

    @Inject
    Provider<GenesListPresenter> presenterProvider;

    @ProvidePresenter
    GenesListPresenter providePresenter() {
        return presenterProvider.get();
    }

    @Inject
    public GenesAdapter adapter;

    private RequestErrorView requestErrorView;
    private FragmentGenesListBinding binding;

    //region ===================== Public ======================

    public static GenesListFragment newInstance() {
        return new GenesListFragment();
    }

    //endregion


    //region ===================== Callbacks ======================

    private OnListItemClickListener<Integer> onListItemClickListener = (object, position) -> presenter.onGeneItemClicked(object);

    private View.OnClickListener onRetryClickListener = view -> presenter.onRetryClicked();

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = () -> presenter.onRefreshClicked();

    //endregion


    //region ===================== Lifecycle ======================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initDI();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_genes_list, container, false);
        initUI(binding.getRoot());
        return binding.getRoot();
    }

    //endregion

    //region ===================== Internal ======================

    private void initDI() {
        getApplicationComponent().plus(new GenesListModule(
                onListItemClickListener)).inject(this);
    }

    private void initUI(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        setToolbar(toolbar);
        binding.rvGenes.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvGenes.setAdapter(adapter);
        binding.swipeToRefresh.setOnRefreshListener(onRefreshListener);
        requestErrorView = RequestErrorView.create(getContext(), view.findViewById(R.id.root), onRetryClickListener);
    }

    //endregion

    //region ===================== View ======================

    @Override
    public void showGenesList(List<GeneItem> genes) {
        adapter.swapItems(genes);
        binding.rvGenes.setVisibility(View.VISIBLE);
        binding.swipeToRefresh.setRefreshing(false);
    }

    @Override
    public void openGeneDetailsScreen(int geneId) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, GeneDetailsFragment.newInstance(geneId))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showLoader() {
        binding.flProgress.setVisibility(View.VISIBLE);
        binding.rvGenes.setVisibility(View.GONE);
        binding.swipeToRefresh.setVisibility(View.GONE);
    }

    @Override
    public void hideLoader() {
        binding.flProgress.setVisibility(View.GONE);
        binding.rvGenes.setVisibility(View.VISIBLE);
        binding.swipeToRefresh.setVisibility(View.VISIBLE);
        binding.swipeToRefresh.setRefreshing(false);
    }

    @Override
    public void handleRetry() {
        requestErrorView.hide();
    }

    @Override
    public void handleNoConnectionError() {
        requestErrorView.setupAsNoConnectionView(onRetryClickListener).show();
    }

    @Override
    public void handleRequestError() {
        requestErrorView.setupAsErrorView(onRetryClickListener).show();
    }

    //endregion
}
