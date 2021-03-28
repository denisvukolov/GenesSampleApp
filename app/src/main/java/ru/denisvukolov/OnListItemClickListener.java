package ru.denisvukolov;

public interface OnListItemClickListener<T> {
    void onItemClicked(T object, int position);
}
