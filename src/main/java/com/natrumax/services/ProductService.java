package com.natrumax.services;

import com.cloudinary.utils.StringUtils;
import com.natrumax.config.MisaApiClient;
import com.natrumax.dto.request.CreateProductRequest;
import com.natrumax.dto.request.UpdateProductRequest;
import com.natrumax.dto.response.CloudinaryResponse;
import com.natrumax.dto.response.ProductResponse;
import com.natrumax.models.*;
import com.natrumax.models.MockAPI.MisaGood;
import com.natrumax.models.MockAPI.MisaWarehouse;
import com.natrumax.models.MockAPI.MisaWarehouseGood;
import com.natrumax.models.MockAPI.response.MisaWarehouseResponse;
import com.natrumax.repository.*;
import com.natrumax.security.mapper.ProductMapper;
import com.natrumax.services.APIService.MisaService;
import com.natrumax.services.interfaces.IProductService;
import com.natrumax.utils.FileUploadUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private final MisaService misaService;

    @Autowired
    private final ProductMapper productMapper;

    @Autowired
    private WarehouseProductRepository warehouseProductRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MisaApiClient misaApiClient;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    public ProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            MisaService misaService,
            ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.misaService = misaService;
        this.productMapper = productMapper;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByStatus(boolean status) {
        return productRepository.findByStatus(status);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> getProductsByPaging(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return productRepository.findAll(pageable);
    }

    public Product createProduct(CreateProductRequest request) {
        // Fetch the category
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + request.getCategoryId()));

        Product product = new Product();
        product.setBarcode(request.getBarcode());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBasePrice(request.getBasePrice());
        product.setImage(request.getImage());
        product.setDiscount(request.getDiscount());
        product.setQuantityToGetPromotion(request.getQuantityToGetPromotion());
        product.setStatus(true);
        product.setUnit(request.getUnit());
        product.setMisaCode(request.getMisaCode());
        product.setCreateDate(LocalDateTime.now());

        // Set the category
        product.setCategory(category);

        return productRepository.save(product);
    }

    public Product updateProductStatus(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Toggle status (true -> false, false -> true)
        product.setStatus(!product.isStatus());

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, UpdateProductRequest request, MultipartFile imageFile) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            if (request.getBarcode() != null) product.setBarcode(request.getBarcode());
            if (request.getName() != null) product.setName(request.getName());
            if (request.getDescription() != null) product.setDescription(request.getDescription());
            if (request.getPrice() != null) product.setBasePrice(request.getPrice());
            if (request.getDiscount() != null) product.setDiscount(request.getDiscount());
            if (request.getQuantityToGetPromotion() != null) product.setQuantityToGetPromotion(request.getQuantityToGetPromotion());
            if (request.getStatus() != null) product.setStatus(request.getStatus());
            if (request.getUnit() != null) product.setUnit(request.getUnit());

            // Cập nhật danh mục nếu có
            if (request.getCategoryId() != null) {
                Category category = categoryRepository.findById(request.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found with ID: " + request.getCategoryId()));
                product.setCategory(category);
            }

            // Nếu có file ảnh thì xử lý upload
            if (imageFile != null && !imageFile.isEmpty()) {
                // Xoá ảnh cũ nếu có
                String oldImageId = product.getCloudinaryImageId();
                if (StringUtils.isNotBlank(oldImageId)) {
                    cloudinaryService.deleteFile(oldImageId);
                }

                FileUploadUtil.assertAllowed(imageFile, FileUploadUtil.IMAGE_PATTERN);
                String newFileName = FileUploadUtil.getFileName(imageFile.getOriginalFilename());
                CloudinaryResponse response = cloudinaryService.uploadFile(imageFile, newFileName);

                product.setImage(response.getUrl());
                product.setCloudinaryImageId(response.getPublicId());
            }

            product.setModifyDate(LocalDateTime.now());

            return productRepository.save(product);
        }

        throw new RuntimeException("Product not found with ID: " + id);
    }


