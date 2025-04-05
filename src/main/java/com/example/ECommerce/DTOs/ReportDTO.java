package com.example.ECommerce.DTOs;

import com.example.ECommerce.DTOs.RoleBasedDTO.CustomerDTO;
import com.example.ECommerce.Enums.Report_Category;

public record ReportDTO(Long id, CustomerDTO user, String description, String reportCategory, boolean isSolved,
                          ProductDTO product,CustomerDTO reportedUser, String title,
                        Long reportedUserId,Long userId, Long productId) // so in upcoming requests , those three fields will be sent not the actual objects
{
}
