package com.tinyhabits.razorthink.tinyhabits;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Deepak Detni on 01-02-2017.
 */

public interface AlertDialogCallback<T> {
    public void alertDialogCallback(T allType);
}
