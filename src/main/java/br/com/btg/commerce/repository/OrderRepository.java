package br.com.btg.commerce.repository;

import br.com.btg.commerce.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByPartyId(final Long partyId, Pageable pageable);

}