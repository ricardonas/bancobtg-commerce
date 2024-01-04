package br.com.btg.commerce.controller;

import br.com.btg.commerce.model.Order;
import br.com.btg.commerce.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{id}")
    public ResponseEntity<Order> findById(@PathVariable final Long id) {
        return ResponseEntity.ok(this.orderService.findById(id));
    }

    @GetMapping("party/{partyId}")
    public ResponseEntity<Page<Order>> findByPartyId(
        @PathVariable
        final Long partyId,
        @PageableDefault(size = 25)
        final Pageable pageable) {
        return ResponseEntity.ok(this.orderService.findByPartyId(partyId, pageable));
    }

    @RabbitListener(queues = "order.save")
    public void saveAsync(final Order order) {
        this.orderService.saveAsync(order);
    }

}