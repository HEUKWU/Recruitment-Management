package heukwu.recruitmentmanagement.repository;

import heukwu.recruitmentmanagement.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
