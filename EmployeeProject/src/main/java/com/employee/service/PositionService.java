package com.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.dto.PositionDTO;
import com.employee.mapper.PositionMapper;

@Service
public class PositionService {
	private PositionMapper positionMapper;

	public PositionService(PositionMapper positionMapper) {
		this.positionMapper = positionMapper;
	}

	public List<PositionDTO> selectAllPosition() {
		return positionMapper.selectAllPosition();
	}

	public int insertPosition(PositionDTO dto) {
		return positionMapper.insertPosition(dto);
	}

	public int updatePosition(PositionDTO dto) {
		return positionMapper.updatePosition(dto);
	}

	public int deletePosition(int positionNo) {
		return positionMapper.deletePosition(positionNo);
	}
}
