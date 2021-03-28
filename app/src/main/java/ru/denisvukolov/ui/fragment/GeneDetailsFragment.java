package ru.denisvukolov.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;
import javax.inject.Provider;

import ru.denisvukolov.di.features.genedetails.GeneDetailsModule;
import ru.denisvukolov.domain.entity.GeneItem;
import ru.denisvukolov.genesapp.R;
import ru.denisvukolov.presentation.presenter.GeneDetailsPresenter;
import ru.denisvukolov.presentation.view.GeneDetailsView;
import ru.denisvukolov.ui.base.BaseMvpAppCompatFragment;
import ru.denisvukolov.ui.views.RequestErrorView;

public class GeneDetailsFragment extends BaseMvpAppCompatFragment implements GeneDetailsView {

    private static final String KEY_ID = "KEY_ID";

    @InjectPresenter
    public GeneDetailsPresenter presenter;

    @Inject
    public Provider<GeneDetailsPresenter> presenterProvider;

    @ProvidePresenter
    public GeneDetailsPresenter providePresenter() {
        return presenterProvider.get();
    }

    private RequestErrorView requestErrorView;

    private ProgressBar progressBar;

    private TextView tvDetails;

    //region ===================== Instance ======================

    public static GeneDetailsFragment newInstance(int geneId) {
        Bundle args = new Bundle();
        args.putInt(KEY_ID, geneId);
        GeneDetailsFragment fragment = new GeneDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //endregion

    //region ===================== Callbacks ======================

    private View.OnClickListener onRetryClickListener = view -> presenter.onRetryClicked();

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
        View rootView = inflater.inflate(R.layout.fragment_gene_details, container, false);
        initUI(rootView);
        return rootView;
    }


    //endregion

    //region ===================== Internal ======================

    private void initDI() {
        getApplicationComponent().plus(new GeneDetailsModule(getArguments().getInt(KEY_ID))).inject(this);
    }

    private void initUI(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.fragment_gene_details_title);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24);
        setToolbar(toolbar);
        tvDetails = view.findViewById(R.id.tv_details);
        progressBar = view.findViewById(R.id.progress_bar);
        requestErrorView = RequestErrorView.create(getContext(), view.findViewById(R.id.root), view1 ->
                presenter.onRetryClicked()
        );
    }

    //endregion

    //region ===================== View ======================

    @Override
    public void showDetails(GeneItem geneItem) {
        tvDetails.setText(geneItem.toString());
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void handleNoConnectionError() {
        requestErrorView.setupAsNoConnectionView(onRetryClickListener).show();
    }

    @Override
    public void handleRequestError() {
        requestErrorView.setupAsNoConnectionView(onRetryClickListener).show();
    }

    @Override
    public void handleRetry() {
        requestErrorView.hide();
    }

    //endregion

}
