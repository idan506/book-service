package com.example.demo.repository;

import com.example.demo.entity.TicketEntity;
import com.example.demo.model.Ticket;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity,Long> {

    @Modifying
    @Query(value = "UPDATE TicketEntity b SET b.status = true, b.expirationDate =NOW() where b.id=:id")
    void updateTicket(@Param("id") long id);

    //list ticket da duoc idUser muon
    @Modifying
    @Query(value = "SELECT t from TicketEntity t where t.idUser = :id and t.status = false")
    List<TicketEntity> listTicket(@Param("id") String id);

    @Modifying
    @Query(value = "select t from TicketEntity t where t.status = true")
    List<TicketEntity> listTicketByStatusTrue();

    @Modifying
    @Query(value = "select t from TicketEntity t where MONTH(t.dayOfHire) = MONTH(NOW()) and YEAR(t.dayOfHire) = YEAR(NOW())")
    List<TicketEntity> listTicketExport();
}