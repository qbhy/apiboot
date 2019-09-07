package com.qbhy.apiboot.framework.contracts.kernel.pipeline;

import java.util.List;

public interface Pipeline {
    /**
     * Set the traveler object being sent on the pipeline.
     *
     * @param  traveler traveler
     * @return $this
     */
    public Pipeline send(Passable traveler);

    /**
     * Set the stops of the pipeline.
     *
     * @param stops stops
     * @return $this
     */
    public Pipeline through(List<Dockable> stops);

    /**
     * Run the pipeline with a final destination callback.
     *
     * @param  destination destination
     * @return Object
     */
    public Object then(Destination destination);
}
