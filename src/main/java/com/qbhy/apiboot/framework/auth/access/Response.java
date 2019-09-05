package com.qbhy.apiboot.framework.auth.access;

import com.qbhy.apiboot.framework.contracts.fmt.Stringify;

public class Response implements Stringify {

    private String message;

    public Response(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    /**
     * Get the string representation of the message.
     *
     * @return string
     */
    public String toString()
    {
        return this.message;
    }
}
