package com.maplr.sugarshack.repository;

import com.maplr.sugarshack.entity.CatalogueItem;
import com.maplr.sugarshack.entity.Etype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogueItemRepository extends JpaRepository<CatalogueItem, String> {
    List<CatalogueItem> findByType(Etype type);
}
