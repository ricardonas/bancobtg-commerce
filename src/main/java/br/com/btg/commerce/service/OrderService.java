package br.com.btg.commerce.service;

import br.com.btg.commerce.exception.BusinessException;
import br.com.btg.commerce.exception.ExceptionMessage;
import br.com.btg.commerce.model.Order;
import br.com.btg.commerce.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topicExchange;

    public Order findById(final Long id) {
        return this.orderRepository.findById(id)
            .orElseThrow(() -> new BusinessException(new ExceptionMessage("not-found")));
    }

    public Page<Order> findByPartyId(final Long partyId, Pageable pageable) {
        return this.orderRepository.findByPartyId(partyId, pageable);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void saveAsync(Order order) {
        order = this.orderRepository.save(order);
        // Publishes in a topic to communicate an order update for anyone who has an interest in knowing.
        this.rabbitTemplate.convertAndSend(topicExchange.getName(), "order.update", order);
    }

}