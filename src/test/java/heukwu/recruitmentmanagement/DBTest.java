package heukwu.recruitmentmanagement;

import heukwu.recruitmentmanagement.entity.Company;
import heukwu.recruitmentmanagement.repository.CompanyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

//@ExtendWith(SpringExtension.class)
//@ActiveProfiles("test")
//@DataJpaTest
//public class DBTest {
//
//    @Autowired
//    private CompanyRepository companyRepository;
//
//    @Test
//    void getCompany() {
//        List<Company> companies = companyRepository.findAll();
//        System.out.println(companies);
//        Assertions.assertThat(companies).isEqualTo(null);
//    }
//}
