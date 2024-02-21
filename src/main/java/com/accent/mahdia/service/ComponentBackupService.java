package com.accent.mahdia.service;

import com.accent.mahdia.dto.ComponentBackupDto;
import com.accent.mahdia.entities.ComponentBackup;

import java.util.List;

public interface ComponentBackupService {
    public List<ComponentBackup> findAllComponent();
    public ComponentBackupDto addComponent(ComponentBackupDto componentDto);

    public ComponentBackupDto updateComponentDto(ComponentBackupDto componentDto);

    public Boolean deleteComponent(int id);

    ComponentBackupDto getComponentById(Integer idComponent);
}
