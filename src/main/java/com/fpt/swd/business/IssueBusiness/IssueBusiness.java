package com.fpt.swd.business.IssueBusiness;

import com.fpt.swd.Response.APIResponse;
import com.fpt.swd.database.dto.Issue.AddNewIssueDto;
import com.fpt.swd.database.dto.Issue.GetIssueDto;
import com.fpt.swd.database.dto.Issue.GetIssueVer2Dto;
import com.fpt.swd.database.dto.Issue.UpdateIssueDto;
import com.fpt.swd.database.entity.Issue;
import com.fpt.swd.database.repo.IIssueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class IssueBusiness implements IIssueBusiness{
    private final IIssueRepository _issueRepository;
    private final ModelMapper _mapper;
    @Autowired
    public IssueBusiness(IIssueRepository issueRepository, ModelMapper mapper){
        this._issueRepository = issueRepository;
        _mapper = mapper;
    }


    @Override
    public APIResponse<Iterable<GetIssueVer2Dto>> GetAllIssue(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<GetIssueVer2Dto> dbIssue = _issueRepository.findAllList(pageable);
        var serviceResponse = new APIResponse<Iterable<GetIssueVer2Dto>>();
        serviceResponse.Data = dbIssue.getContent().stream().map(s -> _mapper.map(s, GetIssueVer2Dto.class)).toList();
        serviceResponse.pagination.pageNo = dbIssue.getNumber();
        serviceResponse.pagination.pageSize = dbIssue.getSize();
        serviceResponse.pagination.totalElements = dbIssue.getTotalElements();
        serviceResponse.pagination.totalPages = dbIssue.getTotalPages();

        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetIssueDto>> AddNewIssue(AddNewIssueDto requestIssueDto) {
        var serviceResponse = new APIResponse<Iterable<GetIssueDto>>();
        Issue newIssue = _mapper.map(requestIssueDto, Issue.class);
        _issueRepository.save(newIssue);
        serviceResponse.Data = _issueRepository.findAll().stream().map(s -> _mapper.map(s, GetIssueDto.class)).toList();
        serviceResponse.pagination = null;
        return serviceResponse;
    }

    @Override
    public APIResponse<GetIssueDto> UpdateIssue(UpdateIssueDto requestIssueDto) {
        var serviceResponse = new APIResponse<GetIssueDto>();
        serviceResponse.pagination = null;
        Optional<Issue> findIssue = _issueRepository.findById(requestIssueDto.getId());
        if(findIssue.isPresent()){
            Issue exitsIssue = findIssue.get();
            _mapper.map(requestIssueDto, exitsIssue);
            _issueRepository.save(exitsIssue);
            serviceResponse.Data = _mapper.map(exitsIssue, GetIssueDto.class);
        }else{
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "The Issue does not exits";
        }
        return serviceResponse;
    }

    @Override
    public APIResponse<GetIssueDto> GetIssue(int issueId) {
        var serviceResponse = new APIResponse<GetIssueDto>();
        serviceResponse.pagination = null;
        Optional<Issue> responseIssue = _issueRepository.findById(issueId);
        if(responseIssue.isPresent()){
            serviceResponse.Data = _mapper.map(responseIssue.get(), GetIssueDto.class);

        }else{
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message= "Assignment with id " + issueId + " does not exists";
        }
        return serviceResponse;
    }

    @Override
    public APIResponse<Iterable<GetIssueDto>> RemoveIssue(int issueId) {
        var serviceResponse = new APIResponse<Iterable<GetIssueDto>>();
        serviceResponse.pagination = null;
        Optional<Issue> responseIssue = _issueRepository.findById(issueId);
        if(responseIssue.isPresent()){
            Issue issueObj = responseIssue.get();
            _issueRepository.delete(issueObj);
            serviceResponse.Data = _issueRepository.findAll().stream().map(s -> _mapper.map(s, GetIssueDto.class)).toList();
        }else{
            serviceResponse.Data = null;
            serviceResponse.status = false;
            serviceResponse.message = "Assignment with id " + issueId + " does not exists";
        }
        return serviceResponse;
    }
}
