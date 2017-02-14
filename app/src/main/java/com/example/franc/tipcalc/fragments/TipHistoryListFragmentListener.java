package com.example.franc.tipcalc.fragments;

import com.example.franc.tipcalc.models.TipRecord;

/**
 * Created by franc on 11/2/2017.
 */

public interface TipHistoryListFragmentListener {
    void addToList(TipRecord record);
    void clearList();

}
