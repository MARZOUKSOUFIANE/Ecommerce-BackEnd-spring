package org.glsid.lightecomv1.dao;

import org.glsid.lightecomv1.entities.Payement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin("*")
@RepositoryRestResource
public interface PayementRepository extends JpaRepository<Payement,Long> {
}
