package me.pedrocaires.fff.project;

import me.pedrocaires.fff.project.models.CreateProjectResponse;
import me.pedrocaires.fff.project.models.Project;
import me.pedrocaires.fff.project.models.ProjectResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    CreateProjectResponse projectToProjectResponse(Project project);

    List<ProjectResponse> projectsToProjectsResponse(List<Project> projects);

}
