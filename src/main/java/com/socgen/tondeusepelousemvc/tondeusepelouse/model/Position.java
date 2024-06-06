package com.socgen.tondeusepelousemvc.tondeusepelouse.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Position {
    private int x;
    private int y;
    private OrientationEnum orientation;

}
