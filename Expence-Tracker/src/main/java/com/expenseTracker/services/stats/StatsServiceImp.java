package com.expenseTracker.services.stats;

import com.expenseTracker.dto.GraphDTO;
import com.expenseTracker.dto.StatsDTO;
import com.expenseTracker.entity.Expense;
import com.expenseTracker.entity.Income;
import com.expenseTracker.repository.ExpenseRepository;
import com.expenseTracker.repository.IncomeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class StatsServiceImp implements StatsServices{

    private final IncomeRepo incomeRepo;

    private final ExpenseRepository expenseRepository;

    public GraphDTO getChartDate(){
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(27);

        GraphDTO graphDTO = new GraphDTO();

        graphDTO.setExpensesList(expenseRepository.findByDateBetween(startDate,endDate));
        graphDTO.setIncomeList(incomeRepo.findByDateBetween(startDate,endDate));

        return graphDTO;
    }

    public StatsDTO getStats(){
        Double totalIncome = incomeRepo.sumAllAmount();
        Double totalExpense = expenseRepository.sumAllAmount();

        Optional<Income> optionalIncome = incomeRepo.findFirstByOrderByDateDesc();
        Optional<Expense> optionalExpense = expenseRepository.findFirstByOrderByDateDesc();

        StatsDTO statsDTO = new StatsDTO();
        statsDTO.setExpense(totalExpense);
        statsDTO.setIncome(totalIncome);

        optionalIncome.ifPresent(statsDTO::setLatestIncome);
        optionalExpense.ifPresent(statsDTO::setLatestExpense);

        statsDTO.setBalance(totalIncome-totalExpense);

        List<Income> incomeList = incomeRepo.findAll();
        List<Expense> expenseList =  expenseRepository.findAll();

        OptionalDouble minIncome = incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble maxIncome = incomeList.stream().mapToDouble(Income::getAmount).max();

        OptionalDouble minExpense = expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble maxExpense = expenseList.stream().mapToDouble(Expense::getAmount).max();

        statsDTO.setMaxExpense(maxExpense.isPresent() ? maxExpense.getAsDouble() : null);
        statsDTO.setMinExpense(minExpense.isPresent() ? minExpense.getAsDouble() : null);

        statsDTO.setMaxIncome(maxIncome.isPresent() ? maxIncome.getAsDouble() : null);
        statsDTO.setMinIncome(minIncome.isPresent() ? minIncome.getAsDouble() : null);


        return statsDTO;
    }
}
