package com.example.demo.mappers;


import com.example.demo.dtos.ChangePassDTO;
import com.example.demo.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface UserMapper {
//    Users mapToEntity(UserDto userDto);
//    UserDto mapToDto(Users users);
    void update(ChangePassDTO source, @MappingTarget User target);

}
