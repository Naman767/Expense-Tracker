package com.expenseTracker.controller;

import com.expenseTracker.dto.ExpenseDTO;
import com.expenseTracker.entity.Expense;
import com.expenseTracker.services.expenses.ExpenseServiceRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpenseController {

    private final ExpenseServiceRepo expenseServiceRepo;

    @PostMapping
    ResponseEntity<?> postExpense(@RequestBody ExpenseDTO expenseDTO){
        Expense createExpense = expenseServiceRepo.postExpense(expenseDTO);
        if(createExpense != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createExpense);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllExpense(){
        return ResponseEntity.ok(expenseServiceRepo.getAllExpense());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id){

        try {
            return ResponseEntity.ok(expenseServiceRepo.getExpenseById(id));
        
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id,@RequestBody ExpenseDTO expenseDTO){
        try {
            return ResponseEntity.ok(expenseServiceRepo.updateExpense(id,expenseDTO));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id){
        try {
            expenseServiceRepo.deleteExpense(id);
            return ResponseEntity.ok(null);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}
