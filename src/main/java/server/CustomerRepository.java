package server;

import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import server.model.Customer;

@Service
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
