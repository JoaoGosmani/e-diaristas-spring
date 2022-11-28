package br.com.joaogosmani.ediaristas.core.specifications;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.criteria.JoinType;

import org.springframework.data.jpa.domain.Specification;

import br.com.joaogosmani.ediaristas.core.enums.DiariaStatus;
import br.com.joaogosmani.ediaristas.core.models.Diaria;

public class DiariaSpecifications {

    public static Specification<Diaria> comNumeroCandidatosIgualA(int numeroCandidatos) {
        return (root, query, criteriaBuilder) -> {
            var quantidadeCandidatos = criteriaBuilder.size(root.get("candidatos"));
            return criteriaBuilder.equal(quantidadeCandidatos, numeroCandidatos);
        };
    }

    public static Specification<Diaria> comNumeroCandidatosMenorQue(int numeroCandidatos) {
        return (root, query, criteriaBuilder) -> {
            var quantidadeCandidatos = criteriaBuilder.size(root.get("candidatos"));
            return criteriaBuilder.lessThan(quantidadeCandidatos, numeroCandidatos);
        };
    }

    public static Specification<Diaria> comNumeroCandidatosMaiorOuIgualA(int numeroCandidatos) {
        return (root, query, criteriaBuilder) -> {
            var quantidadeCandidatos = criteriaBuilder.size(root.get("candidatos"));
            return criteriaBuilder.greaterThanOrEqualTo(quantidadeCandidatos, numeroCandidatos);
        };
    }

    public static Specification<Diaria> isPago() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("status"), DiariaStatus.PAGO);
        };
    }

    public static Specification<Diaria> isSemPagamento() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("status"), DiariaStatus.SEM_PAGAMENTO);
        };
    }

    public static Specification<Diaria> semDiarista() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isNull(root.get("diarista"));
        };
    }

    public static Specification<Diaria> comMaisDe24HorasDesdeCriacao() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(
                root.get("createdAt"),
                LocalDateTime.now().minusHours(24)
            );
        };
    }

    public static Specification<Diaria> semCandidatos() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.isEmpty(root.get("candidatos"));
        };
    }

    public static Specification<Diaria> comMenos24HorasParaAtendimento() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.lessThan(
                root.get("dataAtendimento"), 
                LocalDateTime.now().plusHours(24)
            );
        };
    }

    public static Specification<Diaria> clienteNomeCompletoContem(String valorBusca) {
        return (root, query, criteriaBuilder) -> {
            if (valorBusca == null || valorBusca.isEmpty()) {
                return criteriaBuilder.and();
            }
            var cliente = root.join("cliente", JoinType.LEFT);
            var nomeCompletoMinusculo = criteriaBuilder.lower(cliente.get("nomeCompleto"));
            return criteriaBuilder.like(nomeCompletoMinusculo, "%" + valorBusca.toLowerCase() + "%");
        };
    }

    public static Specification<Diaria> statusIn(List<DiariaStatus> status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null || status.isEmpty()) {
                return criteriaBuilder.and();
            }
            return root.get("status").in(status);
        };
    }

}