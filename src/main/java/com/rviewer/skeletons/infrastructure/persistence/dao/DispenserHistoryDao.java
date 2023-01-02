package com.rviewer.skeletons.infrastructure.persistence.dao;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Entity(name = "BT_DISPENSER_HISTORY")
public class DispenserHistoryDao {
    @Id
    private Long id;

    @Column(name = "TOTAL_SPENT")
    private Double totalSpent;

    @Column(name = "OPENED_AT")
    private Date openedAt;

    @Column(name = "CLOSED_AT")
    private Date closedAt;

}
