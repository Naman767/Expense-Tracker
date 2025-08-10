package com.expenseTracker.entity;

import com.expenseTracker.dto.IncomeDTO;
import com.expenseTracker.services.income.IncomeServices;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer amount;

    private LocalDate date;

    private String category;

    private String description;

    public IncomeDTO getIncomeDTO(){
        IncomeDTO incomeDTO = new IncomeDTO();

        incomeDTO.setId(id);
        incomeDTO.setTitle(title);
        incomeDTO.setAmount(amount);
        incomeDTO.setCategory(category);
        incomeDTO.setDescription(description);
        incomeDTO.setDate(date);

        return incomeDTO;
    }

}
