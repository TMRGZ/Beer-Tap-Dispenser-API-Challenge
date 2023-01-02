package com.rviewer.skeletons.infrastructure.persistence.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "BT_DISPENSER")
public class DispenserDao {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "FLOW_VOLUME")
    private Long flowVolume;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DISPENSER_ID")
    private List<DispenserHistoryDao> dispenserHistory;

}
