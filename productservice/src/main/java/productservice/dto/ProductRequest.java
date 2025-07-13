package productservice.dto;

import java.math.BigDecimal;

public class ProductRequest {
    public String name;
    public String description;
    public BigDecimal price;
    public int stock;
    public String sku;
    public Long brandId;
    public Long categoryId;
}