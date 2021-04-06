package ru.denisvukolov.genesapp.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import ru.denisvukolov.genesapp.util.Utils;
import ru.denisvukolov.genesapp.di.features.genedetails.GeneDetailsModule;
import ru.denisvukolov.domain.entity.FunctionalCluster;
import ru.denisvukolov.domain.entity.GeneItem;
import ru.denisvukolov.genesapp.R;
import ru.denisvukolov.genesapp.databinding.FragmentGeneDetailsBinding;
import ru.denisvukolov.genesapp.presentation.presenter.GeneDetailsPresenter;
import ru.denisvukolov.genesapp.presentation.view.GeneDetailsView;
import ru.denisvukolov.genesapp.ui.base.BaseMvpAppCompatFragment;
import ru.denisvukolov.genesapp.ui.views.RequestErrorView;

public class GeneDetailsFragment extends BaseMvpAppCompatFragment implements GeneDetailsView {

    private static final String KEY_ID = "KEY_ID";
    private static final String GENE_ORIENTATION_MINUS = "-1";

    @InjectPresenter
    public GeneDetailsPresenter presenter;

    @Inject
    public Provider<GeneDetailsPresenter> presenterProvider;

    @ProvidePresenter
    public GeneDetailsPresenter providePresenter() {
        return presenterProvider.get();
    }

    private FragmentGeneDetailsBinding binding;
    private RequestErrorView requestErrorView;

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

    private final View.OnClickListener onRetryClickListener = view -> presenter.onRetryClicked();

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gene_details, container, false);
        initUI(binding.getRoot());
        return binding.getRoot();
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
        toolbar.setNavigationOnClickListener((view1) -> getActivity().onBackPressed());
        requestErrorView = RequestErrorView.create(getContext(), view.findViewById(R.id.root), onRetryClickListener);
    }

    private void showCommentFunction(String commentFunction) {
        if (!TextUtils.isEmpty(commentFunction)) {
            binding.tvFunctionsComment.setText(commentFunction);
            binding.groupFunctions.setVisibility(View.VISIBLE);
        } else {
            binding.groupFunctions.setVisibility(View.GONE);
        }
    }

    private void showCommentEvolution(String commentEvolution) {
        if (!TextUtils.isEmpty(commentEvolution)) {
            binding.tvEvolutionComment.setText(commentEvolution);
            binding.groupEvolution.setVisibility(View.VISIBLE);
        } else {
            binding.groupEvolution.setVisibility(View.GONE);
        }
    }

    private void showBornInfo(String phylum, String age) {
        String bornInfo = getString(R.string.fragment_gene_details_born_info_text_format, phylum, age);
        binding.tvBorn.setText(Utils.fromHtml(bornInfo));

    }

    private void showPlaceInfo(String band, String orientation) {
        String orientationText;
        if (GENE_ORIENTATION_MINUS.equals(orientation)) {
            orientationText = getString(R.string.fragment_gene_details_gene_orientation_minus);
        } else {
            orientationText = getString(R.string.fragment_gene_details_gene_orientation_plus);
        }
        String placeInfo = getString(R.string.fragment_gene_details_place_text_format, band, orientationText);
        binding.tvPlace.setText(Utils.fromHtml(placeInfo));
    }

    private void showFunctionalClusters(List<FunctionalCluster> clusters) {
        StringBuilder clustersText = new StringBuilder();
        for (FunctionalCluster cluster : clusters) {
            clustersText.append(cluster.getName()).append(", ");
        }
        clustersText.delete(clustersText.length() - 2, clustersText.length());
        binding.tvFunctions.setText(clustersText);
    }

    private void showCommentCause(@Nullable String[] commentCauses) {
        if (commentCauses != null && commentCauses.length > 0) {
            StringBuilder commentCauseText = new StringBuilder();
            for (String commentCause : commentCauses) {
                commentCauseText.append("- ").append(commentCause).append(";\n");
            }
            binding.tvCauseComment.setText(commentCauseText);
            binding.groupFunctions.setVisibility(View.VISIBLE);
        } else {
            binding.groupFunctions.setVisibility(View.GONE);
        }
    }

    //endregion

    //region ===================== View ======================

    @Override
    public void showDetails(GeneItem geneItem) {
        binding.tvGeneSymbol.setText(geneItem.getSymbol());
        binding.tvGeneName.setText(geneItem.getName());
        showCommentFunction(geneItem.getCommentFunction());
        showCommentEvolution(geneItem.getCommentEvolution());
        showBornInfo(geneItem.getOrigin().getPhylum(), geneItem.getOrigin().getAge());
        showPlaceInfo(geneItem.getBand(), geneItem.getOrientation());
        showFunctionalClusters(geneItem.getFunctionalClusters());
        showCommentCause(geneItem.getCommentCause());
    }

    @Override
    public void showLoader() {
        binding.flProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        binding.flProgress.setVisibility(View.GONE);
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
