package com.rviewer.skeletons.application.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class PutSetDispenserStatusRequest {
    @NotBlank
    private String status;

    @NotNull
    private Date updatedAt;

}
