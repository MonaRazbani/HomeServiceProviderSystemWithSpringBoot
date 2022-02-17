package ir.maktab.homeserviceprovidersystemwithspringboot.services.interfaces;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.Order;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.SubService;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.enums.OrderStatus;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.*;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.CustomerDto;
import ir.maktab.homeserviceprovidersystemwithspringboot.dto.modelDtos.roles.ExpertDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order saveOrder(OrderDto orderDto);

    void editOrderExplanation(OrderDto orderDto, String newExplanation);

    void editOrderAddress(OrderDto orderDto, AddressDto newAddressDto);

    void editOrderSuggestedPrice(OrderDto orderDto, double suggestedPrice);

    void editOrderServiceType(OrderDto orderDto, SubServiceDto subServiceDto);

    void deleteOrder(OrderDto orderDto);

    List<Order> findOrderByCustomer(CustomerDto customerDto);

    List<Order> findOrderByExpertAndStatus(ExpertDto expertDto, OrderStatus status);

    Order findOrderById(long id);

    Order findOrderByIdentificationCode(UUID identificationCode);

    OrderDto findOrderDtoByIdentificationCode(UUID identificationCode);

    long findOrderId (UUID identificationCode);

    void updateOrder(OrderDto orderDto);

    void updateOrder(Order order);

    List<OrderDto> findOrderByStatusAndSubService(OrderStatus orderStatus, SubService subService);

    List<OrderDto> findOrderForExpertBasedOnSubService(String expertEmail);

    void changeOrderStatus(Order order,OrderStatus orderStatus);

    void setCommentForOrder(OrderDto orderDto , CommentDto commentDto);
    
    void paymentWithCredit(TransactionDto transactionDto, OrderDto orderDto);
}
