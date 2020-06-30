package com.ipb.projekt.services;

import com.ipb.projekt.entities.*;
import com.ipb.projekt.exceptions.NoShelfException;
import com.ipb.projekt.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductManagementService {


    private final ProductRepo productRepo;
    private final AnnexRepo annexRepo;
    private final ClientRepo clientRepo;
    private final ReceivedFakturaRepo receivedFakturaRepo;
    private final CreatedFakturaRepo createdFakturaRepo;
    private final SaleRepo saleRepo;
    private final ShelfRepo shelfRepo;


    public ProductRepo getProductRepo() {
        return productRepo;
    }

    public AnnexRepo getAnnexRepo() {
        return annexRepo;
    }

    public ClientRepo getClientRepo() {
        return clientRepo;
    }

    public ReceivedFakturaRepo getReceivedFakturaRepo() {
        return receivedFakturaRepo;
    }

    public CreatedFakturaRepo getCreatedFakturaRepo() {
        return createdFakturaRepo;
    }

    public SaleRepo getSaleRepo() {
        return saleRepo;
    }

    public ShelfRepo getShelfRepo() {
        return shelfRepo;
    }

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

    public void createDostawa(List<String> names, List<String> serialNumbers, List<Integer> amounts,
                              String fakturaNumber, LocalDate date,
                              LocalTime time, String scannedImagePath)
    {
        List<ProductEntity> productList = new ArrayList<ProductEntity>();
        int count =0;
        ProductEntity tmpProduct;
        for (String name:names)
        {
             tmpProduct = createProduct(name,serialNumbers.get(count),amounts.get(count));
             productList.add(tmpProduct);
        }
        ReceivedFakturaEntity tmpFaktura;
        tmpFaktura = createReceivedFaktura(fakturaNumber,date,time,scannedImagePath,productList);
        for (ProductEntity product:productList) {
            product.setReceivedFakturaEntity(tmpFaktura);
        }
    }

    public ProductEntity createProduct(String name, String serialNumber, int amount)
    {
        ProductEntity newProduct = new ProductEntity(name,serialNumber,amount,"Odebrany");
        productRepo.save(newProduct);
        return newProduct;
    }

    public ReceivedFakturaEntity createReceivedFaktura(String fakturaNumber, LocalDate date,
                                 LocalTime time, String scannedImagePath,
                                 Collection<ProductEntity> productEntities)
    {
        ReceivedFakturaEntity newReceivedFaktura = new ReceivedFakturaEntity(fakturaNumber,date,time,scannedImagePath,productEntities);
        receivedFakturaRepo.save(newReceivedFaktura);
        return newReceivedFaktura;
    }

    public void choiceShelfForProduct(ProductEntity product)
    {
        List<ShelfEntity> shelfs = new ArrayList<>();
        shelfRepo.findAll().forEach(shelfs::add);
        //usuwanie pełnych pułek
        for (ShelfEntity shelf:shelfs)
        {
            try{
                //jeśli jest pełna, to usunie, jeśli pusta zwróci nullPointerException i nie usunie.
                shelf.getProductEntity();
                shelfs.remove(shelf);
            }catch(NullPointerException e)
            {
                System.out.println("Pusta Polka, nie usuwamy");//temporary handle
            }
        }
        //----------------------
        //TODO:zrobić pickowanie shelfa!
        //Pick Shelf
        ShelfEntity choicedShelf = null;//temporary null

        //-------------
        try {
            putProductOnShelf(product,choicedShelf.getIdShelf());
        }catch(NoShelfException e){e.printStackTrace();}
        choicedShelf.setProductEntity(product);
        shelfRepo.save(choicedShelf); //TODO: upewnić się że przypisanie na zmienną "choicedShelf" shelfa nie spowoduje duplikacji.
    }

    public ProductEntity searchForProduct(String name,String manufacturer, String serialNumber) throws Exception {
        List<ShelfEntity> shelfs = new ArrayList<>();
        shelfRepo.findAll().forEach(shelfs::add);
        ShelfEntity searchedShelf = new ShelfEntity();
        ProductEntity product;
        boolean found = false;
        for (ShelfEntity shelf:shelfs) {
            try{
                //przypisanie produktu i potem branie go 3 razy, jest szybsze niż branie półki, i branie półki 3 razy, i potem produktu 3 razy
                product = shelf.getProductEntity();
                if(product.getName().equals(name) && product.getManufacturer().equals(manufacturer) && product.getSerialNumber().equals(serialNumber))
                {
                    searchedShelf = shelf;
                    found = true;
                }
            }catch(NullPointerException e)
            {
                System.out.println("pusta polka, SearchForProduct");
            }
        }
        if(found)
        return searchedShelf.getProductEntity();
        else throw new Exception("nie znaleziono takiego produktu");

    }

    public void sellProduct(String name,String manufacturer, String serialNumber)
    {
        try {
            ProductEntity product = searchForProduct(name, manufacturer, serialNumber);
            product.setShelfEntity(null);
            product.setStatus("Sold");
            //SaleEntity sale = new SaleEntity(LocalDate.now(),LocalTime.now(),CENA,product);
            //product.setSaleEntity(sale);
        }catch (Exception e)
        {
            System.out.println("prak produktu");
        }


    }

    public void createSaleFaktura(String fakturaNumber, LocalDate date,
                                  LocalTime time, String scannedImagePath, SaleEntity saleEntity)
    {
        CreatedFakturaEntity faktura = new CreatedFakturaEntity(fakturaNumber,date,time,scannedImagePath,saleEntity);
        createdFakturaRepo.save(faktura);
    }

    public void CreateMagazyn(int columns,int rows, int levels)
    {
        ShelfEntity newShelf;
        for(int c =0;c<columns;c++)
        {
            for(int r=0;r<rows;r++)
            {
                for(int l=0;l<levels;l++)
                {
                    newShelf = new ShelfEntity(c,r,l);
                    shelfRepo.save(newShelf);
                }
            }
        }
        System.out.println(shelfRepo.findAll());

    }

    public void putProductOnShelf(ProductEntity e, int idShelf) throws NoShelfException {
        Optional<ShelfEntity> shelf = this.shelfRepo.findById(idShelf);
        e.setShelfEntity(shelf.orElseThrow(
                () -> new NoShelfException("Shelf with id "+idShelf+" doesn't exist")
        ));
        this.productRepo.save(e);
        shelf.get().setProductEntity(e);
        this.shelfRepo.save(shelf.get());
    }

    public Iterable<ShelfEntity> getAllShelves() {
        return this.shelfRepo.findAll();
    }


}
