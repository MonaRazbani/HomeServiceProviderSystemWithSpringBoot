package ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities;

import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.Customer;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.entities.roles.Expert;
import ir.maktab.homeserviceprovidersystemwithspringboot.data.models.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "CustomerOrder")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(columnDefinition = "BINARY(16)")
    private UUID identificationCode;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer ;

    private double suggestedPrice ;

    private String explanation ;

    @ManyToOne(fetch = FetchType.EAGER)
    private SubService subService;

    private Date performedOrder ;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @CreationTimestamp
    private Date creation ;

    @ManyToOne
    private Expert expert ;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @OneToOne(cascade = CascadeType.ALL)
    private Comment comment ;



}
