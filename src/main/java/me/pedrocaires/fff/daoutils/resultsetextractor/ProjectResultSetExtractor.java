package me.pedrocaires.fff.daoutils.resultsetextractor;

import me.pedrocaires.fff.project.models.Project;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProjectResultSetExtractor extends EntityResultSetExtractor<Project>{

    @Override
    Project rawExtraction(ResultSet resultSet) throws SQLException {
        var project = new Project();
        project.setId(resultSet.getInt("ID"));
        project.setName(resultSet.getString("NAME"));
        project.setAccountId(resultSet.getInt("ACCOUNT_ID"));
        return project;
    }

}
