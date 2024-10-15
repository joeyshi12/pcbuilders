package database;

import transfers.PcBuildOuterClass.PcBuild;
import java.util.*;

final class QueryUtil {
    public static String formQueryWithIdCondition(String tableName, String[] columns, String[] ids) throws Exception {
        StringBuilder builder = new StringBuilder(formTableSelectQuery(tableName, columns));
        if (ids != null && ids.length > 0) {
            builder.append(" WHERE ").append(formIdCondition(ids));
        }
        return builder.toString();
    }

    public static String formTableSelectQuery(String tableName, String[] columns) throws Exception {
        if (columns.length == 0) {
            throw new Exception("No columns provided in query");
        }
        StringBuilder builder = new StringBuilder("SELECT ");
        builder.append(columns[0]);
        for (int i = 1; i < columns.length; i++) {
            builder.append(",").append(columns[i]);
        }
        return builder
            .append(" FROM ")
            .append(tableName)
            .toString();
    }

    public static String formIdCondition(String[] ids) throws Exception {
        if (ids == null || ids.length == 0) {
            throw new Exception("No IDs in ID condition");
        }
        StringBuilder builder = new StringBuilder("id IN ('")
            .append(ids[0])
            .append("'");
        for (int i = 1; i < ids.length; i++) {
            builder.append(",'").append(ids[i]).append("'");
        }
        return builder
            .append(")")
            .toString();
    }

    public static String formComponentIdValues(PcBuild build) {
        List<String> componentIds = new ArrayList<>();
        for (String cpuId : build.getCpuIdsList()) {
            componentIds.add(cpuId);
        }
        for (String motherboardId : build.getMotherboardIdsList()) {
            componentIds.add(motherboardId);
        }
        for (String memoryId : build.getMemoryIdsList()) {
            componentIds.add(memoryId);
        }
        for (String storageId : build.getStorageIdsList()) {
            componentIds.add(storageId);
        }
        for (String videoCardId : build.getVideoCardIdsList()) {
            componentIds.add(videoCardId);
        }
        for (String powerSupplyId : build.getPowerSupplyIdsList()) {
            componentIds.add(powerSupplyId);
        }
        List<String> isPartOfValues = componentIds.stream()
            .map((String id) -> String.format("('%s','%s')", id, build.getUuid()))
            .toList();
        return String.join(",", isPartOfValues);
    }
}
