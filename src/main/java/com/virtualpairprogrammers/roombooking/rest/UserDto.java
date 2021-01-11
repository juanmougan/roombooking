package com.virtualpairprogrammers.roombooking.rest;

import com.virtualpairprogrammers.roombooking.model.entities.User;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    Long id;

    String name;

    public static UserDto fromUser(final User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
