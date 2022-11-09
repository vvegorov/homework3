//package ru.digitalhabits.homework3.dao;
//
//import org.aspectj.lang.annotation.Before;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@DataJpaTest
//class PersonDaoTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private PersonDao personDao;
//
//    @Before
//    public void setUp(){
//        // given
//        Product product = Product.builder()
//                .name("P1")
//                .description("P1 desc")
//                .price(new BigDecimal("1"))
//                .build();
//
//        testEntityManager.persist(product);
//    }
//
//    @Test
//    void findById() {
//        // TODO: NotImplemented
//    }
//
//    @Test
//    void findAll() {
//        // TODO: NotImplemented
//    }
//
//    @Test
//    void update() {
//        // TODO: NotImplemented
//    }
//
//    @Test
//    void delete() {
//        // TODO: NotImplemented
//    }
//}