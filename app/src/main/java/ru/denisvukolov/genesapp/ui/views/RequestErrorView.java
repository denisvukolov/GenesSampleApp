package ru.denisvukolov.genesapp.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.concurrent.atomic.AtomicBoolean;

import ru.denisvukolov.genesapp.LayoutTraverser;
import ru.denisvukolov.genesapp.R;

public class RequestErrorView extends LinearLayout {

    private Integer id;
    private TextView tvTitle;
    private TextView tvSubtitle;
    private ImageView ivIcon;
    private View btnRetry;
    private ViewGroup container;


    //region ===================== Constructors ======================

    public RequestErrorView(Context context) {
        this(context, null, 0);
    }

    public RequestErrorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RequestErrorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    //endregion

    //region ===================== Public ======================

    public static RequestErrorView create(Context context,
                                          ViewGroup container,
                                          View.OnClickListener btnRetryClickListener) {
        RequestErrorView requestErrorView = new RequestErrorView(context);
        requestErrorView.setRootContainer(container);
        requestErrorView.setRetryClickListener(btnRetryClickListener);
        return requestErrorView;
    }

    public RequestErrorView setIcon(@DrawableRes int id) {
        ivIcon.setImageResource(id);
        return this;
    }

    public RequestErrorView setTitle(@StringRes int id) {
        tvTitle.setText(id);
        return this;
    }

    public RequestErrorView setSubtitle(@StringRes int id) {
        tvSubtitle.setText(id);
        return this;
    }

    public RequestErrorView setRetryClickListener(View.OnClickListener btnRetryClickListener) {
        btnRetry.setOnClickListener(btnRetryClickListener);
        return this;
    }

    public RequestErrorView setup(@DrawableRes int iconId,
                                  @StringRes int titleId,
                                  @StringRes int subtitleId,
                                  View.OnClickListener btnRetryClickListener) {
        setIcon(iconId);
        setTitle(titleId);
        setSubtitle(subtitleId);
        setRetryClickListener(btnRetryClickListener);
        return this;
    }

    public RequestErrorView setupAsNoConnectionView(View.OnClickListener btnRetryClickListener) {
        return setup(R.drawable.ic_wifi_off_56,
                R.string.request_error_view_no_connection_title,
                R.string.request_error_view_no_connection_subtitle,
                btnRetryClickListener);
    }

    public RequestErrorView setupAsErrorView(View.OnClickListener btnRetryClickListener) {
        return setup(R.drawable.ic_report_problem_56,
                R.string.request_error_view_default_title,
                R.string.request_error_view_default_subtitle,
                btnRetryClickListener);
    }

    public void setRootContainer(ViewGroup container) {
        this.container = container;
    }

    public RequestErrorView show() {
        if (container == null) {
            throw new RuntimeException("You forgot to specify container for RequestErrorView (in layout)");
        }
        if (!containerHasErrorView(container)) {
            if (container instanceof ConstraintLayout) {
                ConstraintLayout constraintLayout = (ConstraintLayout) container;
                id = View.generateViewId();
                setId(id);
                LinearLayout.LayoutParams requestErrorViewLayoutParams = new LinearLayout.LayoutParams(0, 0);
                setLayoutParams(requestErrorViewLayoutParams);
                container.addView(this);
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(constraintLayout);
                constraintSet.connect(getId(), ConstraintSet.TOP, container.getId(), ConstraintSet.TOP);
                constraintSet.connect(getId(), ConstraintSet.BOTTOM, container.getId(), ConstraintSet.BOTTOM);
                constraintSet.connect(getId(), ConstraintSet.LEFT, container.getId(), ConstraintSet.LEFT);
                constraintSet.connect(getId(), ConstraintSet.RIGHT, container.getId(), ConstraintSet.RIGHT);
                constraintSet.applyTo(constraintLayout);
            } else {
                container.addView(this);
            }
        }
        setVisibility(VISIBLE);
        return this;
    }

    public void hide() {
        setVisibility(GONE);
    }

    //endregion

    //region ===================== Internal ======================

    private void initUI() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_request_error, this, true);
        tvTitle = findViewById(R.id.tv_title);
        tvSubtitle = findViewById(R.id.tv_subtitle);
        ivIcon = findViewById(R.id.iv_icon);
        btnRetry = findViewById(R.id.btn_retry);
        interceptClicks(this, false);
    }

    public static void interceptClicks(View target, boolean soundEffectsEnabled) {
        target.setOnClickListener(v -> {
        });
        target.setSoundEffectsEnabled(soundEffectsEnabled);
    }

    public boolean containerHasErrorView(ViewGroup container) {
        AtomicBoolean hasView = new AtomicBoolean(false);
        LayoutTraverser.build(view -> {
            if (view instanceof RequestErrorView) {
                hasView.set(true);
            }
        }).traverse(container);
        return hasView.get();
    }

    //endregion
}
