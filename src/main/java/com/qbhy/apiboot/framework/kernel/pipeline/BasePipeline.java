package com.qbhy.apiboot.framework.kernel.pipeline;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Destination;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Dockable;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Pipeline;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Stack;

import java.util.List;

abstract public class BasePipeline implements Pipeline {
    protected Object traveler;
    protected List<Dockable> stops;

    /**
     * Set the traveler object being sent on the pipeline.
     *
     * @param traveler traveler
     * @return $this
     */
    @Override
    public Pipeline send(Object traveler) {
        this.traveler = traveler;
        return this;
    }

    /**
     * Set the stops of the pipeline.
     *
     * @param stops stops
     * @return $this
     */
    @Override
    public Pipeline through(List<Dockable> stops) {
        this.stops = stops;
        return this;
    }

    /**
     * Run the pipeline with a final destination callback.
     *
     * @param destination destination
     * @return Object
     */
    @Override
    public Object then(Destination destination) throws Throwable {
        Stack stack = ListUtil.reduce(this.stops, this.prepareDestination(destination));

        return stack.next(this.traveler);
    }


    protected Stack prepareDestination(Destination destination) {
        Object passable = this.traveler;
        return (Object traveler) -> {
            try {
                return destination.handle(traveler);
            } catch (Throwable e) {
                return handleException(passable, e);
            }
        };
    }

    protected Object handleException(Object passable, Throwable e) throws Throwable {
        throw e;
    }
}
