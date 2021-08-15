package me.pedrocaires.fff.endpoint.project;

import me.pedrocaires.fff.endpoint.project.models.Project;
import me.pedrocaires.fff.endpoint.project.models.CreateProjectResponse;
import me.pedrocaires.fff.endpoint.project.models.ProjectResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

	CreateProjectResponse projectToCreateProjectResponse(Project project);

	List<ProjectResponse> projectsToProjectsResponse(List<Project> projects);

}
