package org.mdahsan101.Exceptions;

import lombok.Data;

@Data
public class EmployeeAlredyExists extends RuntimeException
{
    private String message;

    public EmployeeAlredyExists() {
        this.message = "Employee already exists with same username. Give different username.";
    }
}

