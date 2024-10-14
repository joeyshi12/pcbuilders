package database;

import org.eclipse.jetty.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import transfers.PcBuildOuterClass.*;

import java.sql.*;
import java.util.*;

public class PcBuildDatabase {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ConnectionHandler connectionHandler;

    public PcBuildDatabase(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public PcBuild insertPcBuild(PcBuild build) throws SQLException {
        String query = "INSERT INTO pc_build (id, display_name, creation_date, last_updated_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = connectionHandler.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, build.getUuid());
            ps.setString(2, build.getDisplayName());
            ps.setDate(3, new java.sql.Date(build.getCreationDate()));
            ps.setDate(4, new java.sql.Date(build.getLastUpdateDate()));
            ps.executeQuery();
            connection.commit();
            logger.info(String.format("Created computer build %s", build.getUuid()));
        }
        return build;
    }

    public List<PcBuild> getAllPcBuilds(String[] buildIds, String username) throws Exception {
        List<PcBuild> builds = new ArrayList<>();
        String query = QueryUtil.formQueryWithIdsFilter("pc_build", TableColumnNames.COMPUTER_BUILD_COLUMNS, buildIds);
        try (Connection connection = connectionHandler.getConnection()) {
            PreparedStatement ps;
            if (StringUtil.isBlank(username)) {
                ps = connection.prepareStatement(query);
            } else {
                if (buildIds == null || buildIds.length == 0) {
                    query = query + " WHERE username = ?";
                } else {
                    query = query + " AND username = ?";
                }
                ps = connection.prepareStatement(query);
                ps.setString(1, username);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PcBuild build = PcBuild.newBuilder()
                    .setUuid(rs.getString(TableColumnNames.ID))
                    .setDisplayName(rs.getString(TableColumnNames.DISPLAY_NAME))
                    .setUsername(rs.getString(TableColumnNames.USERNAME))
                    .setCreationDate(rs.getDate(TableColumnNames.CREATION_DATE).getTime())
                    .setLastUpdateDate(rs.getDate(TableColumnNames.LAST_UPDATED_DATE).getTime())
                    .build();
                builds.add(build);
            }
        }
        return builds;
    }

    public void updatePcBuild(PcBuild build) {
        logger.info(String.format("Updated computer build %s", build.getUuid()));
    }

    public void deletePcBuild(String buildId) {
        logger.info(String.format("Deleted computer build %s", buildId));
    }
}
