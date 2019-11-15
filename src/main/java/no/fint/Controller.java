package no.fint;

import no.fint.betaling.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@RestController
public class Controller {

    @Autowired
    MongoTemplate mongoTemplate;

    @Value("${fint.betaling.collection}")
    private String collectionName;

    @PostMapping("/")
    public void createClaim() {
        Claim claim = new Claim();
        claim.setOriginalAmountDue(10000L);

        Customer customer = new Customer();
        customer.setName("Duck, Donald Donaldo");
        customer.setEmail("donald@duck.no");
        customer.setMobile("90909090");
        claim.setCustomer(customer);

        User user = new User();
        user.setName("Pat, Postmann");
        Organisation organisation = new Organisation();
        organisation.setName("Telemark Fylkeskommune");
        organisation.setOrganisationNumber("999999999");
        user.setOrganisation(organisation);
        Organisation organisationUnitSkien = new Organisation();
        organisationUnitSkien.setName("Skien VGS");
        organisationUnitSkien.setOrganisationNumber("987654321");
        Organisation organisationUnitPorsgrunn = new Organisation();
        organisationUnitPorsgrunn.setName("Porsgrunn VGS");
        organisationUnitPorsgrunn.setOrganisationNumber("888888888");
        ArrayList<Organisation> organisationUnits = new ArrayList<>();
        organisationUnits.add(organisationUnitSkien);
        organisationUnits.add(organisationUnitPorsgrunn);
        user.setOrganisationUnits(organisationUnits);

        claim.setCreatedBy(user);

        claim.setClaimStatus(ClaimStatus.STORED);

        claim.setClasses(new HashSet<>());

        claim.setTimestamp(System.currentTimeMillis());
        mongoTemplate.insert(claim, collectionName);
    }

    @GetMapping("/")
    public List<Claim> getClaims() {
        return mongoTemplate.findAll(Claim.class, collectionName);
    }

    @PostMapping("/issue")
    public void issuePayments()  {
        Query query = new Query();
        query.addCriteria(Criteria.where("classes").nin("issued"));

        Update update = new Update();
        update.push("classes", "issued");
        update.set("timestamp", System.currentTimeMillis());

        mongoTemplate.updateMulti(query, update, collectionName);
    }



}
