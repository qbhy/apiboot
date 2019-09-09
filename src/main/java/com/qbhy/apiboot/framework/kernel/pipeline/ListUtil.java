package com.qbhy.apiboot.framework.kernel.pipeline;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Dockable;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Stack;

import java.util.List;
import java.util.ListIterator;

public class ListUtil {
    public static Stack reduce(List<Dockable> list, Stack initial) {
        ListIterator<Dockable> iterator = list.listIterator();
        Stack stack = initial;
        while (iterator.hasNext()) {
            Dockable pipe = iterator.next();
            Stack finalStack = stack;
            stack = (Object passable) -> pipe.handle(passable, finalStack);
        }

        return stack;
    }
}
