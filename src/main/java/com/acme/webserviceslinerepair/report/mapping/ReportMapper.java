package com.acme.webserviceslinerepair.report.mapping;

import com.acme.webserviceslinerepair.report.domain.model.entity.Report;
import com.acme.webserviceslinerepair.report.resource.CreateReportResource;
import com.acme.webserviceslinerepair.report.resource.ReportResource;
import com.acme.webserviceslinerepair.report.resource.UpdateReportResource;
import com.acme.webserviceslinerepair.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ReportMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    //Object Mapping
    public ReportResource toResource(Report model){
        return mapper.map(model,ReportResource.class);
    }

    public List<ReportResource> toResource(List<Report>model){
        return mapper.mapList(model, ReportResource.class);
    }

    public Report toModel(CreateReportResource resource){
        return mapper.map(resource, Report.class);
    }

    public Report toModel(UpdateReportResource resource){
        return mapper.map(resource, Report.class);
    }

    public Page<ReportResource> modelListToPage(List<Report> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, ReportResource.class), pageable, modelList.size());
    }
}
