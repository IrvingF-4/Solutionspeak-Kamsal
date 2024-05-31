package com.solutionspeak.Kamsal.Workshop.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Status {
    NEW(0), LIGHTLY_USED(1), USED(2), BROKEN(3), POOR_CONDITION(4);
    private final int key;

    public static Status getStatus(Integer key) {
        Map<Integer, Status> RolMap =
                Arrays.stream(Status.values()).collect(Collectors.toMap(Status::getKey, Function.identity()));
        return RolMap.get(key);
    }
}
