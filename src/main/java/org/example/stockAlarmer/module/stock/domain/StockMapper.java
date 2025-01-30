package org.example.stockAlarmer.module.stock.domain;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.util.Map;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = {LocalDate.class})
public interface StockMapper {

    @Mapping(target = "symbol", expression = "java(record.get(\"symbol\"))")
    @Mapping(target = "name", expression = "java(record.get(\"name\"))")
    @Mapping(target = "exchange", expression = "java(record.get(\"exchange\"))")
    @Mapping(target = "assetType", expression = "java(record.get(\"assetType\"))")
    @Mapping(target = "ipoDate", expression = "java(parseDate(record.get(\"ipoDate\")))")
    @Mapping(target = "delistingDate", expression = "java(parseDate(record.get(\"delistingDate\")))")
    @Mapping(target = "status", expression = "java(record.get(\"status\"))")
    Stock toDomain(Map<String, String> record);

    default LocalDate parseDate(String date) {
        return date == null || date.isEmpty() || "null".equals(date) ? null : LocalDate.parse(date);
    }


}
