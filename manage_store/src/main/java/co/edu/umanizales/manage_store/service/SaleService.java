package co.edu.umanizales.manage_store.service;

import co.edu.umanizales.manage_store.controller.dto.BestSellerDTO;
import co.edu.umanizales.manage_store.controller.dto.BestStoreDTO;
import co.edu.umanizales.manage_store.controller.dto.MetaStoreDTO;
import co.edu.umanizales.manage_store.model.Sale;
import co.edu.umanizales.manage_store.model.Seller;
import co.edu.umanizales.manage_store.model.Store;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class SaleService {
    private List<Sale> sales;

    public SaleService() {
        this.sales = new ArrayList<>();
    }

    public void addSale(Sale sale){
        this.sales.add(sale);
    }

    public int getTotalSales()
    {
        int sum = 0;
        for(Sale sale:sales){
            sum = sum + sale.getQuantity();
        }
        return sum;
    }

    public int getTotalSalesBySeller(String codeSeller)
    {
        int sum = 0;
        for(Sale sale:sales){
            if(sale.getSeller().getCode().equals(codeSeller)){
                sum = sum + sale.getQuantity();
            }
        }
        return sum;
    }
    public int getTotalSalesByStore(String codeStore){
        int sum=0;
        for(Sale sale:sales){
            if(Sale.getStore().getCode().equals(codeStore)){
                sum=sum +sale.getQuantity();
            }
        }
    }
    public BestSellerDTO getBestSeller(List<Seller> sellers)
    {
        //Referencia como mayor;
        BestSellerDTO bestSellerDTO = new BestSellerDTO(new Seller(), 0);
        //Recorremos todas las ventas
        for(Seller seller:sellers)
        {
            int quant = getTotalSalesBySeller(seller.getCode());
            if(quant >= bestSellerDTO.getQuantity()){
                bestSellerDTO = new BestSellerDTO(seller,quant);
            }
        }
        return bestSellerDTO;
    }
    public void getBestStore (List<Store> stores) {

        BestStoreDTO bestStoreDTO;
        bestStoreDTO = new BestStoreDTO(new Store(), 0);

        for (Store store : stores) {
            int quant = getTotalSalesByStore(store.getCode());
            if (quant >= bestStoreDTO.getQuantity()) {
                bestStoreDTO = new BestStoreDTO(store, quant);
            }
        }
        return bestStoreDTO;


    }
    public void getMetaStore(){
        MetaStoreDTO metaStoreDTO = new MetaStoreDTO(new Store(), 0);

        int quantity=getTotalSalesByStore(store.getCode());
        if(quant>=metaStoreDTO.getQuantity()){
            metaStoreDTO = new MetaStoreDTO(store,quant);
        }
    return MetaStoreDTO;

    }
}
