package com.ipb.projekt.services;

import com.ipb.projekt.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductManagementService {

    private final ProductRepo productRepo;
    private final AnnexRepo annexRepo;
    private final ClientRepo clientRepo;
    private final ReceivedFakturaRepo receivedFakturaRepo;
    private final CreatedFakturaRepo createdFakturaRepo;
    private final SaleRepo saleRepo;
    private final ShelfRepo shelfRepo;

    @Autowired
    public ProductManagementService(ProductRepo productRepo,
                                    AnnexRepo annexRepo,
                                    ClientRepo clientRepo,
                                    ReceivedFakturaRepo receivedFakturaRepo,
                                    CreatedFakturaRepo createdFakturaRepo,
                                    SaleRepo saleRepo, ShelfRepo shelfRepo) {
        this.productRepo = productRepo;
        this.annexRepo = annexRepo;
        this.clientRepo = clientRepo;
        this.receivedFakturaRepo = receivedFakturaRepo;
        this.createdFakturaRepo = createdFakturaRepo;
        this.saleRepo = saleRepo;
        this.shelfRepo = shelfRepo;
    }
}
