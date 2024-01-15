package com.employee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.employee.dto.PositionDTO;

@Mapper
public interface PositionMapper {

	List<PositionDTO> selectAllPosition();

	int insertPosition(PositionDTO dto);

	int updatePosition(PositionDTO dto);

	int deletePosition(int positionNo);
}
