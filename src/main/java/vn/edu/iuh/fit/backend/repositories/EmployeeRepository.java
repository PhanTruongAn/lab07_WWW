package vn.edu.iuh.fit.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.backend.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    public Page<Employee> findAll(EmployeeStatus status1,EmployeeStatus status2, Pageable pageable);
    public Optional<Employee> findById(long id);

    @Query("select e from Employee e where e.email = :email and e.phone=:phone")
    public Optional<Employee> loginEmpl(String email,String phone);

}