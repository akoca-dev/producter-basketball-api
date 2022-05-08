package com.akoca.producterbasketballapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum Position {

    PG("Point Guard"),
    SG("Shooting Guard"),
    SF("Small Forward"),
    PF("Power Forward"),
    C("Center");

    private final String positionName;

    public static boolean isValidPosition(String positionName) {
        return Arrays.stream(Position.values())
                .anyMatch(position -> position.getPositionName().equalsIgnoreCase(positionName) || position.name().equalsIgnoreCase(positionName));
    }

    public static Optional<Position> fromStringValue(String positionName) {
        for(Position position : Position.values()) {
            if(position.getPositionName().equalsIgnoreCase(positionName) ||
               position.name().equalsIgnoreCase(positionName)) {
                return Optional.of(position);
            }
        }

        return Optional.empty();
    }
}
