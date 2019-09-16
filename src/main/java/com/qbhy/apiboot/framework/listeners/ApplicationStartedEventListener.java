package com.qbhy.apiboot.framework.listeners;

import com.qbhy.apiboot.framework.contracts.kernel.ServiceRegister;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent>, Ordered {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent e) {
//        e.getApplicationContext().getBean(ServiceRegister.class).registerServices();
    }

    @Override
    public int getOrder() {
        return 10;
    }

}