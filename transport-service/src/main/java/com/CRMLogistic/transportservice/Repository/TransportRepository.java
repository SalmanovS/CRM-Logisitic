package com.CRMLogistic.transportservice.Repository;

import com.CRMLogistic.transportservice.Model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Integer> {

    List<Transport> findByStatus(String status);

    Transport findByNumber(String number);

}
