package com.groupproject.services;

import com.groupproject.entities.Account;
import com.groupproject.entities.Order;
import com.groupproject.repository.AccountRepository;
import com.groupproject.repository.OrderDetailsRepository;
import com.groupproject.repository.OrderRepository;
import com.groupproject.requests.OrderRequest;
import java.time.LocalDateTime;
import java.util.List;

import com.groupproject.services.interfaces.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Override
    public List<Order> getAll() {
        log.info("Ready to find all the orders");
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        log.info("Ready to find order by id");
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getOrdersByAccountId(Long accountId) {
        log.info("Ready to find Order by account id {}",accountId);
        return orderRepository.findOrderByAccount_AccountId(accountId);
    }

    @Override
    public Long createOrder(OrderRequest request) {

        //want instead of the whole account to give only accountId
        log.info("Ready to find th account");
        Long accountId= request.getAccountId();
        Account account=accountRepository.findById(accountId).orElse(null);

        log.info("Ready to insert a new Order");
        LocalDateTime now = LocalDateTime.now();
        Order order = new Order(now, account,0); //request.getTotalCoins());//probably 0 coins here
        Order newOrder = orderRepository.save(order);
        log.info("The new order is {}", newOrder);
        log.info("The order has been inserted to the DB");
        return order.getOrderId();
    }

    @Override
    public Order updateOrder(Long id, OrderRequest request) {
        log.info("Ready to update an Order ");
        Order existingOrder = orderRepository.findById(id).orElse(null);
        double orderCoinsBeforeUpdate=existingOrder.getTotalCoins();
        if (isNull(existingOrder)) {
            log.info("The order does not exists");
            return null;
        }
        Long accountId=existingOrder.getAccount().getAccountId();
        Account account= existingOrder.getAccount();
        //find the account not needed
        //log.info("Ready to find the account");
        //Long accountId=request.getAccountId();
        //Account account=accountRepository.findById(accountId).orElse(null);
        existingOrder.setTotalCoins(request.getTotalCoins());
        existingOrder.setOrderDate(LocalDateTime.now());
        Order updatedOrder = orderRepository.save(existingOrder);
        double orderCoinsAfterUpdate= existingOrder.getTotalCoins();
        log.info("The updated order is {}", updatedOrder);
        log.info("The updated order has been inserted to the DB");
        if(orderCoinsAfterUpdate>orderCoinsBeforeUpdate){
            log.info("updating account coins");
            account=accountServiceImpl.updateAccount(accountId,account.getCoins()-(orderCoinsAfterUpdate-orderCoinsBeforeUpdate));
            accountRepository.save(account);//not needed
        }
        else if(orderCoinsAfterUpdate<orderCoinsBeforeUpdate){
            log.info("updating account coins");
            account=accountServiceImpl.updateAccount(accountId,account.getCoins()+(orderCoinsBeforeUpdate-orderCoinsAfterUpdate));
            accountRepository.save(account);
        }
        return updatedOrder;
    }

    @Override
    public Order updateOrder(Long id, double totalCoins) {
        log.info("Ready to update an Order ");
        Order existingOrder = orderRepository.findById(id).orElse(null);
        double orderCoinsBeforeUpdate=existingOrder.getTotalCoins();
        if (isNull(existingOrder)) {
            log.info("The order does not exists");
            return null;
        }
        Long accountId=existingOrder.getAccount().getAccountId();
        Account account= existingOrder.getAccount();
        existingOrder.setTotalCoins(totalCoins);
//        existingOrder.setOrderDate(LocalDateTime.now());
        Order updatedOrder = orderRepository.save(existingOrder);
        double orderCoinsAfterUpdate= existingOrder.getTotalCoins();
        log.info("The updated order is {}", updatedOrder);
        log.info("The updated order has been inserted to the DB");
        log.info("Updating accounts coins");
//        double accountCoinsBeforeUpdate=account.getCoins();
//        double accountCoinsAfterUpdate=
        if(orderCoinsAfterUpdate>orderCoinsBeforeUpdate){
            log.info("updating account coins");
            account=accountServiceImpl.updateAccount(accountId,account.getCoins()-(orderCoinsAfterUpdate-orderCoinsBeforeUpdate));
            accountRepository.save(account);//not needed
        }
        else if(orderCoinsAfterUpdate<orderCoinsBeforeUpdate){
            log.info("updating account coins");
            account=accountServiceImpl.updateAccount(accountId,account.getCoins()+(orderCoinsBeforeUpdate-orderCoinsAfterUpdate));
            accountRepository.save(account);
        }
        return updatedOrder;
    }

    @Override
    public boolean deleteById(Long id) {

        log.info("Ready to delete an Order");
        if (orderRepository.existsById(id)) {
            Long accountId=orderRepository.findById(id).orElse(null).getAccount().getAccountId();
            Account account = accountRepository.findById(accountId).orElse(null);
            double orderTotalCoins=orderRepository.findById(id).orElse(null).getTotalCoins();
            orderRepository.deleteById(id);
            log.info("Order successfully deleted");
            log.info("updating account coins");
            account=accountServiceImpl.updateAccount(accountId,account.getCoins()+orderTotalCoins);
            accountRepository.save(account);//not needed
            return true;
        }
        log.info("Order wasn't deleted successfully");
        return false;
    }
}
