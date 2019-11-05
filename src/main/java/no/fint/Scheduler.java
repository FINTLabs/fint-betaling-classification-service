package no.fint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class Scheduler {

    @Autowired
    private Set<Minion> minions;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${fint.betaling.collection}")
    private String collectionName;

    private Long timestamp = 0L;


    @Scheduled(initialDelay = 1000L, fixedRate = 1000L)
    public void run() {

        Query q = new Query();
        q.addCriteria(Criteria.where("timestamp").gt(timestamp));

        log.info("q={}", q);
        List<Claim> claims = mongoTemplate.find(q, Claim.class, collectionName);
        log.info("claims={}", claims);

        claims.forEach(claim -> {
            int hashCode = claim.hashCode();
            minions.forEach(minion -> minion.classify(claim));
            timestamp = Math.max(timestamp, claim.getTimestamp());
            if (claim.hashCode() != hashCode) {
                claim.setTimestamp(System.currentTimeMillis());
                mongoTemplate.save(claim, collectionName);
            }
        });


    }


}
