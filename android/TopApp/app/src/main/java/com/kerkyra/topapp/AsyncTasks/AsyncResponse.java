package com.kerkyra.topapp.AsyncTasks;

import com.kerkyra.topapp.model.User;

/**
 * Created by andras.timar on 6/15/2016.
 */
public interface AsyncResponse<T> {
    void processFinish(T output);
}
