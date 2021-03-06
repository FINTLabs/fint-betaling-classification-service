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

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        claim.setOrgId("Test Org-Id");
        claim.setOrderNumber("OrderNumber123");
        claim.setInvoiceNumbers(Stream.of("invoice number 456123", "invoice number 985313").collect(Collectors.toSet()));
        claim.setInvoiceDate(LocalDate.now());
        claim.setPaymentDueDate(LocalDate.of(2019, 11, 29));
        claim.setCreatedDate(LocalDate.of(2019, 1, 1));
        claim.setLastModifiedDate(LocalDate.of(2019, 5, 26));

        ArrayList<CreditNote> creditNotes = new ArrayList<>();
        CreditNote creditNote = new CreditNote();
        creditNote.setAmount(2333200L);
        creditNote.setDate(LocalDate.of(2019, 11, 20));
        creditNote.setId("Creditnote 1");
        creditNote.setComment("Første kreditering");
        CreditNote creditNote2 = new CreditNote();
        creditNote2.setAmount(67200L);
        creditNote2.setDate(LocalDate.of(2019, 11, 20));
        creditNote2.setId("Creditnote 2");
        creditNote2.setComment("Krediterte feil første gangen");
        creditNotes.add(creditNote);
        creditNotes.add(creditNote2);
        claim.setCreditNotes(creditNotes);

        claim.setAmountDue(99999L);
        claim.setOriginalAmountDue(555555L);
        claim.setRequestedNumberOfDaysToPaymentDeadline("14");

        Customer customer = new Customer();
        customer.setName("Duck, Donald Donaldo");
        customer.setEmail("donald@duck.no");
        customer.setMobile("90909090");
        customer.setPostalAddress("FakturaVeien 19B");
        customer.setCity("InkassoByen");
        customer.setPostalCode("6660");
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
        claim.setOrganisationUnit(organisationUnitSkien);

        try {
            Principal principal = new Principal();
            principal.setCode("912391239");
            principal.setDescription("Principal");
            principal.setLineitems(null);
            principal.setUri(new URI("www.telemarkfk.no"));
            claim.setPrincipal(principal);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            claim.setInvoiceUri(new URI("www.telemarkfk.no/fakturaavdelingen"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        ArrayList<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setDescription("frontendnavn En datamaskin");
        Lineitem lineItem = new Lineitem();
        lineItem.setItemCode("123321");
        lineItem.setItemPrice(2000L);
        lineItem.setTaxrate(25L);
        lineItem.setDescription("Offisielt navn MACBOOK pro");
        try {
            lineItem.setUri(new URI("www.datamaskin.no/ny"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        orderItem.setLineitem(lineItem);
        orderItem.setItemQuantity(1L);
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setDescription("frontendnavn noe annet");
        Lineitem lineItem2 = new Lineitem();
        lineItem2.setItemCode("321123");
        lineItem2.setItemPrice(500L);
        lineItem2.setTaxrate(25L);
        lineItem2.setDescription("Offisielt navn DATAMUS COOL");
        try {
            lineItem2.setUri(new URI("www.mus.no/ny"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        orderItem2.setLineitem(lineItem2);
        orderItem2.setItemQuantity(1L);
        claim.setOrderItems(orderItems);
        claim.setClaimStatus(ClaimStatus.PAID);
        claim.setClasses(new HashSet<>());
        claim.setTimestamp(System.currentTimeMillis());
        mongoTemplate.insert(claim, collectionName);
    }

    @GetMapping("/")
    public List<Claim> getClaims() {
        return mongoTemplate.findAll(Claim.class, collectionName);
    }

    @PostMapping("/issue")
    public void issuePayments() {
        Query query = new Query();
        query.addCriteria(Criteria.where("classes").nin("issued"));

        Update update = new Update();
        update.push("classes", "issued");
        update.set("timestamp", System.currentTimeMillis());

        mongoTemplate.updateMulti(query, update, collectionName);
    }

}
