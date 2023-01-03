package com.rviewer.skeletons.infrastructure.persistence.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity(name = "BT_DISPENSER_HISTORY")
public class DispenserHistoryDao {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "TOTAL_SPENT")
    private Double totalSpent;

    @Column(name = "OPENED_AT", nullable = false)
    private Date openedAt;

    @Column(name = "CLOSED_AT")
    private Date closedAt;

}
