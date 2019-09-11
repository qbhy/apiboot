package com.qbhy.apiboot.framework.kernel.pipeline;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Dockable;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Stack;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class PipelineUtil {
    /**
     * @param list    停靠站点
     * @param initial 初始栈
     * @return 栈
     */
    public static Stack reduce(List<Dockable> list, Stack initial) {
        Collections.reverse(list);
        ListIterator<Dockable> stops = list.listIterator();
        Stack finalStack = initial;
        while (stops.hasNext()) {
            Dockable dock = stops.next();
            Stack stack = finalStack;
            finalStack = (Object passable) -> dock.handle(passable, stack);
        }

        return finalStack;
    }
}
