package productservice.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductResponse {
    public Long id;
    public String name;
    public String description;
    public BigDecimal price;
    public String sku;
    public int stock;
    public String brand;
    public String category;
    public List<String> imageUrls;
}