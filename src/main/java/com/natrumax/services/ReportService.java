package com.natrumax.services;

import com.natrumax.dto.response.ProductReportResponse;
import com.natrumax.dto.response.ProductSoldResponse;
import com.natrumax.models.Product;
import com.natrumax.models.Report;
import com.natrumax.models.UserProduct;
import com.natrumax.repository.ReportRepository;
import com.natrumax.repository.UserProductRepository;
import com.natrumax.services.interfaces.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService implements IReportService {
    @Autowired
    private UserProductRepository userProductRepository;

    @Autowired
    private ReportRepository reportRepository;

    public List<ProductReportResponse> getUserReport(Long userId, int month, int year) {
        List<UserProduct> userProducts = userProductRepository.findByUserId(userId);
        List<ProductReportResponse> result = new ArrayList<>();

        int prevMonth = (month == 1) ? 12 : month - 1;
        int prevYear = (month == 1) ? year - 1 : year;

        for (UserProduct userProduct : userProducts) {
            Optional<Report> prevReportOpt = reportRepository.findByUserProductAndMonthAndYear(userProduct, prevMonth, prevYear);
            Optional<Report> currentReportOpt = reportRepository.findByUserProductAndMonthAndYear(userProduct, month, year);

            if (currentReportOpt.isPresent()) {
                Report currentReport = currentReportOpt.get();

                int previousStock = prevReportOpt.map(Report::getEndingInventory).orElse(0);
                int stockIn = currentReport.getQuantityImported();
                int endingStock = currentReport.getEndingInventory();

                Product product = userProduct.getProduct();
                ProductReportResponse productReportResponse = new ProductReportResponse();
                productReportResponse.setProductId(product.getProductId());
                productReportResponse.setProductName(product.getName());
                productReportResponse.setQuantityImported(stockIn);
                productReportResponse.setQuantitySold(previousStock + stockIn - endingStock);

                result.add(productReportResponse);
            }
        }
        return result;
    }

    public List<ProductSoldResponse> getSoldReport(int month, int year) {
        int prevMonth = (month == 1) ? 12 : month - 1;
        int prevYear = (month == 1) ? year - 1 : year;

        List<Report> currentReports = reportRepository.findByMonthAndYear(month, year);
        List<ProductSoldResponse> result = new ArrayList<>();

        for (Report currentReport : currentReports) {
            UserProduct userProduct = currentReport.getUserProduct();
            Product product = userProduct.getProduct();

            Optional<Report> prevReportOpt = reportRepository.findByUserProductAndMonthAndYear(
                    userProduct, prevMonth, prevYear
            );

            int previousStock = prevReportOpt.map(Report::getEndingInventory).orElse(0);
            int stockIn = currentReport.getQuantityImported();
            int endingStock = currentReport.getEndingInventory();

            int sold = previousStock + stockIn - endingStock;

            if (sold > 0) {
                ProductSoldResponse productSoldResponse = new ProductSoldResponse();
                productSoldResponse.setProductId(product.getProductId());
                productSoldResponse.setProductName(product.getName());
                productSoldResponse.setQuantitySold(sold);

                result.add(productSoldResponse);
            }
        }

        return result;
    }
}
