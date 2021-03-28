package ru.denisvukolov;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LayoutTraverser {

    private Processor processor;

    public LayoutTraverser(Processor processor) {
        this.processor = processor;
    }

    public static LayoutTraverser build(@NonNull Processor processor) {
        return new LayoutTraverser(processor);
    }

    // https://stackoverflow.com/a/28509431/6325722
    public void traverse(@Nullable View root) {
        if (root != null) {
            processor.process(root);
            if (root instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) root;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View child = viewGroup.getChildAt(i);
                    traverse(child);
                }
            }
        }
    }

    public interface Processor {
        void process(View view);
    }
}
