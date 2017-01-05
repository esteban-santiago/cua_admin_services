package com.cua.admin.tests.core;

import com.cua.admin.model.core.Address;
import com.cua.admin.model.core.Category;
import com.cua.admin.model.core.IdentityCard;
import com.cua.admin.model.core.IdentityCardType;
import com.cua.admin.model.core.MedicalCertification;
import com.cua.admin.model.core.MedicalCertificationClass;
import com.cua.admin.model.core.Member;
import com.cua.admin.model.core.MemberRole;
import com.cua.admin.model.core.Nationality;
import com.cua.admin.model.core.flight.Rating;
import com.cua.admin.model.core.repositories.CategoryRepository;
import com.cua.admin.model.core.repositories.MemberRepository;
import com.cua.admin.model.core.repositories.NationalityRepository;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MemberTests extends SpringIntegrationTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CategoryRepository categoryReposity;

    @Autowired
    private NationalityRepository nationalityRepository;

    @Test
    public void createMember() {
        Address address = new Address();
        address.setStreet("Av. Berlin 729");
        address.setCity("Longchamps");
        address.setZip("1854");

        Address address2 = new Address();
        address2.setStreet("Pasaje Bélgica 837");
        address2.setCity("Adrogué");
        address2.setZip("1846");

        Member member = new Member();
        member.setName("Socio 1");
        member.setCategory(categoryReposity.findById(1));
        member.addAddress(address);
        member.addAddress(address2);
        member.setIdentityCard(new IdentityCard(IdentityCardType.DNI, "24036873"));
        member.setNationality(nationalityRepository.findById(1));
        member.addRating(Rating.IFR);
        member.addRating(Rating.CROSSING);
        member.addRole(MemberRole.PILOT);
        memberRepository.save(member);

        Member member2 = new Member();
        member2.setName("Socio 2");
        member2.setCategory(categoryReposity.findById(1));
        member2.setNationality(nationalityRepository.findById(2));
        member2.setIdentityCard(new IdentityCard(IdentityCardType.DNI, "24036873"));
        memberRepository.save(member2);

        memberRepository.findAll()
                .stream().forEach(
                        aMember -> System.out.println(aMember));
        
    }
    
}
