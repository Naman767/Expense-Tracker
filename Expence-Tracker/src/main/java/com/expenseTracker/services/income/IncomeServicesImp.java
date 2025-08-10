package com.expenseTracker.services.income;

import com.expenseTracker.dto.IncomeDTO;
import com.expenseTracker.entity.Income;
import com.expenseTracker.repository.IncomeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServicesImp implements IncomeServices{

    private final IncomeRepo incomeRepo;

    public Income postIncome(IncomeDTO incomeDTO){ return saveOrUpdateIncome(new Income(),incomeDTO);
    }

    public Income saveOrUpdateIncome(Income income, IncomeDTO incomeDTO){
        income.setTitle(incomeDTO.getTitle());
        income.setDate(incomeDTO.getDate());
        income.setAmount(incomeDTO.getAmount());
        income.setCategory(incomeDTO.getCategory());
        income.setDescription(incomeDTO.getDescription());

        return incomeRepo.save(income);
    }

    public Income updateIncome(Long id , IncomeDTO incomeDTO){
        Optional<Income> optionalIncome = incomeRepo.findById(id);
        if(optionalIncome.isPresent()){
            return saveOrUpdateIncome(optionalIncome.get(),incomeDTO);
        }else{
            throw new EntityNotFoundException("Income is not present with id" + id);
        }
    }

    public List<IncomeDTO> getAllIncomes(){
        return incomeRepo.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDTO)
                .collect(Collectors.toList());
    }

    public IncomeDTO getById(Long id){
        Optional<Income> optionalIncome = incomeRepo.findById(id);
        if(optionalIncome.isPresent()){
            return optionalIncome.get().getIncomeDTO();
        }else{
            throw new EntityNotFoundException("Income is not present with id " + id);
        }
    }

    public void deleteById(Long id){
        Optional<Income> optionalIncome = incomeRepo.findById(id);
        if(optionalIncome.isPresent()){
            incomeRepo.deleteById(id);
        }else{
            throw new EntityNotFoundException("Income is not present with id" + id);
        }
    }

}
