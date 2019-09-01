package com.qbhy.apiboot.framework.contracts.auth;

import java.util.Map;

public interface RetrieveAble {

    public AuthenticateAble retrieveByCredentials(Map<String, String> credentials);
}
