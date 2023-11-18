package vn.edu.iuh.fit.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Customer;
import vn.edu.iuh.fit.backend.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServices {
    @Autowired
    private CustomerRepository customerRepository;
    public Page<Customer> findPaginated(int pageNo,int pageSize,String sortBy,String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        return customerRepository.getAll(pageable);
    }

    public Optional<Customer> findbyId(long id){
        return customerRepository.findById(id);
    }
}
