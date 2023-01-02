package com.rviewer.skeletons.application.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostCreateDispenserRequest {

    @NotNull
    private Double flowVolume;

}
