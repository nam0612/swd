package com.fpt.swd.business.IssueSettingBusiness;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.dto.IssueSetting.AddNewIssueSettingDto;
import com.fpt.swd.database.dto.IssueSetting.GetIssueSettingDto;
import com.fpt.swd.database.dto.IssueSetting.UpdateIssueSettingDto;
import com.fpt.swd.database.entity.IssueSetting;
import com.fpt.swd.database.repo.IIssueSettingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class IssueSettingBusiness implements IIssueSettingBusiness{
    private final IIssueSettingRepository _issueSettingRepository;

    private final ModelMapper _mapper;
    @Autowired
    public IssueSettingBusiness(IIssueSettingRepository repository, ModelMapper map){
        _issueSettingRepository = repository;
        _mapper = map;
    }
    @Override
    public APIResponse<Iterable<GetIssueSettingDto>> GetAllIssueSetting(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<IssueSetting> dbIssue = _issueSettingRepository.findAll(pageable);
        var serviceResponse = new APIResponse<Iterable<GetIssueSettingDto>>();
        serviceResponse.Data = dbIssue.getContent().stream().map(s -> _mapper.map(s, GetIssueSettingDto.class)).toList();
        serviceResponse.pagination.pageNo = dbIssue.getNumber();
        serviceResponse.pagination.pageSize = dbIssue.getSize();
        serviceResponse.pagination.totalElements = dbIssue.getTotalElements();
        serviceResponse.pagination.totalPages = dbIssue.getTotalPages();

        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetIssueSettingDto>> AddNewIssueSetting(AddNewIssueSettingDto requestIssueSettingDto) {
        var serviceResponse = new APIResponse<Iterable<GetIssueSettingDto>>();
        IssueSetting newIssueSetting = _mapper.map(requestIssueSettingDto, IssueSetting.class);
        _issueSettingRepository.save(newIssueSetting);
        serviceResponse.Data = _issueSettingRepository.findAll().stream().map(s -> _mapper.map(s, GetIssueSettingDto.class)).toList();
        serviceResponse.pagination = null;
        return serviceResponse;
    }

    @Override
    public APIResponse<GetIssueSettingDto> UpdateIssueSetting(UpdateIssueSettingDto requestIssueSettingDto) {
        var serviceResponse = new APIResponse<GetIssueSettingDto>();
        serviceResponse.pagination = null;
        Optional<IssueSetting> findIssueSetting = _issueSettingRepository.findById(requestIssueSettingDto.getId());
        if(findIssueSetting.isPresent()){
            IssueSetting exitsIssueSetting = findIssueSetting.get();
            _mapper.map(requestIssueSettingDto, exitsIssueSetting);
            _issueSettingRepository.save(exitsIssueSetting);
            serviceResponse.Data = _mapper.map(exitsIssueSetting, GetIssueSettingDto.class);
        }else{
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "The IssueSetting does not exits";
        }
        return serviceResponse;
    }

    @Override
    public APIResponse<GetIssueSettingDto> GetIssueSetting(int issueSettingId) {
        var serviceResponse = new APIResponse<GetIssueSettingDto>();
        serviceResponse.pagination = null;
        Optional<IssueSetting> responseIssueSetting = _issueSettingRepository.findById(issueSettingId);
        if(responseIssueSetting.isPresent()){
            serviceResponse.Data = _mapper.map(responseIssueSetting.get(), GetIssueSettingDto.class);

        }else{
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message= "Assignment with id " + issueSettingId + " does not exists";
        }
        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetIssueSettingDto>> RemoveIssueSetting(int issueSettingId) {
        var serviceResponse = new APIResponse<Iterable<GetIssueSettingDto>>();
        serviceResponse.pagination = null;
        Optional<IssueSetting> responseIssueSetting = _issueSettingRepository.findById(issueSettingId);
        if(responseIssueSetting.isPresent()){
            IssueSetting issueSettingObj = responseIssueSetting.get();
            _issueSettingRepository.delete(issueSettingObj);
            serviceResponse.Data = _issueSettingRepository.findAll().stream().map(s -> _mapper.map(s, GetIssueSettingDto.class)).toList();
        }else{
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "Assignment with id " + issueSettingId + " does not exists";
        }
        return serviceResponse;
    }
}
