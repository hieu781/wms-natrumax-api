package com.natrumax.models;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
=======
import jakarta.persistence.*;
import java.util.Date;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
import java.util.List;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(name = "product_id")
    private Long productId;
=======
    private int id;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    @Column(name = "barcode", nullable = false, length = 255)
    private String barcode;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
<<<<<<< HEAD
    private LocalDateTime createDate;

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifyDate;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "discount", nullable = false)
    private double discount;

=======
    private Date createDate;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "discount", nullable = false)
    private float discount;

    @Column(name = "last_modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifyDate;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    @Column(name = "name", length = 255)
    private String name;

<<<<<<< HEAD
    @Column(name = "base_price", nullable = false)
    private double basePrice;

    @Column(name = "image", length = 500)
    private String image;

    @JsonIgnore
    private String cloudinaryImageId;

    @Column(name = "misa_code", nullable = false, length = 255)
    private String misaCode;
=======
    @Column(name = "quantity", nullable = false)
    private int quantity;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    @Column(name = "quantity_to_get_promotion")
    private Integer quantityToGetPromotion;

<<<<<<< HEAD

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "unit", nullable = false)
    private String unit;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LotteryCode> lotterycodes;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<WarehouseProduct> warehouseProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserProduct> userProducts;
=======
    @Column(name = "status", nullable = false)
    private boolean status;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrderDetail orderDetail;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommissionProduct> commissionProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RedemptionCode> redemptionCodes;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    // Getters and Setters


    public Product() {
    }

<<<<<<< HEAD
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
=======
    public Product(int id, String barcode, Date createDate, String description, float discount, Date lastModifyDate, String name, int quantity, Integer quantityToGetPromotion, boolean status, OrderDetail orderDetail) {
        this.id = id;
        this.barcode = barcode;
        this.createDate = createDate;
        this.description = description;
        this.discount = discount;
        this.lastModifyDate = lastModifyDate;
        this.name = name;
        this.quantity = quantity;
        this.quantityToGetPromotion = quantityToGetPromotion;
        this.status = status;
        this.orderDetail = orderDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

<<<<<<< HEAD
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
=======
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

<<<<<<< HEAD
    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
=======
    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCloudinaryImageId() {
        return cloudinaryImageId;
    }

    public void setCloudinaryImageId(String cloudinaryImageId) {
        this.cloudinaryImageId = cloudinaryImageId;
    }

    public String getMisaCode() {
        return misaCode;
    }

    public void setMisaCode(String misaCode) {
        this.misaCode = misaCode;
    }


=======
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    public Integer getQuantityToGetPromotion() {
        return quantityToGetPromotion;
    }

    public void setQuantityToGetPromotion(Integer quantityToGetPromotion) {
        this.quantityToGetPromotion = quantityToGetPromotion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

<<<<<<< HEAD
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<LotteryCode> getLotterycodes() {
        return lotterycodes;
    }

    public void setLotterycodes(List<LotteryCode> lotterycodes) {
        this.lotterycodes = lotterycodes;
    }

    public List<WarehouseProduct> getWarehouseProducts() {
        return warehouseProducts;
    }

    public void setWarehouseProducts(List<WarehouseProduct> warehouseProducts) {
        this.warehouseProducts = warehouseProducts;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<UserProduct> getUserProducts() {
        return userProducts;
    }

    public void setUserProducts(List<UserProduct> userProducts) {
        this.userProducts = userProducts;
=======
    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    }
}
