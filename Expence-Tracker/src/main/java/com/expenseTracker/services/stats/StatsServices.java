package com.expenseTracker.services.stats;

import com.expenseTracker.dto.GraphDTO;
import com.expenseTracker.dto.StatsDTO;

public interface StatsServices {

    GraphDTO getChartDate();
    StatsDTO getStats();
}