//    public Product importProductFromMisa(String misaId, Long categoryId) {
//        // Check if the product already exists with this MISA code
//        Optional<Product> existingProduct = productRepository.findByMisaCode(misaId);
//        if (existingProduct.isPresent()) {
//            return existingProduct.get();
//        }
//
//        // Get the product from MISA API
//        Optional<MisaGood> misaGoodOptional = misaGoodService.getMisaGoodById(misaId);
//
//        if (misaGoodOptional.isPresent()) {
//            MisaGood misaGood = misaGoodOptional.get();
//
//            // Map to our Product entity
//            Product product = productMapper.mapToProduct(misaGood);
//
//            // Set the category
//            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
//            categoryOptional.ifPresent(product::setCategory);
//
//            // Save and return
//            return productRepository.save(product);
//        } else {
//            throw new RuntimeException("Product not found in MISA API with ID: " + misaId);
//        }
//    }

    public Product importProductFromMisa(String misaCode, Long categoryId, String barcode) {
        if (productRepository.findByMisaCode(misaCode).isPresent()) {
            throw new RuntimeException("MISA Code " + misaCode + " already exist");
        }

        Optional<MisaGood> misaGoodOptional = misaService.getMisaGoodByCode(misaCode);

        if (misaGoodOptional.isPresent()) {
            MisaGood misaGood = misaGoodOptional.get();

            // Truyền thêm barcode vào map
            Product product = productMapper.mapToProduct(misaGood, barcode);

            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            if (categoryOptional.isPresent()) {
                product.setCategory(categoryOptional.get());
            } else {
                throw new RuntimeException("Category not found with ID: " + categoryId);
            }

            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found in MISA API with Code: " + misaCode);
        }
    }


    public List<ProductResponse> getProductListByWarehouseId(Long warehouseId) {
        // Lấy thông tin kho local
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        // Gọi API MISA để lấy danh sách warehouse
        MisaWarehouseResponse response = misaApiClient.getAllMisaWarehouses();
        List<MisaWarehouse> misaWarehouses = response.getData();

        // Tìm MISA warehouseId tương ứng với clientSecret = accessCode
        String misaWarehouseId = misaWarehouses.stream()
                .filter(mw -> warehouse.getAccessCode().equals(mw.getClientSecret()))
                .map(MisaWarehouse::getId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cant found MISA warehouse with clientSecret: " + warehouse.getAccessCode()));

        // Gọi API MISA để lấy danh sách sản phẩm trong kho
        List<MisaWarehouseGood> misaWarehouseGoods = misaApiClient.getAllMisaWarehouseGoods();

        List<MisaWarehouseGood> filteredMisaGoods = misaWarehouseGoods.stream()
                .filter(mg -> mg.getWarehouse().equals(misaWarehouseId))
                .collect(Collectors.toList());

        // Lọc danh sách sản phẩm thuộc kho MISA tương ứng
        Map<String, Integer> misaQuantityMap = filteredMisaGoods.stream()
                .filter(mg -> mg.getGoods() != null && mg.getGoods().getCode() != null)
                .collect(Collectors.toMap(
                        mg -> mg.getGoods().getCode(),
                        MisaWarehouseGood::getQuantity,
                        (q1, q2) -> q1 // nếu trùng code, lấy quantity đầu tiên
                ));

        // Lấy danh sách WarehouseProduct từ DB
        List<WarehouseProduct> warehouseProducts = warehouseProductRepository.findByWarehouse_WarehouseId(warehouseId);

        // Cập nhật số lượng từ MISA
        for (WarehouseProduct warehouseProduct : warehouseProducts) {
            String misaCode = warehouseProduct.getProduct().getMisaCode();
            if (misaQuantityMap.containsKey(misaCode)) {
                Integer quantityFromMisa = misaQuantityMap.get(misaCode);
                warehouseProduct.setQuantity(quantityFromMisa);
            }
        }

        warehouseProductRepository.saveAll(warehouseProducts);

        // Trả về danh sách DTO
        return warehouseProducts.stream()
                .map(wp -> new ProductResponse(
                        wp.getProduct().getProductId(),
                        wp.getProduct().getBarcode(),
                        wp.getProduct().getMisaCode(),
                        wp.getProduct().getName(),
                        wp.getProduct().getImage(),
                        wp.getProduct().getCategory().getCategoryName(),
                        wp.getProduct().getBasePrice(),
                        wp.getProduct().getDiscount(),
                        wp.getQuantity(),
                        wp.getProduct().getUnit(),
                        wp.getProduct().getQuantityToGetPromotion(),
                        wp.getProduct().getDescription(),
                        wp.getProduct().isStatus()
                )).collect(Collectors.toList());
    }

    public void updateWarehouseProductsFromMisa(Long warehouseId) {
        // Lấy thông tin kho local
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        // Gọi API MISA để lấy danh sách warehouse
        MisaWarehouseResponse response = misaApiClient.getAllMisaWarehouses();
        List<MisaWarehouse> misaWarehouses = response.getData();

        // Tìm MISA warehouseId tương ứng với clientSecret = accessCode
        String misaWarehouseId = misaWarehouses.stream()
                .filter(mw -> warehouse.getAccessCode().equals(mw.getClientSecret()))
                .map(MisaWarehouse::getId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cant found MISA warehouse with clientSecret: " + warehouse.getAccessCode()));

        // Gọi API MISA để lấy danh sách sản phẩm trong kho
        List<MisaWarehouseGood> misaWarehouseGoods = misaApiClient.getAllMisaWarehouseGoods();

        List<MisaWarehouseGood> filteredMisaGoods = misaWarehouseGoods.stream()
                .filter(mg -> mg.getWarehouse().equals(misaWarehouseId))
                .collect(Collectors.toList());

        // Map code -> quantity
        Map<String, Integer> misaQuantityMap = filteredMisaGoods.stream()
                .filter(mg -> mg.getGoods() != null && mg.getGoods().getCode() != null)
                .collect(Collectors.toMap(
                        mg -> mg.getGoods().getCode(),
                        MisaWarehouseGood::getQuantity,
                        (q1, q2) -> q1
                ));

        // Cập nhật số lượng trong DB
        List<WarehouseProduct> warehouseProducts = warehouseProductRepository.findByWarehouse_WarehouseId(warehouseId);
        for (WarehouseProduct warehouseProduct : warehouseProducts) {
            String misaCode = warehouseProduct.getProduct().getMisaCode();
            if (misaQuantityMap.containsKey(misaCode)) {
                Integer quantityFromMisa = misaQuantityMap.get(misaCode);
                warehouseProduct.setQuantity(quantityFromMisa);
            }
        }

        warehouseProductRepository.saveAll(warehouseProducts);
    }

    public List<ProductResponse> getWarehouseProducts(Long warehouseId) {
        List<WarehouseProduct> warehouseProducts = warehouseProductRepository.findByWarehouse_WarehouseId(warehouseId);

        return warehouseProducts.stream()
                .map(wp -> new ProductResponse(
                        wp.getProduct().getProductId(),
                        wp.getProduct().getBarcode(),
                        wp.getProduct().getMisaCode(),
                        wp.getProduct().getName(),
                        wp.getProduct().getImage(),
                        wp.getProduct().getCategory().getCategoryName(),
                        wp.getProduct().getBasePrice(),
                        wp.getProduct().getDiscount(),
                        wp.getQuantity(),
                        wp.getProduct().getUnit(),
                        wp.getProduct().getQuantityToGetPromotion(),
                        wp.getProduct().getDescription(),
                        wp.getProduct().isStatus()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductResponse getProductByProductIdAndWarehouseId(Long warehouseId, Long productId) {
        // Lấy thông tin kho
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with ID: " + warehouseId));

        // Lấy WarehouseProduct
        WarehouseProduct warehouseProduct = warehouseProductRepository
                .findByProduct_ProductIdAndWarehouse_WarehouseId(productId, warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse Product not found"));

        Product product = warehouseProduct.getProduct();

//        // Lấy quantity từ MISA theo misaCode
//        String misaWarehouseId = misaApiClient.getAllMisaWarehouses().getData().stream()
//                .filter(mw -> warehouse.getAccessCode().equals(mw.getClientSecret()))
//                .map(MisaWarehouse::getId)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Cant found MISA warehouse with clientSecret: " + warehouse.getAccessCode()));
//
//        List<MisaWarehouseGood> misaGoods = misaApiClient.getAllMisaWarehouseGoods();
//
//        Optional<MisaWarehouseGood> matchedGoodOpt = misaGoods.stream()
//                .filter(g -> misaWarehouseId.equals(g.getWarehouse()))
//                .filter(g -> g.getGoods() != null && product.getMisaCode().equals(g.getGoods().getCode()))
//                .findFirst();
//
//        matchedGoodOpt.ifPresent(mg -> {
//            warehouseProduct.setQuantity(mg.getQuantity());
//            warehouseProductRepository.save(warehouseProduct);
//        });

        // Trả về DTO
        return new ProductResponse(
                product.getProductId(),
                product.getBarcode(),
                product.getMisaCode(),
                product.getName(),
                product.getImage(),
                product.getCategory().getCategoryName(),
                product.getBasePrice(),
                product.getDiscount(),
                warehouseProduct.getQuantity(),
                product.getUnit(),
                product.getQuantityToGetPromotion(),
                product.getDescription(),
                product.isStatus()
        );
    }

    @Transactional
    public void uploadImage(final Long productId, final MultipartFile file) {
        final Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        final String cloudinaryImageId = product.getCloudinaryImageId();
        if (StringUtils.isNotBlank(cloudinaryImageId)) {
            cloudinaryService.deleteFile(cloudinaryImageId);
        }

        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
        final String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
        final CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName);
        product.setImage(response.getUrl());
        product.setCloudinaryImageId(response.getPublicId());
        productRepository.save(product);
    }
}

