package com.inqoo.travelofficeweb.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
    Ta klasa ma:
    - przechowywać informacje o wycieczce (zakres i typy wg opisu)
    - udostępniać możliwość wyświetlania informacji o wycieczce
 */
@Entity
@Getter
@Setter
@ToString

@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Audited
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "trip_start")
    private LocalDate start;
    @Column(name = "trip_end")
    private LocalDate end;
    @Column(name = "destination")
    private String destination;
    @Column(name = "price_eur")
    private double priceEur;


    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    // kolumny audytowe
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdOn;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedOn;
}