package com.qbhy.apiboot.framework.contracts.auth;

import java.util.List;
import java.util.Map;

public interface GuardProvider {
    public Map<String, Guard> guards();
}
