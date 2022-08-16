package br.com.dh.meli.projeto_integrador.model;

import br.com.dh.meli.projeto_integrador.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import javax.persistence.*;
import java.time.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatchStock {
    @Id
    @Column(name = "id")
    private Integer batchNumber;

    @Column(name = "product_sku", nullable = false)
    private String productId;

    @Column(name = "current_temperature", nullable = false)
    private Float currentTemperature;

    @Column(name = "minimum_temperature", nullable = false)
    private Float minimumTemperature;

    @Column(name = "initial_quantity", nullable = false)
    private Integer initialQuantity;

    @Column(name = "current_quantity", nullable = false)
    private Integer currentQuantity;

    @Column(name = "manufacturing_date", nullable = false)
    private LocalDate manufacturingDate;

    @Column(name = "manufacturing_time", nullable = false)
    private LocalDateTime manufacturingTime;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "inbound_order_id", nullable = false)
    @JsonIgnoreProperties("inboundOrders")
    private InboundOrder inboundOrder;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnoreProperties("batchStocks")
    private Section section;

    private State state;

}
