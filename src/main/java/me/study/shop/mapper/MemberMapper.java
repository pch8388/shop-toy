package me.study.shop.mapper;

import me.study.shop.domain.Member;
import me.study.shop.dto.MemberRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

	MemberMapper MEMBER_MAPPER = Mappers.getMapper(MemberMapper.class);

	Member toProductEntity(MemberRequestDto dto);
}
