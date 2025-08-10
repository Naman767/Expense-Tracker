package com.expenseTracker.dto;

import com.expenseTracker.entity.Expense;
import com.expenseTracker.entity.Income;
import lombok.Data;

import java.util.List;

@Data
public class GraphDTO {

    private List<Expense> expensesList;
    private List<Income> incomeList;


}
