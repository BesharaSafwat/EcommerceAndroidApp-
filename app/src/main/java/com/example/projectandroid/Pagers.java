package com.example.projectandroid;

public enum Pagers {

    FIRST(R.string.white,R.layout.first_view),
    SECOND(R.string.white,R.layout.second_view),
    THIRD(R.string.white,R.layout.third_view);

    private int myTitleResId;
    private int myLayoutResId;

    Pagers(int titleResId, int layoutResId) {
        myTitleResId = titleResId;
        myLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return myTitleResId;
    }

    public int getLayoutResId() {
        return myLayoutResId;
    }

}
