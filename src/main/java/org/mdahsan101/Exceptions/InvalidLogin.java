package org.mdahsan101.Exceptions;
import lombok.Data;

@Data
public class InvalidLogin extends RuntimeException
{
    private String message;

    public InvalidLogin() {
        this.message= "Username or password is not correct...";
    }
}

