package com.qbhy.apiboot.framework.kernel.pipeline;

import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Destination;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Dockable;
import com.qbhy.apiboot.framework.contracts.kernel.pipeline.Pipeline;

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
        return PipelineUtil.reduce(this.stops, destination::handle).next(this.traveler);
    }
}
