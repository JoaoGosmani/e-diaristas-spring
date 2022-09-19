package br.com.joaogosmani.ediaristas.core.services.storage.providers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.joaogosmani.ediaristas.core.models.Foto;
import br.com.joaogosmani.ediaristas.core.repositories.FotoRepository;
import br.com.joaogosmani.ediaristas.core.services.storage.adapters.StorageService;
import br.com.joaogosmani.ediaristas.core.services.storage.exceptions.StorageServiceException;

@Service
public class LocalStorageService implements StorageService {
    
    private final Path pastaUpload = Paths.get("uploads");

    @Autowired
    private FotoRepository fotoRepository;

    @Override
    public Foto salvar(MultipartFile file) throws StorageServiceException {
        try {
            return trySalvar(file);
        } catch (IOException exception) {
            throw new StorageServiceException(exception.getLocalizedMessage());
        }
    }

    private Foto trySalvar(MultipartFile file) throws IOException {
        if (!Files.exists(pastaUpload)) {
            Files.createDirectories(pastaUpload);
        }

        var foto = gerarModelFoto(file);
        Files.copy(file.getInputStream(), pastaUpload.resolve(foto.getFilename()));
        return fotoRepository.save(foto);
    }

    private Foto gerarModelFoto(MultipartFile file) {
        var foto = new Foto();
        var filename = gerarNovoFilename(file.getOriginalFilename());
        foto.setFilename(filename);
        foto.setContentLength(file.getSize());
        foto.setContentType(file.getContentType());
        foto.setUrl("url da foto");
        return foto;
    }

    private String gerarNovoFilename(String filenameOriginal) {
        var ext = filenameOriginal.split("\\.")[1];
        var filename = UUID.randomUUID().toString() + "." + ext;
        return filename;
    }

}
