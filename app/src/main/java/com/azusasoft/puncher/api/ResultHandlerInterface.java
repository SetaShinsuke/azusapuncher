package com.azusasoft.puncher.api;

public interface ResultHandlerInterface {
    void onResponse(Object response);

    void onError(Exception e);
}
