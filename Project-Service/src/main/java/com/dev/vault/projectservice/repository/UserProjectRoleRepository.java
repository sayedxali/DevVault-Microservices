package com.dev.vault.ProjectService.repository;

import com.dev.vault.ProjectService.model.entity.Project;
import com.dev.vault.ProjectService.model.entity.UserProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProjectRoleRepository extends JpaRepository<UserProjectRole, Long> {

    Optional<UserProjectRole> findByUserIdAndRoleIdAndProject(Long userId, Long roleId, Project project);

}