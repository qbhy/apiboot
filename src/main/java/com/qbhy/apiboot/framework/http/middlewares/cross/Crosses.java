package com.qbhy.apiboot.framework.http.middlewares.cross;

import java.util.HashMap;

public class Crosses extends HashMap<String, Cross> {
    /**
     * 自己指定cross
     *
     * @param cross cross
     * @return Crosses
     */
    public Crosses add(Cross cross) {
        this.put(cross.getHost(), cross);
        return this;
    }

    /**
     * 使用默认cross
     * @param host host
     * @return Crosses
     */
    public Crosses add(String host) {
        return add(new Cross(host));
    }
}
