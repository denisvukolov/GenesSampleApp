package ru.denisvukolov.ui.fragment;

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

import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Provider;

import ru.denisvukolov.Utils;
import ru.denisvukolov.di.features.genedetails.GeneDetailsModule;
import ru.denisvukolov.domain.entity.GeneItem;
import ru.denisvukolov.genesapp.R;
import ru.denisvukolov.genesapp.databinding.FragmentGeneDetailsBinding;
import ru.denisvukolov.presentation.presenter.GeneDetailsPresenter;
import ru.denisvukolov.presentation.view.GeneDetailsView;
import ru.denisvukolov.ui.base.BaseMvpAppCompatFragment;
import ru.denisvukolov.ui.views.RequestErrorView;

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

        requestErrorView = RequestErrorView.create(getContext(), view.findViewById(R.id.root), onRetryClickListener
        );
    }

    //endregion

    //region ===================== View ======================

    @Override
    public void showDetails(GeneItem geneItem) {
        binding.tvGeneSymbol.setText(geneItem.getSymbol());
        binding.tvGeneName.setText(geneItem.getName());
        if (!TextUtils.isEmpty(geneItem.getCommentFunction())) {
            binding.tvFunctionsComment.setText(geneItem.getCommentFunction());
            binding.groupFunctions.setVisibility(View.VISIBLE);
        } else {
            binding.groupFunctions.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(geneItem.getCommentEvolution())) {
            binding.tvEvolutionComment.setText(geneItem.getCommentEvolution());
            binding.groupEvolution.setVisibility(View.VISIBLE);
        } else {
            binding.groupEvolution.setVisibility(View.GONE);
        }

        String bornInfo = getString(R.string.fragment_gene_details_born_info_text_format, geneItem.getOrigin().getPhylum(), geneItem.getOrigin().getAge());
        binding.tvBorn.setText(Utils.fromHtml(bornInfo));
        String orientation;
        if (GENE_ORIENTATION_MINUS.equals(geneItem.getOrientation())) {
            orientation = getString(R.string.fragment_gene_details_gene_orientation_minus);
        } else {
            orientation = getString(R.string.fragment_gene_details_gene_orientation_plus);
        }
        String placeInfo = getString(R.string.fragment_gene_details_place_text_format, geneItem.getBand(), orientation);
        binding.tvPlace.setText(Utils.fromHtml(placeInfo));
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
