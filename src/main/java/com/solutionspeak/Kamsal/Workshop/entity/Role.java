package com.solutionspeak.Kamsal.Workshop.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {
    Workshop_Foreman(0), Visitor(1), Administrator(2);
    private final int key;
    public static Role getRole(Integer key){
        Map<Integer, Role> RolMap =
                Arrays.stream(Role.values()).collect(Collectors.toMap(Role::getKey, Function.identity()));
        return RolMap.get(key);
    }
}
