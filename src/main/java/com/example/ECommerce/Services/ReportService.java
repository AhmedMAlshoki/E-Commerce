package com.example.ECommerce.Services;

import com.example.ECommerce.DTOs.ReportDTO;
import com.example.ECommerce.Entities.Product;
import com.example.ECommerce.Entities.Report;
import com.example.ECommerce.Entities.SubEntities.Customer;
import com.example.ECommerce.Enums.Report_Category;
import com.example.ECommerce.Mappers.ReportMapper;
import com.example.ECommerce.Repositories.JPA.RoleBasedRepositories.ReportRepository;
import com.example.ECommerce.SecurityConfig.SecurityServices.UserDetailsImp;
import com.example.ECommerce.Services.UserServices.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public ReportService(ReportRepository reportRepository,@Lazy ReportMapper reportMapper
            , CustomerService customerService, ProductService productService) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Transactional
    public ReportDTO addReport(ReportDTO reportDTO) throws Exception {
        Report report = reportMapper.reportDTOToReport(reportDTO);
        Customer customer = customerService.getRawCustomer(reportDTO.userId());
        report.setUser(customer);
        if (reportDTO.productId() != null)
        {
            Product product = productService.getRawProduct(reportDTO.productId());
            report.setProduct(product);
        }
        if (reportDTO.reportedUserId() != null)
        {
            Customer reportedUser = customerService.getRawCustomer(reportDTO.reportedUserId());
            report.setReportedUser(reportedUser);
        }
        switch (reportDTO.reportCategory()) {
            case "SCAM_PRODUCT" -> report.setReportCategory(Report_Category.SCAM_PRODUCT);
            case "SCAM_USER" -> report.setReportCategory(Report_Category.SCAM_USER);
            case "SCAM_ORDER" -> report.setReportCategory(Report_Category.SCAM_ORDER);
            case "SCAM_REVIEW" -> report.setReportCategory(Report_Category.SCAM_REVIEW);
            case "ILLEGAL_PRODUCT" -> report.setReportCategory(Report_Category.ILLEGAL_PRODUCT);
            case "SPAM" -> report.setReportCategory(Report_Category.SPAM);
            default -> report.setReportCategory(Report_Category.OTHER);
        }
        reportRepository.save(report);
        return reportMapper.reportToReportDTO(report);
    }

    public boolean isOwner(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        Long userId = reportRepository.findById(id).orElseThrow().getUser().getId();
        return userId.equals(userDetails.getId());
    }

    public String updateReport(Long id, ReportDTO reportDTO) {
        Report report = reportRepository.findById(id).orElseThrow();
        report.setTitle(reportDTO.title());
        report.setDescription(reportDTO.description());
        report.setReportCategory(Report_Category.valueOf(reportDTO.reportCategory()));
        reportRepository.save(report);
        return "Report is updated";
    }

    public String deleteReport(Long id) {
        boolean exists = reportRepository.existsById(id);
        if (exists) {
            reportRepository.deleteById(id);
            return "Report is deleted";
        }
        return "Report not found";
    }

    public ReportDTO getReport(Long id) {
        Report report = reportRepository.findById(id).orElseThrow();
        return reportMapper.reportToReportDTO(report);
    }

    public List<ReportDTO> getReportsByUser(Long id) {
       List<Report> reports = reportRepository.findByUserId(id);
       return reports.stream().map(reportMapper::reportToReportDTO).toList();
    }

    public List<ReportDTO> getReportsByProduct(Long id) {
        List<Report> reports = reportRepository.findByProductId(id);
        return reports.stream().map(reportMapper::reportToReportDTO).toList();
    }

    public List<ReportDTO> getReportsOnUser(Long id) {
        List<Report> reports = reportRepository.findByReportedUserId(id);
        return reports.stream().map(reportMapper::reportToReportDTO).toList();
    }

    public String resolveReport(Long id) {
        reportRepository.toggleSolved(id);
        return "Report's has been changed";
    }
}
