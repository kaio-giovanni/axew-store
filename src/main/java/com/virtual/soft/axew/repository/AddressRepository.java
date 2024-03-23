package com.virtual.soft.axew.repository;

import com.virtual.soft.axew.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM addresses WHERE street = :street AND" +
                    " number = :number AND district = :district AND zipCode = :zipCode")
    List<Address> findAddress(@Param("street") String street,
                              @Param("number") String number,
                              @Param("district") String district,
                              @Param("zipCode") String zipCode);
}
