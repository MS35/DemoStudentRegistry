package com.lluqteq.demoShell.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Student {

    @Id
    private String Id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    private String cellphoneNumber;

    private Date dateOfBirth;

    public String getFullName(){
        return getName() + " " + getSurname();
    }
}
