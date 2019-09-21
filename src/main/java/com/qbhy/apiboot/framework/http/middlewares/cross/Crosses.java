package com.qbhy.apiboot.framework.http.middlewares.cross;

import java.util.HashMap;

public class Crosses extends HashMap<String, Cross> {
    /**
     * 自己指定cross
     *
     * @param cross
     * @return
     */
    public Crosses add(Cross cross) {
        this.put(cross.getHost(), cross);
        return this;
    }

    /**
     * 使用默认cross
     * @param host
     * @return
     */
    public Crosses add(String host) {
        Cross cross = new Cross(host);
        this.put(cross.getHost(), cross);
        return this;
    }
}
