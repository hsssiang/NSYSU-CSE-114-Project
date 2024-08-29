package gui;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class FileReader {
    private final SimpleStringProperty product;
    private final SimpleIntegerProperty profit;
    private final SimpleIntegerProperty pid;

    public FileReader(Integer pid, String product, Integer profit ) {
        this.pid = new SimpleIntegerProperty(pid);
        this.product = new SimpleStringProperty(product);
        this.profit = new SimpleIntegerProperty(profit);
    }

    public String getProduct() {
        return product.get();
    }

    public void setProduct(String product) {
        this.product.set(product);
    }

    public Integer getProfit() {
        return profit.get();
    }

    public void setProfit(Integer profit) {
        this.profit.set(profit);
    }

    public Integer getPid() {
        return pid.get();
    }

    public void setPid(Integer pid) {
        this.pid.set(pid);
    }

}
