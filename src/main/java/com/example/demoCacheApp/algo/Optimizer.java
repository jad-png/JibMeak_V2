package com.taxist.JibMeak.algo;

import com.taxist.JibMeak.model.Delivery;
import com.taxist.JibMeak.model.Tour;
import com.taxist.JibMeak.model.Vehicle;
import com.taxist.JibMeak.model.Warehouse;

import java.util.List;

public interface Optimizer {
    Tour optimizeTour(Warehouse wh, List<Delivery> allDvs, Vehicle vh);
}
