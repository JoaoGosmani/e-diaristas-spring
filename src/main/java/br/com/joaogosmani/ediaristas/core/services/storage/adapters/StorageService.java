package br.com.joaogosmani.ediaristas.core.services.storage.adapters;

import org.springframework.web.multipart.MultipartFile;

import br.com.joaogosmani.ediaristas.core.models.Foto;
import br.com.joaogosmani.ediaristas.core.services.storage.exceptions.StorageServiceException;

public interface StorageService {
    
    Foto salvar(MultipartFile file) throws StorageServiceException;

    void apagar(String filename) throws StorageServiceException;

}
