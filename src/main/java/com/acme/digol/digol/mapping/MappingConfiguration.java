package com.acme.digol.digol.mapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("digolMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public SportFieldMapper sportFieldMapper() {
        return new SportFieldMapper();
    }
    @Bean
    public CustomerMapper customerMapper() {
        return new CustomerMapper();
    }
    @Bean
    public ReservationMapper reservationMapper() {
        return new ReservationMapper();
    }

}
