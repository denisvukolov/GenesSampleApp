package ru.denisvukolov.ui.activity;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;
import javax.inject.Provider;

import ru.denisvukolov.di.features.maincontainer.MainContainerModule;
import ru.denisvukolov.genesapp.R;
import ru.denisvukolov.presentation.presenter.GenesMainContainerPresenter;
import ru.denisvukolov.presentation.view.GenesMainContainerView;
import ru.denisvukolov.ui.base.BaseMvpAppCompatActivity;
import ru.denisvukolov.ui.fragment.GenesListFragment;

public class MainContainerActivity extends BaseMvpAppCompatActivity implements GenesMainContainerView {

    @InjectPresenter
    GenesMainContainerPresenter presenter;
    @Inject
    Provider<GenesMainContainerPresenter> presenterProvider;

    @ProvidePresenter
    GenesMainContainerPresenter providePresenter(){
        return presenterProvider.get();
    }

    //region ===================== Lifecycle ======================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initDI();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);
    }

    //endregion

    //region ===================== Internal ======================

    private void initDI() {
        getAppComponent().plus(new MainContainerModule()).inject(this);
    }

    //endregion

    //region ===================== View ======================

    @Override
    public void showGenesListScreen() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, GenesListFragment.newInstance())
                .commit();
    }

    //endregion
}
