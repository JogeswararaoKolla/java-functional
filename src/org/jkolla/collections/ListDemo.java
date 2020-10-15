package org.jkolla.collections;

import org.jkolla.models.FilterOption;
import org.jkolla.models.Product;
import org.jkolla.models.ProductDetail;
import org.jkolla.utils.Util;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListDemo {
    public static void main(String[] args) {
        Util util= new Util();
        List<Product> products = util.createProducts();
        System.out.println("products = " + products);
        List<FilterOption> filterOptions = util.createFilterOptions();
        System.out.println("filterOptions = " + filterOptions);

        Map<String, List<String>> filterOptionsMap = filterOptions.stream()
                .collect(Collectors.toMap(FilterOption::getCategory,
                        FilterOption::getSelectValues));
        System.out.println("filterOptionsMap = " + filterOptionsMap);

        List<Product> productsFiltered = products.stream()
                .filter(e -> filterOptionsMap.containsKey(e.getProductId()))
                .map(new Function<Product, Product>() {
                    @Override
                    public Product apply(Product product) {
                        List<ProductDetail> collect = product.getProductDetails().stream()
                                .peek(System.out::println)
                                .filter(e -> filterOptionsMap.get(product.getProductId()).contains(e.getProductMerchantId()))
                                .collect(Collectors.toList());
                        System.out.println("collect = " + collect);
                        if( ! filterOptionsMap.get(product.getProductId()).isEmpty())
                        product.setProductDetails(collect);
                        return product;
                    }
                })
                .collect(Collectors.toList());
        System.out.println("productsFiltered = " + productsFiltered);
    }
}
