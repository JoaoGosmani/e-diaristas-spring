package br.com.joaogosmani.ediaristas.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import br.com.joaogosmani.ediaristas.api.dtos.requests.AtualizarUsuarioRequest;
import br.com.joaogosmani.ediaristas.api.dtos.requests.UsuarioRequest;
import br.com.joaogosmani.ediaristas.api.dtos.responses.MensagemResponse;
import br.com.joaogosmani.ediaristas.api.dtos.responses.TokenResponse;
import br.com.joaogosmani.ediaristas.api.dtos.responses.UsuarioCadastroResponse;
import br.com.joaogosmani.ediaristas.api.dtos.responses.UsuarioResponse;
import br.com.joaogosmani.ediaristas.api.mappers.ApiUsuarioMapper;
import br.com.joaogosmani.ediaristas.core.exceptions.SenhaIncorretaException;
import br.com.joaogosmani.ediaristas.core.exceptions.SenhasNaoConferemException;
import br.com.joaogosmani.ediaristas.core.models.Usuario;
import br.com.joaogosmani.ediaristas.core.publishers.NovoUsuarioPublisher;
import br.com.joaogosmani.ediaristas.core.repositories.UsuarioRepository;
import br.com.joaogosmani.ediaristas.core.services.storage.adapters.StorageService;
import br.com.joaogosmani.ediaristas.core.services.token.adapters.TokenService;

import br.com.joaogosmani.ediaristas.core.utils.SecurityUtils;
import br.com.joaogosmani.ediaristas.core.validators.UsuarioValidator;

@Service
public class ApiUsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ApiUsuarioMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioValidator validator;

    @Autowired
    private StorageService storageService;

    @Autowired
    private NovoUsuarioPublisher novoUsuarioPublisher;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SecurityUtils securityUtils;

    public UsuarioResponse cadastrar(UsuarioRequest request) {
        validarConfirmacaoSenha(request);

        var usuarioParaCadastrar = mapper.toModel(request);

        validator.validar(usuarioParaCadastrar);

        var senhaEncriptada = passwordEncoder.encode(usuarioParaCadastrar.getSenha());
        usuarioParaCadastrar.setSenha(senhaEncriptada);

        var fotoDocumento = storageService.salvar(request.getFotoDocumento());
        usuarioParaCadastrar.setFotoDocumento(fotoDocumento);

        if (usuarioParaCadastrar.isDiarista()) {
            var reputacaoMedia = calcularMediaReputacaoDiaristas();
            usuarioParaCadastrar.setReputacao(reputacaoMedia);
        }

        var usuarioCadastrado = repository.save(usuarioParaCadastrar);
        novoUsuarioPublisher.publish(usuarioCadastrado);

        var response = mapper.toCadastroResponse(usuarioCadastrado);
        var tokenResponse = gerarTokenResponse(response);
        response.setToken(tokenResponse);
        return response;
    }

    public MensagemResponse atualizarFotoUsuario(MultipartFile fotoUsuario) {
        var usuarioLogado = securityUtils.getUsuarioLogado();
        var foto = storageService.salvar(fotoUsuario);
        usuarioLogado.setFotoUsuario(foto);
        repository.save(usuarioLogado);
        return new MensagemResponse("Foto salva com sucesso!");
    }
    
    public MensagemResponse atualizar(AtualizarUsuarioRequest request) {
        var usuarioLogado = securityUtils.getUsuarioLogado();

        atualizarInformacoesUsuarioLogado(request, usuarioLogado);

        validator.validar(usuarioLogado);

        alterarSenha(request, usuarioLogado);

        repository.save(usuarioLogado);

        return new MensagemResponse("Usuário atualizado com sucesso!");
    }

    private void alterarSenha(AtualizarUsuarioRequest request, Usuario usuarioLogado) {
        var hasSenhas = request.getPassword() != null
            && request.getNewPassword() != null
            && request.getPasswordConfirmation() != null;

        if (hasSenhas) {
            verificarSenha(request, usuarioLogado);
            validarConfirmacaoSenha(request);
            var novaSenha = request.getNewPassword();
            var novaSenhaHash = passwordEncoder.encode(novaSenha);
            usuarioLogado.setSenha(novaSenhaHash);
        }
    }

    private void verificarSenha(AtualizarUsuarioRequest request, Usuario usuarioLogado) {
        var senhaRequest = request.getPassword();
        var senhaDB = usuarioLogado.getSenha();

        if (!passwordEncoder.matches(senhaRequest, senhaDB)) {
            var mensagem = "A senha informada está incorreta";
            var fieldError = new FieldError(request.getClass().getName(), "password", senhaRequest, false, null, null, mensagem);
            throw new SenhaIncorretaException(mensagem, fieldError);
        }
    }

    private void atualizarInformacoesUsuarioLogado(AtualizarUsuarioRequest request, Usuario usuarioLogado) {
        usuarioLogado.setNomeCompleto(
            request.getNomeCompleto() != null ? request.getNomeCompleto() : usuarioLogado.getNomeCompleto()
        );
        usuarioLogado.setEmail(
            request.getEmail() != null ? request.getEmail() : usuarioLogado.getEmail()
        );
        usuarioLogado.setCpf(
            request.getCpf() != null ? request.getCpf() : usuarioLogado.getCpf()
        );
        usuarioLogado.setNascimento(
            request.getNascimento() != null ? request.getNascimento() : usuarioLogado.getNascimento()
        );
        usuarioLogado.setTelefone(
            request.getTelefone() != null ? request.getTelefone() : usuarioLogado.getTelefone()
        );
        usuarioLogado.setChavePix(
            request.getChavePix() != null ? request.getChavePix() : usuarioLogado.getChavePix()
        );
    }

    private TokenResponse gerarTokenResponse(UsuarioCadastroResponse response) {
        var token = tokenService.gerarAccessToken(response.getEmail());
        var refresh = tokenService.gerarRefreshToken(response.getEmail());
        var tokenResponse = new TokenResponse(token, refresh);
        return tokenResponse;
    }

    private Double calcularMediaReputacaoDiaristas() {
        var reputacaoMedia = repository.getMediaReputacaoDiarista();

        if (reputacaoMedia == null || reputacaoMedia == 0.0) {
            reputacaoMedia = 5.0;
        }
        return reputacaoMedia;
    }

    private void validarConfirmacaoSenha(UsuarioRequest request) {
        var senha = request.getPassword();
        var confirmacaoSenha = request.getPasswordConfirmation();

        if (!senha.equals(confirmacaoSenha)) {
            var mensagem = "Os dois campos de senha não conferem";
            var fieldError = new FieldError(request.getClass().getName(), "passwordConfirmation", request.getPasswordConfirmation(), false, null, null, mensagem);
        
            throw new SenhasNaoConferemException(mensagem, fieldError);
        }
    }

    private void validarConfirmacaoSenha(AtualizarUsuarioRequest request) {
        var senha = request.getNewPassword();
        var confirmacaoSenha = request.getPasswordConfirmation();

        if (!senha.equals(confirmacaoSenha)) {
            var mensagem = "Os dois campos de senha não conferem";
            var fieldError = new FieldError(request.getClass().getName(), "passwordConfirmation", request.getPasswordConfirmation(), false, null, null, mensagem);
        
            throw new SenhasNaoConferemException(mensagem, fieldError);
        }
    }

}
