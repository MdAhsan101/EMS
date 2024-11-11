package org.mdahsan101.Repository;
import org.mdahsan101.Entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long>
{
    @Query(value = "select eid from employee where uname=:uname and password=:pwd", nativeQuery = true)
    public Long getEmployeeId(String uname, String pwd);

    @Query(value = "select count(eid) from employee where uname=:uname", nativeQuery = true)
    public long getUnameCount(String uname);
}

