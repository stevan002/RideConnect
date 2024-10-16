package com.rideconnect.server.mapper;

import com.rideconnect.server.dto.ProfileResponse;
import com.rideconnect.server.dto.RegisterRequest;
import com.rideconnect.server.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);
    User registerUser(RegisterRequest user);
    ProfileResponse getProfile(User user);
}
