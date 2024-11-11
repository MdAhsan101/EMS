package org.mdahsan101.Entity;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eid; //Employee ID

    @Column(name = "uname")
    public String UName;

    @Column(name="password")
    public String Password;

    @Column(name="name")
    public String Name;

    @Column(name = "age")
    public Integer Age;

    @Column(name = "gender")
    public String Gender;

    public void Employee(){
    }

    public void Employee(String UName, String Password, String Name, int Age, String Gender)
    {
        this.UName = UName;
        this.Password= Password;
        this.Name= Name;
        this.Age= Age;
        this.Gender= Gender;
    }
}

