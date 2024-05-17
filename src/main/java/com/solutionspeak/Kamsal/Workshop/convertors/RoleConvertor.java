package com.solutionspeak.Kamsal.Workshop.convertors;

import com.solutionspeak.Kamsal.Workshop.entity.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConvertor implements AttributeConverter<Role, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.getKey();
    }

    @Override
    public Role convertToEntityAttribute(Integer key) {
        if (key == null) {
            return null;
        }
        return Role.getRole(key);
    }
}
