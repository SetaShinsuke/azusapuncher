package com.azusasoft.puncher.events;

/**
 * Created by SETA on 2016/2/22.
 */
public class ExitEvent {
    public enum EXIT_TYPE{
        EXIT,LOG_OUT
    }

    public EXIT_TYPE exitType = EXIT_TYPE.EXIT;
}
