package com.accent.sav.service;

import com.accent.sav.dto.ComponentBackupDto;
import com.accent.sav.entities.ComponentBackup;

import java.util.List;

public interface ComponentBackupService {
    public List<ComponentBackup> findAllComponent();
    public ComponentBackup addComponent(ComponentBackupDto componentDto);

    public ComponentBackup updateComponentDto(ComponentBackupDto componentDto);

    public Boolean deleteComponent(int id);

    ComponentBackupDto getComponentById(Integer idComponent);
}
