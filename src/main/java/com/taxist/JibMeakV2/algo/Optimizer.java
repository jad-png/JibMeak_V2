package com.taxist.JibMeakV2.algo;


import com.taxist.JibMeakV2.model.Delivery;
import com.taxist.JibMeakV2.model.Tour;
import com.taxist.JibMeakV2.model.Vehicle;
import com.taxist.JibMeakV2.model.Warehouse;

import java.util.List;

public interface Optimizer {
    Tour optimizeTour(Warehouse wh, List<Delivery> allDvs, Vehicle vh);
}
