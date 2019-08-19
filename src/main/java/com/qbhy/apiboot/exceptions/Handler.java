package com.qbhy.apiboot.exceptions;

public class Handler {
    public final void handle(Exception e) {
        if (e instanceof RenderableException) {
            ((RenderableException) e).render();
        }
    }
}
