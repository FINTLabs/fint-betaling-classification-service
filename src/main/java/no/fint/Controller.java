package no.fint;

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
        claim.setId(UUID.randomUUID().toString());
        claim.setTimestamp(System.currentTimeMillis());
        claim.setClasses(new HashSet<>());

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
