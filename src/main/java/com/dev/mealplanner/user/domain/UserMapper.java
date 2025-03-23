package com.dev.mealplanner.user.domain;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User dtoToSource(RegisterTO to);

    UserTO sourceToDto(User user);
}