package com.fpt.swd.business.AssignmentBusiness;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.dto.Assignment.AddNewAssignmentDto;
import com.fpt.swd.database.dto.Assignment.GetAssignmentDto;
import com.fpt.swd.database.dto.Assignment.UpdateAssignmentDto;
import com.fpt.swd.database.entity.Assignment;
import com.fpt.swd.database.repo.IAssignmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentBusiness implements IAssignmentBusiness {

    private final IAssignmentRepository _assignmentRepository;

    private final ModelMapper _mapper;

    @Autowired
    public AssignmentBusiness(IAssignmentRepository assignmentRepository, ModelMapper mapper) {
        _assignmentRepository = assignmentRepository;
        _mapper = mapper;
    }

    @Override
    public APIResponse<Iterable<GetAssignmentDto>> GetAllAssignment(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Assignment> dbAssignment = _assignmentRepository.findAll(pageable);
        var serviceResponse = new APIResponse<Iterable<GetAssignmentDto>>();
        serviceResponse.Data = dbAssignment.getContent().stream().map(s -> _mapper.map(s, GetAssignmentDto.class)).toList();
        serviceResponse.pagination.pageNo = dbAssignment.getNumber();
        serviceResponse.pagination.pageSize = dbAssignment.getSize();
        serviceResponse.pagination.totalElements = dbAssignment.getTotalElements();
        serviceResponse.pagination.totalPages = dbAssignment.getTotalPages();
        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetAssignmentDto>> AddNewAssignment(AddNewAssignmentDto requestAssignmentDto) {
        var serviceResponse = new APIResponse<Iterable<GetAssignmentDto>>();
        Assignment newAssignment = _mapper.map(requestAssignmentDto, Assignment.class);
        _assignmentRepository.save(newAssignment);
        serviceResponse.Data = _assignmentRepository.findAll().stream().map(s -> _mapper.map(s, GetAssignmentDto.class)).toList();
        serviceResponse.pagination = null;
        return serviceResponse;
    }

    @Override
    public APIResponse<GetAssignmentDto> UpdateAssignment(UpdateAssignmentDto requestAssignmentDto) {
        var serviceResponse = new APIResponse<GetAssignmentDto>();
        serviceResponse.pagination = null;
        Optional<Assignment> findAssignment = _assignmentRepository.findById(requestAssignmentDto.getId());
        if (findAssignment.isPresent()) {
            Assignment existingAssignment = findAssignment.get();
            _mapper.map(requestAssignmentDto, existingAssignment);
            _assignmentRepository.save(existingAssignment);
            serviceResponse.Data = _mapper.map(existingAssignment, GetAssignmentDto.class);
        } else {
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "The Assignment does not exist !!!";
        }
        return serviceResponse;
    }

    @Override
    public APIResponse<GetAssignmentDto> GetAssignment(int assignmentId) {
        var serviceResponse = new APIResponse<GetAssignmentDto>();
        serviceResponse.pagination = null;
        Optional<Assignment> responseAssignment = _assignmentRepository.findById(assignmentId);
        if (responseAssignment.isPresent()) {
            serviceResponse.Data = _mapper.map(responseAssignment.get(), GetAssignmentDto.class);
        } else {
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "Assignment with id " + assignmentId + " does not exists";
        }
        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetAssignmentDto>> RemoveAssignment(int assignmentId) {
        var serviceResponse = new APIResponse<Iterable<GetAssignmentDto>>();
        serviceResponse.pagination = null;
        Optional<Assignment> responseAssignment = _assignmentRepository.findById(assignmentId);
        if (responseAssignment.isPresent()) {
            Assignment assignmentObj = responseAssignment.get();
            _assignmentRepository.delete(assignmentObj);
            serviceResponse.Data = _assignmentRepository.findAll().stream().map(s -> _mapper.map(s, GetAssignmentDto.class)).toList();
        } else {
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "Assignment with id " + assignmentId + " does not exists";
        }
        return serviceResponse;
    }
}
