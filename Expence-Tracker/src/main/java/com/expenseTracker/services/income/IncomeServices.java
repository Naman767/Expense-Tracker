package com.expenseTracker.services.income;


import com.expenseTracker.dto.IncomeDTO;
import com.expenseTracker.entity.Income;

import java.util.List;

public interface IncomeServices {

    Income postIncome(IncomeDTO incomeDTO);

    List<IncomeDTO> getAllIncomes();

    Income updateIncome(Long id , IncomeDTO incomeDTO);
    IncomeDTO getById(Long id);
    void deleteById(Long id);


}
