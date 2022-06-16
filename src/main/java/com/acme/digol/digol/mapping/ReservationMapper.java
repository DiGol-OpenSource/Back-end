package com.acme.digol.digol.mapping;

import com.acme.digol.digol.domain.model.entity.Reservation;

import com.acme.digol.digol.resource.*;
import com.acme.digol.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ReservationMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;
    public ReservationResource toResource(Reservation model) {
        return mapper.map(model, ReservationResource.class);
    }

    public Page<ReservationResource> modelListPage(List<Reservation> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ReservationResource.class), pageable, modelList.size());
    }

    public Reservation toModel(CreateReservationResource resource) {
        return mapper.map(resource, Reservation.class);
    }

    public Reservation toModel(UpdateReservationResource resource) {
        return mapper.map(resource, Reservation.class);
    }

}
