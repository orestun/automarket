package com.automarket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Make field can`t be null")
    private String make;

    @NotNull(message = "Model field can`t be null")
    private String model;

    private String configuration;

    @CreationTimestamp
    private LocalDateTime createTimestamp;

    @UpdateTimestamp
    private LocalDateTime updateTimeStamp;

    @Column(name = "VIN_code")
    private String VINCode;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private User seller;

    @OneToOne
    @JoinColumn(name = "history_id", referencedColumnName = "id")
    private VehicleHistory history;

    @OneToOne
    @JoinColumn(name = "technical_data_id", referencedColumnName = "id")
    private VehicleTechnicalData technicalData;

    @OneToOne
    @JoinColumn(name = "order_info_id", referencedColumnName = "id")
    private VehicleOrderInfo orderInfo;

    @OneToOne
    @JoinColumn(name = "basic_info_id", referencedColumnName = "id")
    private VehicleBasicInfo basicInfo;
}
