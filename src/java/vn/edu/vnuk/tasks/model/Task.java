/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnuk.tasks.model;

import java.util.Calendar;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author michel
 */
public class Task {
    private Long id;
    
    @NotNull
    @Size(min = 5, message="Description must contain at least 5 characters")
    private String description;
    
    private boolean achieved;
    
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Calendar dateOfAchievement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAchieved() {
        return achieved;
    }

    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public Calendar getDateOfAchievement() {
        return dateOfAchievement;
    }

    public void setDateOfAchievement(Calendar dateOfAchievement) {
        this.dateOfAchievement = dateOfAchievement;
    }
    
    
}
