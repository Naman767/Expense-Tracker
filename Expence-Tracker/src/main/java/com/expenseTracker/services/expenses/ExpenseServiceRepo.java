package com.expenseTracker.services.expenses;

import com.expenseTracker.dto.ExpenseDTO;
import com.expenseTracker.entity.Expense;

import java.util.List;

public interface ExpenseServiceRepo {

    Expense postExpense(ExpenseDTO expenseDTO);

    List<Expense> getAllExpense();
    Expense getExpenseById(Long id);
    Expense updateExpense(Long id,ExpenseDTO expenseDTO);
    void deleteExpense(Long id);

}
