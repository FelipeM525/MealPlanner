package com.dev.mealplanner.user.domain;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToSource(RegisterTO to);
}
